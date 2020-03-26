package RequeteCONTROLID;

import Authentification.VerificateurUsersPasswordHash;
import BeanSymetricKey.BeanHMACKey;
import BeanSymetricKey.BeanSessionKey;
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
import RequeteReponseCINAP.RequeteCINAP;
import RequeteVERIFID.ReponseVERIFID;
import RequeteVERIFID.RequeteVERIFID;
import RequeteVERIFIDS.ReponseVERIFIDS;
import RequeteVERIFIDS.RequeteVERIFIDS;
import SecurityCrypto.Certif;
import SecurityCrypto.KeyStoreClass;
import guis.FenetreControleVehicule;
import guis.FenetreLoginGarde;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import serveur_compagnie.FenServer;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import jdk.internal.org.objectweb.asm.util.CheckSignatureAdapter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
        
public class RequeteCONTROLID implements Requete, Serializable
{
    public static int REQUEST_VERIF_RESERVATION = 1;
    public static int REQUEST_BUY_TICKET = 2;
    public static int REQUEST_END_OF_CONNEXION = 3;
    public static final int REQUEST_LOGIN = 4;
    public static final int REQUEST_NEWUSER = 5;
    public static final int REQUEST_MAKINGBOOKING = 7;
    public static int EnConnexion = 1;
    
    private int compteurReservation;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ObjectOutputStream oosInternational;
    private ObjectInputStream oisInternational;
    private int type;
    private String messRequete;
    private FenetreAchatTicket fenetreAchat;
    private String PrenomConducteur;
    private String NumImmatriculation;
    private int NbPassagers;
    private String NumCarteCredit;
    private String NomClient;
    private String NumTraversee;
    private String Permis;
    private String NumClient;
    private int prixcommande;
    private Socket cliSock;
    private Properties propServeurInternational;
    private Statement instruct;
    private Statement instruct2;
    private Statement instrucCart;
    private Connection ConnexionBDCompagnie;
    private Connection ConnexionBDFrontiereNationale;
    private Connection ConnexionBDFrontiereInternational;
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
    private String Pays;
    private int TypeAppareil;
    private Properties propServeurFrontieresNationale;
    private Properties propServeurCompagnie;
    private byte[] hashcli;
    private String String3;
    private boolean ok = true;
    private Signature signature;
    private Certif certificat;
    private KeyStoreClass MyKeyStore;
    private static String codeProvider = "BC"; // "BC";
    private byte [] bytType = null;
    private byte [] bytText = null;
    private byte [] bytSignature = null;
    private byte [] bytPays = null;
    private byte [] bytPermis = null;
    private byte [] encryptedMessage;
    private String decryptedMessage;
    private Socket cliSockInternational;
    private SSLSocket cliSockInternationalSSL;
    private byte[] bytCléHMAC;
    private byte[] bytcleSecrete;
    private String SessionKey;
    private String HMACKey;
    private byte[] bytCléSession;
    private String PlaqueImmatriculation;
    private byte[] ByteSignature;
    private boolean CheckSignature;
    private boolean Test;
    private Certif cer;
    private KeyStoreClass keystore;
    private KeyStore ks;
    private static SecretKey sk1;
    private static SecretKey cléHMAC;
    public static BeanSessionKey BeanCléSession;
    public static BeanHMACKey BeanCléHMAC;
    private String NumeroNational;
    private String Nom;
    private String Prenom;
    private String DateNaissance;
    private String DateMaxValiditeIdentite;
    private String DateMaxValiditePermis;
    private String NuméroPermis;
    private byte[] bytType2 ;
    private byte[] bytText2 ;
    private byte[] bytPermis2 ;
    private byte[] bytPays2;
    private int TypeClé = 0;
    public RequeteCONTROLID(int t, String Username, byte [] Password)
    {
        type = t; this.String1 = Username; this.hashcli = Password;
        LibJDBC = new LibrairieJDBC();
        propServeurFrontieresNationale = LibJDBC.RecupPropertiesFrontieresNationale();
    }
    
    public RequeteCONTROLID(byte [] bytReq, byte [] bytReqIdentite, byte [] bytReqPays, byte [] bytReqPermis, byte [] bytReqcléSecr, byte [] bytReqCléHMAC, byte [] bytReqSignature)
    {
        this.bytType = bytReq; this.bytText = bytReqIdentite; this.bytPermis = bytReqPermis; this.bytcleSecrete = bytReqcléSecr; this.bytCléHMAC = bytReqCléHMAC; this.bytPays = bytReqPays;
        this.bytSignature = bytReqSignature;
        LibJDBC = new LibrairieJDBC();
        propServeurFrontieresNationale = LibJDBC.RecupPropertiesFrontieresNationale();
        EnConnexion = 0;
    }
    
