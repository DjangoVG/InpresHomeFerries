package RequeteCONTROLID;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ReponseCONTROLID implements Reponse, Serializable
{   
    private int code;
    private ArrayList array = new ArrayList();
    private DefaultTableModel dm;
    private DefaultTableModel dm2;
    public static final int LOGIN_OK = 101;
    public static final int LOGIN_KO = 102;
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
    private byte[] Signature;
    private byte[] cléSecreteCrypté;
    private byte[] cléHMACCrypté;
    private byte[] NumeroNational;
    private byte[] NumeroPermis;
    private byte[] Nom;
    private byte[] Prenom;
    private byte[] DateNaissance;
    private byte[] DateMaxValiditeIdentite;
    private byte[] DateMaxValiditePermis;
    
    public ReponseCONTROLID(int c, byte [] NumeroNational, byte [] NumeroPermis, byte [] Nom, byte [] Prenom, byte [] DateNaissance, byte [] DateMaxValiditeIdentite, byte [] DateMaxValiditePermis )
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
    
    public ReponseCONTROLID(int c, byte [] NumeroNational,byte [] Nom, byte [] Prenom, byte [] DateNaissance, byte [] DateMaxValiditeIdentite)
    {
        code =c;
        this.NumeroNational = NumeroNational;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.DateNaissance = DateNaissance;
        this.DateMaxValiditeIdentite = DateMaxValiditeIdentite;
    }
    

    public byte[] getBytDLM() {
        return bytDLM;
    }

    public byte[] getBytDLM2() {
        return bytDLM2;
    }

    public byte[] getDateMaxValiditeIdentite() {
        return DateMaxValiditeIdentite;
    }

    public byte[] getDateMaxValiditePermis() {
        return DateMaxValiditePermis;
    }

    
    
    public byte[] getDateNaissance() {
        return DateNaissance;
    }

    public DefaultTableModel getDm() {
        return dm;
    }

    public DefaultTableModel getDm2() {
        return dm2;
    }

    public byte[] getNumeroNational() {
        return NumeroNational;
    }

    public byte[] getNom() {
        return Nom;
    }

    public byte[] getNumeroPermis() {
        return NumeroPermis;
    }

    public byte[] getPrenom() {
        return Prenom;
    }
    
    
    
    /* Constructeurs */
    public ReponseCONTROLID (int c)
    {
        code =c;
    }
    
    public ReponseCONTROLID(int c, DefaultTableModel dtmdl, byte [] signature, byte [] cléSecreteCryptée, byte [] cléHMACCryptée)
    {
        code =c;
        dm = dtmdl;
        this.Signature = signature;
        this.cléSecreteCrypté = cléSecreteCryptée;
        this.cléHMACCrypté = cléHMACCryptée;
    }
    
    public ReponseCONTROLID(int c, DefaultTableModel dtmdl, byte [] signature)
    {
        code =c;
        dm = dtmdl;
        this.Signature = signature;
    }

    public byte[] getSignature() {
        return Signature;
    }

    public byte[] getCléHMACCrypté() {
        return cléHMACCrypté;
    }

    public byte[] getCléSecreteCrypté() {
        return cléSecreteCrypté;
    }
    
    
    
    public ReponseCONTROLID(int c, DefaultTableModel dtmdl)
    {
        code =c;
        dm = dtmdl;
    }
    
    public ReponseCONTROLID(int c, DefaultTableModel dtmdl, DefaultTableModel dtmdl2)
    {
        code =c;
        dm = dtmdl;
        dm2 = dtmdl2;
    }
    
    public ReponseCONTROLID(int c, byte [] dlmCrypted, byte [] dlmCrypted2)
    {
        code =c;
        this.bytDLM = dlmCrypted;
        this.bytDLM2 = dlmCrypted2;
    }
    
     public ReponseCONTROLID(int c, ArrayList arrayL)
    {
        code =c;
        array = arrayL;
    }

    ReponseCONTROLID(int c, byte[] byteDLM1, int Taille) {
        code = c; this.bytDLM = byteDLM1; this.TailleBloc = Taille;
    }
    
    ReponseCONTROLID(int c, byte[] byteDLM1, byte[] byteDLM2, int Taille) {
        code = c; this.bytDLM = byteDLM1; this.bytDLM2 = byteDLM2; this.TailleBloc = Taille;
    }
    
    /* Getters */
    @Override
    public int getCode()
    {
        return code;
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
    
    public byte [] getByteDLM2 ()
    {
        return bytDLM2;
    }
    
    public int getTaille () 
    {
        return this.TailleBloc;
    }
}
