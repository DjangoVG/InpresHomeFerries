/*
* ThreadServeur.java
*/
package PoolDeThreads;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import java.net.*;
import java.io.*;
import java.util.*;
import serveur_compagnie.ClientsList;
import serveur_compagnie.ConsoleServeur;
import serveur_compagnie.FenServer;
import serveur_compagnie.SourcesTaches;

public class ThreadServeurAdmin extends Thread implements ConsoleServeur
{
    private final SourcesTaches tachesAExecuter;
    private final FenServer fenetreApplication;
    private ServerSocket SocketAdmin = null;
    private Properties propServeur;
    public ClientsList ListClients;
    private ArrayList<ThreadClient> ListClientsThreads;
    private LibrairieJDBC LibJDBC;
    private ThreadServeur ts;
    
    public ThreadServeurAdmin(SourcesTaches st, FenServer fs, ThreadServeur ts)
    {
        ListClients = new ClientsList();
        tachesAExecuter = st; fenetreApplication = fs;
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        this.ts = ts;
    }

    public void run()
    {
        try
        {
            SocketAdmin = new ServerSocket(Integer.valueOf((String) propServeur.getProperty("portEcouteAdmin"))); //Instancie la socket d'écoute du serveur
        }
        catch (IOException e)
        {
            System.exit(1);
        }
        for (int i=0; i<1; i++)
        {
            ThreadClientAdmin thr = new ThreadClientAdmin (ListClients, fenetreApplication, this.ts); //Instancie les threads clients
            thr.start(); //Lance les threads client créés
            System.out.println("Demarrage Thread Compagnie Admin numéro : " + thr.getId());
        }
        
        // Mise en attente du serveur
        Socket CSocket = null;
        while (!isInterrupted())
        {
            try
            {
               
                System.out.println("************ Serveur en attente ************");
                System.out.println("-------Avant le accept----------");
                CSocket = SocketAdmin.accept();
                this.getClients().recordClient(CSocket); //Ajoute le client à la LinkedList
            }
            catch (InterruptedIOException ex)
            {
                this.interrupt();
            } 
            catch (IOException ex) 
            {
                System.out.println("");
            }
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