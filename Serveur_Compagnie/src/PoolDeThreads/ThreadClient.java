package PoolDeThreads;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseCINAP.*;
import RequeteReponseEBOOP.*;
import RequeteReponseMultiThread.Requete;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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
    private LibrairieJDBC LibJDBC;
    private Properties propServeur;
    private ThreadServeur ts;
    
    public ThreadClient(ClientsList ListeClients, FenServer fs, FichierLog fichierLog)
    {
        ListClients = ListeClients;
        fenetreApplication = fs;
        this.fichierLog = fichierLog;
        this.fichierLog.ecritLigne("Constructeur ThreadClient");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
    }  
    public ThreadClient(ClientsList ListeClients, FenServer fs, FichierLog fichierLog,int Detection)
    {
        ListClients = ListeClients;
        fenetreApplication = fs;
        this.fichierLog = fichierLog;
        this.fichierLog.ecritLigne("Constructeur ThreadClient");
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
                    ois = new ObjectInputStream(CliSock.getInputStream());
                    oos = new ObjectOutputStream(CliSock.getOutputStream());

                    while (!CliSock.isClosed())
                    {
                        RequeteCINAP requeteC = null;
                        RequeteEBOOP reqE = null;
                        
                        Requete reqGen = (Requete)ois.readObject();
                        Class<?> c = reqGen.getClass();
                        System.out.println("Nom Class récupérée : " + c.getName());
                        if(c.getName() == "RequeteReponseCINAP.RequeteCINAP") 
                        {
                            System.out.println("Dans le lancement de requeteCINAP");
                            requeteC = (RequeteCINAP)reqGen;
                            int RequeteMessage = requeteC.getCode();
                            if (RequeteMessage == 0)
                            MessageRequete = "Ajout d'un nouvel agent";                        
                            if (RequeteMessage == 1)
                                MessageRequete = "Demande de vérification de réservation";
                            if (RequeteMessage == 2)
                                MessageRequete = "Demande d'achat de tickets";
                            if (RequeteMessage == 3)
                                MessageRequete = "Client déconnecté";
                            if (RequeteMessage == 4)
                                MessageRequete = "Demande de connexion";
                            if (RequeteMessage == 6)
                                MessageRequete = "Demande de validation de check-in";
                            if (RequeteMessage == 7)
                                MessageRequete = "Demande de connexion via le WEB";
                            if (RequeteMessage != 5)
                            {
                                Date date2 = new Date();
                                DefaultTableModel tablemessagerecus2 = (DefaultTableModel)fenetreApplication.TableMessageRecus.getModel();
                                String d2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(date2);
                                tablemessagerecus2.addRow(new Object[]{d2,CliSock.getInetAddress(), MessageRequete});                            
                            }

                            travail = requeteC.createRunnable(CliSock, ListClients, this.fichierLog, fenetreApplication, ois, oos);
                        } 
                        if(c.getName() == "RequeteReponseEBOOP.RequeteEBOOP")
                        {
                            System.out.println("Dans le lancement de requeteEBOOP");
                            reqE = (RequeteEBOOP)reqGen;
                            travail = reqE.createRunnable(CliSock,ois, oos);

                        }
                        if (travail != null)
                            travail.run(); 
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
                    System.out.println("-- FIN THREAD COMPAGNIE --");
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
    
    public void stopClient (int time)
    {
        System.out.println("Je stop mon client");

            try 
            {
                System.out.println("Je tente de me connecter en urgence");
                System.out.println("inetadresse : " + CliSock.getInetAddress());
                System.out.println("port : " + Integer.valueOf(propServeur.getProperty("portEcouteUrgent")));
                Socket socket = new Socket(this.CliSock.getInetAddress(), Integer.valueOf(propServeur.getProperty("portEcouteUrgent")));
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(time);
                System.out.println("Je viens d'envoyer la réponse et je dors");
                this.sleep(time*1000);
                this.CliSock.close();
                this.oos.close();
                this.ois.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) { }
    }
    
    public Socket getClientSocket() {
        return this.CliSock;
    }
}