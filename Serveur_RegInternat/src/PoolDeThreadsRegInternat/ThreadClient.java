package PoolDeThreadsRegInternat;

import Outils.FichierLog;
import RequeteVERIFID.RequeteVERIFID;
import RequeteReponseCINAP.*;
import RequeteReponseEBOOP.*;
import RequeteReponseMultiThread.Requete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur_compagnie.ClientsList;
import serveur_compagnie.FenServer;


public class ThreadClient extends Thread implements Serializable
{
    private Socket CliSock;
    public ClientsList ListClients;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public FenServer fenetreApplication;
    public FichierLog fichierLog;
    private String MessageRequete;
    private Runnable travail;
    //public static int Detection; //0 = CINAP , 1 = EBOOP
    
    public ThreadClient(ClientsList ListeClients, FichierLog fichierLog)
    {
        ListClients = ListeClients;
        this.fichierLog = fichierLog;
        this.fichierLog.ecritLigne("Constructeur ThreadClient");
    }  
    public ThreadClient(ClientsList ListeClients, FichierLog fichierLog,int Detection)
    {
        ListClients = ListeClients;
        this.fichierLog = fichierLog;
        this.fichierLog.ecritLigne("Constructeur ThreadClient");

    }  
    
    public void run()
    {
        while (!isInterrupted())
        {
            CliSock = null;
            try
            {
                CliSock = this.getClients().getClient(); //Récuperer la linkedlist de la variable local 
                System.out.println("Je recup la Socket du Client "+ CliSock + " et je suis le Thread "+ this.getId());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (CliSock != null)
            {
                try 
                {
                    System.out.println("Création des flux de l'international");
                    ois = new ObjectInputStream(CliSock.getInputStream());
                    oos = new ObjectOutputStream(CliSock.getOutputStream());

                    while (!CliSock.isClosed())
                    {
                        RequeteVERIFID requeteC = null;
                        
                        requeteC = (RequeteVERIFID)ois.readObject();

                        travail = requeteC.createRunnable(CliSock, ListClients, ois, oos);

                        System.out.println("Je lance un runnable");
                        if (travail != null)
                            travail.run(); 
                    }
                } catch (IOException ex) {
                    try 
                    {
                        this.CliSock.close(); this.oos.close(); this.ois.close();
                    } 
                    catch (IOException ex1) 
                    {
                        Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    System.out.println("-- FIN THREAD REGINTERNAT --");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        System.out.println("Fin du thread client : " + this.getId());
    }

    public ClientsList getClients() {
        return ListClients;
    }
}