    public RequeteCONTROLID(byte [] bytReq, byte [] bytReqIdentite, byte [] bytReqPays, byte [] bytReqPermis, byte [] bytReqcléSecr, byte [] bytReqCléHMAC, byte [] bytReqSignature,int TypeCle)
    {
        this.bytType = bytReq; this.bytText = bytReqIdentite; this.bytPermis = bytReqPermis; this.bytcleSecrete = bytReqcléSecr; this.bytCléHMAC = bytReqCléHMAC; this.bytPays = bytReqPays;
        this.bytSignature = bytReqSignature;
        LibJDBC = new LibrairieJDBC();
        propServeurFrontieresNationale = LibJDBC.RecupPropertiesFrontieresNationale();
        EnConnexion = 0;
        TypeClé = 1;
    }
    
    public RequeteCONTROLID (int type, String Plaque, String Pays, byte [] signature)
    {
        this.type = type;
        PlaqueImmatriculation = Plaque;
        this.Pays = Pays;
        this.ByteSignature = signature;
        LibJDBC = new LibrairieJDBC();
        propServeurFrontieresNationale = LibJDBC.RecupPropertiesFrontieresNationale();
        EnConnexion = 0;
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
        System.out.println("EnConnexion : " + EnConnexion);
        if(EnConnexion == 0) //Ce n'est pas une connexion
        {
            CheckSignature = false;
            if (this.type == 30)
                CheckSignature = VerifSignature();
            else
            {
                if(TypeClé == 0)
                    ok = DecryptCode(this.bytType,this.bytText, this.bytPermis, this.bytPays, this.bytSignature, this.bytcleSecrete, this.bytCléHMAC);
                else
                    ok = DecryptCodeHMAC(this.bytType,this.bytText, this.bytPermis, this.bytPays, this.bytSignature, this.bytcleSecrete, this.bytCléHMAC);
            }
                

        }
        
        if(ok == true || CheckSignature == true)
        {
            switch (type)
            {
                case 1:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteEndOfConnexion(SocketClient, ListClients, fenetreServer); fichierLog.ecritLigne("Client "+SocketClient.getLocalSocketAddress()+" déconnecté");
                        }
                    };
                case 2:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteConnexion(SocketClient); fichierLog.ecritLigne("Client "+SocketClient.getLocalSocketAddress()+" Demande de connexion");
                        }
                    };
                case 30:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteListingVehicules(SocketClient);
                        }
                    };    
                case 4:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteListingIdentiteNational(SocketClient);
                        }
                    };  
                case 5:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteListingPassagerIdentiteNational(SocketClient);
                        }
                    };  
            }
        }
        return null;
    }
    
    private void traiteRequeteListingIdentiteNational(Socket SocketClient2)
    {
        System.out.println("******************* Début traiteRequeteListingIdentiteNational ***********************************");
        try 
        {   
            ReponseCONTROLID reponse=null;
            
            if (!Pays.equalsIgnoreCase("Belgique"))
            {
                System.out.println("Je suis dans CONTROLID et je suis pas Belge");
                this.oosInternational = null;
                this.oisInternational = null;
                propServeurInternational = LibJDBC.RecupPropertiesFrontieresInternationale();
                
                System.out.println("Connexion to International OK");
                
                if(propServeurInternational.getProperty("Securite").equals("true"))
                {
                    //SECURISE
                    System.out.println("traiteRequeteListingConducteur Securise");
                    cliSockInternationalSSL = CreerSocketSSL();
                    this.oosInternational = new ObjectOutputStream(cliSockInternationalSSL.getOutputStream());
                    this.oisInternational = new ObjectInputStream(cliSockInternationalSSL.getInputStream());
                    
                    bytType2 = GenerateCrypt(Integer.toString(type).getBytes());
                    bytText2 = GenerateCrypt(String1.getBytes());
                    bytPermis2 = GenerateCrypt(Permis.getBytes());
                    bytPays2 = GenerateCrypt(Pays.getBytes());
                    
                    RequeteVERIFIDS reqInternational = new RequeteVERIFIDS(bytType2, bytText2,bytPays2, bytPermis2, null, null, null);
                    oosInternational.writeObject(reqInternational);
                    ReponseVERIFIDS reponse2 = (ReponseVERIFIDS)this.oisInternational.readObject();
                    if (reponse2.getCode() == ReponseVERIFIDS.LISTEVEHICULES_OK)
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTEVEHICULES_OK, reponse2.getNumeroNational(),reponse2.getNumeroPermis(),reponse2.getNom(),reponse2.getPrenom(),reponse2.getDateNaissance(),reponse2.getDateMaxValiditeIdentite(),reponse2.getDateMaxValiditePermis());
                    }
                    else
                    {
                        if (reponse2.getCode() == ReponseVERIFIDS.LISTIDENTITEPERMIS_KO)
                        {
                            reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTIDENTITEPERMIS_KO);    
                        }
                        else
                        {
                            if (reponse2.getCode() == ReponseVERIFIDS.LISTNUMNATIONAL_KO)
                            {
                                reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTNUMNATIONAL_KO);    
                            } 
                            else
                            {
                                if (reponse2.getCode() == ReponseVERIFIDS.LISTPERMIS_KO)
                                {
                                    reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPERMIS_KO);    
                                }
                            }
                        }
                    }
                    cliSockInternationalSSL.close();
                    oisInternational.close();
                    oosInternational.close();
                }
                else
                {
                    //NON SECURISE
                    System.out.println("traiteRequeteListingConducteur NON-Securise");
                    cliSockInternational = new Socket(propServeurInternational.getProperty("adresse"), Integer.valueOf((String) propServeurInternational.getProperty("portEcoute")));

                    this.oosInternational = new ObjectOutputStream(cliSockInternational.getOutputStream());
                    this.oisInternational = new ObjectInputStream(cliSockInternational.getInputStream());
                    
                    bytType2 = GenerateCrypt(Integer.toString(type).getBytes());
                    bytText2 = GenerateCrypt(String1.getBytes());
                    bytPermis2 = GenerateCrypt(Permis.getBytes());
                    bytPays2 = GenerateCrypt(Pays.getBytes());
                    //byte[] bytcleSecrete2 = GenerateCrypt(SessionKey.getBytes());
                    //byte[] bytCléHMAC2 = GenerateCrypt(HMACKey.getBytes());
                    
                    RequeteVERIFID reqInternational = new RequeteVERIFID(bytType2, bytText2,bytPays2, bytPermis2, null, null, null);
                    oosInternational.writeObject(reqInternational);
                    System.out.println("Envoie de la requete VERIFID");
                    ReponseVERIFID reponse2 = (ReponseVERIFID)this.oisInternational.readObject();
                    
                    if (reponse2.getCode() == ReponseVERIFID.LISTEVEHICULES_OK)
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTEVEHICULES_OK,reponse2.getNumeroNational(),reponse2.getNumeroPermis(),reponse2.getNom(),reponse2.getPrenom(),reponse2.getDateNaissance(),reponse2.getDateMaxValiditeIdentite(),reponse2.getDateMaxValiditePermis());
                    }
                    else
                    {
                        if (reponse2.getCode() == ReponseVERIFID.LISTIDENTITEPERMIS_KO)
                        {
                            reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTIDENTITEPERMIS_KO);    
                        }
                        else
                        {
                            if (reponse2.getCode() == ReponseVERIFID.LISTNUMNATIONAL_KO)
                            {
                                reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTNUMNATIONAL_KO);    
                            } 
                            else
                            {
                                if (reponse2.getCode() == ReponseVERIFID.LISTPERMIS_KO)
                                {
                                    reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPERMIS_KO);    
                                }
                            }
                        }
                    }
                    cliSockInternational.close();
                    oisInternational.close();
                    oosInternational.close();
                }
                
            }   
            else
            {
                System.out.println("Je suis dans CONTROLID et je suis Belge");
                getIdentite();
                getPermis();

                if (NuméroPermis != null && NumeroNational != null)
                {
                    System.out.println("OK");
                    
                    byte[] bytNumReg = GenerateCrypt(NumeroNational.getBytes());
                    byte[] bytNumPermis = GenerateCrypt(NuméroPermis.getBytes());
                    byte[] bytNom = GenerateCrypt(Nom.getBytes());
                    byte[] bytPrenom = GenerateCrypt(Prenom.getBytes());
                    byte[] bytDateNaissance = GenerateCrypt(DateNaissance.getBytes());
                    byte[] bytDateMaxValiditeIdentite = GenerateCrypt(DateMaxValiditeIdentite.getBytes());
                    byte[] bytDateMaxValiditePermis = GenerateCrypt(DateMaxValiditePermis.getBytes());
                    
                    reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTEVEHICULES_OK,bytNumReg,bytNumPermis,bytNom,bytPrenom,bytDateNaissance,bytDateMaxValiditeIdentite,bytDateMaxValiditePermis);                    
                }
                else
                {
                    if (NuméroPermis == null && NumeroNational == null)
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTIDENTITEPERMIS_KO);      
                    }
                    else
                    {
                        if (NuméroPermis == null)
                        {
                            reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTNUMNATIONAL_KO);      
                        }
                        else
                        {
                            if (NumeroNational == null)
                            {
                                reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPERMIS_KO);      
                            }
                        }                        
                    }   
                }
            }
            oos.writeObject(reponse);  
            
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } catch (ClassNotFoundException ex) { 
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }        
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
            ClientSocket = (SSLSocket) SslSFac.createSocket("localhost",50052);
            
            
        } catch (KeyStoreException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ClientSocket;
    }
    
    private void traiteRequeteListingPassagerIdentiteNational(Socket SocketClient2)
    {
       System.out.println("******************* Début traiteRequeteListingPassagers ***********************************");
        try 
        {   
            ReponseCONTROLID reponse=null;
            this.oosInternational = null;
            this.oisInternational = null;
            propServeurInternational = LibJDBC.RecupPropertiesFrontieresInternationale();
            if (!Pays.equalsIgnoreCase("Belgique"))
            {
                if(propServeurInternational.getProperty("Securite").equals("true"))
                {
                    //SECURISE
                    System.out.println("traiteRequeteListingPassagers Securise");
                    cliSockInternationalSSL = CreerSocketSSL();
                    this.oosInternational = new ObjectOutputStream(cliSockInternationalSSL.getOutputStream());
                    this.oisInternational = new ObjectInputStream(cliSockInternationalSSL.getInputStream());
                    
                    bytType2 = GenerateCrypt(Integer.toString(type).getBytes());
                    bytText2 = GenerateCrypt(String1.getBytes());
                    bytPays2 = GenerateCrypt(Pays.getBytes());
                    
                    RequeteVERIFIDS reqInternational = new RequeteVERIFIDS(bytType2, bytText2,bytPays2, null, null, null, null);
                    oosInternational.writeObject(reqInternational);
                    ReponseVERIFIDS reponse2 = (ReponseVERIFIDS)this.oisInternational.readObject();
                    if (reponse2.getCode() == ReponseVERIFIDS.LISTPASSAGER_OK)
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPASSAGER_OK, reponse2.getNumeroNational(),reponse2.getNom(),reponse2.getPrenom(),reponse2.getDateNaissance(),reponse2.getDateMaxValiditeIdentite());
                    }
                    else
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPASSAGER_KO);
                    }
                    cliSockInternationalSSL.close();
                    oisInternational.close();
                    oosInternational.close();
                    
                }
                else
                {
                    //NON SECURISE
                    System.out.println("traiteRequeteListingPassagers Non-Securise");
                    cliSockInternational = new Socket(propServeurInternational.getProperty("adresse"), Integer.valueOf((String) propServeurInternational.getProperty("portEcoute")));

                    this.oosInternational = new ObjectOutputStream(cliSockInternational.getOutputStream());
                    this.oisInternational = new ObjectInputStream(cliSockInternational.getInputStream());
                    System.out.println("Connexion to International OK");
                    
                    bytType2 = GenerateCrypt(Integer.toString(type).getBytes());
                    bytText2 = GenerateCrypt(String1.getBytes());
                    bytPays2 = GenerateCrypt(Pays.getBytes());
                    
                    RequeteVERIFID reqInternational = new RequeteVERIFID(bytType2, bytText2,bytPays2, null, null, null, null);
                    oosInternational.writeObject(reqInternational);
                    ReponseVERIFID reponse2 = (ReponseVERIFID)this.oisInternational.readObject();
                    if (reponse2.getCode() == ReponseVERIFID.LISTPASSAGER_OK)
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPASSAGER_OK, reponse2.getNumeroNational(),reponse2.getNom(),reponse2.getPrenom(),reponse2.getDateNaissance(),reponse2.getDateMaxValiditeIdentite());
                    }
                    else
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPASSAGER_KO);
                    }
                    cliSockInternational.close();
                    oisInternational.close();
                    oosInternational.close();
                }
                
                
            }   
            else
            {
                System.out.println("Je suis dans CONTROLID et je suis Belge");
                getIdentite();

                if (NumeroNational != null)
                {
                    System.out.println("OK on va mettre à jour le modele pour le belge");

                    /*DefaultTableModel ModelNumNational= new DefaultTableModel(new String[] {"Numéro national", "Nom", "Prénom", "Date de naissance", "Date max de validité" }, 0);
                    ModelNumNational.addRow(new String [] {NumeroNational.toString(), Nom.toString(), Prenom.toString(), DateNaissance.toString(), DateMaxValiditeIdentite.toString() });*/
                    byte[] bytNumReg = GenerateCrypt(NumeroNational.getBytes());
                    byte[] bytNom = GenerateCrypt(Nom.getBytes());
                    byte[] bytPrenom = GenerateCrypt(Prenom.getBytes());
                    byte[] bytDateNaissance = GenerateCrypt(DateNaissance.getBytes());
                    byte[] bytDateMaxValiditeIdentite = GenerateCrypt(DateMaxValiditeIdentite.getBytes());
                    
                    reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPASSAGER_OK,bytNumReg,bytNom,bytPrenom,bytDateNaissance,bytDateMaxValiditeIdentite);                    
                }
                else
                {
                    if ( NumeroNational == null)
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTIDENTITEPERMIS_KO);      
                    }
                    else
                    {
                        if (NumeroNational == null)
                        {
                            reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTPERMIS_KO);      
                        }
                                        
                    }   
                }
            }
            oos.writeObject(reponse);  
            
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } catch (ClassNotFoundException ex) { 
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void traiteRequeteListingVehicules(Socket SocketClient2)
    {
        Security.addProvider(new BouncyCastleProvider());
        System.out.println("******************* Début traiteRequeteListingVehicules ***********************************");
        try 
        {   
            ReponseCONTROLID reponse=null;
            
            if (Pays.equalsIgnoreCase("Belgique"))
            {
                System.out.println("Je suis dans CONTROLID et je suis Belge");
                DefaultTableModel dlm = getTableVehicule();
                cer = new Certif();

                keystore = new KeyStoreClass();
                ks = keystore.getMyKeyStore();

                PrivateKey cléPrivée;
                cléPrivée = (PrivateKey) ks.getKey("serveurfrontieres","1234".toCharArray());

                Signature s = Signature.getInstance("SHA256withRSA","BC");
                s.initSign(cléPrivée);
                s.update(String.valueOf(ReponseCONTROLID.LISTEVEHICULES_OK).getBytes());
                byte [] signature2 = s.sign();
                if (dlm.getRowCount() > 0)
                {
                        KeyGenerator keyGen = KeyGenerator.getInstance("DES","BC"); 
                        keyGen.init(new SecureRandom());
                        //BeanCléSession = new BeanSessionKey();
                        //BeanCléSession.setClédeSession(keyGen.generateKey());
                        sk1 = keyGen.generateKey();
                        
                        
                        keyGen.init(new SecureRandom()); 
                        //BeanCléHMAC = new BeanHMACKey();
                        //BeanCléHMAC.setCléHMAC(keyGen.generateKey());
                        cléHMAC = keyGen.generateKey();
                        
                        
                        byte [] cléSecreteCryptée = GenerateCrypt(Base64.getEncoder().encodeToString(sk1.getEncoded()).getBytes());
                        byte [] cléHMACCryptée = GenerateCrypt(Base64.getEncoder().encodeToString(cléHMAC.getEncoded()).getBytes());
                    
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTEVEHICULES_OK, dlm, signature2, cléSecreteCryptée, cléHMACCryptée); 
                }
                else
                    reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTEVEHICULES_KO);  
                oos.writeObject(reponse);  
            }   
            else
            {
                System.out.println("Je suis dans CONTROLID et je suis pas Belge");
                this.oosInternational = null;
                this.oisInternational = null;
                propServeurInternational = LibJDBC.RecupPropertiesFrontieresInternationale();
                
                if(propServeurInternational.getProperty("Securite").equals("true"))
                {
                    //SECURISE
                    System.out.println("traiteRequeteListingVehicules Securise");
                    cliSockInternationalSSL = CreerSocketSSL();
                    this.oosInternational = new ObjectOutputStream(cliSockInternationalSSL.getOutputStream());
                    this.oisInternational = new ObjectInputStream(cliSockInternationalSSL.getInputStream());
                    System.out.println("Connexion to International OK");
                    RequeteVERIFIDS reqInternational = new RequeteVERIFIDS(this.type, this.PlaqueImmatriculation, ByteSignature);
                    oosInternational.writeObject(reqInternational);
                    ReponseVERIFIDS reponse2 = (ReponseVERIFIDS)this.oisInternational.readObject();
                    if (reponse2.getCode() == ReponseVERIFIDS.LISTEVEHICULES_OK)
                    {
                        KeyGenerator keyGen = KeyGenerator.getInstance("DES","BC");
                        keyGen.init(new SecureRandom());
                        sk1 = keyGen.generateKey();
                        keyGen.init(new SecureRandom()); 
                        cléHMAC = keyGen.generateKey();
                        
                        byte [] cléSecreteCryptée = GenerateCrypt(Base64.getEncoder().encodeToString(sk1.getEncoded()).getBytes());
                        byte [] cléHMACCryptée = GenerateCrypt(Base64.getEncoder().encodeToString(cléHMAC.getEncoded()).getBytes());
                        reponse = new ReponseCONTROLID(reponse2.getCode(), reponse2.getModele(), reponse2.getSignature(), cléSecreteCryptée, cléHMACCryptée);
                    }
                    else
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTEVEHICULES_KO);
                    }
                    oos.writeObject(reponse);  
                    cliSockInternationalSSL.close();
                    oisInternational.close();
                    oosInternational.close();
                }
                else
                {
                    //NON SECURISE
                    System.out.println("traiteRequeteListingVehicules Non-Securise");
                    cliSockInternational = new Socket(propServeurInternational.getProperty("adresse"), Integer.valueOf((String) propServeurInternational.getProperty("portEcoute")));

                    this.oosInternational = new ObjectOutputStream(cliSockInternational.getOutputStream());
                    this.oisInternational = new ObjectInputStream(cliSockInternational.getInputStream());
                    System.out.println("Connexion to International OK");
                    RequeteVERIFID reqInternational = new RequeteVERIFID(this.type, this.PlaqueImmatriculation, ByteSignature);
                    oosInternational.writeObject(reqInternational);
                    ReponseVERIFID reponse2 = (ReponseVERIFID)this.oisInternational.readObject();
                    if (reponse2.getCode() == ReponseVERIFID.LISTEVEHICULES_OK)
                    {
                        KeyGenerator keyGen = KeyGenerator.getInstance("DES","BC");
                        keyGen.init(new SecureRandom());
                        sk1 = keyGen.generateKey();
                        keyGen.init(new SecureRandom()); 
                        cléHMAC = keyGen.generateKey();
                        
                        byte [] cléSecreteCryptée = GenerateCrypt(Base64.getEncoder().encodeToString(sk1.getEncoded()).getBytes());
                        byte [] cléHMACCryptée = GenerateCrypt(Base64.getEncoder().encodeToString(cléHMAC.getEncoded()).getBytes());
                        reponse = new ReponseCONTROLID(reponse2.getCode(), reponse2.getModele(), reponse2.getSignature(), cléSecreteCryptée, cléHMACCryptée);
                    }
                    else
                    {
                        reponse = new ReponseCONTROLID(ReponseCONTROLID.LISTEVEHICULES_KO);
                    }
                    oos.writeObject(reponse);  
                    cliSockInternational.close();
                    oisInternational.close();
                    oosInternational.close(); 
                }      
            }
            
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } catch (ClassNotFoundException ex) { 
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        private byte [] GenerateCrypt(byte [] AHashed) {
        
        try
        {
            encryptedMessage = null;
            MyKeyStore = new KeyStoreClass();
            certificat = new Certif();
            KeyStore ks = MyKeyStore.getMyKeyStore();
            System.out.println("Keystore récupéré : " + ks);
            System.out.println("Obtention de le clé privée du serveur national");
            PrivateKey cléPrivée;
            cléPrivée = (PrivateKey) ks.getKey("serveurfrontieres", "1234".toCharArray());
            System.out.println(" *** Cle privee recuperee = " + cléPrivée.toString());

            Cipher encrypt=Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, cléPrivée);
            encryptedMessage=encrypt.doFinal(AHashed);
            System.out.println("JE CRYPTE OK");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FenetreLoginGarde.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedMessage;
    }
    
    private byte [] GenerateSalt(String Password) {
        byte[] hashedPassword = null;
        Integer HashCodePassword = Password.hashCode();
        System.out.println("Mot de passe du client de la bdd après le hash code GenerateSalt RequeteCONTROLID : " + HashCodePassword);
        String tmp = Integer.toString(HashCodePassword);
        /*SecureRandom random = new SecureRandom();
        byte[]salt = new byte[16];
        random.nextBytes(salt);*/
        Date datesalt = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dat = dateFormat.format(datesalt);
        System.out.println("Date du jour : " + dat);
        byte[]salt = dat.getBytes();
        MessageDigest md;
        try 
        {
            md = MessageDigest.getInstance("MD5");
            md.update(salt);
            hashedPassword = md.digest(Password.getBytes());
            
        } catch (NoSuchAlgorithmException ex) {
            
        }

        System.out.println("Return du byte salé");
        return hashedPassword;
    }
    
    private void traiteRequeteConnexion(Socket SocketClient) /* Gere la requete "Client déconnecté" */
    {
        String log, pwd;
        ReponseCONTROLID reponse=null;
        log = String1; pwd = String2;
        try 
        { 
            ConnexionBDFrontiereNationale = null;
            instruct = null;
            ConnexionBDFrontiereNationale = LibJDBC.ConnexionToBDNationale();
            instruct = LibJDBC.CreationStatementFrontieresNationale(ConnexionBDFrontiereNationale);

            ResultSet rs = instruct.executeQuery("SELECT * FROM garde WHERE garde.Id_Garde = '"+log+"';");
            if (rs.first())
            {
                String mdp = rs.getString("PasswordGarde");
                System.out.println("mdp bdd : " + mdp);
                
                byte[] hashBDD = GenerateSalt(mdp);
                
                System.out.println("hashcli : " + hashcli);
                System.out.println("hashbdd: " + hashBDD);
                
                
                if(java.util.Arrays.equals(hashcli,hashBDD))
                {
                    EnConnexion = 0;
                    System.out.println("Login OK");
                    reponse = new ReponseCONTROLID(ReponseCONTROLID.LOGIN_OK);  
                }
                else
                {
                    reponse = new ReponseCONTROLID(ReponseCONTROLID.LOGIN_KO); 
                    System.out.println("Login KO");
                }
            }
            else
            {
                System.out.println("Pas de garde trouvé");
                reponse = new ReponseCONTROLID(ReponseCONTROLID.LOGIN_KO);                 
            }
            oos.writeObject(reponse);
            System.out.println("Je renvoi la reponse");
        }
        catch (IOException e) { 
            System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        catch (SQLException ex) {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex); }
        /*catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex); }   */     
    }
    
    private void traiteRequeteEndOfConnexion(Socket SocketClient, ClientsList ListClients, FenServer fenetreSer) /* Gere la requete "Client déconnecté" */
    {
        FenServer fenetreServer = fenetreSer;
        try {
            SocketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(ReponseCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void getIdentite()
    {
        ConnexionBDFrontiereNationale = null;
        instruct = null;
        DefaultTableModel dlmIdentite = new DefaultTableModel();
        ConnexionBDFrontiereNationale = LibJDBC.ConnexionToBDNationale();
        instruct = LibJDBC.CreationStatementFrontieresNationale(ConnexionBDFrontiereNationale);
        
        try 
        {
            ResultSet rs = instruct.executeQuery("SELECT * FROM individus where NumRegistreNational = '"+String1+"';");
            if (rs.first())
            {
                NumeroNational = rs.getString(1);
                Nom = rs.getString(2);
                Prenom = rs.getString(3);
                DateNaissance = rs.getString(4);
                DateMaxValiditeIdentite = rs.getString(5);                
            }

              
        }
        catch (SQLException e) 
        {
            System.err.println("Erreur : " + e);
        }
        try {
            System.out.println("Fermeture connexion with Database");
            ConnexionBDFrontiereNationale.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getPermis()
    {
        ConnexionBDFrontiereNationale = null;
        instruct = null;
        DefaultTableModel dlmPermis = new DefaultTableModel();
        ConnexionBDFrontiereNationale = LibJDBC.ConnexionToBDNationale();
        instruct = LibJDBC.CreationStatementFrontieresNationale(ConnexionBDFrontiereNationale);
        
        try 
        {
            ResultSet rs = instruct.executeQuery("SELECT * FROM permis where NumPermis = '"+Permis+"';");
            if (rs.first())
            {
                NuméroPermis = rs.getString(1);
                Nom = rs.getString(2);
                Prenom = rs.getString(3);
                DateMaxValiditePermis = rs.getString(4);                 
            }

        }
        catch (SQLException e) 
        {
            System.err.println("Erreur : " + e);
        }
        try {
            System.out.println("Fermeture connexion with Database");
            ConnexionBDFrontiereNationale.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private byte [] serialize(DefaultTableModel dlm) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(dlm);
        return out.toByteArray();
    }
        
    public DefaultTableModel getTableVehicule()
    {
        instruct = null;
        DefaultTableModel modelReservations = new DefaultTableModel();
        ConnexionBDFrontiereNationale = LibJDBC.ConnexionToBDNationale();
        instruct = LibJDBC.CreationStatementFrontieresNationale(ConnexionBDFrontiereNationale);
        try 
        {
            ResultSet rs = instruct.executeQuery("SELECT * FROM vehicule where Id_Immatriculation = '"+this.PlaqueImmatriculation+"';");
            
            System.out.println("Instruction SELECT sur reservations envoyée à Home-Ferries's DataBase");
            
            ResultSetMetaData meta = rs.getMetaData();
            int nbCol = meta.getColumnCount();
            System.out.println("Nombre colonne du select : " + nbCol);
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
            ConnexionBDFrontiereNationale.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelReservations;
    }  

    public Runnable createRunnable(Socket CliSock, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Boolean VerifSignature() 
    {
        try {
            certificat = new Certif();
            System.out.println("Obtention de le clé publique du garde");
            X509Certificate certif = certificat.getCertif("regisyannis.cer");
            
            PublicKey clePublic = certif.getPublicKey();
            System.out.println(" *** Cle public recuperee = " + clePublic.toString());
            certif.verify(clePublic); 
            
            // Création en local d'une signature
            Signature s = Signature.getInstance("SHA256withRSA", "BC");
            s.initVerify(clePublic);
            s.update(PlaqueImmatriculation.getBytes());
            
            Test = s.verify(ByteSignature);
            System.out.println("Boolean après test RequeteControlID : " + Test);
        } catch (CertificateException ex) {
            return false;
        } catch (NoSuchAlgorithmException ex) {
            return false;
        } catch (InvalidKeyException ex) {
            return false;
        } catch (NoSuchProviderException ex) {
            return false;
        } catch (SignatureException ex) {
            return false;
        }
        return Test;
    }
    
    private Boolean DecryptCode(byte[] typeReq ,byte[] mess, byte [] permis, byte [] pays, byte[] signature, byte [] clésession, byte [] cléhmac) 
    {
        try {
            
            Cipher decrypt = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            System.out.println("Avant l'init : Cipher = " + decrypt + " Clé de session sk1 = " + sk1);
            decrypt.init(Cipher.DECRYPT_MODE, sk1);
            
            byte[] typeReq2 = decrypt.doFinal(typeReq);
            byte[] mess2 = null;
            if (mess != null) mess2 = decrypt.doFinal(mess);
            byte[] permis2 = null;
            if (permis != null) permis2 = decrypt.doFinal(permis);
            byte[] signature2 = null;
            if (signature != null) signature2 = decrypt.doFinal(signature);
            byte[] pays2 = null;
            if (pays != null) pays2 = decrypt.doFinal(pays);
            byte[] clésession2 = null;
            if (clésession != null) clésession2 = decrypt.doFinal(clésession);
            byte[] cléhmac2 = null;
            if (cléhmac != null) cléhmac2 = decrypt.doFinal(cléhmac);
            
            String typetmp = new String(typeReq2);
            String messtmp = null;
            if (mess2 != null) messtmp = new String(mess2);
            String signaturetmp = null;
            if (signature2 != null) signaturetmp = new String(signature2);
            String permistmp = null;
            if (permis2 != null) permistmp = new String(permis2);
            String paystmp = null;
            if (pays2 != null) paystmp = new String(pays2);
            String clésessiontmp = null;
            if (clésession2 != null) clésessiontmp = new String(clésession2);
            String cléhmactmp = null;
            if (cléhmac2 != null) cléhmactmp = new String(cléhmac2);
            
            System.out.println("Type décrypté : " + typetmp);
            type = Integer.parseInt(typetmp);
            System.out.println("Mess décrypté : " + messtmp);
            String1 = messtmp;
            System.out.println("Pays décrypté : " + paystmp);
            Pays = paystmp;
            System.out.println("Permis décrypté : " + permistmp);
            Permis = permistmp;
            System.out.println("Clé de session décryptée : " + clésessiontmp);
            SessionKey = clésessiontmp;
            System.out.println("clé HMAC décryptée : " + signaturetmp);
            HMACKey = cléhmactmp;
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    private Boolean DecryptCodeHMAC(byte[] typeReq ,byte[] mess, byte [] permis, byte [] pays, byte[] signature, byte [] clésession, byte [] cléhmac) 
    {
        try {
            
            Cipher decrypt = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            System.out.println("Avant l'init : Cipher = " + decrypt + " Clé de session sk1 = " + cléHMAC);
            decrypt.init(Cipher.DECRYPT_MODE, cléHMAC);
            
            byte[] typeReq2 = decrypt.doFinal(typeReq);
            byte[] mess2 = null;
            if (mess != null) mess2 = decrypt.doFinal(mess);
            byte[] permis2 = null;
            if (permis != null) permis2 = decrypt.doFinal(permis);
            byte[] signature2 = null;
            if (signature != null) signature2 = decrypt.doFinal(signature);
            byte[] pays2 = null;
            if (pays != null) pays2 = decrypt.doFinal(pays);
            byte[] clésession2 = null;
            if (clésession != null) clésession2 = decrypt.doFinal(clésession);
            byte[] cléhmac2 = null;
            if (cléhmac != null) cléhmac2 = decrypt.doFinal(cléhmac);
            
            String typetmp = new String(typeReq2);
            String messtmp = null;
            if (mess2 != null) messtmp = new String(mess2);
            String signaturetmp = null;
            if (signature2 != null) signaturetmp = new String(signature2);
            String permistmp = null;
            if (permis2 != null) permistmp = new String(permis2);
            String paystmp = null;
            if (pays2 != null) paystmp = new String(pays2);
            String clésessiontmp = null;
            if (clésession2 != null) clésessiontmp = new String(clésession2);
            String cléhmactmp = null;
            if (cléhmac2 != null) cléhmactmp = new String(cléhmac2);
            
            System.out.println("Type décrypté : " + typetmp);
            type = Integer.parseInt(typetmp);
            System.out.println("Mess décrypté : " + messtmp);
            String1 = messtmp;
            System.out.println("Pays décrypté : " + paystmp);
            Pays = paystmp;
            System.out.println("Permis décrypté : " + permistmp);
            Permis = permistmp;
            System.out.println("Clé de session décryptée : " + clésessiontmp);
            SessionKey = clésessiontmp;
            System.out.println("clé HMAC décryptée : " + signaturetmp);
            HMACKey = cléhmactmp;
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}


