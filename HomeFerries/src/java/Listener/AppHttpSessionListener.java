package Listener;

import JavaBean.PanierListingBean;
import RequeteReponseEBOOP.ReponseEBOOP;
import RequeteReponseEBOOP.RequeteEBOOP;
import Traversees.Traversees;
import Utilities.SocketHandler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AppHttpSessionListener implements HttpSessionListener
{
        ArrayList <Traversees> liste_panier = new ArrayList<Traversees>();
        private PanierListingBean panier;
        private Socket MaSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private String Res;
        private String numcarte,nomClient,prenomClient;
        private int prixpanier;
        private String idTraversee;
        private String NumClient;
        private int numcli ;
        private HttpSession session;
        
    public AppHttpSessionListener() 
    {
        
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) 
    {
        System.out.println("----------- SESSION CREATED ----------");
        session = se.getSession();
        session.setMaxInactiveInterval(1*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) 
    {
        System.out.println("----------- SESSION DESTROYED ----------");
        try 
        {
            MaSocket = SocketHandler.getSocket(); 
            oos = SocketHandler.getOos();
            ois = SocketHandler.getOis();
            
            PanierListingBean panier = (PanierListingBean)session.getAttribute("panier");
            Iterator <Traversees> list = panier.getPanier().iterator();

            while(list.hasNext())
            {
                    System.out.println("Suppression d'une traversee dans BD");
                    Traversees traversee = list.next();
                    String IdTraver = traversee.getIdTraversees();
                    RequeteReponseEBOOP.RequeteEBOOP req = new RequeteEBOOP(2, IdTraver);
                    oos.writeObject(req);
            }
            
        } catch (IOException ex) { Logger.getLogger(AppHttpSessionListener.class.getName()).log(Level.SEVERE, null, ex); }
    } 
}
