package Serveur;

import java.net.Socket;

public interface SourcesTaches {
    public Socket getClient() throws InterruptedException;
    public void recordClient(Socket client);
    public boolean existClient();
}
