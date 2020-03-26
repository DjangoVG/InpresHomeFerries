package RequeteReponseMultiThread;

import Outils.FichierLog;
import java.net.Socket;
import Serveur.ClientsList;
import Serveur.Serveur_DataMining;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Requete {

    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, FichierLog fichierLog, Serveur_DataMining fenetreServer,ObjectInputStream ois, ObjectOutputStream oos);
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients,Serveur_DataMining fenetreServer,ObjectInputStream ois, ObjectOutputStream oos);
    
}
