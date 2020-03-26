package Outils;

import AppliClientCompagnie.FenClientTest;
import RequeteReponseCINAP.RequeteCINAP;
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

public class LibrairieJDBC implements Serializable
{
    public static final int REQUETE_SELECT = 1;
    public static final int REQUETE_UPDATE = 2;
    public static final int REQUETE_INSERT = 3;
    public static final int REQUETE_DELETE = 4;
    public static final int REQUETE_NEWUSER = 5;
    public static final int REQUETE_MAKINGBOOKING = 7;
    private int type;
    private Connection connCompagnie = null;
    private Connection connCartes = null;
    private Connection connEmbarquements = null;
    private Connection connFrontieresNationale = null;
    private Connection connFrontieresInternationale = null;    
    
    private Properties propServeurCompagnie;
    public void setPropServeur(Properties prop) { propServeurCompagnie = prop; } 
    public Properties getPropServeur() { return propServeurCompagnie; }
    
    private Properties propServeurCartes;
    public void setPropServeurCartes(Properties prop) { propServeurCartes = prop; } 
    public Properties getPropServeurCartes() { return propServeurCartes; }
    
    private Properties propServeurEmbarquements;
    public void setPropServeurEmbarquements(Properties prop) { propServeurEmbarquements = prop; } 
    public Properties getPropServeurEmbarquements() { return propServeurEmbarquements; }
    
    private Properties propServeurFrontieresNationale;
    public void setPropServeurFrontieresNationale(Properties prop) { propServeurFrontieresNationale = prop; } 
    public Properties getPropServeurFrontieresNationale() { return propServeurFrontieresNationale; }
    
        
    private Properties propServeurFrontieresInternationale;
    public void setPropServeurFrontieresInternationale(Properties prop) { propServeurFrontieresInternationale = prop; } 
    public Properties getPropServeurFrontieresInternationale() { return propServeurFrontieresInternationale; }
    
    private Statement instructCompagnie;
    public void setInstruct(Statement ins) { instructCompagnie = ins; } 
    public Statement getInstruct() { return instructCompagnie; }
    
    private Statement instructCartes;
    public void setInstructCartes(Statement ins) { instructCartes = ins; } 
    public Statement getInstructCartes() { return instructCartes; }
    
    private Statement instructEmbarquements;
    public void setInstructEmbarquements(Statement ins) { instructEmbarquements = ins; } 
    public Statement getInstructEmbarquements() { return instructEmbarquements; }
    
    private Statement instructFrontieresNational;
    public void setinstructFrontieresNational(Statement ins) { instructFrontieresNational = ins; } 
    public Statement getinstructFrontieresNational() { return instructFrontieresNational; }
    
    private Statement instructFrontieresInternationale;
    public void setinstructFrontieresInternationale(Statement ins) { instructFrontieresInternationale = ins; } 
    public Statement getinstructFrontieresInternationale() { return instructFrontieresInternationale; }
    
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
    
    private String filePropertiesEmbarquements;
    public void setFilePropertiesEmbarquements(String file) { filePropertiesEmbarquements = file; } 
    public String getFilePropertiesEmbarquements() { return filePropertiesEmbarquements; }
    
    private String filePropertiesFrontieresNationale;
    public void setFilePropertiesFrontieresNationale(String file) { filePropertiesFrontieresNationale = file; } 
    public String getFilePropertiesFrontieresNationale() { return filePropertiesFrontieresNationale; }
    
    private String filePropertiesFrontieresInternationale;
    public void setFilePropertiesFrontieresInternationale(String file) { filePropertiesFrontieresInternationale = file; } 
    public String getFilePropertiesFrontieresInternationale() { return filePropertiesFrontieresInternationale; }
    
