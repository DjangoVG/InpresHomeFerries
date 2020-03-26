/*
* ThreadServeur.java
*/
package PoolDeThreadsFrontieres;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import java.net.*;
import java.io.*;
import java.util.*;
import serveur_compagnie.ClientsList;
import serveur_compagnie.ConsoleServeur;

public class ThreadServeur extends Thread implements ConsoleServeur
{
    private ServerSocket SSocket = null;
    public static FichierLog fichierLog;
    private Properties propServeur;
    private Properties propServeurInternational;
    public ClientsList ListClients;
    private ArrayList<ThreadClient> ListClientsThreads;
    private LibrairieJDBC LibJDBC;
    private PoolDeThreadsRegInternat.ThreadServeur pdtrits;
    private PoolDeThreadsRegInternat.ThreadServeurSSL pdtritsSSL;
    public ThreadServeur()
    {
        ListClients = new ClientsList();
        fichierLog = new FichierLog("FichierLogFrontieres.txt");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesFrontieresNationale();
        propServeurInternational = LibJDBC.RecupPropertiesFrontieresInternationale();
    }

    public void run()
    {
        try
        {
            SSocket = new ServerSocket(Integer.valueOf((String) propServeur.getProperty("portEcoute"))); //Instancie la socket d'écoute du serveur
        }
        catch (IOException e)
        {
            fichierLog.ecritLigne ("Erreur de port d'écoute ! ? [" + e + "]"); System.exit(1);
        }
        for (int i=0; i<Integer.valueOf((String) propServeur.getProperty("ClientsMaximum")); i++)
        {
            ThreadClient thr = new ThreadClient (ListClients, fichierLog,0); //Instancie les threads clients
            thr.start(); //Lance les threads client créés
            System.out.println("Demarrage Thread Frontières numéro : " + thr.getId()); fichierLog.ecritLigne ("Demarrage Thread numéro : " + thr.getId());
        }
        if(propServeurInternational.getProperty("Securite").equals("true"))
        {
             pdtritsSSL = new PoolDeThreadsRegInternat.ThreadServeurSSL();
             pdtritsSSL.start();
        }
        else
        {
            pdtrits = new PoolDeThreadsRegInternat.ThreadServeur();
            pdtrits.start();
        }
        
        
        Socket CSocket = null;
        while (!isInterrupted())
        {
            try
            {
               
                System.out.println("************ Serveur en attente ************"); fichierLog.ecritLigne ("************ Serveur en attente ************");
                System.out.println("-------Avant le accept----------");
                CSocket = SSocket.accept();
                System.out.println("-------J'accepte un garde frontière----------"); fichierLog.ecritLigne ("Client "+CSocket.getLocalSocketAddress()+" connecté !");
                this.getClients().recordClient(CSocket); //Ajoute le client à la LinkedList
            }
            catch (InterruptedIOException ex)
            {
                this.interrupt();
                fichierLog.ecritLigne ("Erreur InterruptedIOException [" + ex + "]");
            } 
            catch (IOException ex) 
            {
                fichierLog.ecritLigne ("Erreur IOException ! ? [" + ex + "]");
            }
        }
        for (ThreadClient thr : ListClientsThreads)
        {
            thr.interrupt();
            System.out.println("J'interrompes les threads");
        }
    }

    @Override
    public void TraceEvenements(String commentaire) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ClientsList getClients() {
        return ListClients;
    }

    private  ArrayList<ThreadClient> getClientThreads() {
        return ListClientsThreads;
    }
}