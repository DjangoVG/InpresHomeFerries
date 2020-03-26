/*
* ThreadServeur.java
*/
package PoolDeThreadsRegInternat;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.net.ssl.SSLServerSocket;
import serveur_compagnie.ClientsList;
import serveur_compagnie.ConsoleServeur;

public class ThreadServeur extends Thread implements ConsoleServeur
{
    private ServerSocket SSocket = null;
    public static FichierLog fichierLog;
    private Properties propServeur;
    public ClientsList ListClients;
    private ArrayList<ThreadClient> ListClientsThreads;
    private LibrairieJDBC LibJDBC;
    
    public ThreadServeur()
    {
        ListClients = new ClientsList();
        fichierLog = new FichierLog("FichierLogRegInternat.txt");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesFrontieresInternationale();
    }

    public void run()
    {
        try
        {
            SSocket = new SSLServerSocket(Integer.valueOf((String) propServeur.getProperty("portEcoute"))) {
                @Override
                public String[] getEnabledCipherSuites() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void setEnabledCipherSuites(String[] strings) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public String[] getSupportedCipherSuites() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public String[] getSupportedProtocols() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public String[] getEnabledProtocols() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void setEnabledProtocols(String[] strings) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void setNeedClientAuth(boolean bln) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean getNeedClientAuth() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void setWantClientAuth(boolean bln) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean getWantClientAuth() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void setUseClientMode(boolean bln) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean getUseClientMode() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void setEnableSessionCreation(boolean bln) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public boolean getEnableSessionCreation() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }; //Instancie la socket d'écoute du serveur
        }
        catch (IOException e)
        {
            fichierLog.ecritLigne ("Erreur de port d'écoute ! ? [" + e + "]"); System.exit(1);
        }
        for (int i=0; i<Integer.valueOf((String) propServeur.getProperty("ClientsMaximum")); i++)
        {
            ThreadClient thr = new ThreadClient (ListClients, fichierLog,0); //Instancie les threads clients
            thr.start(); //Lance les threads client créés
            System.out.println("Demarrage Thread Internationale numéro : " + thr.getId()); fichierLog.ecritLigne ("Demarrage Thread numéro : " + thr.getId());
        }
        
        Socket CSocket = null;
        while (!isInterrupted())
        {
            try
            {
               
                System.out.println("************ Serveur en attente ************"); fichierLog.ecritLigne ("************ Serveur en attente ************");
                System.out.println("-------Avant le accept----------");
                CSocket = SSocket.accept();
                System.out.println("-------J'accepte un client Frontiere----------"); fichierLog.ecritLigne ("Client "+CSocket.getLocalSocketAddress()+" connecté !");
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