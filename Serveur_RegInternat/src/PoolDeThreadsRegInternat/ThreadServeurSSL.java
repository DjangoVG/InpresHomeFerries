/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoolDeThreadsRegInternat;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import SecurityCrypto.KeyStoreClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import serveur_compagnie.ClientsList;
import serveur_compagnie.ConsoleServeur;

/**
 *
 * @author yanni
 */
public class ThreadServeurSSL  extends Thread implements ConsoleServeur
{
    private SSLServerSocket sSLServerSocket = null;
   
    
    public static FichierLog fichierLog;
    private Properties propServeur;
    public ClientsList ListClients;
    private ArrayList<ThreadClient> ListClientsThreads;
    private LibrairieJDBC LibJDBC;
    private PoolDeThreadsRegInternat.ThreadServeur pdtrits;
    private KeyStoreClass MyKeyStore;
    
    public ThreadServeurSSL()
    {
        ListClients = new ClientsList();
        fichierLog = new FichierLog("FichierLogFrontieres.txt");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesFrontieresNationale();
    }

    public void run()
    {
        
        try {
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
            SSLServerSocketFactory SslSFac= SslC.getServerSocketFactory();
            // 4. Socket
            sSLServerSocket = (SSLServerSocket) SslSFac.createServerSocket(50052);
            System.out.println("La socketSSL Serveur à bien été créée !");
            
            
            
            for (int i=0; i<Integer.valueOf((String) propServeur.getProperty("ClientsMaximum")); i++)
            {
                ThreadClientSSL thr = new ThreadClientSSL (ListClients, fichierLog,0); //Instancie les threads clients
                thr.start(); //Lance les threads client créés
                System.out.println("Demarrage Thread FrontièresSSL numéro : " + thr.getId()); fichierLog.ecritLigne ("Demarrage Thread numéro : " + thr.getId());
            }
            
            SSLSocket SslSocket = null;
            while (!isInterrupted())
            {
                try
                {
                    
                    System.out.println("************ Serveur en attente ************"); fichierLog.ecritLigne ("************ Serveur en attente ************"); 
                    System.out.println("-------Avant le accept Serveur SSL----------");
                    SslSocket = (SSLSocket)sSLServerSocket.accept();
                    System.out.println("-------J'accepte un garde frontière----------"); fichierLog.ecritLigne ("Client "+SslSocket.getLocalSocketAddress()+" connecté !");
                    this.getClients().recordClientSSL(SslSocket); //Ajoute le client à la LinkedList
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
        } catch (KeyStoreException ex) {
            Logger.getLogger(ThreadServeurSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadServeurSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThreadServeurSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ThreadServeurSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(ThreadServeurSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(ThreadServeurSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(ThreadServeurSSL.class.getName()).log(Level.SEVERE, null, ex);
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