    public LibrairieJDBC()
    {
        System.out.println("Passage dans constructeur LibrairieJDBC");
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
    
    public Connection ConnexionToBDCartes()
    {
        try
        {
            Class leDriver = Class.forName((String)propServeurCartes.getProperty("DriverManager"));
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver adéquat non trouvable : " + e.getMessage());
        }
        System.out.println("Essai de connexion à (Cartes) HomeFerries's Database");
        try
        {
            connCartes = DriverManager.getConnection(propServeurCartes.getProperty("ConnexionBD"),propServeurCartes.getProperty("BDUsername"),propServeurCartes.getProperty("BDPassword"));
        } catch (SQLException ex) 
        {
            Logger.getLogger(LibrairieJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion à (Cartes) HomeFerries's Database réalisée");
            
        return connCartes;  
    }
    
    public Connection ConnexionToBDFiles()
    {
        try
        {
            Class leDriver = Class.forName((String)propServeurEmbarquements.getProperty("DriverManager"));
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver adéquat non trouvable : " + e.getMessage());
        }
        System.out.println("Essai de connexion à (Cartes) HomeFerries's Database");
        try
        {
            connEmbarquements = DriverManager.getConnection(propServeurEmbarquements.getProperty("ConnexionBD"),propServeurEmbarquements.getProperty("BDUsername"),propServeurEmbarquements.getProperty("BDPassword"));
        } catch (SQLException ex) 
        {
            Logger.getLogger(LibrairieJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion à (Cartes) HomeFerries's Database réalisée");
            
        return connEmbarquements;  
    }
    
    public Connection ConnexionToBDNationale()
    {
        try
        {
            Class leDriver = Class.forName((String)propServeurFrontieresNationale.getProperty("DriverManager"));
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver adéquat non trouvable : " + e.getMessage());
        }
        System.out.println("Essai de connexion à (Cartes) HomeFerries's Database");
        try
        {
            connFrontieresNationale = DriverManager.getConnection(propServeurFrontieresNationale.getProperty("ConnexionBD"),propServeurFrontieresNationale.getProperty("BDUsername"),propServeurFrontieresNationale.getProperty("BDPassword"));
        } catch (SQLException ex) 
        {
            Logger.getLogger(LibrairieJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion à (Frontieres NATIONALE) HomeFerries's Database réalisée");
            
        return connFrontieresNationale;  
    }
    
        public Connection ConnexionToBDInternationale()
    {
        try
        {
            Class leDriver = Class.forName((String)propServeurFrontieresInternationale.getProperty("DriverManager"));
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver adéquat non trouvable : " + e.getMessage());
        }
        System.out.println("Essai de connexion à (Cartes) HomeFerries's Database");
        try
        {
            connFrontieresInternationale = DriverManager.getConnection(propServeurFrontieresInternationale.getProperty("ConnexionBD"),propServeurFrontieresInternationale.getProperty("BDUsername"),propServeurFrontieresInternationale.getProperty("BDPassword"));
        } catch (SQLException ex) 
        {
            Logger.getLogger(LibrairieJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion à (Frontieres INTERNATIONALE) HomeFerries's Database réalisée");
            
        return connFrontieresInternationale;  
    }
    
    public Statement CreationStatementCompagnie(Connection connect)
    {
        try 
        {
            instructCompagnie = connect.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Création d'une instance d'instruction pour cette connexion");
        
        return instructCartes;
    }
    
    public Statement CreationStatementEmbarquements(Connection connect)
    {
        try 
        {
            instructEmbarquements = connect.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Création d'une instance d'instruction pour cette connexion");
        
        return instructEmbarquements;
    }
        
    public Statement CreationStatementFrontieresNationale(Connection connect)
    {
        try 
        {
            instructFrontieresNational = connect.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Création d'une instance d'instruction pour cette connexion");
        
        return instructFrontieresNational;
    }
    
    public Statement CreationStatementFrontieresInternationale(Connection connect)
    {
        try 
        {
            instructFrontieresInternationale = connect.createStatement();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Création d'une instance d'instruction pour cette connexion");
        
        return instructFrontieresInternationale;
    }
    
    public Properties RecupPropertiesCompagnie()
    {
        this.setSeparateur(System.getProperty("file.separator"));
        this.setRepertoireCourant("C:\\");
        System.out.println("repertoire courant : " + repertoireCourant);
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
    
    public Properties RecupPropertiesFrontieresNationale()
    {
        this.setSeparateur(System.getProperty("file.separator"));
        this.setRepertoireCourant("C:\\");
        System.out.println("repertoire courant : " + repertoireCourant);
        this.setFilePropertiesFrontieresNationale(repertoireCourant + separateur + "src" + separateur + "Properties" + separateur +  "ServeurFrontieresNationale.properties");
        this.setPropServeurFrontieresNationale(new Properties());
        Properties PropserveurNational = this.getPropServeurFrontieresNationale();
        try 
        {
            PropserveurNational.load (new FileInputStream (this.getFilePropertiesFrontieresNationale()));
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("Fichier ServeurFrontieresNationale.properties non-trouvé."); 
        } 
        catch (IOException e)
        {
            System.out.println("Erreur d'IO : ServeurFrontieresNationale.properties" + e.getMessage());
        }
        return PropserveurNational;
    }
    
    public Properties RecupPropertiesFrontieresInternationale()
    {
        this.setSeparateur(System.getProperty("file.separator"));
        this.setRepertoireCourant("C:\\");
        System.out.println("repertoire courant : " + repertoireCourant);
        this.setFilePropertiesFrontieresInternationale(repertoireCourant + separateur + "src" + separateur + "Properties" + separateur +  "ServeurFrontieresInternationale.properties");
        this.setPropServeurFrontieresInternationale(new Properties());
        Properties Propserveur = this.getPropServeurFrontieresInternationale();
        try 
        {
            Propserveur.load (new FileInputStream (this.getFilePropertiesFrontieresInternationale()));
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("Fichier ServeurFrontieresInternationale.properties non-trouvé."); 
        } 
        catch (IOException e)
        {
            System.out.println("Erreur d'IO : ServeurFrontieresInternationale.properties" + e.getMessage());
        }
        return Propserveur;
    }    
    
    public Properties RecupPropertiesCard()
    {
        this.setSeparateur(System.getProperty("file.separator"));
        this.setRepertoireCourant("C:\\");
        this.setFilePropertiesCartes(repertoireCourant + separateur + "src" + separateur + "Properties" + separateur +  "ServeurCartes.properties");
        this.setPropServeurCartes(new Properties());
        Properties propServeurCartes = this.getPropServeurCartes();
        try 
        {
            propServeurCartes.load (new FileInputStream (this.getFilePropertiesCartes()));
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("Fichier ServeurCartes.properties non-trouvé."); 
        } 
        catch (IOException e)
        {
            System.out.println("Erreur d'IO : ServeurCartes.properties" + e.getMessage());
        }
        return propServeurCartes;
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
    
    public void PreparedSelect(FenClientTest fct)
    {
        try
        {
            ResultSet rs = instructCompagnie.executeQuery(fct.getTextRequete());

            System.out.println("Instruction SELECT envoyée à Home-Ferries's DataBase");
            DefaultTableModel modelReservations = new DefaultTableModel();

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
            fct.TableRequetes.setModel((DefaultTableModel)modelReservations);
        }
        catch (SQLException e) 
        {
            System.err.println("Erreur : " + e);
            String msg;
            msg = "Erreur de syntaxe dans la requête !";
            JOptionPane.showMessageDialog(fct, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
        }
        finally
        {
            try {
                connCompagnie.close();
            } catch (SQLException ex) {
                Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized void PreparedDeleteAndUpdate(FenClientTest fct)
    {
        try {
            String sql = fct.getTextRequete();
            instructCompagnie.executeUpdate(sql);
            fct.setTextRequete("");
            String msg;
            msg = "Requête validée !";
            JOptionPane.showMessageDialog(fct, msg, "Validation !", JOptionPane.INFORMATION_MESSAGE, null);
        } catch (SQLException ex) {
            Logger.getLogger(FenClientTest.class.getName()).log(Level.SEVERE, null, ex);
            String msg;
            msg = "Erreur de syntaxe dans la requête !";
            JOptionPane.showMessageDialog(fct, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }
    
    public synchronized void PreparedInsert(FenClientTest fct)
    {
        try 
            {
                String query = fct.getTextRequete();
                PreparedStatement preparedStmt;
                preparedStmt = connCompagnie.prepareStatement(query);
                preparedStmt.execute();
                System.out.println("Instruction INSERT envoyée à Home-Ferries's DataBase");
                fct.setTextRequete("");
                String msg;
                msg = "Requête validée !";
                JOptionPane.showMessageDialog(fct, msg, "Validation !", JOptionPane.INFORMATION_MESSAGE, null);
            } catch (SQLException ex) 
            {
                Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
                String msg;
                msg = "Erreur de syntaxe dans la requête !";
                JOptionPane.showMessageDialog(fct, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
            }        
            finally
            {
                try {
                    connCompagnie.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequeteCINAP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
}