package RequeteVERIFID;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ReponseVERIFID implements Reponse, Serializable
{   
    private int code;
    private ArrayList array = new ArrayList();
    private DefaultTableModel dm;
    private DefaultTableModel dm2;
    public static final int LISTEVEHICULES_OK = 103;
    public static final int LISTEVEHICULES_KO = 104;
    public static final int LISTNUMNATIONAL_OK = 105;
    public static final int LISTNUMNATIONAL_KO = 106;
    public static final int LISTPERMIS_OK = 107;
    public static final int LISTPERMIS_KO = 108;
    public static final int LISTPASSAGER_OK = 109;
    public static final int LISTPASSAGER_KO = 110;
    public static final int LISTIDENTITEPERMIS_KO = 111;
    private byte[] bytDLM;
    private byte[] bytDLM2;
    private int TailleBloc;
    private byte[] ByteSignature;
    
    /* Constructeurs */
    private byte[] NumeroNational;
    private byte[] Prenom;
    private byte[] Nom;
    private byte[] DateNaissance;
    private byte[] DateMaxValiditeIdentite;
    private byte[] DateMaxValiditePermis;
    private byte[] NumeroPermis;
    
    public ReponseVERIFID(int c, byte [] NumeroNational, byte [] NumeroPermis, byte [] Nom, byte [] Prenom, byte [] DateNaissance, byte [] DateMaxValiditeIdentite, byte [] DateMaxValiditePermis )
    {
        code =c;
        this.NumeroNational = NumeroNational;
        this.NumeroPermis = NumeroPermis;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.DateNaissance = DateNaissance;
        this.DateMaxValiditeIdentite = DateMaxValiditeIdentite;
        this.DateMaxValiditePermis = DateMaxValiditePermis;
    }
    public ReponseVERIFID(int c, byte [] NumeroNational, byte [] Nom, byte [] Prenom, byte [] DateNaissance, byte [] DateMaxValiditeIdentite)
    {
        code =c;
        this.NumeroNational = NumeroNational;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.DateNaissance = DateNaissance;
        this.DateMaxValiditeIdentite = DateMaxValiditeIdentite;
    }
    public ReponseVERIFID (int c)
    {
        code =c;
    }
    public ReponseVERIFID(int c, DefaultTableModel dtmdl)
    {
        code =c;
        dm = dtmdl;
    }
    public ReponseVERIFID(int c, DefaultTableModel dtmdl,DefaultTableModel dtmdl2)
    {
        code =c;
        dm = dtmdl;
        dm2 = dtmdl2;
    }
    public ReponseVERIFID(int c, DefaultTableModel dtmdl, int TailleBloc)
    {
        code =c;
        dm = dtmdl;
    }
    
    public ReponseVERIFID(int c, DefaultTableModel dtmdl, DefaultTableModel dtmdl2, int TailleBloc)
    {
        code =c;
        dm = dtmdl;
        dm2 = dtmdl2;
    }
    
    public ReponseVERIFID(int c, byte [] dtmdl, int TailleBloc)
    {
        code =c;
        this.bytDLM = dtmdl;
        this.TailleBloc = TailleBloc;
    }
    
    public ReponseVERIFID(int c, byte [] dtmdl, byte [] dlm2, int TailleBloc)
    {
        code =c;
        this.bytDLM = dtmdl;
        this.bytDLM2 = dlm2;
        this.TailleBloc = TailleBloc;
    }
    
     public ReponseVERIFID(int c, DefaultTableModel dlm, byte [] ByteSignature)
    {
        code =c;
        this.dm = dlm;
        this.ByteSignature = ByteSignature;
    }
    
    /* Getters */
    @Override
    public int getCode()
    {
        return code;
    }
    
    public byte[]  getNumeroNational() 
    {
        return this.NumeroNational;
    }
    
    public byte[]  getNumeroPermis() 
    {
        return this.NumeroPermis;
    }
    
    public byte[]  getNom() 
    {
        return this.Nom;
    }
    
    public byte[]  getPrenom() 
    {
        return this.Prenom;
    }
    
    public byte[]  getDateNaissance() 
    {
        return this.DateNaissance;
    }
    
    public byte[]  getDateMaxValiditeIdentite() 
    {
        return this.DateMaxValiditeIdentite;
    }
    
    public byte[]  getDateMaxValiditePermis()
    {
        return this.DateMaxValiditePermis;
    }
    
    public byte [] getSignature () {
        return this.ByteSignature;
    }

    public DefaultTableModel getModele()
    {
        return dm;
    }
    
    public DefaultTableModel getModele2()
    {
        return dm2;
    }

    public ArrayList getArray()
    {
        return array;
    }
    
    public byte [] getByteDLM1 ()
    {
        return bytDLM;
    }
    
    public byte [] getByteDLM2 () {
        return bytDLM2;
    }
    
    public int  getTaille() 
    {
        return this.TailleBloc;
    }
    
}
