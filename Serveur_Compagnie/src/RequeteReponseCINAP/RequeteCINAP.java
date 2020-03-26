package RequeteReponseCINAP;

import Authentification.VerificateurUsersPasswordHash;
import Outils.FichierLog;
import guis.FenetreAchatTicket;
import RequeteReponseMultiThread.*;
import java.net.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur_compagnie.ClientsList;
import Outils.*;
import RequeteReponseCHECKCARD.ReponseCHECKCARD;
import RequeteReponseCHECKCARD.RequeteCHECKCARD;
import RequeteReponseCHECKCARDS.ReponseCHECKCARDS;
import RequeteReponseCHECKCARDS.RequeteCHECKCARDS;
import RequeteReponseHAFIC.ReponseHAFIC;
import RequeteReponseHAFIC.RequeteHAFIC;
import Traversees.Traversees;
import static java.lang.Math.abs;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import serveur_compagnie.FenServer;
        
public class RequeteCINAP implements Requete, Serializable
{
    public static int REQUEST_VERIF_RESERVATION = 1;
    public static int REQUEST_BUY_TICKET = 2;
    public static int REQUEST_END_OF_CONNEXION = 3;
    public static final int REQUEST_LOGIN = 4;
    public static final int REQUEST_NEWUSER = 5;
    public static final int REQUEST_MAKINGBOOKING = 7;

    private int compteurReservation;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int type;
    private String messRequete;
    private FenetreAchatTicket fenetreAchat;
    private String PrenomConducteur;
    private String NumImmatriculation;
    private int NbPassagers;
    private String NumCarteCredit;
    private String NomClient;
    private String NumTraversee;
    private String NumClient;
    private int prixcommande;
    private Socket cliSock;
    private Properties propServeurCompagnie;
    private Properties propServeurCartes;
    private Properties propServeurEmbarquements;
    private Statement instruct;
    private Statement instruct2;
    private Statement instrucCart;
    private Connection ConnexionBDCompagnie;
    private LibrairieJDBC LibJDBC;
    private String String1;
    private String String2;
    private String MakingBookingNom = null;
    private String MakingBookingPrenom;
    private String MakingBookingAdresse;
    private String MakingBookingEmail;
    private VerificateurUsersPasswordHash Sub;
    private String AdresseClient;
    private String EmailClient;
    private int TypeAppareil;
    
