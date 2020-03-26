/*
* ThreadServeur.java
*/
package PoolDeThreads;
import DemandeThreadsCards.ThreadServeurSSL;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import java.net.*;
import java.io.*;
import java.util.*;
import serveur_compagnie.ClientsList;
import serveur_compagnie.ConsoleServeur;
import serveur_compagnie.FenServer;
import serveur_compagnie.SourcesTaches;

public class ThreadServeur extends Thread implements ConsoleServeur
{
    private final SourcesTaches tachesAExecuter;
    private final FenServer fenetreApplication;
    public ServerSocket SSocket = null;
    public static FichierLog fichierLog;
    private Properties propServeur;
    public ClientsList ListClients;
    public ArrayList<ThreadClient> ListClientsThreads;
    private LibrairieJDBC LibJDBC;
    private DemandeThreadsCards.ThreadServeur tsc;
    private DemandeThreadsEmbarquements.ThreadServeur tse;
    private PoolDeThreadsFrontieres.ThreadServeur tsf;
    private PoolDeThreadsMailing.ThreadServeur tsm;
    private PoolDeThreads.ThreadServeurAdmin tsa;
    private Properties propServeurCard;
    private ThreadServeurSSL tscSSL;
    
    public ThreadServeur(SourcesTaches st, FenServer fs)
    {
        ListClients = new ClientsList();
        tachesAExecuter = st; fenetreApplication = fs;
        fichierLog = new FichierLog("FichierLog.txt");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        ListClientsThreads = new ArrayList<>();
        propServeurCard = LibJDBC.RecupPropertiesCard();
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
            ThreadClient thr = new ThreadClient (ListClients, fenetreApplication, fichierLog,0); //Instancie les threads clients
            this.ListClientsThreads.add(thr);
            thr.start(); //Lance les threads client créés
            System.out.println("Demarrage Thread Compagnie numéro : " + thr.getId()); fichierLog.ecritLigne ("Demarrage Thread numéro : " + thr.getId());
        }
        tsa = new PoolDeThreads.ThreadServeurAdmin(new ClientsList(), this.fenetreApplication, this);
        tsa.start();
        /*tsc = new DemandeThreadsCards.ThreadServeur();
        tsc.start();*/
        tse = new DemandeThreadsEmbarquements.ThreadServeur();
        tse.start();
        tsf = new PoolDeThreadsFrontieres.ThreadServeur();
        tsf.start();
        tsm = new PoolDeThreadsMailing.ThreadServeur();
        tsm.start();
        
        if(propServeurCard.getProperty("Securite").equals("true"))
        {
            tscSSL = new DemandeThreadsCards.ThreadServeurSSL();
            tscSSL.start();
        }
        else
        {
            tsc = new DemandeThreadsCards.ThreadServeur();
            tsc.start();
        }
        
        // Mise en attente du serveur
        Socket CSocket = null;
        while (!isInterrupted())
        {
            try
            {
               
                System.out.println("************ Serveur en attente ************"); fichierLog.ecritLigne ("************ Serveur en attente ************");
                System.out.println("-------Avant le accept----------");
                CSocket = SSocket.accept();
                System.out.println("-------Après le accept----------"); fichierLog.ecritLigne ("Client "+CSocket.getLocalSocketAddress()+" connecté !");
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
        System.out.println("*** SERVEUR DOWN ***");
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