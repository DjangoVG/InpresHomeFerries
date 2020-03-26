package RequeteReponseFECOP;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ReponseFECOP implements Reponse, Serializable
{   
    private int code;
    private ArrayList array = new ArrayList();
    private DefaultTableModel dm;
    private String NumReservation;
    private String HeureTraversee;
    private String NomFerry;
    private int NumFile;
    private int port ;
    private float CalculMonetaire;
    private String Adresse ;
    public static final int LOGIN_OK = 1 ;
    public static final int LOGIN_KO = 2 ;
    
    
    /* Constructeurs */
    public ReponseFECOP (int c)
    {
        code =c;
    }
    
    public ReponseFECOP (float Calcul)
    {
        this.CalculMonetaire = Calcul;
    }
    
    
    
    public ReponseFECOP (int c, String NumRes)
    {
        code =c; NumReservation = NumRes;
    }
    
    public ReponseFECOP (int c, String NumRes, String HeureTraversee, String NomFerry)
    {
        code =c; NumReservation = NumRes; this.HeureTraversee = HeureTraversee;
        this.NomFerry = NomFerry;
    }
    
    public ReponseFECOP(int c, DefaultTableModel dtmdl)
    {
        code =c;
        dm = dtmdl;
    }
    
    public ReponseFECOP(int c, DefaultTableModel dtmdl, int i)
    {
        code =c;
        dm = dtmdl;
        NumFile = i;
    }
    
    public ReponseFECOP(int c, int i)
    {
        code =c;
        NumFile = i;
    }
    
    public ReponseFECOP(int c, int portrecu, String add)
    {
        code =c;
        port = portrecu;
        Adresse = add;
    }
    
     public ReponseFECOP(int c, ArrayList arrayL)
    {
        code =c;
        array = arrayL;
    }
    
    /* Getters */
    @Override
    public int getCode()
    {
        return code;
    }
    
    public float getCalculMonetaire () {
        return this.CalculMonetaire;
    }

    @Override
    public Object getParams(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel getModele2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
