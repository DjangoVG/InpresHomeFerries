package Outil;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrairieJDBC implements Serializable
{
    private int type;
    private Connection connEmbarquements = null;
    
    private Properties propServeurEmbarquements;
    public void setPropServeurEmbarquements(Properties prop) { propServeurEmbarquements = prop; } 
    public Properties getPropServeurEmbarquements() { return propServeurEmbarquements; }

    private Statement instructEmbarquements;
    public void setInstructEmbarquements(Statement ins) { instructEmbarquements = ins; } 
    public Statement getInstructEmbarquements() { return instructEmbarquements; }
    
    private String separateur;
    public void setSeparateur(String sep) { separateur = sep; } 
    public String getSeparateur() { return separateur; }
    
    private String repertoireCourant;
    public void setRepertoireCourant(String rep) { repertoireCourant = rep; } 
    public String getRepertoireCourant() { return repertoireCourant; }
    
    private String filePropertiesEmbarquements;
    public void setFilePropertiesEmbarquements(String file) { filePropertiesEmbarquements = file; } 
    public String getFilePropertiesEmbarquements() { return filePropertiesEmbarquements; }
    
    public LibrairieJDBC()
    {
        System.out.println("Passage dans constructeur LibrairieJDBC");
    }
    
    public Connection ConnexionToBDFiles()
    {
        try
        {
            Class leDriver = Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver adéquat non trouvable : " + e.getMessage());
        }
        System.out.println("Essai de connexion à (Embarquements) HomeFerries's Database");
        try
        {
            connEmbarquements = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_ferries?serverTimezone=UTC","root","");
        } catch (SQLException ex) 
        {
            Logger.getLogger(LibrairieJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion à (Embarquements) HomeFerries's Database réalisée");
            
        return connEmbarquements;  
    }
    
    public Statement CreationStatementEmbarquements(Connection connect)
    {
        try 
        {
            instructEmbarquements = connect.createStatement();
        } 
        catch (SQLException ex) 
        {
            System.out.println(": " +ex);
        }
        System.out.println("Création d'une instance d'instruction pour cette connexion");
        
        return instructEmbarquements;
    }
    
    public Properties RecupPropertiesEmbarquements()
    {
        this.setSeparateur(System.getProperty("file.separator"));
        this.setRepertoireCourant("C:\\");
        this.setFilePropertiesEmbarquements(repertoireCourant + separateur + "src" + separateur + "Properties" + separateur +  "ServeurEmbarquements.properties");
        this.setPropServeurEmbarquements(new Properties());
        Properties propServeurEmbarquements = this.getPropServeurEmbarquements();
        try 
        {
            propServeurEmbarquements.load (new FileInputStream (this.getFilePropertiesEmbarquements()));
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("Fichier ServeurEmbarquements.properties non-trouvé."); 
        } 
        catch (IOException e)
        {
            System.out.println("Erreur d'IO : ServeurEmbarquements.properties" + e.getMessage());
        }
        return propServeurEmbarquements;
    }
}