package RequeteReponseCHECKCARDS;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class ReponseCHECKCARDS implements Reponse, Serializable
{   
    private int code;
    public static final int ACHATTICKET_CARTENONTROUVE = 130;
    public static final int ACHATTICKET_DEBITIMPOSSIBLE = 131;
    public static final int ACHATTICKET_ACHAT_OK = 132;
    public static final int ACHATTICKET_DEBITROPELEVE = 133;
    
    
    /* Constructeurs */
    public ReponseCHECKCARDS (int c)
    {
        code =c;
    }
    
    /* Getters */
    @Override
    public int getCode()
    {
        return code;
    }

    public String getRes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultTableModel getModele2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList getArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getHeure() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNomFerry() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNumFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdTraversee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getDateDepart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPortDepart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPortArrivee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMatricule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPrix() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
