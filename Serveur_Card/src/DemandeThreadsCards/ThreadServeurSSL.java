/*
* ThreadServeur.java
*/
package DemandeThreadsCards;
import Outils.LibrairieJDBC;
import java.net.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class ThreadServeurSSL extends Thread
{
    private SSLServerSocket sSLServerSocket = null;
    private Properties propServeurCartes;
    private LibrairieJDBC LibJDBC;
    private DemandeThreadsCards.ThreadClientSSL tcc;
    
    public ThreadServeurSSL()
    {
        LibJDBC = new LibrairieJDBC();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
    }

    public void run()
    {
        System.out.println("Lancement threadServeur Card SSL");
        SSLSocket cliSock = null;
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
            SSLServerSocketFactory SslSFac= SslC.getServerSocketFactory();
            // 4. Socket
            sSLServerSocket = (SSLServerSocket) SslSFac.createServerSocket(50083);
            System.out.println("La socketSSL Serveur à bien été créée !");
        }
        catch (IOException e)
        {
            System.out.println("Erreur de port d'écoute ! ? [" + e + "]"); System.exit(1);
        } catch (KeyStoreException ex) {
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
        while (!isInterrupted())
        {
            try
            {
                cliSock = (SSLSocket) sSLServerSocket.accept();
                tcc = new DemandeThreadsCards.ThreadClientSSL(cliSock);
                tcc.start(); //Lance 1 seul thread
                System.out.println("Demarrage Thread numéro : " + tcc.getId());
            }
            catch (InterruptedIOException ex)
            {
                this.interrupt();
                System.out.println("Erreur InterruptedIOException [" + ex + "]");
            } 
            catch (IOException ex) 
            {
                System.out.println("Erreur IOException ! ? [" + ex + "]");
            }
        }
    }
}

