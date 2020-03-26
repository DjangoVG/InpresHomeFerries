/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import Outils.LibrairieJDBC;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class ThreadServeur extends Thread implements ConsoleServeur{
    private final SourcesTaches tachesAExecuter;
    private final Serveur_DataMining fenetreApplication;
    private ServerSocket SSocket = null;
    private Properties propServeur;
    public ClientsList ListClients;
    private ArrayList<ThreadClient> ListClientsThreads;
    private LibrairieJDBC LibJDBC;
    
    public ThreadServeur(SourcesTaches st, Serveur_DataMining fs)
    {
        ListClients = new ClientsList();
        tachesAExecuter = st; fenetreApplication = fs;
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
    }

    public void run()
    {
        try
        {
            SSocket = new ServerSocket(Integer.valueOf((String) propServeur.getProperty("portEcoute"))); //Instancie la socket d'écoute du serveur
        }
        catch (IOException e)
        {
             System.out.println("Erreur de port d'écoute ! ? [" + e + "]"); System.exit(1);
        }
        for (int i=0; i<Integer.valueOf((String) propServeur.getProperty("ClientsMaximum")); i++)
        {
            ThreadClient thr = new ThreadClient (ListClients, fenetreApplication); //Instancie les threads clients
            thr.start(); //Lance les threads client créés
            System.out.println("Demarrage Thread numéro : " + thr.getId());
        }
        // Mise en attente du serveur
        Socket CSocket = null;
        while (!isInterrupted())
        {
            try
            {
               
                System.out.println("************ Serveur en attente ************");
                System.out.println("-------Avant le accept----------");
                CSocket = SSocket.accept();
                System.out.println("-------Après le accept----------");
                this.getClients().recordClient(CSocket); //Ajoute le client à la LinkedList
            }
            catch (InterruptedIOException ex)
            {
                this.interrupt();
            } 
            catch (IOException ex) 
            {
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
