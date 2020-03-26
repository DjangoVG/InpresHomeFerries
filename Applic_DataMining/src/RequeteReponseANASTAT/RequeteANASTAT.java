
package RequeteReponseANASTAT;

import Authentification.VerificateurUsersPasswordHash;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseMultiThread.Requete;
import Serveur.ClientsList;
import Serveur.Serveur_DataMining;
import guis.FenetreRequete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import org.rosuda.REngine.*;
import org.rosuda.REngine.Rserve.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class RequeteANASTAT implements Requete,Serializable {
    
    static final int REQUETE_LOGIN = 0 ;
    static final int REQUETE_A = 1 ;
    static final int REQUETE_B = 2 ;
    static final int REQUETE_C = 3 ;
    static final int REQUETE_D = 4 ;
    static final int REQUETE_E = 5 ;
    static final int REQUETE_F = 6 ;
    static final int REQUETE_G = 7 ;
    String query = "INSERT INTO `bd_aide_decision`.`donnees` (`Id`,`PValue`, `Difference`, `NumRequete`) values (null, ?, ?, ?)";
    PreparedStatement preparedStmt = null;
    String s ;
    String MessInter ;
    private int compteurReservation;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int type;
    private String messRequete;
    private Socket cliSock;
    private Properties propServeurCompagnie;
    private Connection ConnexionBDCompagnie;
    private Connection ConnexionBDAideDecision ;
    private LibrairieJDBC LibJDBC;
    private String String1;
    private String String2;
    private VerificateurUsersPasswordHash Sub;
    private int code;
    private Statement instruct;
    private Statement instruct2;
    private RConnection c = null ;
    private REXP x = null ;
    private int diff = 0 ;
    public RequeteANASTAT(int t)
    {
        type = t;
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
    }
    
    public RequeteANASTAT(int t, String NumTraverseeParametre, String PrenomConducteurParametre, String NomConducteurParametre, String NumImmatriculationParametre, String NbPassagersParametre, String NumCarteCreditParametre)
    {
        type = t;
        LibJDBC = new LibrairieJDBC(); 
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
    }
    
    public RequeteANASTAT(int t, String s1, String s2)
    {
        type = t; String1 = s1; String2 = s2;
        LibJDBC = new LibrairieJDBC(); propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
    }
    
    public RequeteANASTAT(int t, String s1)
    {
        type = t; String1 = s1;
        LibJDBC = new LibrairieJDBC(); propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
    }
    
    public int getCode()
    {
        return type;
    }
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, Serveur_DataMining fenetreServer, ObjectInputStream ois, ObjectOutputStream oos) {
        this.ois = ois;
        this.oos = oos;
        switch (type)
        {
            case 0:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteConnexion(SocketClient, ListClients, fenetreServer);
                    }
                };
            case 1:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteA(SocketClient);
                    }
                };
            case 2:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteB(SocketClient);
                    }
                };
            case 3:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteC(SocketClient);
                    }
                };
            case 4:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteD(SocketClient);
                    }
                };
            case 5:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteE(SocketClient);
                    }
                };
            case 6:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteF(SocketClient);
                    }
                };
            case 7:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        traiteRequeteG(SocketClient);
                    }
                };
        }
        return null;
    }
    public int Interpretationrequete(int NumRequete,double pvalue)
    {
        try {
            preparedStmt = ConnexionBDAideDecision.prepareStatement(query);
        
        preparedStmt.setDouble(1, pvalue);
        if(pvalue > 0.05)
        {
            diff = 1;
            preparedStmt.setInt(2, diff);
            MessInter = pvalue + "\n" + "Il y a une différence significative !";
        }
        else
        {
            diff = 0;
            preparedStmt.setInt(2, diff);
            MessInter = pvalue + "\n" + "Il n'y a pas de différence significative !";
        }

        preparedStmt.setInt(3, NumRequete);
        synchronized(this)
        {
            int rowCount = preparedStmt.executeUpdate();
        }
        }
        catch (SQLException ex) {
            System.out.println("Erreur : " + ex.getMessage() + "Etat : " + ex.getSQLState());
        }
        preparedStmt = null;
        return diff;
    }
    private void traiteRequeteA(Socket SocketClient)
    {
        ReponseANASTAT reponse = null;
        ConnexionBDAideDecision = null;
        instruct = null;
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
        try 
        {
            c = new RConnection();
            c.voidEval("install.package(RMySQL)");
            c.voidEval("library(RMySQL)");
            c.voidEval("DB <- dbConnect(MySQL(), user=\"root\", host=\"localhost\",password=\"\", dbname=\"bd_ferries\")");
            c.voidEval("Requete <- \"SELECT count(IdReservations) as NbReservations,traversees.IdTraversees from reservations inner join traversees on reservations.IdTraversee = traversees.IdTraversees\n" +
"            where reservations.AnneeReservations = YEAR(NOW()) group by traversees.IdTraversees,reservations.AnneeReservations\"");
            c.voidEval("ResQuery <- dbGetQuery(DB, Requete)");
            
            c.voidEval("Reservation <- ResQuery[,1]");
            c.voidEval("Traversee <- ResQuery[,2]");
            System.out.println("Après InitVaraible");
            
            
            c.voidEval("modele <- lm(Reservation~Traversee)");
            System.out.println("Après modele");
            
            
            System.out.println("Avant lmp fonction");
            /**Fonction pour récupérer la pvalue du modèlen, (utiliser summary(modele)$coefficients[,4] pour avoir la pvalue des coefficients**/
            c.voidEval("lmp <- function (modelobject) {\n" +
                        "  if (class(modelobject) != \"lm\") stop(\"Not an object of class 'lm' \")\n" +
                        "  f <- summary(modele)$fstatistic\n" +
                        "  p <- pf(f[1],f[2],f[3],lower.tail=F)\n" +
                        "  attributes(p) <- NULL\n" +
                        "  return(p)\n" +
                        "}");
            String s1=c.eval("paste(capture.output(print(lmp(modele))),collapse='\\n')").asString(); //Afficher le summary
            System.out.println(s1);
            String s = c.eval("lmp(modele)").asString();
            
            
            System.out.println(s);
            double x = Double.parseDouble(s) ;
            int difference ;
            difference = Interpretationrequete(REQUETE_A,x);
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_A_OK,s,difference);
            
        } 
        catch (RserveException ex) {
               System.out.println("erreur RserveException: " + ex.getMessage());
               reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_A_KO);
        } catch (REXPMismatchException ex) {
            System.out.println("erreur RexpMismatchException : " + ex.getMessage());
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_A_KO);
        }

        try
        {
            c.close();
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }            
    }
    
    private void traiteRequeteB(Socket SocketClient)
    {
        ReponseANASTAT reponse = null;
        ConnexionBDAideDecision = null;
        instruct = null;
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
        try 
        {
            c = new RConnection();
            //c.voidEval("install.packages(RMySQL)");
            c.voidEval("library(RMySQL)");
            c.voidEval("DB <- dbConnect(MySQL(), user=\"root\", host=\"localhost\",password=\"\", dbname=\"bd_ferries\")");
            c.voidEval("Requete <- \"SELECT NbrVoyageur,traversees.IdTraversee from reservations inner join traversees on reservations.IdTraversee = traversees.IdTraversees\n" +
"                             group by traversees.IdTraversees,reservations.NbrVoyageur\"");
            c.voidEval("ResQuery <- dbGetQuery(DB, Requete)");
            
            c.voidEval("Voyageur <- ResQuery[,1]");
            c.voidEval("Traversee <- ResQuery[,2]");
            
            
            c.voidEval("modele <- lm(Voyageur~Traversee)");
            
            /**Fonction pour récupérer la pvalue du modèlen, (utiliser summary(modele)$coefficients[,4] pour avoir la pvalue des coefficients**/
            c.voidEval("lmp <- function (modelobject) {\n" +
                        "  if (class(modelobject) != \"lm\") stop(\"Not an object of class 'lm' \")\n" +
                        "  f <- summary(modele)$fstatistic\n" +
                        "  p <- pf(f[1],f[2],f[3],lower.tail=F)\n" +
                        "  attributes(p) <- NULL\n" +
                        "  return(p)\n" +
                        "}");
            s = c.eval("lmp(modele)").asString();
            
            
            System.out.println(s);
            double x = Double.parseDouble(s) ;
            int difference ;
            difference = Interpretationrequete(REQUETE_B,x);
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_B_OK,s,difference);
            
        } 
        catch (RserveException ex) {
               System.out.println("erreur RserveException: " + ex.getMessage());
               reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_B_KO);
        } catch (REXPMismatchException ex) {
            System.out.println("erreur RexpMismatchException : " + ex.getMessage());
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_B_KO);
        }

        try
        {
            c.close();
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }           
    }
    
    private void traiteRequeteC(Socket SocketClient)
    {
        ReponseANASTAT reponse = null;
        
        ConnexionBDAideDecision = null;
        instruct = null;
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
        try 
        {
            c = new RConnection();
            c.voidEval("library(RMySQL)");
            c.voidEval("DB <- dbConnect(MySQL(), user=\"root\", host=\"localhost\",password=\"\", dbname=\"bd_ferries\")");
            c.voidEval("Requete <- \"SELECT reservations.NbrVoyageur,voyageurs.Age,reservations.IdReservations from reservations,voyageurs where reservations.NumeroClient = voyageurs.NumeroClient\n" +
"            order by voyageurs.Age asc \"");
            c.voidEval("ResQuery <- dbGetQuery(DB, Requete)");
            
            c.voidEval("NbrVoyageurs <- ResQuery[,1]");
            c.voidEval("AgeVoyageurs <- ResQuery[,2]");
            
            
            c.voidEval("modele <- lm(NbrVoyageurs~AgeVoyageurs)");
            
            /**Fonction pour récupérer la pvalue du modèle, (utiliser summary(modele)$coefficients[,4] pour avoir la pvalue des coefficients**/
            c.voidEval("lmp <- function (modelobject) {\n" +
                        "  if (class(modelobject) != \"lm\") stop(\"Not an object of class 'lm' \")\n" +
                        "  f <- summary(modele)$fstatistic\n" +
                        "  p <- pf(f[1],f[2],f[3],lower.tail=F)\n" +
                        "  attributes(p) <- NULL\n" +
                        "  return(p)\n" +
                        "}");
            s = c.eval("lmp(modele)").asString();
            
            
            System.out.println(s);
            double x = Double.parseDouble(s) ;
            int difference ;
            difference = Interpretationrequete(REQUETE_C,x);
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_C_OK,s,difference);
            
        } 
        catch (RserveException ex) {
               System.out.println("erreur RserveException: " + ex.getMessage());
               reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_C_KO);
        } catch (REXPMismatchException ex) {
            System.out.println("erreur RexpMismatchException : " + ex.getMessage());
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_C_KO);
        }

        try
        {
            c.close();
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }                     
    }
    
    private void traiteRequeteD(Socket SocketClient)
    {
        ReponseANASTAT reponse = null;
        ConnexionBDAideDecision = null;
        instruct = null;
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
        try 
        {
            c = new RConnection();
            c.voidEval("library(RMySQL)");
            c.voidEval("DB <- dbConnect(MySQL(), user=\"root\", host=\"localhost\",password=\"\", dbname=\"bd_ferries\")");
            c.voidEval("Requete <- \"SELECT traversees.PortArrivee, traversees.MomentDepart from traversees order by MomentDepart\"");
            c.voidEval("ResQuery <- dbGetQuery(DB, Requete)");
            
            c.voidEval("Destination <- ResQuery[,1]");
            c.voidEval("MomentDep <- ResQuery[,2]");
            
            c.voidEval("Dest_MOM <- ResQuery[,c(1,2)]");
            c.voidEval("tabCont <- table(Dest_MOM)");
            
            
            c.voidEval("tchi2 <- chisq.test(tabCont)");
            s = c.eval("tchi2$p.value").asString();
            
            System.out.println(s);
            double x = Double.parseDouble(s) ;
            int difference ;
            difference = Interpretationrequete(REQUETE_D,x);
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_D_OK,s,difference);
            
        } 
        catch (RserveException ex) {
               System.out.println("erreur RserveException: " + ex.getMessage());
               reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_D_KO);
        } catch (REXPMismatchException ex) {
            System.out.println("erreur RexpMismatchException : " + ex.getMessage());
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_D_KO);
        }

        try
        {
            c.close();
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }           
    }
    
    private void traiteRequeteE(Socket SocketClient)
    {
        ReponseANASTAT reponse = null;
        ConnexionBDAideDecision = null;
        instruct = null;
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
        try 
        {
            c = new RConnection();
            c.voidEval("library(RMySQL)");
            c.voidEval("DB <- dbConnect(MySQL(), user=\"root\", host=\"localhost\",password=\"\", dbname=\"bd_ferries\")");
            c.voidEval("Requete <- \"SELECT Voyageurs.Age,traversees.PortArrivee,reservations.IdReservations from Voyageurs,reservations,traversees where Voyageurs.NumeroClient = reservations.NumeroClient\n" +
"and reservations.IdTraversee = traversees.IdTraversees order by traversees.PortArrivee,Voyageurs.Age\"");
            c.voidEval("ResQuery <- dbGetQuery(DB, Requete)");
            
            c.voidEval("AgeVoyageurs <- ResQuery[,1]");
            c.voidEval("destinationtrav <- ResQuery[,2]");
            c.voidEval("AgeVoyageurs <- as.numeric(gsub(\"\\\\.\", \"\", AgeVoyageurs))");
            
            c.voidEval("modele <- lm(AgeVoyageurs~destinationtrav)");
            
            /**Fonction pour récupérer la pvalue du modèlen, (utiliser summary(modele)$coefficients[,4] pour avoir la pvalue des coefficients**/
            c.voidEval("lmp <- function (modelobject) {\n" +
                        "  if (class(modelobject) != \"lm\") stop(\"Not an object of class 'lm' \")\n" +
                        "  f <- summary(modele)$fstatistic\n" +
                        "  p <- pf(f[1],f[2],f[3],lower.tail=F)\n" +
                        "  attributes(p) <- NULL\n" +
                        "  return(p)\n" +
                        "}");
            s = c.eval("lmp(modele)").asString();
            
            
            System.out.println(s);
            double x = Double.parseDouble(s) ;
            int difference ;
            difference = Interpretationrequete(REQUETE_E,x);
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_E_OK,s,difference);
            
        } 
        catch (RserveException ex) {
               System.out.println("erreur RserveException: " + ex.getMessage());
               reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_E_KO);
        } catch (REXPMismatchException ex) {
            System.out.println("erreur RexpMismatchException : " + ex.getMessage());
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_E_KO);
        }

        try
        {
            c.close();
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }              
    }
    
    private void traiteRequeteF(Socket SocketClient)
    {
        ReponseANASTAT reponse = null;
        ConnexionBDAideDecision = null;
        instruct = null;
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
        try 
        {
            c = new RConnection();
            c.voidEval("library(RMySQL)");
            c.voidEval("DB <- dbConnect(MySQL(), user=\"root\", host=\"localhost\",password=\"\", dbname=\"bd_ferries\")");
            c.voidEval("Requete <- \"SELECT Voyageurs.Age, traversees.portArrivee,traversees.MomentDepart,reservations.IdReservations from Voyageurs,reservations,traversees where Voyageurs.NumeroClient = reservations.NumeroClient\n" +
"and reservations.IdTraversee = traversees.IdTraversee order by traversees.PortArrivee,Voyageurs.Age,traversees.MomentDepart\"");
            c.voidEval("ResQuery <- dbGetQuery(DB, Requete)");
            
            c.voidEval("AgeVoyageurs <- ResQuery[,1]");
            c.voidEval("destinationtrav <- ResQuery[,2]");
            c.voidEval("MomentDep <- ResQuery[,3]");
            
            c.voidEval("modele <- lm(AgeVoyageurs~destinationtrav + MomentDep)");
            
            /**Fonction pour récupérer la pvalue du modèlen, (utiliser summary(modele)$coefficients[,4] pour avoir la pvalue des coefficients**/
            c.voidEval("lmp <- function (modelobject) {\n" +
                        "  if (class(modelobject) != \"lm\") stop(\"Not an object of class 'lm' \")\n" +
                        "  f <- summary(modele)$fstatistic\n" +
                        "  p <- pf(f[1],f[2],f[3],lower.tail=F)\n" +
                        "  attributes(p) <- NULL\n" +
                        "  return(p)\n" +
                        "}");
            s = c.eval("lmp(modele)").asString();

            System.out.println(s);
            double x = Double.parseDouble(s) ;
            int difference ;
            difference = Interpretationrequete(REQUETE_F,x);
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_F_OK,s,difference);
            
        } 
        catch (RserveException ex) {
               System.out.println("erreur RserveException: " + ex.getMessage());
               reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_F_KO);
        } catch (REXPMismatchException ex) {
            System.out.println("erreur RexpMismatchException : " + ex.getMessage());
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_F_KO);
        }

        try
        {
            c.close();
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }                    
    }
    
    private void traiteRequeteG(Socket SocketClient) //***A modifier***//
    {
        ReponseANASTAT reponse = null;
        ConnexionBDAideDecision = null;
        instruct = null;
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
        try 
        {
            c = new RConnection();
            c.voidEval("library(RMySQL)");
            c.voidEval("DB <- dbConnect(MySQL(), user=\"root\", host=\"localhost\",password=\"\", dbname=\"bd_ferries\")");
            c.voidEval("Requete <- \"SELECT voiture.Puissance, voyageurs.Age, reservations.NbrVoyageur from reservations,voiture,voyageurs where reservations.Immatriculation = voiture.Immatriculation and reservations.NumeroClient = voyageurs.NumeroClient\"");
            c.voidEval("ResQuery <- dbGetQuery(DB, Requete)");
            
            c.voidEval("Puissance <- ResQuery[,1]");
            c.voidEval("Age <- ResQuery[,2]");
            c.voidEval("NbrVoyageurs <- ResQuery[,3]");
            
            c.voidEval("modele <- lm(Puissance~Age+NbrVoyageurs)");
            
            /**Fonction pour récupérer la pvalue du modèlen, (utiliser summary(modele)$coefficients[,4] pour avoir la pvalue des coefficients**/
            c.voidEval("lmp <- function (modelobject) {\n" +
                        "  if (class(modelobject) != \"lm\") stop(\"Not an object of class 'lm' \")\n" +
                        "  f <- summary(modele)$fstatistic\n" +
                        "  p <- pf(f[1],f[2],f[3],lower.tail=F)\n" +
                        "  attributes(p) <- NULL\n" +
                        "  return(p)\n" +
                        "}");
            s = c.eval("lmp(modele)").asString();

            System.out.println(s);
            double x = Double.parseDouble(s) ;
            int difference ;
            difference = Interpretationrequete(REQUETE_G,x);
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_G_OK,s,difference);
            
        } 
        catch (RserveException ex) {
               System.out.println("erreur RserveException: " + ex.getMessage());
               reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_G_KO);
        } catch (REXPMismatchException ex) {
            System.out.println("erreur RexpMismatchException : " + ex.getMessage());
            reponse = new ReponseANASTAT(ReponseANASTAT.REQUETE_G_KO);
        }

        try
        {
            c.close();
            oos.writeObject(reponse);
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }            
    }
    private void traiteRequeteConnexion(Socket SocketClient, ClientsList ListClients, Serveur_DataMining fenetreSer)
    {
        Serveur_DataMining fenetreServer = fenetreSer;
        String log, pwd;
        ReponseANASTAT reponse=null;
        log = String1; pwd = String2;
        this.Sub = new VerificateurUsersPasswordHash (log);

        if (Sub.findPwd(log, pwd) || Sub.isOk(log, pwd))
        {
            reponse = new ReponseANASTAT(ReponseANASTAT.LOGIN_OK);
            
            java.util.Date date2 = new java.util.Date();
            DefaultTableModel tablemessagerecus2 = (DefaultTableModel)fenetreServer.TableMessageRecus.getModel();
            String d2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(date2);
            tablemessagerecus2.addRow(new Object[]{d2,SocketClient.getInetAddress(), "Client connecté ! "});
            
            DefaultTableModel tableclients = (DefaultTableModel)fenetreServer.TableClientsConnectes.getModel();
            tableclients.addRow(new Object[]{SocketClient.getInetAddress(),SocketClient.getLocalPort(), SocketClient.getLocalAddress()});
        }
        else
        {
            reponse = new ReponseANASTAT(ReponseANASTAT.LOGIN_KO);            
        }


        try
        {
            this.oos.writeObject(reponse);
            System.out.println("Je renvoi la reponse de la connexion au client");
        }
        catch (IOException e)
        { System.err.println("Erreur réseau Serveur ? [" + e.getMessage() + "]"); }            
    }
    @Override
    public Runnable createRunnable(Socket SocketClient, ClientsList ListClients, FichierLog fichierLog, Serveur_DataMining fenetreServer, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
