/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequeteReponseANASTAT;

import RequeteReponseMultiThread.Reponse;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class ReponseANASTAT implements Reponse,Serializable{
    
    private int code;
    private int type;
    public String Sum ;
    public int diff;
    public static final int LOGIN_OK = 1 ;
    public static final int LOGIN_KO = 2 ;
    public static final int REQUETE_A_OK = 101 ;
    public static final int REQUETE_A_KO = 102 ;
    public static final int REQUETE_B_OK = 201 ;
    public static final int REQUETE_B_KO = 202 ;
    public static final int REQUETE_C_OK = 301 ;
    public static final int REQUETE_C_KO = 302 ;
    public static final int REQUETE_D_OK = 401 ;
    public static final int REQUETE_D_KO = 402 ;
    public static final int REQUETE_E_OK = 501 ;
    public static final int REQUETE_E_KO = 502 ;
    public static final int REQUETE_F_OK = 601 ;
    public static final int REQUETE_F_KO = 602 ;
    public static final int REQUETE_G_OK = 701 ;
    public static final int REQUETE_G_KO = 702 ;
    public static final int NEWUSER_OK = 3;
    
    public ReponseANASTAT (int c)
    {
        code =c;
    }
    public ReponseANASTAT (int c,String s)
    {
        code =c; Sum = s ;
    }
    public ReponseANASTAT (int c,String s,int a)
    {
        code =c; Sum = s ; diff = a;
    }
    public int getCode()
    {
        return code;
    }
    public String getS()
    {
        return Sum;
    }
    public int getDiff()
    {
        return diff;
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
