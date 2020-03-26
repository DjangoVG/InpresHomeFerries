package RequeteReponseHAFICSA;

import Authentification.VerificateurUsersPasswordHash;
import Outils.FichierLog;
import RequeteReponseMultiThread.*;
import java.net.*;
import java.io.*;
import serveur_compagnie.ClientsList;
import Outils.*;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.swing.table.DefaultTableModel;
import serveur_compagnie.FenServer;
        
public class RequeteHAFICSA implements Requete, Serializable
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int type;
    private String NomAdmin;
    private String PasswordAdmin;
    private int NombreSecondesAvantArret;
    private LibrairieJDBC LibJDBC;
    private Properties propServeurCompagnie;
    private Statement instructCompagnie;
    private Connection ConnexionBDCompagnie;
    
    public static final int REQUETE_SLEEP = 2;
    public static final int REQUETE_STOP = 3;
    
    public RequeteHAFICSA (int t)
    {
        type = t;
    }
    
    public RequeteHAFICSA(int t,String NomAdmin,String PasswordAdmin)
    {
        type = t;
        this.NomAdmin = NomAdmin; this.PasswordAdmin = PasswordAdmin;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
    }
    
    public RequeteHAFICSA(int t, int NombreSecondes)
    {
        this.type = t;
        this.NombreSecondesAvantArret = NombreSecondes;
    }
    
    public int getCode()
    {
        return type;
    }
    
    public int getSleep ()
    {
        return this.NombreSecondesAvantArret;
    }
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListeAdmin, ObjectInputStream ois, ObjectOutputStream oos, FenServer fenetreServeur) 
    {
        System.out.println("Passage createRunnable HAFICSA");
        this.ois = ois;
        this.oos = oos;
        switch (type)
        {
            case 0:
                return new Runnable()
                {
                    @Override
                    public void run() {
                      TraiteRequeteConnexion(SocketClient);
                    }
                };
            case 1:
                return new Runnable()
                {
                    @Override
                    public void run() {
                      TraiteRequeteListClients(SocketClient, fenetreServeur);
                    }
                };
        }
        return null;
    }
    
    
    private void TraiteRequeteListClients (Socket SocketClient, FenServer fenetreServeur)
    {
        ReponseHAFICSA reponse=null;
        DefaultTableModel dlm = new DefaultTableModel(new String [] { "Adresse IP des clients connectés"}, 0);
        for (int i=0; i < fenetreServeur.TableClientsConnectes.getRowCount(); i++)
        {
            dlm.addRow(new Object [] {fenetreServeur.TableClientsConnectes.getValueAt(i, 0)});
            System.out.println("Client connecté : " + fenetreServeur.TableClientsConnectes.getValueAt(i, 0));  

        }            
        reponse = new ReponseHAFICSA(dlm);
        try {
            oos.writeObject(reponse);
        } catch (IOException ex) {
            Logger.getLogger(RequeteHAFICSA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void TraiteRequeteConnexion (Socket SocketClient)
    {
        String log, pwd;
        ReponseHAFICSA reponse=null;
        log = NomAdmin; pwd = PasswordAdmin;
        
            ConnexionBDCompagnie = null;
            instructCompagnie = null;
            ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
            instructCompagnie = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
            try 
            {
                ResultSet rs = instructCompagnie.executeQuery("SELECT * FROM agents WHERE agents.Username = '"+ log + "' AND agents.Password = '"+pwd+"';");
                if (rs.first())
                {
                    int Type = rs.getInt(4);
                    if (Type == 1)
                        reponse = new ReponseHAFICSA(ReponseHAFICSA.LOGIN_OK);
                    else
                        reponse = new ReponseHAFICSA(ReponseHAFICSA.LOGIN_NOTADMIN);
   
                }
                else
                    reponse = new ReponseHAFICSA(ReponseHAFICSA.LOGIN_KO);
            }   
            catch (SQLException ex) 
            {
                Logger.getLogger(ReponseHAFICSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        try
        {
            oos.writeObject(reponse);
            System.out.println("Je renvoi la reponse");
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
    }
    
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, FichierLog fichierLog, FenServer fenetreServer, ObjectInputStream ois, ObjectOutputStream oos) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Runnable createRunnable(Socket SocketClient, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Runnable createRunnable(SSLSocket SocketClient, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

