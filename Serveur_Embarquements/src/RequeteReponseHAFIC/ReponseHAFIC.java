package RequeteReponseHAFIC;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;

public class ReponseHAFIC implements Reponse, Serializable
{   
    private int code;
    private int i;
    public static final int VERIFFILE_OK = 15;
    public static final int VERIFFILE_FERRYCOMPLET = 16;
    public static final int CONNECT_OK = 17;
    public static final int CONNECT_KO = 18;
    public static final int VERIFFILE = 19;
    public static final int VIDEFILE_FILEDEJAVIDE = 20;
    public static final int VIDEFILE_FILENOTIN = 21;
    public static final int VIDEFILE_FILEOK = 22;
    public static final int BADFILE_CHANGE = 23;
    public static final int BADFILE_RESTE = 24;
    public static final int FULLFILE_OK = 25;
    public static final int FULLFILE_KO = 26;
    
    /* Constructeurs */
    public ReponseHAFIC (int c)
    {
        code =c;
    }
    
    public ReponseHAFIC (int c, int file)
    {
        code =c; i = file;
    }
    /* Getters */
    @Override
    public int getCode()
    {
        return code;
    }

    @Override
    public String getRes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHeure() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomFerry() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumFile() {
        return i;
    }
    
    @Override
    public int getPourcentage() {
        return i;
    }
}
