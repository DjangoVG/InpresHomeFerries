package RequeteVERIFID;

import Authentification.VerificateurUsersPasswordHash;
import RequeteReponseMultiThread.*;
import java.net.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur_compagnie.ClientsList;
import Outils.*;
import RequeteVERIFIDS.ReponseVERIFIDS;
import RequeteVERIFIDS.RequeteVERIFIDS;
import SecurityCrypto.Certif;
import SecurityCrypto.KeyStoreClass;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLSocket;
        
public class RequeteVERIFID implements Requete, Serializable
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int type;
    private Statement instruct;
    private Connection ConnexionBDFrontiereInternationale;
    private LibrairieJDBC LibJDBC;
    private String String1;
    private String String2;
    private Properties propServeurFrontieresInternationale;
    private byte[] bytType;
    private byte[] bytText;
    private String Pays;
    private byte[] bytSignature;
    private byte[] bytCléHMAC;
    private byte[] bytPays;
    private byte[] bytPermis;
    private byte[] bytcleSecrete;
    private Certif certificat;
    private String Permis;
    private String SessionKey;
    private String HMACKey;
    private String PlaqueImmatriculation;
    private Boolean Test;
    private Boolean CheckSignature;
    private Certif cer;
    private KeyStoreClass keystore;
    private KeyStore ks;
    private String NumeroNational;
    private String Nom;
    private String Prenom;
    private String DateNaissance;
    private String DateMaxValiditeIdentite;
    private String NuméroPermis;
    private String DateMaxValiditePermis;
    private byte[] encryptedMessage;
    private KeyStoreClass MyKeyStore;
    private int TailleBlocCrypte;
    public RequeteVERIFID(byte [] bytReq, byte [] bytReqIdentite, byte [] bytReqPays, byte [] bytReqPermis, byte [] bytReqcléSecr, byte [] bytReqCléHMAC, byte [] bytReqSignature)
    {
        this.bytType = bytReq; this.bytText = bytReqIdentite; this.bytPermis = bytReqPermis; this.bytcleSecrete = bytReqcléSecr; this.bytCléHMAC = bytReqCléHMAC; this.bytPays = bytReqPays;
        this.bytSignature = bytReqSignature;
        LibJDBC = new LibrairieJDBC();
        propServeurFrontieresInternationale = LibJDBC.RecupPropertiesFrontieresInternationale();
    }

    public RequeteVERIFID(int type, String PlaqueImmatriculatio, byte [] signature) {
        this.type = type;
        this.PlaqueImmatriculation = PlaqueImmatriculatio;
        this.bytSignature = signature;
        LibJDBC = new LibrairieJDBC();
        propServeurFrontieresInternationale = LibJDBC.RecupPropertiesFrontieresInternationale();
    }
    
    public int getCode()
    {
        return type;
    }
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, ObjectInputStream ois, ObjectOutputStream oos) 
    {
        this.ois = ois;
        this.oos = oos;
        CheckSignature = false;
        Boolean ok = false;
        if (this.type == 30)
            CheckSignature = VerifSignature();
        else
            ok = DecryptCode(this.bytType,this.bytText, this.bytPermis, this.bytPays, this.bytSignature, this.bytcleSecrete, this.bytCléHMAC);
        
            
        if (ok || CheckSignature)
        {
            switch (type)
            {
                case 30:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteListingVehiculesInternational(SocketClient);
                        }
                    };    
                case 4:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteListingIdentiteInternational(SocketClient);
                        }
                    };  
                case 5:
                    return new Runnable()
                    {
                        @Override
                        public void run() {
                            traiteRequeteListingPassagerIdentiteInternational(SocketClient);
                        }
                    };  
            }            
        }
        return null;
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
            
            Test = s.verify(bytSignature);
            System.out.println("Boolean après test : " + Test);
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
            certificat = new Certif();
            System.out.println("Obtention de le clé publique du garde");
            X509Certificate certif = certificat.getCertif("serveurfrontieres.cer");
            
            PublicKey clePublic = certif.getPublicKey();
            System.out.println(" *** Cle public recuperee = " + clePublic.toString());
            certif.verify(clePublic);
            
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, clePublic);
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
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(SignatureException exs)
        {
            System.out.println("SignatureException : " + exs);
            return false;
        } catch (CertificateException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    
    
    private void traiteRequeteListingIdentiteInternational(Socket SocketClient2)
    {
        try 
        {   
            ReponseVERIFID reponse=null;
            
            getIdentite();
            getPermis();
            if (NumeroNational!= null && NuméroPermis != null)
            {
                byte[] bytNumReg = GenerateCrypt(NumeroNational.getBytes());
                byte[] bytNumPermis = GenerateCrypt(NuméroPermis.getBytes());
                byte[] bytNom = GenerateCrypt(Nom.getBytes());
                byte[] bytPrenom = GenerateCrypt(Prenom.getBytes());
                byte[] bytDateNaissance = GenerateCrypt(DateNaissance.getBytes());
                byte[] bytDateMaxValiditeIdentite = GenerateCrypt(DateMaxValiditeIdentite.getBytes());
                byte[] bytDateMaxValiditePermis = GenerateCrypt(DateMaxValiditePermis.getBytes());
                
                
                reponse = new ReponseVERIFID(ReponseVERIFIDS.LISTEVEHICULES_OK,bytNumReg,bytNumPermis,bytNom,bytPrenom,bytDateNaissance,bytDateMaxValiditeIdentite,bytDateMaxValiditePermis);
            }
            else
            {
                if (NumeroNational == null)
                {
                    reponse = new ReponseVERIFID(ReponseVERIFIDS.LISTNUMNATIONAL_KO);                    
                }   
                else
                {
                    if (NuméroPermis == null)
                    {
                        reponse = new ReponseVERIFID(ReponseVERIFIDS.LISTPERMIS_KO);
                    }
                    else
                        reponse = new ReponseVERIFID(ReponseVERIFIDS.LISTIDENTITEPERMIS_KO);
                }
            }

            oos.writeObject(reponse);
            System.out.println("Après envoie réponse traiteRequeteListingIdentiteInternational");
        }  
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        
    }
    private byte [] GenerateCrypt(byte [] AHashed) {
        
        try
        {
            encryptedMessage = null;
            MyKeyStore = new KeyStoreClass();
            certificat = new Certif();
            KeyStore ks = MyKeyStore.getMyKeyStore();
            System.out.println("Keystore récupéré : " + ks);
            System.out.println("Obtention de le clé privée du serveur inter");
            PrivateKey cléPrivée;
            cléPrivée = (PrivateKey) ks.getKey("serveurinternational", "1234".toCharArray());
            System.out.println(" *** Cle privee recuperee = " + cléPrivée.toString());

            Cipher encrypt=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            encrypt.init(Cipher.ENCRYPT_MODE, cléPrivée);
            encryptedMessage = encrypt.doFinal(AHashed);
            TailleBlocCrypte = encryptedMessage.length;
            System.out.println("JE CRYPTE OK");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedMessage;
    }
    private void traiteRequeteListingPassagerIdentiteInternational(Socket SocketClient2)
    {
        try 
        {   
            ReponseVERIFID reponse=null;
            
            getIdentite();
            
            if (NumeroNational!= null)
            {
                byte[] bytNumReg = GenerateCrypt(NumeroNational.getBytes());
                byte[] bytNom = GenerateCrypt(Nom.getBytes());
                byte[] bytPrenom = GenerateCrypt(Prenom.getBytes());
                byte[] bytDateNaissance = GenerateCrypt(DateNaissance.getBytes());
                byte[] bytDateMaxValiditeIdentite = GenerateCrypt(DateMaxValiditeIdentite.getBytes());
                
                
                reponse = new ReponseVERIFID(ReponseVERIFID.LISTPASSAGER_OK,bytNumReg,bytNom,bytPrenom,bytDateNaissance,bytDateMaxValiditeIdentite);
            }
            else
                reponse = new ReponseVERIFID(ReponseVERIFID.LISTPASSAGER_KO);

            oos.writeObject(reponse);
        }  
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        
    }
    
    private void traiteRequeteListingVehiculesInternational(Socket SocketClient2)
    {
        try 
        {   
            ReponseVERIFID reponse=null;
            
            DefaultTableModel dlm = getTableVehicule();
            cer = new Certif();
                
            keystore = new KeyStoreClass();
            ks = keystore.getMyKeyStore();

            PrivateKey cléPrivée;
            cléPrivée = (PrivateKey) ks.getKey("serveurinternational","1234".toCharArray());

            Signature s = Signature.getInstance("SHA256withRSA","BC");
            s.initSign(cléPrivée);
            s.update(String.valueOf(ReponseVERIFID.LISTEVEHICULES_OK).getBytes());
            byte [] signature2 = s.sign();
            if (dlm.getRowCount() > 0)
                reponse = new ReponseVERIFID(ReponseVERIFID.LISTEVEHICULES_OK, dlm, signature2);
            else
                reponse = new ReponseVERIFID(ReponseVERIFID.LISTEVEHICULES_KO);
            oos.writeObject(reponse);
        }  
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void getIdentite()
    {
        ConnexionBDFrontiereInternationale = null;
        instruct = null;
        DefaultTableModel dlmIdentite = new DefaultTableModel();
        ConnexionBDFrontiereInternationale = LibJDBC.ConnexionToBDInternationale();
        instruct = LibJDBC.CreationStatementFrontieresNationale(ConnexionBDFrontiereInternationale);
        
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
            ConnexionBDFrontiereInternationale.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getPermis()
    {
        ConnexionBDFrontiereInternationale = null;
        instruct = null;
        DefaultTableModel dlmPermis = new DefaultTableModel();
        ConnexionBDFrontiereInternationale = LibJDBC.ConnexionToBDInternationale();
        instruct = LibJDBC.CreationStatementFrontieresNationale(ConnexionBDFrontiereInternationale);
        
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
            ConnexionBDFrontiereInternationale.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteVERIFIDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public DefaultTableModel getTableVehicule()
    {
        //ConnexionBDCompagnie = null;
        instruct = null;
        DefaultTableModel modelReservations = new DefaultTableModel();
        ConnexionBDFrontiereInternationale = LibJDBC.ConnexionToBDInternationale();
        instruct = LibJDBC.CreationStatementFrontieresNationale(ConnexionBDFrontiereInternationale);
        try 
        {
            ResultSet rs = instruct.executeQuery("SELECT * FROM vehicule where Id_Immatriculation = '"+this.PlaqueImmatriculation+"';");
            
            System.out.println("Instruction SELECT sur vehicule internationaux envoyée à Home-Ferries's DataBase");
            
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
                System.out.println("1 véhicule trouvé");
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
            ConnexionBDFrontiereInternationale.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequeteVERIFID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelReservations;
    }  

    public Runnable createRunnable(Socket CliSock, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Runnable createRunnable(SSLSocket SocketClient, ClientsList ListClients, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


