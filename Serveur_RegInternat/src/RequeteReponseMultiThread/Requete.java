package RequeteReponseMultiThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import serveur_compagnie.ClientsList;

public interface Requete {
    public Runnable createRunnable(Socket SocketClient, ObjectInputStream ois, ObjectOutputStream oos);
    public Runnable createRunnable(Socket SocketClient, ClientsList list, ObjectInputStream ois, ObjectOutputStream oos);
    public Runnable createRunnable(SSLSocket SocketClient, ClientsList ListClients, ObjectInputStream ois, ObjectOutputStream oos);
}
