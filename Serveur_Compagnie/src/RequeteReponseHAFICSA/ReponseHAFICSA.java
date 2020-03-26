/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequeteReponseHAFICSA;

import RequeteReponseEBOOP.*;
import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Regis
 */
public class ReponseHAFICSA implements Reponse, Serializable{
    
    private int code;
    private String Resultat ;
    private int NumFile ;
    public static final int LOGIN_OK = 101;
    public static final int LOGIN_KO = 102;
    public static final int LOGIN_NOTADMIN = 103;
    private DefaultTableModel dlm;
    
    public ReponseHAFICSA (int c)
    {
        code =c;
    }
    public ReponseHAFICSA (int c,int i)
    {
        code =c; NumFile = i;
    }
    public ReponseHAFICSA (int c,String res)
    {
        code =c; Resultat = res;
    }
    
    public ReponseHAFICSA (DefaultTableModel dlm)
    {
        this.dlm = dlm;
    }
    
    @Override
    public int getCode() {
        return code;
    }
    
    public String getResultat() {
        return Resultat;
    }

    public DefaultTableModel getDlm() {
        return dlm;
    }

    
    
}
