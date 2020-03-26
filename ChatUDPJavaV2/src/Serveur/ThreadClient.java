package Serveur;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseFECOP.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Serveur.ClientsList;
import Serveur.Serveur_Information;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Properties;

public class ThreadClient extends Thread
{
    private Socket CliSock;
    public ClientsList ListClients;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    DataInputStream dis;
    DataOutputStream dos;
    public Serveur_Information fenetreApplication;
    public FichierLog fichierLog;
    private String MessageRequete;
    
    public ThreadClient(ClientsList ListeClients, Serveur_Information fs, FichierLog fichierLog)
    {
        ListClients = ListeClients;
        fenetreApplication = fs;
    }
    
    public ThreadClient(ClientsList ListeClients, Serveur_Information fs)
    {
        ListClients = ListeClients;
        fenetreApplication = fs;
    }
    
    public void run()
    {
        while (!isInterrupted())
        {
            CliSock = null;
            try
            {
                CliSock = this.getClients().getClient(); //Récuperer la linkedlist de la variable local 
                System.out.println("Je recup la socket du client "+ CliSock + " et je suis le thread "+ this.getId());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (CliSock != null)
            {
                
                    RequeteFECOP requete = null;
                    try 
                    {
                        ois = new ObjectInputStream(CliSock.getInputStream());
                        oos = new ObjectOutputStream(CliSock.getOutputStream());
                        
                        dis = new DataInputStream(CliSock.getInputStream());
                        dos = new DataOutputStream(CliSock.getOutputStream());
                    } catch (IOException ex) 
                    {
                        Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                    
                    while (!CliSock.isClosed())
                    {
                        try
                        {
                            requete = (RequeteFECOP)ois.readObject();
                            int RequeteMessage = requete.getCode();
                            
                            Runnable travail = requete.createRunnable(CliSock, ListClients,fenetreApplication,ois,oos,dis,dos);
                            if (travail != null)
                                travail.run(); 
                        }
                        catch (IOException | ClassNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                    }
            }

        }
        System.out.println("Fin du thread client : " + this.getId());
    }

    public ClientsList getClients() {
        return ListClients;
    }
}