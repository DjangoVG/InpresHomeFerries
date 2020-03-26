package RequeteReponseEBOOP;

import Outils.FichierLog;
import RequeteReponseMultiThread.*;
import java.net.*;
import java.io.*;
import serveur_compagnie.ClientsList;
import Outils.*;
import RequeteReponseCHECKCARD.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur_compagnie.FenServer;
import RequeteReponseCINAP.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.itextpdf.text.pdf.*;
import java.text.DateFormat;
import javax.net.ssl.SSLSocket;

        
public class RequeteEBOOP implements Requete, Serializable
{
    public static int REQUEST_PAYMENT = 1;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int type;
    private String NumCarte;
    private String NomClient;
    private String PrenomClient;
    private String IdTraversee;
    private int PrixCommande;
    private LibrairieJDBC LibJDBC;
    private Properties propServeurCartes;
    private Properties propServeurCompagnie;
    private Properties propServeurEmbarquement;
    private int NumClient;
    private String NumReservation;
    private Statement instructEmbarquements;
    private String email;
    private Object MailEnvoyeur;
    private Object MailDestinataire;
    private Object PasswordEnvoyeur;
    private Object ObjetMail;
    private Object ContenuMail;
    private MimeBodyPart mbp;
    private File fichierTemp;
    private PdfWriter PdfWriter;
    private int matriculeFerry;
    private String NomFerry;
    private String dateheure;
    private String NumRes;
    private String DateS;
    private PdfPTable table;
    private String PortDepart;
    private String PortArrivee;
    private PdfPCell cell;
    
    public RequeteEBOOP(int t,String NumCarte,String NomClient, String PrenomClient, int PrixCommande,String IdTraversee,int numClient, String email)
    {
        System.out.println("Passage constructeur EBOOP");
        type = t;
        this.email = email;
        this.NumCarte = NumCarte; this.PrixCommande = PrixCommande; this.NomClient = NomClient; this.PrenomClient = PrenomClient; this.IdTraversee = IdTraversee; this.NumClient=numClient;
        System.out.println("Avant constructeur LIBJDBC");
        LibJDBC = new LibrairieJDBC();
        System.out.println("Après constructeur LIBJDBC");
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        System.out.println("Fin Passage constructeur EBOOP");
    }
    public RequeteEBOOP(int t, String IdTraversee)
    {
        System.out.println("Passage constructeur EBOOP pour Place");
        type = t;
        this.IdTraversee = IdTraversee;
        LibJDBC = new LibrairieJDBC();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        propServeurEmbarquement = LibJDBC.RecupPropertiesEmbarquements();
        System.out.println("Fin Passage constructeur EBOOP Place");
    }
    
