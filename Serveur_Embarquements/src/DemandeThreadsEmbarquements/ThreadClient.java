package DemandeThreadsEmbarquements;

import RequeteReponseHAFIC.RequeteHAFIC;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadClient extends Thread implements Serializable
{
    private Socket CliSock;
    private ServerSocket SocketServer;
    private ObjectInputStream oisEmbarquements;
    private ObjectOutputStream oosEmbarquements;
    
    public ThreadClient(Socket CliSock)
    {
        this.CliSock = CliSock;
    }
    public void run()
    {
        while (!isInterrupted())
        {
            try 
            {
                this.oosEmbarquements = new ObjectOutputStream(this.CliSock.getOutputStream());
                this.oisEmbarquements = new ObjectInputStream(this.CliSock.getInputStream());
                
                while (!this.CliSock.isClosed())
                {
                    RequeteHAFIC requete = null;
                    requete = (RequeteHAFIC)oisEmbarquements.readObject();
                    System.out.println("Je viens de lire la requete Android ! ");
                    Runnable travail = requete.createRunnable(this.CliSock, this.oisEmbarquements, this.oosEmbarquements);
                    if (travail != null)
                        travail.run();                     
                }

            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Fin du thread client embarquements : " + this.getId());
    }
}