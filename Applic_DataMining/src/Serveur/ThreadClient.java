package Serveur;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseANASTAT.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Serveur.ClientsList;
import Serveur.Serveur_DataMining;
import java.util.Properties;

public class ThreadClient extends Thread
{
    private Socket CliSock;
    public ClientsList ListClients;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public Serveur_DataMining fenetreApplication;
    public FichierLog fichierLog;
    private String MessageRequete;
    
    public ThreadClient(ClientsList ListeClients, Serveur_DataMining fs, FichierLog fichierLog)
    {
        ListClients = ListeClients;
        fenetreApplication = fs;
    }
    
    public ThreadClient(ClientsList ListeClients, Serveur_DataMining fs)
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
                
                    RequeteANASTAT requete = null;
                    try 
                    {
                        ois = new ObjectInputStream(CliSock.getInputStream());
                        oos = new ObjectOutputStream(CliSock.getOutputStream());
                    } catch (IOException ex) 
                    {
                        Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                    
                    while (!CliSock.isClosed())
                    {
                        try
                        {
                            requete = (RequeteANASTAT)ois.readObject();
                            int RequeteMessage = requete.getCode();


                            if (RequeteMessage == 0)
                                MessageRequete = "Demande de connexion";                        
                            if (RequeteMessage == 1)
                                MessageRequete = "Traitement requete A";
                            if (RequeteMessage == 2)
                                MessageRequete = "Traitement requete B";
                            if (RequeteMessage == 3)
                                MessageRequete = "Traitement requete C";
                            if (RequeteMessage == 4)
                                MessageRequete = "Traitement requete D";
                            if (RequeteMessage == 5)
                                MessageRequete = "Traitement requete E";
                            if (RequeteMessage == 6)
                                MessageRequete = "Traitement requete F";
                            if (RequeteMessage == 7)
                                MessageRequete = "Traitement requete G";
                            Runnable travail = requete.createRunnable(CliSock, ListClients,fenetreApplication,ois,oos);
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