/*
* ThreadServeur.java
*/
package DemandeThreadsEmbarquements;
import Outils.LibrairieJDBC;
import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadServeur extends Thread
{
    private ServerSocket SSocket = null;
    private Properties propServeurEmbarquements;
    private LibrairieJDBC LibJDBC;
    private DemandeThreadsEmbarquements.ThreadClient tde;
    private Socket cliSock = null;
    
    public ThreadServeur()
    {
        LibJDBC = new LibrairieJDBC();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }

    public void run()
    {
        System.out.println("Lancement threadEmbarquement");
        try
        {
            SSocket = new ServerSocket(Integer.valueOf((String) propServeurEmbarquements.getProperty("portEcoute"))); //Instancie la socket d'écoute du serveur
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
                tde = new DemandeThreadsEmbarquements.ThreadClient(cliSock);
                tde.start(); //Lance 1 seul thread
                System.out.println("Demarrage Thread numéro : " + tde.getId());
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