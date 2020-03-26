package RequeteReponseHAFIC;

import RequeteReponseMultiThread.*;
import java.net.*;
import java.io.*;
import Outil.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import RequeteReponseCINAP.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class RequeteHAFIC implements Requete, Serializable
{
    public static int REQUEST_VERIF = 0;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ObjectInputStream oisEmbarquementsToCompagnie;
    private ObjectOutputStream oosEmbarquementsToCompagnie;
    private int type;
    private String NumSteward;
    private String NumFile;
    private String GoodFile;
    private String BadFile;
    private String PasswordSteward;
    private String NumTraversee;
    private String Num;
    private String MatriculeFerry;
    private LibrairieJDBC LibJDBC;
    private Properties propServeurEmbarquements;
    private Statement instructEmbarquements;
    private ResultSet rs;
    private float PourcentageCapacite;
    
    public RequeteHAFIC(int t, String NumFerry)
    {
        type = t;
        this.MatriculeFerry = NumFerry;
        LibJDBC = new LibrairieJDBC();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteHAFIC(int t, String BadFile, String GoodFile, String MatriculeFerry)
    {
        type = t; this.GoodFile = GoodFile;
        this.BadFile = BadFile;
        this.MatriculeFerry = MatriculeFerry;
        LibJDBC = new LibrairieJDBC();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteHAFIC(int t, String NumFerry, String NumFile, int a)
    {
        type = t;
        this.MatriculeFerry = NumFerry;
        this.NumFile = NumFile;
        LibJDBC = new LibrairieJDBC();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteHAFIC(int t, String NumTraversee, String Num)
    {
        type = t;
        this.NumTraversee = NumTraversee; this.Num = Num;
        LibJDBC = new LibrairieJDBC();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public RequeteHAFIC(int t, String UsernameSteward, String PassSteward, boolean a)
    {
        type = t;
        this.NumSteward = UsernameSteward; this.PasswordSteward = PassSteward;
        LibJDBC = new LibrairieJDBC();
        propServeurEmbarquements = LibJDBC.RecupPropertiesEmbarquements();
    }
    
    public int getCode()
    {
        return type;
    }
    
    @Override
    public Runnable createRunnable(Socket SocketClient, ObjectInputStream ois, ObjectOutputStream oos) 
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
                        RequeteValidationFile(SocketClient);
                    }
                };
            case 1:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        RequeteConnexionAndroid(SocketClient);
                    }
                };
            case 2:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        RequeteVerifEtatFerry(SocketClient);
                    }
                };
            case 3:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        RequeteVideFile(SocketClient);
                    }
                };
            case 4:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        RequeteBadFile(SocketClient);
                    }
                };
            case 5:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        RequeteVerifEtatFile(SocketClient);
                    }
                };
            case 6:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        RequeteFullFile(SocketClient);
                    }
                };
        }
        return null;
    }

    public synchronized void RequeteConnexionAndroid(Socket SocketClient)
    {
        try 
        {
            System.out.println("Passage connexion Android"); 
            Socket SocketEmbarquement = new Socket("10.59.22.74", 50000);
            RequeteCINAP req;
            ReponseCINAP rep;
            ReponseHAFIC reponseHafic;
            
            oosEmbarquementsToCompagnie = new ObjectOutputStream(SocketEmbarquement.getOutputStream());
            oisEmbarquementsToCompagnie = new ObjectInputStream(SocketEmbarquement.getInputStream());
               
            req = new RequeteCINAP(4, this.NumSteward, this.PasswordSteward, 2);

            oosEmbarquementsToCompagnie.writeObject(req);
            System.out.println("Jenvoi la demande de connexion pour Android");

            rep = (ReponseCINAP)oisEmbarquementsToCompagnie.readObject();
                
            if (rep.getCode() == ReponseCINAP.LOGIN_OK)
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.CONNECT_OK);
            else
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.CONNECT_KO);
            this.oos.writeObject(reponseHafic);
        }
        catch (IOException | ClassNotFoundException e)
        { 
            System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
        } 
    }
    
    public synchronized void RequeteValidationFile(Socket SocketClient)
    {
        ResultSet rs;
        int NumFerry = 0, NbFiles = 0;
        ReponseHAFIC reponse=null;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements);
        try {
            rs = instructEmbarquements.executeQuery("SELECT traversees.Matricule FROM traversees INNER JOIN reservations ON traversees.IdTraversees = reservations.IdTraversee AND reservations.IdReservations = '"+ this.Num+ "';");
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
                    bool = true; i--;
                    preparedStmtUpdate = connexionEmbarquements.prepareStatement("UPDATE `reservations` SET `PassCheckIN`= 1  WHERE `IdReservations` = '"+this.Num+"'");
                    preparedStmtUpdate.execute();
                }
            }
            if (bool == true)
            {
                reponse = new ReponseHAFIC(ReponseHAFIC.VERIFFILE_OK, i);                
            }
            else
                reponse = new ReponseHAFIC(ReponseHAFIC.VERIFFILE_FERRYCOMPLET);
  
            
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
    
    public synchronized void RequeteVerifEtatFerry(Socket SocketClient)
    {
        ResultSet rs;
        ReponseHAFIC reponseHafic;
        int CapaciteMaxFerry=0, CapaciteActuelFerry=0;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements);  
        
        try
        {
            rs = instructEmbarquements.executeQuery("SELECT sum(CapaciteMaxFile) FROM files WHERE Matricule = '"+ MatriculeFerry+"';");
            System.out.println("Instruction SELECT sur files our verif files envoyée à Home-Ferries's DataBase");
            while (rs.next())
                CapaciteMaxFerry = rs.getInt(1);

            rs = instructEmbarquements.executeQuery("SELECT sum(CapaciteActuelFile) FROM files WHERE Matricule = '"+ MatriculeFerry+"';");
            while (rs.next())
                CapaciteActuelFerry = rs.getInt(1);

            PourcentageCapacite = (float) (1.0*(100 * CapaciteActuelFerry) / CapaciteMaxFerry);
            reponseHafic = new ReponseHAFIC(ReponseHAFIC.VERIFFILE, (int)PourcentageCapacite);
            
            this.oos.writeObject(reponseHafic);
            
        }
        catch (SQLException | IOException e)
        { 
            System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
        }
    }
    
    public synchronized void RequeteVerifEtatFile(Socket SocketClient)
    {
        ResultSet rs;
        ReponseHAFIC reponseHafic;
        int CapaciteMaxFile=0, CapaciteActuelFile=0;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements);  
        
        try
        {
            rs = instructEmbarquements.executeQuery("SELECT * FROM files WHERE Matricule = '"+ MatriculeFerry+"' AND NumFile = '"+this.NumFile+"';");
            if (rs.first())
            {
                rs = instructEmbarquements.executeQuery("SELECT sum(CapaciteMaxFile) FROM files WHERE Matricule = '"+ MatriculeFerry+"' AND NumFile = '"+this.NumFile+"';");
                System.out.println("Instruction SELECT sur files our verif files envoyée à Home-Ferries's DataBase");
                while (rs.next())
                    CapaciteMaxFile = rs.getInt(1);

                rs = instructEmbarquements.executeQuery("SELECT sum(CapaciteActuelFile) FROM files WHERE Matricule = '"+ MatriculeFerry+"' AND NumFile = '"+this.NumFile+"';");
                while (rs.next())
                    CapaciteActuelFile = rs.getInt(1);

                PourcentageCapacite = (float) (1.0*(100 * CapaciteActuelFile) / CapaciteMaxFile);
                System.out.println("Cap : " + PourcentageCapacite);
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.VERIFFILE, (int)PourcentageCapacite);                
            }
            else
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.VIDEFILE_FILENOTIN, 0);

            
            this.oos.writeObject(reponseHafic);
            
        }
        catch (SQLException | IOException e)
        { 
            System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
        }
    }
    
    public synchronized void RequeteVideFile(Socket SocketClient)
    {
        ReponseHAFIC reponseHafic;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements); 
        ResultSet rs;
        try 
        {
            rs = instructEmbarquements.executeQuery("SELECT * FROM files WHERE Matricule = '"+ MatriculeFerry+"' AND NumFile = '"+this.NumFile+"';");  
            if (rs.first())
            {
                int CapaciteActuelFile=0;
                rs = instructEmbarquements.executeQuery("SELECT CapaciteActuelFile FROM files WHERE Matricule = '"+ MatriculeFerry+"' AND NumFile = '"+this.NumFile+"';");
                if (rs.first())
                    CapaciteActuelFile = rs.getInt(1);
                
                if (CapaciteActuelFile > 0)
                {
                    PreparedStatement preparedStmtUpdate;
                    preparedStmtUpdate = connexionEmbarquements.prepareStatement("UPDATE `files` SET `CapaciteActuelFile`= 0  WHERE `files`.`NumFile` = "+this.NumFile+" AND `files`.`Matricule` = '"+MatriculeFerry+"';");
                    preparedStmtUpdate.execute();
                    reponseHafic = new ReponseHAFIC(ReponseHAFIC.VIDEFILE_FILEOK);
                }
                else
                    reponseHafic = new ReponseHAFIC(ReponseHAFIC.VIDEFILE_FILEDEJAVIDE);
            }
            else
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.VIDEFILE_FILENOTIN);
            
            this.oos.writeObject(reponseHafic);
        } 
        catch (IOException | SQLException ex) { Logger.getLogger(RequeteHAFIC.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public synchronized void RequeteBadFile(Socket SocketClient)
    {
        ReponseHAFIC reponseHafic=null;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements); 
        try 
        {
            rs = instructEmbarquements.executeQuery("SELECT CapaciteMaxFile, CapaciteActuelFile FROM files WHERE Matricule = '"+ this.MatriculeFerry+"' AND NumFile = '"+this.BadFile+"';");  
            if (rs.first())
            {
                int CapaciteActuelBadFile=0, CapaciteMaxBadFile=0;
                CapaciteMaxBadFile = rs.getInt(1); CapaciteActuelBadFile = rs.getInt(2);
                System.out.println("Capacité actuel de la file de la mauvaise voiture : " + CapaciteActuelBadFile);
                System.out.println("Capacité max de la file de la mauvaise voiture : " + CapaciteMaxBadFile);
                
                rs = instructEmbarquements.executeQuery("SELECT CapaciteMaxFile, CapaciteActuelFile FROM files WHERE Matricule = '"+ this.MatriculeFerry+"' AND NumFile = '"+this.GoodFile+"';");  
                if (rs.first())
                {
                    int CapaciteActuelGoodFile=0, CapaciteMaxGoodFile=0;
                    CapaciteMaxGoodFile = rs.getInt(1); CapaciteActuelGoodFile = rs.getInt(2);
                    System.out.println("Capacité actuel de la bonne file : " + CapaciteActuelGoodFile);
                    System.out.println("Capacité max de la bonne file : " + CapaciteMaxGoodFile);
                    
                    if (CapaciteActuelGoodFile < CapaciteMaxGoodFile)
                    {
                        reponseHafic = new ReponseHAFIC(ReponseHAFIC.BADFILE_CHANGE);
                    }
                    else
                    {
                        PreparedStatement preparedStmtUpdate;
                        preparedStmtUpdate = connexionEmbarquements.prepareStatement("UPDATE `files` SET `CapaciteActuelFile`=  `CapaciteActuelFile` - 1  WHERE `files`.`NumFile` = "+this.GoodFile+" AND `files`.`Matricule` = '"+MatriculeFerry+"';");
                        preparedStmtUpdate.execute();
                        preparedStmtUpdate = connexionEmbarquements.prepareStatement("UPDATE `files` SET `CapaciteActuelFile`=  `CapaciteActuelFile` + 1  WHERE `files`.`NumFile` = "+this.BadFile+" AND `files`.`Matricule` = '"+MatriculeFerry+"';");
                        preparedStmtUpdate.execute();
                        reponseHafic = new ReponseHAFIC(ReponseHAFIC.BADFILE_RESTE);
                    }
                }
            }
            else
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.VIDEFILE_FILENOTIN);
            
            this.oos.writeObject(reponseHafic);
        }
        catch (IOException | SQLException ex) { Logger.getLogger(RequeteHAFIC.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public synchronized void RequeteFullFile(Socket SocketClient)
    {
        ReponseHAFIC reponseHafic=null;
        Connection connexionEmbarquements = LibJDBC.ConnexionToBDFiles();
        instructEmbarquements = LibJDBC.CreationStatementEmbarquements(connexionEmbarquements); 
        try 
        {
            rs = instructEmbarquements.executeQuery("SELECT * from files where Matricule = '"+this.MatriculeFerry+ "' AND NumFile = '"+this.NumFile+"';");
            if (rs.first())
            {
                int CapaciteMaxFile = 0;
                rs = instructEmbarquements.executeQuery("SELECT CapaciteMaxFile from files where Matricule = '"+this.MatriculeFerry+ "' AND NumFile = '"+this.NumFile+"';");
                if (rs.first())
                    CapaciteMaxFile = rs.getInt(1);
                System.out.println("Capacite max : " + CapaciteMaxFile);
                PreparedStatement preparedStmtUpdate;
                preparedStmtUpdate = connexionEmbarquements.prepareStatement("UPDATE `files` SET `CapaciteActuelFile`=  "+CapaciteMaxFile+"  where Matricule = '"+this.MatriculeFerry+ "' AND NumFile = '"+this.NumFile+"';");
                preparedStmtUpdate.execute();
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.FULLFILE_OK);
            }
            else
                reponseHafic = new ReponseHAFIC(ReponseHAFIC.FULLFILE_KO);
            
            this.oos.writeObject(reponseHafic);
        }
        catch (IOException | SQLException ex) { Logger.getLogger(RequeteHAFIC.class.getName()).log(Level.SEVERE, null, ex); }
    }
}

