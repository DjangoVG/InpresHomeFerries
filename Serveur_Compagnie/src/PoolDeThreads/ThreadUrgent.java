package PoolDeThreads;

import AppliClientCompagnie.FenClient;
import Outils.LibrairieJDBC;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ThreadUrgent extends Thread implements Serializable
{
    private Socket CliSock;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public FenClient fenetreApplication;
    private ServerSocket SocketUrgent;
    private Properties propServeur;
    private LibrairieJDBC LibJDBC;
    
    public ThreadUrgent(FenClient myFen)
    {
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        fenetreApplication = myFen;
    } 
    
    public void run()
    {
        try {
            System.out.println("Je crée ma socket");
            SocketUrgent = new ServerSocket(Integer.valueOf(propServeur.getProperty("portEcouteUrgent")));
            System.out.println("J'attends sur un accept");
            Socket CSocket = SocketUrgent.accept();
            System.out.println("thread urgent connecté");
            ois = new ObjectInputStream(CSocket.getInputStream());
            System.out.println("1");
            int NombreSecondes = (int)ois.readObject();
            System.out.println("J'ai accepté un client URGENT");

            String msg;
            msg = "Le serveur va s'éteindre dans " + NombreSecondes + " secondes !";
            JOptionPane.showMessageDialog(fenetreApplication, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
            sleep(NombreSecondes * 1000);
            fenetreApplication.ois.close();
            fenetreApplication.oos.close();
            fenetreApplication.cliSock.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadUrgent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadUrgent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadUrgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}