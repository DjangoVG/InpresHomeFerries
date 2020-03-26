package serveur_compagnie;

import java.net.Socket;
import javax.net.ssl.SSLSocket;

public interface SourcesTaches {
    public Socket getClient() throws InterruptedException;
    public void recordClient(Socket client);
    public boolean existClient();
    
    public Socket getClientSSL() throws InterruptedException;
    public void recordClientSSL(SSLSocket client);
    public boolean existClientSSL();
}
