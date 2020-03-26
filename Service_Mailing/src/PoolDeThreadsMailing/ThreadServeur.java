/*
* ThreadServeur.java
*/
package PoolDeThreadsMailing;
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
    private Properties propServeur;
    public ClientsList ListClients;
    private ArrayList<ThreadClient> ListClientsThreads;
    private LibrairieJDBC LibJDBC;
    
    public ThreadServeur()
    {
        ListClients = new ClientsList();
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
    }

    public void run()
    {
        try
        {
            SSocket = new ServerSocket(Integer.valueOf((String) propServeur.getProperty("portEcouteMailing"))); //Instancie la socket d'écoute du serveur
        }
        catch (IOException e)
        {
            System.out.println("Erreur : " + e); System.exit(1);
        }
        for (int i=0; i<Integer.valueOf((String) propServeur.getProperty("ClientsMaximumMailing")); i++)
        {
            ThreadClient thr = new ThreadClient (ListClients); //Instancie les threads clients
            thr.start(); //Lance les threads client créés
            System.out.println("Demarrage Thread Mailing numéro : " + thr.getId());
        }
        
        Socket CSocket = null;
        while (!isInterrupted())
        {
            try
            {
               
                System.out.println("************ Serveur en attente ************");
                System.out.println("-------Avant la réception d'un mail----------");
                CSocket = SSocket.accept();
                System.out.println("------- J'accepte un mail ----------");
                this.getClients().recordClient(CSocket); //Ajoute le client à la LinkedList
            }
            catch (InterruptedIOException ex)
            {
                this.interrupt();
                System.out.println("Erreur : " + ex);
            } 
            catch (IOException ex) 
            {
                System.out.println("Erreur : " + ex);
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