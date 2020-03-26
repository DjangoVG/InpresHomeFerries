package RequeteReponseMultiThread;

import Outils.FichierLog;
import java.net.Socket;
import Serveur.ClientsList;
import Serveur.Serveur_Information;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Requete {

    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, Serveur_Information fenetreServer, ObjectInputStream ois, ObjectOutputStream oos,DataInputStream dis, DataOutputStream dos);
}
