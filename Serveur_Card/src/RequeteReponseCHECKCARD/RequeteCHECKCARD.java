package RequeteReponseCHECKCARD;

import Outils.FichierLog;
import RequeteReponseMultiThread.*;
import java.net.*;
import java.io.*;
import serveur_compagnie.ClientsList;
import Outils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.net.ssl.SSLSocket;
import serveur_compagnie.FenServer;
        
public class RequeteCHECKCARD implements Requete, Serializable
{
    public static int REQUEST_VERIF_CARTE = 1;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int type;
    private String NumCarte;
    private String NomClient;
    private String PrenomClient;
    private int Solde;
    private LibrairieJDBC LibJDBC;
    private Properties propServeurCartes;
    private Statement instrucCart;
    
    public RequeteCHECKCARD(int t, String NumCarte, String NomClient, String PrenomClient, int SoldeADebiter)
    {
        System.out.println("Debut Constructeur RequeteCHECKCARD");
        type = t;
        this.NumCarte = NumCarte; this.NomClient = NomClient; this.PrenomClient = PrenomClient; this.Solde = SoldeADebiter;
        LibJDBC = new LibrairieJDBC();
        propServeurCartes = LibJDBC.RecupPropertiesCard();
        System.out.println("Fin Constructeur RequeteCHECKCARD");
    }
    
    public int getCode()
    {
        return type;
    }
    

    public Runnable createRunnable(Socket SocketClient, ObjectInputStream ois, ObjectOutputStream oos) 
    {
        System.out.println("Debut createRunnable RequeteCHECKCARD");
        this.ois = ois;
        this.oos = oos;
        switch (type)
        {
            case 0:
                return new Runnable()
                {
                    @Override
                    public void run() {
                        System.out.println("run createRunnable reqAchat RequeteCHECKCARD");
                        RequeteAchat(SocketClient);
                    }
                };
        }
        return null;
    }

    public synchronized void RequeteAchat(Socket SocketClient)
    {
        System.out.println("Debut RequteAchat RequeteCHECKCARD");
        ReponseCHECKCARD reponse=null;
        Connection connexionCartes = LibJDBC.ConnexionToBDCartes();
        System.out.println("Connexion Bd_Carte RequeteCHECKCARD");
        instrucCart = LibJDBC.CreationStatementCartes(connexionCartes);
        System.out.println("CreateStatement Bd_Carte RequeteCHECKCARD");
        try {
            if (Solde <= 5000)
            {
                ResultSet rsCartes = instrucCart.executeQuery("SELECT * FROM carte_de_credit WHERE NumCarte = '"+NumCarte+"';");
                System.out.println("Instruction SELECT envoyée à Home-Ferries's DataBase");
                if (rsCartes.first() == true)
                {
                    String NumCompte = rsCartes.getString(2);
                    rsCartes = instrucCart.executeQuery("SELECT compte_bancaire.Solde FROM compte_bancaire INNER JOIN carte_de_credit ON compte_bancaire.NumCompte = carte_de_credit.NumCompte AND carte_de_credit.NumCompte = '"+NumCompte+"';");
                    if (rsCartes.first())
                    {
                        int SoldeCompte = rsCartes.getInt(1);
                        if (SoldeCompte >= Solde) /* On débite la valeur du ticket ! -> 1 passager = 100 euros */
                        {
                            int ValeurTicket = Solde;
                            synchronized (this)
                            {
                                String queryDebit = "UPDATE `compte_bancaire` SET `Solde` = `Solde` - "+ValeurTicket+" WHERE `compte_bancaire`.`NumCompte` = '"+NumCompte+"';";
                                PreparedStatement preparedStmtDebit;
                                preparedStmtDebit = connexionCartes.prepareStatement(queryDebit);
                                preparedStmtDebit.execute();
                            }
                            reponse = new ReponseCHECKCARD(ReponseCHECKCARD.ACHATTICKET_ACHAT_OK); 
                            System.out.println("Instruction INSERT sur reservation envoyée à Home-Ferries's DataBase");                              
                        }
                        else
                            reponse = new ReponseCHECKCARD(ReponseCHECKCARD.ACHATTICKET_DEBITIMPOSSIBLE); 
                    }
                }
                else
                    reponse = new ReponseCHECKCARD(ReponseCHECKCARD.ACHATTICKET_CARTENONTROUVE);                 
            }
            else
                reponse = new ReponseCHECKCARD(ReponseCHECKCARD.ACHATTICKET_DEBITROPELEVE);
  
            System.out.println("Je renvoi la reponse CheckCard");
        oos.writeObject(reponse);    
            
            
        } 
        catch (IOException | SQLException e) 
        {
            System.out.println("Erreur : " + e);
        }
        finally 
        {
            try {
                connexionCartes.close();
            } catch (SQLException e) {
            }
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


    public Runnable createRunnable(SSLSocket SocketClient, ObjectInputStream ois, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

