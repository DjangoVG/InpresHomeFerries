package RequeteReponseMultiThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public interface Requete {
    public Runnable createRunnable(Socket SocketClient, ObjectInputStream ois, ObjectOutputStream oos);
}