    public RequeteCINAP(int t)
    {
        type = t;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteCINAP(int t, String NumTraverseeParametre, String NumClientParametre, String PrenomConducteurParametre, String NomConducteurParametre, String NumImmatriculationParametre, String NbPassagersParametre, String NumCarteCreditParametre)
    {
        type = t; PrenomConducteur = PrenomConducteurParametre; NomClient = NomConducteurParametre; NumImmatriculation = NumImmatriculationParametre; NumCarteCredit = NumCarteCreditParametre;
        NumTraversee = NumTraverseeParametre; NumClient = NumClientParametre;
        NbPassagers = Integer.valueOf(NbPassagersParametre);
        
        System.out.println("Nb passagers : " + NbPassagers);
        LibJDBC = new LibrairieJDBC(); propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    public RequeteCINAP(int t, String NumTraverseeParametre, String NumClientParametre, String PrenomConducteurParametre, String NomConducteurParametre,String NumCarteCreditParametre)
    {
        type = t; PrenomConducteur = PrenomConducteurParametre; NomClient = NomConducteurParametre; NumCarteCredit = NumCarteCreditParametre;
        NumTraversee = NumTraverseeParametre; NumClient = NumClientParametre;
        
        System.out.println("Nb passagers : " + NbPassagers);
        LibJDBC = new LibrairieJDBC(); propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteCINAP(int t, String s1, String s2)
    {
        type = t; String1 = s1; String2 = s2;
        LibJDBC = new LibrairieJDBC(); propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurCartes = LibJDBC.RecupPropertiesCard(); propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteCINAP(int t, String s1, String s2, int TypeAppareil)
    {
        System.out.println("Passage dans le constructeur connexion de différents appareils");
        type = t; String1 = s1; String2 = s2; this.TypeAppareil = TypeAppareil;
        LibJDBC = new LibrairieJDBC(); propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurCartes = LibJDBC.RecupPropertiesCard(); propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteCINAP(int t, String s1, String s2, String s3, String s4, String s5, String s6)
    {
        type = t; String1 = s1; String2 = s2; MakingBookingNom = s3; MakingBookingPrenom = s4; MakingBookingEmail = s5; MakingBookingAdresse = s6;
        LibJDBC = new LibrairieJDBC(); 
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteCINAP(int t, String s1)
    {
        type = t; String1 = s1;
        LibJDBC = new LibrairieJDBC(); propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    public int getCode()
    {
        return type;
    }
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, FichierLog fichierLog, FenServer fenetreServer, ObjectInputStream ois, ObjectOutputStream oos) 
    {
        this.ois = ois;
        this.oos = oos;
        switch (type)
        {
            case 0:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteNewUser(SocketClient);
                    }
                };
            case 1:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteVerifReservation(SocketClient); fichierLog.ecritLigne("Client "+SocketClient.getLocalSocketAddress()+" : Demande de vérification de connexion");
                    }
                };
            case 2:
                return new Runnable()
                {

                    @Override
                    public void run() {
                        traiteRequeteAchatTicket(SocketClient); fichierLog.ecritLigne("Client "+SocketClient.getLocalSocketAddress()+" : Demande d'achat de tickets");
                    }
                };
            case 3:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteEndOfConnexion(SocketClient, ListClients, fenetreServer); fichierLog.ecritLigne("Client "+SocketClient.getLocalSocketAddress()+" déconnecté");
                    }
                };
            case 4:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteConnexion(SocketClient, ListClients, fenetreServer); fichierLog.ecritLigne("Client "+SocketClient.getLocalSocketAddress()+" Demande de connexion");
                    }
                };
            case 5:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteListingAchat(SocketClient);
                    }
                };
            case 6:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteValidateCheckIn(SocketClient);
                    }
                };
            case 7:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteMakeBooking(SocketClient, fenetreServer);
                    }
                };
            case 8:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteListingTraversees(SocketClient);
                    }
                };
            case 9:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteTraversee(SocketClient);
                    }
                };               
        }
        return null;
    }
    
    
    private void traiteRequeteTraversee(Socket SocketClient)
    {
        try
        {
            String IdTraverseeChoisie = String1;
            ReponseCINAP reponse=null;
            Connection connexionCompagnie = LibJDBC.ConnexionToBDCompagnie();

            instruct = LibJDBC.CreationStatementCompagnie(connexionCompagnie);
            ResultSet rs = instruct.executeQuery("SELECT * from traversees WHERE IdTraversees = '"+ IdTraverseeChoisie + "'");
            if(rs.first())
            {
                Date DateDepart = rs.getDate(2);
                String PortDepart = rs.getString(3);
                String PortArrivee = rs.getString(4);
                String Matricule = rs.getString(5);
                String Prix = rs.getString(6);
                reponse = new ReponseCINAP(ReponseCINAP.TRAVERSEEOK, IdTraverseeChoisie, DateDepart, PortDepart, PortArrivee, Matricule, Prix);

            }
            oos.writeObject(reponse);
        }
        catch (IOException e) { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } 
        catch (SQLException ex) { Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    private void traiteRequeteMakeBooking(Socket SocketClient, FenServer fenetreServeur)
    {
        try
        {
            String NumClientMakeBooking, pwd, nom, prenom, email, adresse;
            ReponseCINAP reponse=null;
            Connection connexionCompagnie = LibJDBC.ConnexionToBDCompagnie();
            
            if (MakingBookingNom == null)
            {
                NumClientMakeBooking = String1; pwd = String2;
                
                instruct = LibJDBC.CreationStatementCompagnie(connexionCompagnie);
                ResultSet rs = instruct.executeQuery("SELECT * from clients WHERE NumClient = '"+NumClientMakeBooking+"' AND PasswordClient = '"+pwd+"';");
                if(rs.first())
                {
                    reponse = new ReponseCINAP(ReponseCINAP.MAKEBOOKING_EXISTCLIENT);
                    java.util.Date date2 = new java.util.Date();
                    DefaultTableModel tablemessagerecus2 = (DefaultTableModel)fenetreServeur.TableMessageRecus.getModel();
                    String d2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(date2);
                    tablemessagerecus2.addRow(new Object[]{d2,SocketClient.getInetAddress(), "Client connecté ! "});

                    DefaultTableModel tableclients = (DefaultTableModel)fenetreServeur.TableClientsConnectes.getModel();
                    tableclients.addRow(new Object[]{SocketClient.getInetAddress(),SocketClient.getLocalPort(), SocketClient.getLocalAddress()});
                }
                else
                    reponse = new ReponseCINAP(ReponseCINAP.MAKEBOOKING_LOGINFAILED);
            }
            else
            {
                NumClientMakeBooking = String1; pwd = String2; email = MakingBookingEmail; adresse = MakingBookingAdresse; nom = MakingBookingNom; prenom = MakingBookingPrenom;
                String query = "INSERT INTO `clients` (`NumClient`, `PasswordClient`, `NomClient`, `PrenomClient`, `AdresseClient`, `EmailClient`, `NewUserPromo`) VALUES (?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement preparedStmt;
                preparedStmt = connexionCompagnie.prepareStatement(query);
                LocalDateTime maintenant = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMdd");
                String formattedDate = maintenant.format(myFormatObj);
                
                int hash = 1;
                hash = hash * 3 + nom.hashCode();
                hash = hash * 7 + prenom.hashCode();
                hash = hash * 8 + email.hashCode();
                hash = hash * 7 + adresse.hashCode();
                hash = hash * 15 + formattedDate.hashCode();
                hash = abs(hash);
                System.out.println("Hash généré : " + hash);
                preparedStmt.setInt(1, hash);
                preparedStmt.setString(2, pwd);
                preparedStmt.setString(3, nom);
                preparedStmt.setString(4, prenom);
                preparedStmt.setString(5, adresse);
                preparedStmt.setString(6, email);
                preparedStmt.setBoolean(7, true);
                preparedStmt.execute();
                reponse = new ReponseCINAP(ReponseCINAP.MAKEBOOKING_NEWCLIENT, hash);
            }
            oos.writeObject(reponse);
        }
        catch (IOException e) { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } 
        catch (SQLException ex) { Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex); }
    
    }
    
    private void traiteRequeteNewUser(Socket SocketClient)
    {
        ConnexionBDCompagnie = null;
        instruct = null;
        ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
        instruct = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
        String log, pwd;
        ReponseCINAP reponse=null;
        log = String1; pwd = String2;
        try
        {
            String query = "INSERT INTO `bd_ferries`.`agents` (`Username`, `Password`, `TypeAgent`) values (?, ?, ?)";
            PreparedStatement preparedStmt;
            preparedStmt = ConnexionBDCompagnie.prepareStatement(query);    
            
            preparedStmt.setString(1, log);
            preparedStmt.setString(2, pwd);
            preparedStmt.setInt(3, TypeAppareil);
            preparedStmt.execute();
            reponse = new ReponseCINAP(ReponseCINAP.NEWUSER_OK);
            
            
        } catch (SQLException ex) {
            reponse = new ReponseCINAP(ReponseCINAP.NEWUSER_KO);
        }
        
        try
        {
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }            
    }
    
    private synchronized void traiteRequeteValidateCheckIn(Socket SocketClient)
    {
        String NumRes = String1;
        int NumFerry = 0;
        int NbFiles = 0;
        ReponseCINAP reponse = null;
        
        ConnexionBDCompagnie = null;
        instruct = null;
        instruct2 = null;
        ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
        instruct = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
        instruct2 = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
        try 
        {
            ResultSet rs = instruct.executeQuery("SELECT reservations.PassCheckIN FROM reservations WHERE IdReservations = '"+ NumRes+ "';");
            int Pass = 0;
            if (rs.first() == true)
                Pass = rs.getInt(1);
            if (Pass == 1)
            {
                reponse = new ReponseCINAP(ReponseCINAP.VALIDATE_DEJAEFFECTUE);
            }
            else
            {
                Socket SocketEmbarquements = new Socket(propServeurEmbarquements.getProperty("adresse"), Integer.valueOf((String) propServeurEmbarquements.getProperty("portEcoute")));
                ObjectOutputStream oosEmbarquements = new ObjectOutputStream(SocketEmbarquements.getOutputStream());
                ObjectInputStream oisEmbarquements = new ObjectInputStream(SocketEmbarquements.getInputStream());                
                
                RequeteHAFIC requeteEmbarquements = new RequeteHAFIC(0, NumTraversee, NumRes);
                oosEmbarquements.writeObject(requeteEmbarquements);
                
                ReponseHAFIC reponseEmbarquements = (ReponseHAFIC)oisEmbarquements.readObject();
                if (reponseEmbarquements.getCode() == ReponseHAFIC.VERIFFILE_OK)
                {
                    DefaultTableModel dlm = getTableReservation();
                    reponse = new ReponseCINAP(ReponseCINAP.VALIDATE_OK, dlm, reponseEmbarquements.getNumFile()); 
                }
                if (reponseEmbarquements.getCode() == ReponseHAFIC.VERIFFILE_FERRYCOMPLET)
                {
                      reponse = new ReponseCINAP(ReponseCINAP.VALIDATE_FERRYCOMPLET); 
                }
            }
            oos.writeObject(reponse); 
        }
        catch (IOException | SQLException e) 
        {
            System.err.println("Erreur : " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                ConnexionBDCompagnie.close();
            } catch (SQLException ex) {
                Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void traiteRequeteListingAchat(Socket SocketClient)
    {
        ReponseCINAP reponse=null;
        
        ArrayList array = getArrayAchat();
        if (!array.isEmpty())
            reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_LISTEOK, array);
        else
        {
            reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_LISTEKO, array);
        }

        try
        {
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
    }
    
    private void traiteRequeteListingTraversees(Socket SocketClient2)
    {
        try 
        {
            ReponseCINAP reponse=null;

            ArrayList array = getArrayTraversees(String1, String2);

            if (!array.isEmpty())
                reponse = new ReponseCINAP(ReponseCINAP.LISTETRAVERSEES_OK, array);
            else
                reponse = new ReponseCINAP(ReponseCINAP.LISTETRAVERSEES_KO, array);
            oos.writeObject(reponse);
        }  
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
    }
        
    private void traiteRequeteConnexion(Socket SocketClient, ClientsList ListClients, FenServer fenetreSer) /* Gere la requete "Client déconnecté" */
    {
        FenServer fenetreServer = fenetreSer;
        String log, pwd;
        ReponseCINAP reponse=null;
        log = String1; pwd = String2;
        
        if (TypeAppareil == 3)
        {
            ConnexionBDCompagnie = null;
            instruct = null;
            ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
            instruct = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
            try 
            {
                ResultSet rs = instruct.executeQuery("SELECT * FROM agents WHERE agents.Username = '"+ log + "' AND agents.Password = '"+pwd+"';");
                if (rs.first())
                {
                        reponse = new ReponseCINAP(ReponseCINAP.LOGIN_OK);
                        java.util.Date date2 = new java.util.Date();
                        DefaultTableModel tablemessagerecus2 = (DefaultTableModel)fenetreServer.TableMessageRecus.getModel();
                        String d2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(date2);
                        if (this.TypeAppareil == 1)
                            tablemessagerecus2.addRow(new Object[]{d2,SocketClient.getInetAddress(), "(ApplicCheckIN) Agent connecté ! "});
                        if (this.TypeAppareil == 2)
                            tablemessagerecus2.addRow(new Object[]{d2,SocketClient.getInetAddress(), "(AndroidMOBILE) Steward connecté ! "});

                        DefaultTableModel tableclients = (DefaultTableModel)fenetreServer.TableClientsConnectes.getModel();
                        tableclients.addRow(new Object[]{SocketClient.getInetAddress(),SocketClient.getLocalPort(), SocketClient.getLocalAddress()});
   
                }
                else
                    reponse = new ReponseCINAP(ReponseCINAP.LOGIN_KO);
            }   
            catch (SQLException ex) 
            {
                Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            this.Sub = new VerificateurUsersPasswordHash (log);

            if (Sub.findPwd(log, pwd) || Sub.isOk(log, pwd))
            {
                reponse = new ReponseCINAP(ReponseCINAP.LOGIN_OK);

                java.util.Date date2 = new java.util.Date();
                DefaultTableModel tablemessagerecus2 = (DefaultTableModel)fenetreServer.TableMessageRecus.getModel();
                String d2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(date2);
                if (this.TypeAppareil == 1)
                    tablemessagerecus2.addRow(new Object[]{d2,SocketClient.getInetAddress(), "(ApplicCheckIN) Agent connecté ! "});
                if (this.TypeAppareil == 2)
                    tablemessagerecus2.addRow(new Object[]{d2,SocketClient.getInetAddress(), "(AndroidMOBILE) Steward connecté ! "});

                DefaultTableModel tableclients = (DefaultTableModel)fenetreServer.TableClientsConnectes.getModel();
                tableclients.addRow(new Object[]{SocketClient.getInetAddress(),SocketClient.getLocalPort(), SocketClient.getLocalAddress()});
            }
            else
                reponse = new ReponseCINAP(ReponseCINAP.LOGIN_KO);
        }
        
        try
        {
            oos.writeObject(reponse);
            System.out.println("Je renvoi la reponse");
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }            
    }
    
    private void traiteRequeteVerifReservation(Socket SocketClient) /* Gère la requete de verification des reservations */
    {
        ReponseCINAP reponse=null;
        
        DefaultTableModel dlm = getTableReservation();
        if (dlm.getRowCount() > 0)
            reponse = new ReponseCINAP(ReponseCINAP.LISTRESERVATIONS_OK, dlm);
        else
            reponse = new ReponseCINAP(ReponseCINAP.LISTRESERVATIONS_ERROR, dlm);

        try
        {
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
    }
    
    private synchronized void traiteRequeteAchatTicket(Socket SocketClient) /* Gère la requete d'achat de ticket */
    {
        System.out.println("10");
        ReponseCINAP reponse=null;
        String NumRes;
        cliSock = SocketClient;
        String dateheure = null;
        String NomFerry = null;
        Connection connexionCompagnie = LibJDBC.ConnexionToBDCompagnie();
        System.out.println("11");
        instruct = LibJDBC.CreationStatementCompagnie(connexionCompagnie);
        instruct2 = LibJDBC.CreationStatementCompagnie(connexionCompagnie);
        try 
        {
            System.out.println("12");
            String query = "INSERT INTO `bd_ferries`.`reservations` (`IdReservations`, `IdTraversee`, `NumeroClient`, `Payer`, `PassCheckIN`) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt;
            preparedStmt = connexionCompagnie.prepareStatement(query);

            LocalDateTime maintenant = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = maintenant.format(myFormatObj);
            ResultSet rs;
            boolean ok = false;
            while(ok == false)
            {
                Random rand = new Random();
                compteurReservation = rand.nextInt(10000);
                rs = instruct2.executeQuery("SELECT * FROM reservations WHERE IdReservations = '"+formattedDate+"-RES"+compteurReservation+"';");
                
                if (rs.first() == false)
                    ok = true;
            }

            if(compteurReservation<10)
            {
                preparedStmt.setString(1, formattedDate+"-RES0"+compteurReservation);
                NumRes = ""+formattedDate+"-RES0"+compteurReservation+"";
                System.out.println("num res : " + NumRes);
            }
            else
            {
                preparedStmt.setString(1,formattedDate+"-RES"+compteurReservation);  
                NumRes = ""+formattedDate+"-RES"+compteurReservation+"";
                System.out.println("num res : " + NumRes);
            }

            preparedStmt.setString(2, NumTraversee);
            preparedStmt.setString(3, NumClient);
            preparedStmt.setInt(4, 0);
            preparedStmt.setInt(5, 0);

            rs = instruct2.executeQuery("SELECT NumeroClient FROM voyageurs WHERE Nom = '"+NomClient+"' AND Prenom= '"+PrenomConducteur+"' AND NumeroClient = '"+NumClient+"';");
            System.out.println("Instruction SELECT envoyée à Home-Ferries's DataBase");
            
            if (rs.first() == true)
            {
                if(propServeurCartes.getProperty("Securite").equals("true"))
                {
                    //SECURISE
                    System.out.println("Traite la Requete du CheckCard en SSL (CINAP)");
                    SSLSocket SocketCartes = CreerSocketSSL();
                    ObjectOutputStream oosCartes = new ObjectOutputStream(SocketCartes.getOutputStream());
                    ObjectInputStream oisCartes = new ObjectInputStream(SocketCartes.getInputStream());                

                    RequeteCHECKCARDS requeteCartes = new RequeteCHECKCARDS(0, NumCarteCredit, NomClient, PrenomConducteur, NbPassagers*100);
                    oosCartes.writeObject(requeteCartes);

                    ReponseCHECKCARDS reponseCartes = (ReponseCHECKCARDS)oisCartes.readObject();
                    if (reponseCartes.getCode() == ReponseCHECKCARDS.ACHATTICKET_ACHAT_OK)
                    {
                        synchronized (this)
                        {
                            preparedStmt.execute();
                            String queryValidatePayment = "UPDATE `reservations` SET `Payer` = 1 WHERE `IdReservations` = '"+NumRes+"';";
                            PreparedStatement preparedStmtPayer;
                            preparedStmtPayer = connexionCompagnie.prepareStatement(queryValidatePayment);
                            preparedStmtPayer.execute();

                            rs = instruct2.executeQuery("SELECT traversees.Matricule FROM traversees INNER JOIN reservations ON traversees.IdTraversees = reservations.IdTraversee AND traversees.IdTraversees = '"+NumTraversee+"';");
                            int matriculeFerry=0;
                            if (rs.first() == true)
                                matriculeFerry = rs.getInt(1);

                            rs = instruct2.executeQuery("SELECT navires.Nom FROM navires INNER JOIN traversees ON navires.Matricule = traversees.Matricule AND navires.Matricule = "+matriculeFerry+";");
                            if (rs.first() == true)
                                NomFerry = rs.getString(1);


                            rs = instruct2.executeQuery("SELECT DateDepart FROM traversees WHERE IdTraversees = '"+NumTraversee+"';");
                            String DateS = null;
                            if (rs.first() == true)
                                DateS = rs.getString(1);

                            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss", Locale.ENGLISH);
                            Date parsedDate = sdf.parse(DateS);
                            SimpleDateFormat print = new SimpleDateFormat("HH:mm:ss");
                            dateheure = print.format(parsedDate);

                        }
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_OK, NumRes, dateheure, NomFerry);
                    }
                    if (reponseCartes.getCode() == ReponseCHECKCARDS.ACHATTICKET_CARTENONTROUVE)
                    {
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_CARTENONTROUVE);
                    }
                    if (reponseCartes.getCode() == ReponseCHECKCARDS.ACHATTICKET_DEBITIMPOSSIBLE)
                    {
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_DEBITIMPOSSIBLE);
                    }
                    if (reponseCartes.getCode() == ReponseCHECKCARDS.ACHATTICKET_DEBITROPELEVE)
                    {
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_DEBITTROPELEVE);
                    }
                }
                else
                {
                    //NON SECURISE
                    System.out.println("Traite la Requete du CheckCard en normal (CINAP)");
                    Socket SocketCartes = new Socket(propServeurCartes.getProperty("adresse"), Integer.valueOf((String) propServeurCartes.getProperty("portEcoute")));
                    ObjectOutputStream oosCartes = new ObjectOutputStream(SocketCartes.getOutputStream());
                    ObjectInputStream oisCartes = new ObjectInputStream(SocketCartes.getInputStream());                

                    RequeteCHECKCARD requeteCartes = new RequeteCHECKCARD(0, NumCarteCredit, NomClient, PrenomConducteur, NbPassagers*100);
                    oosCartes.writeObject(requeteCartes);

                    ReponseCHECKCARD reponseCartes = (ReponseCHECKCARD)oisCartes.readObject();
                    if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_ACHAT_OK)
                    {
                        synchronized (this)
                        {
                            preparedStmt.execute();
                            String queryValidatePayment = "UPDATE `reservations` SET `Payer` = 1 WHERE `IdReservations` = '"+NumRes+"';";
                            PreparedStatement preparedStmtPayer;
                            preparedStmtPayer = connexionCompagnie.prepareStatement(queryValidatePayment);
                            preparedStmtPayer.execute();

                            rs = instruct2.executeQuery("SELECT traversees.Matricule FROM traversees INNER JOIN reservations ON traversees.IdTraversees = reservations.IdTraversee AND traversees.IdTraversees = '"+NumTraversee+"';");
                            int matriculeFerry=0;
                            if (rs.first() == true)
                                matriculeFerry = rs.getInt(1);

                            rs = instruct2.executeQuery("SELECT navires.Nom FROM navires INNER JOIN traversees ON navires.Matricule = traversees.Matricule AND navires.Matricule = "+matriculeFerry+";");
                            if (rs.first() == true)
                                NomFerry = rs.getString(1);


                            rs = instruct2.executeQuery("SELECT DateDepart FROM traversees WHERE IdTraversees = '"+NumTraversee+"';");
                            String DateS = null;
                            if (rs.first() == true)
                                DateS = rs.getString(1);

                            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss", Locale.ENGLISH);
                            Date parsedDate = sdf.parse(DateS);
                            SimpleDateFormat print = new SimpleDateFormat("HH:mm:ss");
                            dateheure = print.format(parsedDate);

                        }
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_OK, NumRes, dateheure, NomFerry);
                    }
                    if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_CARTENONTROUVE)
                    {
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_CARTENONTROUVE);
                    }
                    if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_DEBITIMPOSSIBLE)
                    {
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_DEBITIMPOSSIBLE);
                    }
                    if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_DEBITROPELEVE)
                    {
                        reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_DEBITTROPELEVE);
                    }
                }
            }
            else
                reponse = new ReponseCINAP(ReponseCINAP.ACHATTICKET_NOMVOYAGEUR_ERROR);
            

        oos.writeObject(reponse);
                
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ParseException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally
        {
            try {
                connexionCompagnie.close();
            } catch (SQLException ex) {
                Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void traiteRequeteEndOfConnexion(Socket SocketClient, ClientsList ListClients, FenServer fenetreSer) /* Gere la requete "Client déconnecté" */
    {
        FenServer fenetreServer = fenetreSer;
        try {
            SocketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultTableModel tableclients = (DefaultTableModel)fenetreServer.TableClientsConnectes.getModel();
        boolean bool = true;
        for (int i = 0; i< tableclients.getRowCount(); i++)
        {
            if (tableclients.getValueAt(i, 0).equals(SocketClient.getInetAddress()) && bool == true) 
            {
                 tableclients.removeRow(i);
                 bool = false;
            }                                
        } 
    }
    
    public DefaultTableModel getTableReservation()
    {
        ConnexionBDCompagnie = null;
        instruct = null;
        DefaultTableModel modelReservations = new DefaultTableModel();
        ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
        instruct = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
        try 
        {
            ResultSet rs = instruct.executeQuery("SELECT * FROM reservations WHERE IdReservations = '"+String1+"'");
            
            System.out.println("Instruction SELECT sur reservations envoyée à Home-Ferries's DataBase");
            
            ResultSetMetaData meta = rs.getMetaData();
            int nbCol = meta.getColumnCount();
            Vector<String> col_names = new Vector<String>();
            
            for (int i= 0; i< nbCol; i++)
            {
                col_names.add(meta.getColumnName(i+1));
            }
            
            Vector<Object> col_data;
            
            modelReservations.setColumnIdentifiers(col_names);

            while (rs.next())
            {
                col_data = new Vector<Object>();
                for (int i=0; i<nbCol; i++)
                {
                   col_data.add(rs.getObject(i+1)); 
                }
                modelReservations.addRow(col_data);
            }   
        }
        catch (SQLException e) 
        {
            System.err.println("Erreur : " + e);
        }
        try {
            System.out.println("Fermeture connexion with Database");
            ConnexionBDCompagnie.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelReservations;
    }   
    
    private ArrayList<String> getArrayAchat()
    {
        ConnexionBDCompagnie = null;
        instruct = null;
        ArrayList<String> ArrayAchat = new ArrayList<>();
        ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
        instruct = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
        try 
        {
            ResultSet rs = instruct.executeQuery("SELECT * FROM traversees");
            System.out.println("Instruction SELECT sur traversees envoyée à Home-Ferries's DataBase");
            
            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next())
            {
                String cs = rs.getString("IdTraversees");
                ArrayAchat.add(cs);
            }   
        }
        catch (SQLException e) 
        {
            System.err.println("Erreur : " + e);
        }
        try {
            System.out.println("Fermeture connexion with Database");
            ConnexionBDCompagnie.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayAchat;
    }
    
    private ArrayList<Traversees> getArrayTraversees(String DateDepart, String DateArrivee)
    {
        ConnexionBDCompagnie = null;
        instruct = null;
        ArrayList<Traversees> ArrayTraversees = new ArrayList<>();
        ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
        instruct = LibJDBC.CreationStatementCompagnie(ConnexionBDCompagnie);
        try 
        {
            ResultSet rs;
            if (DateDepart != null && DateArrivee != null)
                rs = instruct.executeQuery("SELECT * FROM traversees WHERE DateDepart > '"+DateDepart+"' AND DateArrivee < '"+DateArrivee+"' order by DateDepart");
            else
                rs = instruct.executeQuery("SELECT * FROM traversees order by DateDepart");

            
            System.out.println("Instruction SELECT sur traversees envoyée à Home-Ferries's DataBase");
            
            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next())
            {
                Traversees une_traversee = new Traversees();
                une_traversee.setIdTraversees(rs.getString("IdTraversees"));
                une_traversee.setDateDepart((Date)rs.getObject("DateDepart"));
                une_traversee.setPortDepart(rs.getString("PortDepart"));
                une_traversee.setPortArrivee(rs.getString("PortArrivee"));
                une_traversee.setMatricule(rs.getInt("Matricule"));
                une_traversee.setPrix(rs.getInt("Prix"));
                ArrayTraversees.add(une_traversee);
            }   
        }
        catch (SQLException e) 
        {
            System.err.println("Erreur : " + e);
        }
        try {
            System.out.println("Fermeture connexion with Database");
            ConnexionBDCompagnie.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayTraversees;
    }
    
        private SSLSocket CreerSocketSSL()
    {
        SSLSocket ClientSocket = null;
        try {
            //1. KeyStore
            //MyKeyStore = new KeyStoreClass();
            KeyStore ServerKs = KeyStore.getInstance("JKS");
            String FICHIER_KEYSTORE = "CertificatFrontieres";
            char[] PASSWD_KEYSTORE = "1234".toCharArray();
            FileInputStream ServerFK = new FileInputStream (FICHIER_KEYSTORE);
            ServerKs.load(ServerFK, PASSWD_KEYSTORE);
            
            //2. Contexte
            SSLContext SslC = SSLContext.getInstance("SSLv3");
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            char[] PASSWD_KEY = "1234".toCharArray();
            kmf.init(ServerKs, PASSWD_KEY);
            
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ServerKs);
            SslC.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            // 3. Factory
            SSLSocketFactory SslSFac= SslC.getSocketFactory();
            // 4. Socket
            ClientSocket = (SSLSocket) SslSFac.createSocket("localhost",50083);
            
            
        } catch (KeyStoreException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RequeteCHECKCARDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequeteCHECKCARDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteCHECKCARDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(RequeteCHECKCARDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ClientSocket;
    }

    public Runnable createRunnable(Socket CliSock, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, ObjectInputStream ois, ObjectOutputStream oos, FenServer fenServer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Runnable createRunnable(SSLSocket SocketClient, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


