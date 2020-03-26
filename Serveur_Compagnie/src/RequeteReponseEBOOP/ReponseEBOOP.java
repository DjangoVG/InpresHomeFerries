/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequeteReponseEBOOP;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;

/**
 *
 * @author Regis
 */
public class ReponseEBOOP implements Reponse, Serializable{
    
    private int code;
    private String Resultat ;
    private int NumFile ;
    public static final int PAYEMENTOK = 801;
    public static final int PAYEMENTKO_CARTENOTFOUND = 802;
    public static final int PAYEMENTKO_SOLDETOOLOW = 803;
    public static final int PAYEMENTKO_DEBITTOOMUCH = 804;
    public static final int PAYEMENTKO_CLIENTNOTFOUND = 805;
    public static final int VERIFFILE_OK = 806;
    public static final int VERIFFILE_FERRYCOMPLET = 807;
    
    public ReponseEBOOP (int c)
    {
        code =c;
    }
    public ReponseEBOOP (int c,int i)
    {
        code =c; NumFile = i;
    }
    public ReponseEBOOP (int c,String res)
    {
        code =c; Resultat = res;
    }
    
    @Override
    public int getCode() {
        return code;
    }
    
    public String getResultat() {
        return Resultat;
    }

}
