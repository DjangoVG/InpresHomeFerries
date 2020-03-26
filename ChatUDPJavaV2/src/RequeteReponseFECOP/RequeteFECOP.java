package RequeteReponseFECOP;

import Outils.LibrairieJDBC;
import RequeteReponseMultiThread.*;
import Serveur.ClientsList;
import Serveur.Serveur_Information;
import java.net.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Properties;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class RequeteFECOP implements Requete, Serializable
{
    public static int REQUEST_VERIF_RESERVATION = 1;
    public static int REQUEST_BUY_TICKET = 2;
    public static int REQUEST_END_OF_CONNEXION = 3;
    public static final int REQUEST_LOGIN = 4;
    public static final int REQUEST_NEWUSER = 5;
    public static final int REQUEST_MAKINGBOOKING = 7;

    private Statement instruct;
    PreparedStatement preparedStmt = null;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int type;
    private String NomClient;
    private String NumClient;
    private int NumClientloc;
    private String NumClientDi;
    private Socket cliSock;
    MessageDigest mdCli ;
    LibrairieJDBC LibJDBC ;
    Properties propServeurCompagnie;
    Connection conexionCompagnieBD;
    byte[] digestclient ;
    int PortUDP = -1 ;
    String AdresseUDP = "" ;
    private String PaysBase;
    private String PaysResult;
    private String ValeurACalculer;
    private Connection ConnexionBDPrestige;
    private Connection ConnexionBDInformation;
    
    
    public RequeteFECOP (int t, String ValeurACalculer, String PaysBase, String PaysResult)
    {
        this.type = t;
        this.PaysBase = PaysBase;
        this.PaysResult = PaysResult;
        this.ValeurACalculer = ValeurACalculer;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
    }
    
    public RequeteFECOP(int t)
    {
        type = t;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        Security.addProvider(new BouncyCastleProvider());
    }
    public RequeteFECOP(int t,int NumClient)
    {
        type = t;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        NumClientloc = NumClient;
        NumClientDi = Integer.toString(NumClient);
        Security.addProvider(new BouncyCastleProvider());
       
    }
    public RequeteFECOP(int t,String NomClient,byte[] digestclient )
    {
        type = t;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        this.NomClient = NomClient;
        this.digestclient = digestclient ;
        Security.addProvider(new BouncyCastleProvider());
    }
    public RequeteFECOP(int t,String NomClient,String NumClient)
    {
        type = t;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        this.NomClient = NomClient;
        this.NumClient = NumClient ;
        Security.addProvider(new BouncyCastleProvider());
        
    }
    
    public int getCode()
    {
        return type;
    }
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, Serveur_Information fenetreServer, ObjectInputStream ois, ObjectOutputStream oos,DataInputStream dis, DataOutputStream dos){
        this.ois = ois;
        this.oos = oos;
        this.dis = dis;
        this.dos = dos;
        switch(type)
        {
            case 0:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteLoginGroup(SocketClient, NomClient);
                    }
                };
            case 1:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequetePostQuestion();
                    }
                };
            case 2:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteAnswerQuestion();
                    }
                };
            case 3:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequetePostEvent();
                    }
                };
            case 4: 
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteConnexion(SocketClient,fenetreServer);
                    }
                };
            case 5:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteCoursMonetaire(SocketClient);
                    }
                };     
        }
        
        return null;
    }    private void traiteRequeteConnexion(Socket SocketClient,Serveur_Information fenetreSer)
    {
        Serveur_Information fenetreServer = fenetreSer;
        ReponseFECOP reponse=null;
        
        if (NomClient.equals("Regis") && NumClient.equals("1"))
        {
            reponse = new ReponseFECOP(ReponseFECOP.LOGIN_OK);
            java.util.Date date2 = new java.util.Date();
            DefaultTableModel tablemessagerecus2 = (DefaultTableModel)fenetreServer.TableMessageRecus.getModel();
            String d2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(date2);
            tablemessagerecus2.addRow(new Object[]{d2,SocketClient.getInetAddress(), "Client connecté ! "});
            
            DefaultTableModel tableclients = (DefaultTableModel)fenetreServer.TableClientsConnectes.getModel();
            tableclients.addRow(new Object[]{SocketClient.getInetAddress(),SocketClient.getLocalPort(), SocketClient.getLocalAddress()});
        }
        else
        {
            reponse = new ReponseFECOP(ReponseFECOP.LOGIN_KO);            
        }
        
        try
        {
            this.oos.writeObject(reponse);
            System.out.println("Je renvoi la reponse de la connexion au client");
        }
        catch (IOException e)
        { System.err.println("Erreur réseau Serveur ? [" + e.getMessage() + "]"); }            
    }
    
    
    private void traiteRequeteCoursMonetaire(Socket SocketClient)
    {
        ReponseFECOP rep = null;
        System.out.println("Valeur à calculer : " + ValeurACalculer);
        System.out.println("Unité de base : " + PaysBase);
        System.out.println("Unité voulue : " + PaysResult);
        
        float ValeurCalcul = Float.parseFloat(ValeurACalculer);
        System.out.println("Valeur à calculer en float : " + ValeurCalcul);
        
        instruct = null;
        DefaultTableModel modelReservations = new DefaultTableModel();
        ConnexionBDInformation = LibJDBC.ConnexionToBDCompagnie();
        instruct = LibJDBC.CreationStatementCompagnie(ConnexionBDInformation);
        try 
        {
            ResultSet rs1 = instruct.executeQuery("SELECT CoursMonetaire FROM CoursMonetaireTable WHERE UniteMonetaireBase = '"+this.PaysBase+"' AND UniteMonetaireResult = '" + this.PaysResult + "';");
            System.out.println("Instruction SELECT sur reservations envoyée à Home-Ferries's DataBase");

            float CoursMonetaireFloat = 0;
            float CalculFinal = 0;
            if (rs1.first())
            {
                CoursMonetaireFloat = rs1.getFloat(1);
                System.out.println("Cours monetaire récupéré : " + CoursMonetaireFloat);
                
                CalculFinal = ValeurCalcul * CoursMonetaireFloat;
                
                System.out.println("1 " + PaysBase + " = " + CalculFinal + " " + PaysResult);
            }
            else
            {
                ResultSet rs2 = instruct.executeQuery("SELECT CoursMonetaire FROM CoursMonetaireTable WHERE UniteMonetaireResult = '"+this.PaysBase+"' AND UniteMonetaireBase = '" + this.PaysResult + "';");
                if (rs2.first())
                {
                    CoursMonetaireFloat = rs2.getFloat(1);
                    System.out.println("Cours monetaire récupéré : " + CoursMonetaireFloat);

                    CalculFinal = ValeurCalcul / CoursMonetaireFloat;

                    System.out.println("1 " + PaysBase + " = " + CalculFinal + " " + PaysResult);
                }
            }
            rep = new ReponseFECOP(CalculFinal);
            this.oos.writeObject(rep);
            
        } catch (SQLException ex) {
            Logger.getLogger(RequeteFECOP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequeteFECOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void traiteRequeteLoginGroup(Socket s,String Nom)
    {
        MessageDigest mda = null;
        ReponseFECOP RepTcp = null;
        int a = VerifCheckIn();
        
        if(a == 1)
        {
            try 
            {
                String user = dis.readUTF();
                System.out.println("Utilisateur = " + user);
                String Num = dis.readUTF();
                System.out.println("Utilisateur = " + Num);
                long temps = dis.readLong();
                System.out.println("temps = " + temps);
                double alea = dis.readDouble();
                System.out.println("Nombre aléatoire = " + alea);
                int longueur = dis.readInt();
                System.out.println("Longueur = " + longueur);
                byte[] msgD = new byte[longueur];
                dis.readFully(msgD);

                /*Création DigestServeur*/
                mda = MessageDigest.getInstance("SHA-256", "BouncyCastleProvider");
                mda.update(NomClient.getBytes());
                //mda.update(NumClientDi.getBytes());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream bdos = new DataOutputStream(baos);
                bdos.writeLong(temps); bdos.writeDouble(alea);
                mda.update(baos.toByteArray());
                byte[] msgDLocal = mda.digest();
                /*Fin création digest serveur*/
                
                String rep;
                if (MessageDigest.isEqual(digestclient, msgDLocal) )
                {
                    rep = new String("OK");
                    System.out.println("Le client " + NomClient + " a commencer la communication avec le groupe");
                    PortUDP = 50010 ;
                    AdresseUDP = "234.5.5.10";
                }
                else
                {
                    rep = new String("KO");
                    System.out.println("Le client " + user + " est refusé");
                }
                dos.writeUTF(rep);
                dos.flush();
                System.out.println("Verdict envoyé au client");
                
                RepTcp = new ReponseFECOP(0,PortUDP,AdresseUDP);
                try
                {
                    this.oos.writeObject(RepTcp);
                    System.out.println("Je renvoi la reponse de la connexion au client avec le port/adresse UDP");
                }
                catch (IOException e)
                { System.err.println("Erreur réseau Serveur ? [" + e.getMessage() + "]"); }
            } 
            catch (NoSuchAlgorithmException ex) 
            {
                Logger.getLogger(RequeteFECOP.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (NoSuchProviderException ex) 
            {
                Logger.getLogger(RequeteFECOP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RequeteFECOP.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*finally
            {
                try
                {
                    dis.close();
                    dos.close();
                    System.out.println("Serveur fermé");
                }
                catch (IOException e)
                { System.err.println("Erreur ! ? [" + e + "]"); }
            }*/
        }
        else
            System.out.println("Vous n'avez pas passé le check-in");
    }
    
    private void traiteRequetePostQuestion()
    {
            
    }
    
    private void traiteRequeteAnswerQuestion()
    {
        
    }
    
    private void traiteRequetePostEvent()
    {
        
    }
    
    private int VerifCheckIn()
    {
        int verif = 0;
        
        conexionCompagnieBD = LibJDBC.ConnexionToBDCompagnie();
        
        try 
        {
            preparedStmt.setInt(1, NumClientloc);
            synchronized(this)
            {
                instruct = LibJDBC.CreationStatementCompagnie(conexionCompagnieBD);
                ResultSet rs = instruct.executeQuery("SELECT PassCheckIN FROM reservations WHERE NumeroClient = " + NumClientloc + "AND PassCheckIN = 1;");
                
                if(rs.first())
                {
                    verif = 1;
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Erreur : " + e.getMessage());
        }
        
        return verif;
    }
}


