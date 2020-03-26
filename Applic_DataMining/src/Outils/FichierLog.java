package Outils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class FichierLog 
{
    private String nomFichier;
    private String sep;
    private String cheminFichier;

    // Constructeurs.
    public FichierLog (){
    }
    public FichierLog(String str)
    {
        nomFichier = str;
        sep = System.getProperty("file.separator");
        cheminFichier = System.getProperty("user.dir") + sep + "HomeFerries" + sep + "FichierLog";
        File dirLog = new File (cheminFichier);
        boolean dirExistLog = dirLog.exists();
        if (!dirExistLog)                       
            dirLog.mkdirs();
        cheminFichier += sep + str;  
    }

    // Get et set
    public String getNomFichier() { return nomFichier; }
    public void setNomFichier(String nomFichier) { this.nomFichier = nomFichier; }

    public String getSep() { return sep; }
    public void setSep(String sep) { this.sep = sep; }

    public String getCheminFichier() { return cheminFichier; }
    public void setCheminFichier(String cheminFichier) { this.cheminFichier = cheminFichier; }

    // Autres fonctions.
    public void ecritLigne(String line)
    {
        String Date;
        Date Now = new Date();
        Date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE).format(Now);
        try {         
            FileWriter fW = new FileWriter(cheminFichier, true); // true => écriture à la fin du fichier
            BufferedWriter buffW = new BufferedWriter(fW);
            buffW.write("[" + Date + "] : " + line);
            buffW.newLine();
            buffW.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public String litFichier()
    {   
        System.out.println("CXou");
        String sContent = null;
        try {
            FileReader fR = new FileReader(cheminFichier);
            BufferedReader buffR = new BufferedReader(fR);
            String temp = buffR.readLine();
            int i = 0;
            while (temp != null) {
                if (i == 0)
                    sContent = temp + "\n";
                else
                    sContent += temp + "\n";    
                temp = buffR.readLine();
                i++;
            }
            buffR.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return sContent;
    }
}
