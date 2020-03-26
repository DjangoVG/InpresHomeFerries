/*
* ThreadServeur.java
*/
package DemandeThreadsCards;
import Outils.LibrairieJDBC;
import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadServeur extends Thread
{
    private ServerSocket SSocket = null;
    private Properties propServeurCartes;
    private LibrairieJDBC LibJDBC;
    private DemandeThreadsCards.ThreadClient tcc;
    private Socket cliSock = null;
    
    public ThreadServeur()
    {
        LibJDBC = new LibrairieJDBC();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
    }

    public void run()
    {
        try
        {
            SSocket = new ServerSocket(Integer.valueOf((String) propServeurCartes.getProperty("portEcoute"))); //Instancie la socket d'écoute du serveur
        }
        catch (IOException e)
        {
            System.out.println("Erreur de port d'écoute ! ? [" + e + "]"); System.exit(1);
        }
        while (!isInterrupted())
        {
            try
            {
                cliSock = SSocket.accept();
                tcc = new DemandeThreadsCards.ThreadClient(cliSock);
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