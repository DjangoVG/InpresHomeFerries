package PoolDeThreadsRegInternat;

import Outils.FichierLog;
import RequeteVERIFID.*;
import RequeteReponseCINAP.*;
import RequeteReponseEBOOP.*;
import RequeteReponseMultiThread.Requete;
import RequeteVERIFIDS.RequeteVERIFIDS;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import serveur_compagnie.ClientsList;
import serveur_compagnie.FenServer;


public class ThreadClientSSL extends Thread implements Serializable
{
    private SSLSocket SslSocket = null;
    public ClientsList ListClients;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public FenServer fenetreApplication;
    public FichierLog fichierLog;
    private String MessageRequete;
    private Runnable travail;
    //public static int Detection; //0 = CINAP , 1 = EBOOP
    
    public ThreadClientSSL(ClientsList ListeClients, FichierLog fichierLog)
    {
        ListClients = ListeClients;
        this.fichierLog = fichierLog;
        this.fichierLog.ecritLigne("Constructeur ThreadClient");
    }  
    public ThreadClientSSL(ClientsList ListeClients, FichierLog fichierLog,int Detection)
    {
        ListClients = ListeClients;
        this.fichierLog = fichierLog;
        this.fichierLog.ecritLigne("Constructeur ThreadClient");

    }  
    
    public void run()
    {
        System.out.println("+++++++++++------- Lancer les threads Clients en SSL -------+++++++++++");
        while (!isInterrupted())
        {
            try
            {
                //1. KeyStore
                //MyKeyStore = new KeyStoreClass();
                KeyStore ServerKs = KeyStore.getInstance("JKS");
                String FICHIER_KEYSTORE = "CertificatFrontieres";
                char[] PASSWD_KEYSTORE = "1234".toCharArray();
                FileInputStream ServerFK = new FileInputStream (FICHIER_KEYSTORE);
                ServerKs.load(ServerFK, PASSWD_KEYSTORE);
                
                //2. Contexte
                SSLContext SslC = SSLContext.getInstance("SSLv3");
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                char[] PASSWD_KEY = "1234".toCharArray();
                kmf.init(ServerKs, PASSWD_KEY);

                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
                tmf.init(ServerKs);
                SslC.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
                // 3. Factory
                SSLSocketFactory SslSFac= SslC.getSocketFactory();
                // 4. Socket
                SslSocket = (SSLSocket) this.getClients().getClientSSL();
                System.out.println("+++++++++++------- Après la création de la socket client -------+++++++++++");
                //CliSock = this.getClients().getClient(); //Récuperer la linkedlist de la variable local 
                //System.out.println("Je recup la Socket du Client "+ CliSock + " et je suis le Thread "+ this.getId());
            } catch (KeyStoreException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CertificateException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnrecoverableKeyException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (SslSocket != null)
            {
                try 
                {
                    ois = new ObjectInputStream(SslSocket.getInputStream());
                    oos = new ObjectOutputStream(SslSocket.getOutputStream());

                    while (!SslSocket.isClosed())
                    {
                        RequeteVERIFIDS requeteC = null;
                        
                        Requete reqGen = (Requete)ois.readObject();
                        Class<?> c = reqGen.getClass();
                            requeteC = (RequeteVERIFIDS)reqGen;

                            travail = requeteC.createRunnable(SslSocket, ListClients,ois, oos);

                    
                        if (travail != null)
                            travail.run(); 
                    }
                } catch (IOException ex) {
                    try 
                    {
                        this.SslSocket.close(); this.oos.close(); this.ois.close();
                    } 
                    catch (IOException ex1) 
                    {
                        Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    System.out.println("-- FIN THREAD FRONTIERE --");
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