package RequeteReponseMultiThread;

import Outils.FichierLog;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import serveur_compagnie.ClientsList;
import serveur_compagnie.FenServer;

public interface Requete {
    public Runnable createRunnable(Socket SocketClient, ObjectInputStream ois, ObjectOutputStream oos);
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, FichierLog fichierLog, FenServer fenetreServer, ObjectInputStream ois, ObjectOutputStream oos) ;
}
