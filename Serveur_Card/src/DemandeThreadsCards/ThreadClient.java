package DemandeThreadsCards;

import RequeteReponseCHECKCARD.RequeteCHECKCARD;
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
    private ObjectInputStream oisCartes;
    private ObjectOutputStream oosCartes;
    
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
                this.oosCartes = new ObjectOutputStream(this.CliSock.getOutputStream());
                this.oisCartes = new ObjectInputStream(this.CliSock.getInputStream());
                
                while (!this.CliSock.isClosed())
                {
                    RequeteCHECKCARD requete = null;
                    requete = (RequeteCHECKCARD)oisCartes.readObject();
                    Runnable travail = requete.createRunnable(this.CliSock, this.oisCartes, this.oosCartes);
                    if (travail != null)
                        travail.run();                     
                }

            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Fin du thread client : " + this.getId());
    }
}