    public int getCode()
    {
        return type;
    }
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ObjectInputStream ois, ObjectOutputStream oos) 
    {
        System.out.println("Passage createRunnable EBOOP");
        this.ois = ois;
        this.oos = oos;
        switch (type)
        {
            
            case 0:
                return new Runnable()
                {
                    @Override
                    public void run() {
                      requetePayment(SocketClient);
                    }
                };
            case 1:
                return new Runnable()
                {
                    @Override
                    public void run() {
                      requetePlaces(SocketClient);
                    }
                };
                
            case 2:
                return new Runnable()
                {
                    @Override
                    public void run() {
                      requeteVidePanier(SocketClient);
                    }
                };
        }
        return null;
    }
    
    public synchronized void requetePayment(Socket SocketClient)
    {
        ReponseEBOOP reponse=null;
        NumRes = null;
        dateheure = null;
        NomFerry = null;
        Connection connexionCompagnie = LibJDBC.ConnexionToBDCompagnie();
        Connection connexionCards = LibJDBC.ConnexionToBDCartes();
        int compteurReservation = 0;
        Statement instruct = null;
        Statement instruct4 = null;
        Statement instruct2 = null;
        Statement instruct3 = null;
        instruct = LibJDBC.CreationStatementCompagnie(connexionCompagnie);
        instruct4 = LibJDBC.CreationStatementCompagnie(connexionCompagnie);
        instruct2 = LibJDBC.CreationStatementCompagnie(connexionCompagnie);
        instruct3 = LibJDBC.CreationStatementCartes(connexionCards);
        try 
        {
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

            preparedStmt.setString(2, IdTraversee);
            preparedStmt.setString(3, String.valueOf(NumClient));
            preparedStmt.setInt(4, 0);
            preparedStmt.setInt(5, 0);
            System.out.println("nom : " + NomClient);
            System.out.println("Prenom : " + PrenomClient);
            System.out.println("NumClient : " + NumClient);
            rs = instruct2.executeQuery("SELECT * FROM clients WHERE NomClient = '"+NomClient+"' AND PrenomClient= '"+PrenomClient+"' AND NumClient = '"+String.valueOf(NumClient)+"';");
            
            if (rs.first() == true)
            {
                ResultSet rs2 = instruct.executeQuery("SELECT NewUserPromo from clients where NumClient = '"+String.valueOf(NumClient)+"' AND NewUserPromo =0;");
                if(rs2.first()==true)
                {
                    System.out.println("-------- Réduc de nouveau client de 5% --------------");
                    PrixCommande = PrixCommande - ((PrixCommande *5)/100);
                    String queryNewUserPromo = "UPDATE `clients` SET `NewUserPromo` = 1 WHERE `NumClient` = '"+String.valueOf(NumClient)+"';";
                    PreparedStatement preparedStmtNewUserPromo;
                    preparedStmtNewUserPromo = connexionCompagnie.prepareStatement(queryNewUserPromo);
                    preparedStmtNewUserPromo.execute();
                    System.out.println("Maj du NewUserPRomo");
                }
                System.out.println("Je rentre dans le if");
                Socket SocketCartes = new Socket(propServeurCartes.getProperty("adresse"), Integer.valueOf((String) propServeurCartes.getProperty("portEcoute")));
                System.out.println("100");
                ObjectOutputStream oosCartes = new ObjectOutputStream(SocketCartes.getOutputStream());
                System.out.println("101");
                ObjectInputStream oisCartes = new ObjectInputStream(SocketCartes.getInputStream());
                
                System.out.println("Avant new RequeteCheckcard");
                RequeteCHECKCARD requeteCartes = new RequeteCHECKCARD(0, NumCarte, NomClient, PrenomClient,PrixCommande);
                oosCartes.writeObject(requeteCartes);
                System.out.println("après new RequeteCheckcard");
                
                ReponseCHECKCARD reponseCartes = (ReponseCHECKCARD)oisCartes.readObject();
                System.out.println("après readobject RequeteCheckcard");
                if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_ACHAT_OK)
                {
                    
                    synchronized (this)
                    {
                        System.out.println("J'insere dans la BDD la nouvelle RES");
                        preparedStmt.execute();
                        String queryValidatePayment = "UPDATE `reservations` SET `Payer` = 1 WHERE `IdReservations` = '"+NumRes+"';";
                        PreparedStatement preparedStmtPayer;
                        preparedStmtPayer = connexionCompagnie.prepareStatement(queryValidatePayment);
                        preparedStmtPayer.execute();
                        
                        rs = instruct2.executeQuery("SELECT * FROM traversees INNER JOIN reservations ON traversees.IdTraversees = reservations.IdTraversee AND traversees.IdTraversees = '"+IdTraversee+"';");
                        matriculeFerry=0;
                        PortDepart = null;
                        PortArrivee = null;
                        if (rs.first() == true)
                        {
                            matriculeFerry = rs.getInt(5);
                            System.out.println("MATRCICUE : " + matriculeFerry);
                            PortDepart = rs.getString(3);
                            PortArrivee = rs.getString(4);
                        }
                        
                        rs = instruct2.executeQuery("SELECT navires.Nom FROM navires INNER JOIN traversees ON navires.Matricule = traversees.Matricule AND navires.Matricule = "+matriculeFerry+";");
                        if (rs.first() == true)
                            NomFerry = rs.getString(1);
                    
                        
                        rs = instruct2.executeQuery("SELECT DateDepart FROM traversees WHERE IdTraversees = '"+IdTraversee+"';");
                        DateS = null;
                        if (rs.first() == true)
                            DateS = rs.getString(1);
                        
                        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss", Locale.ENGLISH);
                        Date parsedDate = sdf.parse(DateS);
                        SimpleDateFormat print = new SimpleDateFormat("HH:mm:ss");
                        dateheure = print.format(parsedDate);
                        
                    }
                    EnvoieMessage();
                    reponse = new ReponseEBOOP(ReponseEBOOP.PAYEMENTOK, NumRes);
                }
                if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_CARTENONTROUVE)
                {
                    reponse = new ReponseEBOOP(ReponseEBOOP.PAYEMENTKO_CARTENOTFOUND);
                }
                if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_DEBITIMPOSSIBLE)
                {
                    reponse = new ReponseEBOOP(ReponseEBOOP.PAYEMENTKO_SOLDETOOLOW);
                }
                if (reponseCartes.getCode() == ReponseCHECKCARD.ACHATTICKET_DEBITROPELEVE)
                {
                    reponse = new ReponseEBOOP(ReponseEBOOP.PAYEMENTKO_DEBITTOOMUCH);
                }
            }
            else            
                reponse = new ReponseEBOOP(ReponseEBOOP.PAYEMENTKO_CLIENTNOTFOUND);
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
    
    public synchronized void requetePlaces(Socket SocketClient)
    {
        ResultSet rs;
        int NumFerry = 0, NbFiles = 0;
        ReponseEBOOP reponse=null;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements);
        try {
            rs = instructEmbarquements.executeQuery("SELECT traversees.Matricule FROM traversees WHERE traversees.IdTraversees = '"+ this.IdTraversee+ "';");
            System.out.println("Instruction SELECT sur traversees avec inner join envoyée à Home-Ferries's DataBase");

            while (rs.next())
                NumFerry = rs.getInt(1);

            rs = instructEmbarquements.executeQuery("SELECT count(*) from files where files.Matricule = '"+NumFerry+"';");

            if (rs.first() == true)
                NbFiles = rs.getInt(1);

            boolean bool = false;
            int i = 1;
            for (i=1; i <= NbFiles && bool == false; i++)
            {
                rs = instructEmbarquements.executeQuery("SELECT CapaciteActuelFile from files where Matricule = '"+NumFerry+ "' AND NumFile = '"+i+"'");
                int CapaciteFile = 0;
                if (rs.next())
                    CapaciteFile = rs.getInt(1);

                rs = instructEmbarquements.executeQuery("SELECT CapaciteMaxFile from files where Matricule = '"+NumFerry+ "' AND NumFile = '"+i+"'");
                int CapaciteMaxFile = 0;
                if (rs.next())
                    CapaciteMaxFile = rs.getInt(1);                    

                if (CapaciteFile < CapaciteMaxFile)
                {
                    PreparedStatement preparedStmtUpdate;
                    preparedStmtUpdate = connexionEmbarquements.prepareStatement("UPDATE `files` SET `CapaciteActuelFile`= `CapaciteActuelFile` +1  WHERE `files`.`NumFile` = "+i+" AND `files`.`Matricule` = '"+NumFerry+"';");
                    preparedStmtUpdate.execute();
                    System.out.println("Update effectué");
                    bool = true; i--;
                }
            }
            if (bool == true)
            {
                reponse = new ReponseEBOOP(ReponseEBOOP.VERIFFILE_OK, i);                
            }
            else
                reponse = new ReponseEBOOP(ReponseEBOOP.VERIFFILE_FERRYCOMPLET);
  
            
        oos.writeObject(reponse);
        } 
        catch (IOException | SQLException e) 
        {
            System.out.println("Erreur : " + e);
        }
        finally 
        {
            try {
                connexionEmbarquements.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public synchronized void requeteVidePanier(Socket SocketClient)
    {
        System.out.println("RequeteVidePANIER !!!");
        ResultSet rs;
        int NumFerry = 0, NbFiles = 0, i;
        ReponseEBOOP reponse=null;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements);
        try {
            rs = instructEmbarquements.executeQuery("SELECT traversees.Matricule FROM traversees WHERE traversees.IdTraversees = '"+ this.IdTraversee+ "';");
            System.out.println("Instruction SELECT sur traversees avec inner join envoyée à Home-Ferries's DataBase");

            if (rs.first())
                NumFerry = rs.getInt(1);

            rs = instructEmbarquements.executeQuery("SELECT count(*) from files where files.Matricule = '"+NumFerry+"';");

            if (rs.first())
                NbFiles = rs.getInt(1);
            boolean bool = false;
            for (i=1; i <= NbFiles && bool == false; i++)
            {
                rs = instructEmbarquements.executeQuery("SELECT CapaciteActuelFile from files where Matricule = '"+NumFerry+ "' AND NumFile = '"+i+"'");
                int CapaciteFile = 0;
                if (rs.next())
                    CapaciteFile = rs.getInt(1);

                rs = instructEmbarquements.executeQuery("SELECT CapaciteMaxFile from files where Matricule = '"+NumFerry+ "' AND NumFile = '"+i+"'");
                int CapaciteMaxFile = 0;
                if (rs.next())
                    CapaciteMaxFile = rs.getInt(1);                    

                if (CapaciteFile != CapaciteMaxFile)
                {
                    PreparedStatement preparedStmtUpdate;
                    preparedStmtUpdate = connexionEmbarquements.prepareStatement("UPDATE `files` SET `CapaciteActuelFile`= `CapaciteActuelFile` -1  WHERE `files`.`NumFile` = "+i+" AND `files`.`Matricule` = '"+NumFerry+"';");
                    preparedStmtUpdate.execute();
                    System.out.println("Update effectué");
                    bool = true; i--;
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Erreur : " + e);
        }
        finally 
        {
            try {
                connexionEmbarquements.close();
            } catch (SQLException e) {
            }
        }
    }
    
    private void EnvoieMessage () 
    {
        String Envoyeur = "rtijavamail@gmail.com";
        String Destinataire = email;
        
        String password = "rtijavamail12";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("file.encoding", "iso-8859-1");
        
        Session session = Session.getInstance(prop,new javax.mail.Authenticator()
        {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(Envoyeur,password);
            }
        });
        session.setDebug(true);

        try
        {
            Date maintenant = new Date ();
            String d = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(maintenant);
            System.out.println("Création du message");
                
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom (new InternetAddress(Envoyeur));
            msg.setRecipient (Message.RecipientType.TO, new InternetAddress (Destinataire));
            msg.setSubject("Home-Ferries - Confirmation de votre achat !");
            //Récupérer tout le message Text
            Multipart msgMP = new MimeMultipart();
            MimeBodyPart msgBP = new MimeBodyPart();
            msgBP.setText("Bonjour,\n\nNous vous remercions pour votre achat !\n\nLa facture est en pièce jointe attachée à ce mail.\n\nNous vous souhaitons une agréable traversée et nous nous réjouissons de vous revoir !\n\nCordialement,\n\nL'équipe Home-Ferries");
            msgMP.addBodyPart(msgBP);
            String repertoireCourant = System.getProperty("user.dir");
            String separateur = System.getProperty("file.separator");
            
            Document document = new Document();
            Random rand = new Random(10000);
            PdfWriter.getInstance(document, new FileOutputStream(repertoireCourant + separateur + "Facture" + separateur + "Facture_" + NomClient + NumClient + ".pdf"));
           
            document.open();
            
            Paragraph p = new Paragraph();
            p.add("Numéro du client : " + NumClient);

            document.add(p);
            
            Paragraph p2 = new Paragraph();
            p2.add("Date de la facture : " + d);

            document.add(p2);
            
            
            
            table = new PdfPTable(6);
            cell = null;
            cell = new PdfPCell(new Phrase ("Numéro de réservation"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase ("Date de départ"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase ("Port de départ"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase ("Port d'arrivée"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase ("Navire"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase ("Prix"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase (NumRes));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase (DateS));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase (PortDepart));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase (PortArrivee));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase (String.valueOf(matriculeFerry)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase (String.valueOf(PrixCommande)));
            table.addCell(cell);

            
            
            
            
            document.add(table);
            document.close();
            msgBP = new MimeBodyPart();
            DataSource so = new FileDataSource (repertoireCourant + separateur + "Facture" + separateur + "Facture_" + NomClient + NumClient + ".pdf");
            msgBP.setDataHandler (new DataHandler (so));
            msgBP.setFileName("Facture" + NomClient + ".pdf");
            msgMP.addBodyPart(msgBP);

            System.out.println("Envoi du message");
            
            /* Insertion du nom de la facture dans la bd */
            
            Connection connexionCompagnie = LibJDBC.ConnexionToBDCompagnie();
            PreparedStatement preparedStmtUpdate;
            preparedStmtUpdate = connexionCompagnie.prepareStatement("UPDATE `reservations` SET `NumFacture`= 'Facture_" + NomClient + NumClient + "' WHERE `reservations`.`IdReservations` = '" + NumRes +"';");
            preparedStmtUpdate.execute();
            
            msg.setContent(msgMP);
            Transport.send(msg);
            System.out.println("Message envoyé");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RequeteEBOOP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(RequeteEBOOP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RequeteEBOOP.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

     
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, FichierLog fichierLog, FenServer fenetreServer, ObjectInputStream ois, ObjectOutputStream oos) {
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

