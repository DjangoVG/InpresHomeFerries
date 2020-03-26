package RequeteReponseCINAP;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class ReponseCINAP implements Reponse, Serializable
{   
    private int code;
    private ArrayList array = new ArrayList();
    private DefaultTableModel dm;
    private String NumReservation;
    private String HeureTraversee;
    private String NomFerry;
    private int NumFile;
    private String IdTraversee;
    private Date DateDepart;
    private String PortDepart;
    private String PortArrivee;
    private String Matricule;
    private String Prix;
    public static final int LISTRESERVATIONS_OK = 101;
    public static final int ACHATTICKET_OK = 102;
    public static final int LISTRESERVATIONS_ERROR = 103;
    public static final int ACHATTICKET_NOMVOYAGEUR_ERROR = 104;
    public static final int LOGIN_OK = 105;
    public static final int LOGIN_KO = 106;
    public static final int LOGIN_NOTADMIN = 137;
    public static final int ACHATTICKET_CARTENONTROUVE = 107;
    public static final int ACHATTICKET_DEBITIMPOSSIBLE = 108;
    public static final int ACHATTICKET_DEBITTROPELEVE = 117;
    public static final int ACHATTICKET_FERRYCOMPLET = 109;
    public static final int ACHATTICKET_LISTEOK = 110;
    public static final int ACHATTICKET_LISTEKO = 111;
    public static final int NEWUSER_OK = 112;
    public static final int NEWUSER_KO = 113;
    public static final int VALIDATE_OK = 114;
    public static final int VALIDATE_FERRYCOMPLET = 115;
    public static final int VALIDATE_DEJAEFFECTUE = 116;
    public static final int MAKEBOOKING_NEWCLIENT = 117;
    public static final int MAKEBOOKING_EXISTCLIENT = 118;
    public static final int MAKEBOOKING_LOGINFAILED = 119;
    public static final int LISTETRAVERSEES_OK = 120;
    public static final int LISTETRAVERSEES_KO = 121;
    public static final int TRAVERSEEOK = 122;

    
    
    /* Constructeurs */
    public ReponseCINAP (int c)
    {
        code =c;
    }
    
    public ReponseCINAP (int c, String NumRes)
    {
        code =c; NumReservation = NumRes;
    }
    
    public ReponseCINAP (int c, String NumRes, String HeureTraversee, String NomFerry)
    {
        code =c; NumReservation = NumRes; this.HeureTraversee = HeureTraversee;
        this.NomFerry = NomFerry;
    }
    
    public ReponseCINAP (int c, String IdTraversee, Date DateDepart, String PortDepart, String PortArrivee, String Matricule, String Prix)
    {
        code =c; this.IdTraversee = IdTraversee; this.DateDepart = DateDepart;
        this.PortDepart = PortDepart; this.PortArrivee = PortArrivee; this.Matricule = Matricule;
        this.Prix = Prix;
    }
    
    public ReponseCINAP(int c, DefaultTableModel dtmdl)
    {
        code =c;
        dm = dtmdl;
    }
    
    public ReponseCINAP(int c, DefaultTableModel dtmdl, int i)
    {
        code =c;
        dm = dtmdl;
        NumFile = i;
    }
    
    public ReponseCINAP(int c, int i)
    {
        code =c;
        NumFile = i;
    }
    
     public ReponseCINAP(int c, ArrayList arrayL)
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

    public int getNumFile()
    {
        return NumFile;
    }

    public String getRes()
    {
        return NumReservation;
    }

    public String getHeure()
    {
        return HeureTraversee;
    } 

    public String getNomFerry()
    {
        return NomFerry;
    } 

    public DefaultTableModel getModele2()
    {
        return dm;
    }

    public ArrayList getArray()
    {
        return array;
    }

    public String getIdTraversee()
    {
        return this.IdTraversee;
    }

    public Date getDateDepart()
    {
        return this.DateDepart;
    }
    

    public String getPortDepart()
    {
        return this.PortDepart;
    }
    

    public String getPortArrivee()
    {
        return this.PortArrivee;
    }
    

    public String getMatricule()
    {
        return this.Matricule;
    }

    public String getPrix()
    {
        return this.Prix;
    }
}
