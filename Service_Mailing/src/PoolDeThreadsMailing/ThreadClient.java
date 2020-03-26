package PoolDeThreadsMailing;

import Outils.FichierLog;
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
    
    public ThreadClient(ClientsList ListeClients)
    {
        ListClients = ListeClients;
    }
    
    public void run()
    {
        while (!isInterrupted())
        {
            /* A REMPLIR */

        }
        System.out.println("Fin du thread client : " + this.getId());
    }
}