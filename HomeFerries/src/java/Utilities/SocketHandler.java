package Utilities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler
{
    private static Socket socket;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static String MatriculeFerry;

    public static synchronized Socket getSocket(){
        return socket;
    }
    public static synchronized void setSocket(Socket socket){
        SocketHandler.socket = socket;
    }
    public static synchronized void setOis(ObjectInputStream ois){ SocketHandler.ois = ois; }
    public static synchronized ObjectInputStream getOis(){ return ois; }
    public static synchronized ObjectOutputStream getOos(){ return oos; }
    public static synchronized void setOos(ObjectOutputStream oos){ SocketHandler.oos = oos; }
    public static synchronized void setMatriculeFerry(String Matricule){ SocketHandler.MatriculeFerry = Matricule; }
    public static synchronized String getMatriculeFerry(){ return SocketHandler.MatriculeFerry; }
}

