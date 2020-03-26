package PoolDeThreads;

import Outils.LibrairieJDBC;
import RequeteReponseHAFICSA.RequeteHAFICSA;
import RequeteReponseMultiThread.Requete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur_compagnie.ClientsList;
import serveur_compagnie.FenServer;


public class ThreadClientAdmin extends Thread implements Serializable
{
    private Socket CliSock;
    public ClientsList ListClients;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String MessageRequete;
    private Runnable travail;
    //public static int Detection; //0 = CINAP , 1 = EBOOP
    private FenServer FenetreServeur;
    private ThreadServeur ts;
    private final LibrairieJDBC LibJDBC;
    private final Properties propServeur;
    
    public ThreadClientAdmin(ClientsList ListeClients, FenServer fenetreServeur, ThreadServeur ts)
    {
        ListClients = ListeClients;
        this.FenetreServeur = fenetreServeur;
        this.ts = ts;
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
    } 
    
    public void run()
    {
        while (!isInterrupted())
        {
            CliSock = null;
            try
            {
                CliSock = this.getClients().getClient(); //Récuperer la linkedlist de la variable local 
                System.out.println("Je recup la Socket de l'admin "+ CliSock + " et je suis le Thread "+ this.getId());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (CliSock != null)
            {
                try 
                {
                    ois = new ObjectInputStream(CliSock.getInputStream());
                    oos = new ObjectOutputStream(CliSock.getOutputStream());

                    while (!CliSock.isClosed())
                    {
                        RequeteHAFICSA requeteC = null;
                        
                        Requete reqGen = (Requete)ois.readObject();
                        Class<?> c = reqGen.getClass();
                        System.out.println("Nom Class récupérée : " + c.getName());
                        
                        System.out.println("Dans le lancement de RequeteHAFICSA");
                        requeteC = (RequeteHAFICSA)reqGen;
                        int RequeteMessage = requeteC.getCode();
                        if (RequeteMessage == RequeteHAFICSA.REQUETE_SLEEP)
                        {
                            System.out.println("Je mets le serveur en pause");
                            ts.wait();
                        }
                        else
                        {
                            if (RequeteMessage == RequeteHAFICSA.REQUETE_STOP)
                            {
                                System.out.println("Je me prépare à dormir et je me connecte à tous les clients pour les kicks");
                                System.out.println("Nombre de serveurs : " + ts.ListClientsThreads.size());
                                for (int i=0; i < ts.ListClientsThreads.size(); i++)
                                {
                                    System.out.println("Appel de stop client");
                                    ts.ListClientsThreads.get(i).stopClient(requeteC.getSleep());
                                }
                                sleep(requeteC.getSleep() * 1000);
                                Socket CSocket2 = new Socket (propServeur.getProperty("adresse"), Integer.valueOf(propServeur.getProperty("portEcouteUrgent")));
                                System.out.println("Je stop le serveur");
                                ts.interrupt();                                    
                            }
                            else
                            {
                                travail = requeteC.createRunnable(CliSock, ListClients, ois, oos, FenetreServeur);
                                if (travail != null)
                                    travail.run();
                            }
                        }
                    }
                } catch (IOException ex) 
                {
                    try 
                    {
                        this.CliSock.close(); this.oos.close(); this.ois.close();
                    } 
                    catch (IOException ex1) 
                    {
                        Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    System.out.println("-- FIN THREAD ADMINISTRATEUR --");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadClientAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        System.out.println("Fin du thread client : " + this.getId());
    }

    public ClientsList getClients() {
        return ListClients;
    }
}
