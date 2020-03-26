package Outils;

import applic_datamining.Applic_datamining;
import RequeteReponseANASTAT.RequeteANASTAT;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Serveur.*;

public class LibrairieJDBC implements Serializable
{
    public static final int REQUETE_SELECT = 1;
    public static final int REQUETE_UPDATE = 2;
    public static final int REQUETE_INSERT = 3;
    public static final int REQUETE_DELETE = 4;
    private int type;
    private Connection connCompagnie = null;
    private Connection connCartes = null;
    
    
    private Properties propServeurCompagnie;
    public void setPropServeur(Properties prop) { propServeurCompagnie = prop; } 
    public Properties getPropServeur() { return propServeurCompagnie; }
    
    private Properties propServeurCartes;
    public void setPropServeurCartes(Properties prop) { propServeurCartes = prop; } 
    public Properties getPropServeurCartes() { return propServeurCartes; }
    
    private Statement instructCompagnie;
    public void setInstruct(Statement ins) { instructCompagnie = ins; } 
    public Statement getInstruct() { return instructCompagnie; }
    
    private Statement instructCartes;
    public void setInstructCartes(Statement ins) { instructCartes = ins; } 
    public Statement getInstructCartes() { return instructCartes; }
    
    private String separateur;
    public void setSeparateur(String sep) { separateur = sep; } 
    public String getSeparateur() { return separateur; }
    
    private String repertoireCourant;
    public void setRepertoireCourant(String rep) { repertoireCourant = rep; } 
    public String getRepertoireCourant() { return repertoireCourant; }
    
    private String fileProperties;
   public void setFileProperties(String file) { fileProperties = file; } 
    public String getFileProperties() { return fileProperties; }
    
    private String filePropertiesCartes;
    public void setFilePropertiesCartes(String file) { filePropertiesCartes = file; } 
    public String getFilePropertiesCartes() { return filePropertiesCartes; }
    
    public LibrairieJDBC()
    {
        System.out.println("Passage dans constructeur LibrairieJDBC");
        setInstruct(null);
    }
    
    public Connection ConnexionToBDCompagnie()
    {
        try
        {
            Class leDriver = Class.forName((String)propServeurCompagnie.getProperty("DriverManager"));
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver adéquat non trouvable : " + e.getMessage());
        }
        System.out.println("Essai de connexion à (Compagnie) HomeFerries's Database");
        try
        {
            connCompagnie = DriverManager.getConnection(propServeurCompagnie.getProperty("ConnexionBD"),propServeurCompagnie.getProperty("BDUsername"),propServeurCompagnie.getProperty("BDPassword"));
        } catch (SQLException ex) 
        {
            Logger.getLogger(LibrairieJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion à (Compagnie) HomeFerries's Database réalisée");
            
        return connCompagnie;  
    }
    
    public Connection ConnexionToBDAide()
    {
        try
        {
            System.out.println("Avant le Driver");
            Class leDriver = Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver adéquat non trouvable : " + e.getMessage());
        }
        System.out.println("Essai de connexion à (AideDecision) HomeFerries's Database");
        try
        {
            connCompagnie = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_aide_decision?serverTimezone=UTC","root","");
        } catch (SQLException ex) 
        {
            Logger.getLogger(LibrairieJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion à (AideDecision) HomeFerries's Database réalisée");
            
        return connCompagnie;  
    }
    
    public Statement CreationStatementCompagnie(Connection connect)
    {
        try 
        {
            instructCompagnie = connect.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RequeteANASTAT.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Création d'une instance d'instruction pour cette connexion");
        
        return instructCompagnie;
    }
    
    public Statement CreationStatementCartes(Connection connect)
    {
        try 
        {
            instructCartes = connect.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RequeteANASTAT.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Création d'une instance d'instruction pour cette connexion");
        
        return instructCartes;
    }
    
    public Properties RecupPropertiesCompagnie()
    {
        this.setSeparateur(System.getProperty("file.separator"));
        this.setRepertoireCourant(System.getProperty("user.dir"));
        this.setFileProperties(repertoireCourant + separateur + "src" + separateur + "Properties" + separateur +  "ServeurCompagnie.properties");
        this.setPropServeur(new Properties());
        Properties Propserveur = this.getPropServeur();
        try 
        {
            Propserveur.load (new FileInputStream (this.getFileProperties()));
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("Fichier ServeurProperties.properties non-trouvé."); 
        } 
        catch (IOException e)
        {
            System.out.println("Erreur d'IO : ServeurProperties.properties" + e.getMessage());
        }
        return Propserveur;
    }
}