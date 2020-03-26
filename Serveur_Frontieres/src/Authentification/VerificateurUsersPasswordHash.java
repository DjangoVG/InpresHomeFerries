package Authentification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class VerificateurUsersPasswordHash extends VerificateurMotDePasse {
    private HashMap<String, String> hash;
    private String repertoire;
    private String separateur;
    private File fichier;
    
    public VerificateurUsersPasswordHash (String log){
        super ();
        hash = new HashMap<>();
        repertoire = System.getProperty("user.dir");
        separateur = System.getProperty("file.separator");
        File dir = new File (repertoire + separateur + "HomeFerriesFrontieresNationale" + separateur + "Users");
        boolean dirExist = dir.exists();
        if (!dirExist){
            try {
                hash.put("admin", "1234");
                System.out.println ("Aucun User existe : création du sous dossier avec un admin.");
                dir.mkdirs();
                fichier = new File (dir + separateur + "Users" + log + ".data");
                try (FileOutputStream fos = new FileOutputStream (fichier); ObjectOutputStream oos = new ObjectOutputStream (fos)) {
                    oos.writeObject(hash);
                    oos.flush();
                }
            }
            catch (FileNotFoundException ex){
                System.out.println ("Fichier "+fichier+" non-trouvé lors de l'écriture dans le constructeur Dialogin");
            }
            catch (IOException ex) {
                System.out.println ("IOException Users"+fichier+" lors de l'écriture dans le constructeur Dialogin");
            }            
        }
        else {
            try {
                System.out.println ("Au moins le User admin existe déjà, lecture du fichier correspondant au User encodé.");
                fichier = new File (dir + separateur + "Users" + log + ".data");
                try (FileInputStream fis = new FileInputStream (fichier); ObjectInputStream ois = new ObjectInputStream (fis)) {
                    this.hash = (HashMap) ois.readObject();
                    System.out.println("Login ok ici");
                }
            }
            catch (FileNotFoundException ex) {
                System.out.println ("Fichier Users"+log+".data non-trouvé lors de la lecture");
            }
            catch (IOException ex){
                System.out.println ("IOException Users"+log+".data lors de la lecture");
            }
            catch (ClassNotFoundException ex){
                System.out.println ("ClassNotFoundException Users"+log+".data lors de la lecture");
            }
        }
    }
    
    // Get et Set.
    public String getRepertoire (){ return repertoire; }
    public void setRepertoire (String r){ repertoire = r; }    
    
    public String getSeparateur () { return separateur; }
    public void setSeparateur (String s) { separateur = s; }
    
    public File getFichier () { return fichier; }
    public void setFichier (File f) { fichier = f; }
    
    // Autres fonctions
    public void ajouterLogin (String log, String pwd){
        hash.put(log, pwd);
        try {
            try (FileOutputStream fos = new FileOutputStream (fichier); ObjectOutputStream oos = new ObjectOutputStream (fos)) {
                oos.writeObject(hash);
                oos.flush();
            }
        }
        catch (FileNotFoundException ex){
            System.out.println ("Fichier Users"+log+".data non-trouvé lors de l'écriture dans l'ajout");
        }
        catch (IOException ex) {
            System.out.println ("IOException Users"+log+".data lors de l'écriture dans l'ajout");
        }        
    }
    
    public void modifierLogin (String log, String oldpwd, String newpwd){
        hash.remove(log , oldpwd);
        hash.put (log, newpwd);
        try {
            try (FileOutputStream fos = new FileOutputStream (fichier); ObjectOutputStream oos = new ObjectOutputStream (fos)) {
                oos.writeObject(hash);
                oos.flush();
            }
        }
        catch (FileNotFoundException ex){
            System.out.println ("Fichier Users"+log+".data non-trouvé lors de l'écriture dans l'ajout");
        }
        catch (IOException ex) {
            System.out.println ("IOException Users"+log+".data lors de l'écriture dans l'ajout");
        }        
    }    
    
    @Override
    public boolean findPwd(String log, String pwd) {
        return hash.containsKey(log) && hash.containsValue(pwd);            
    }
}
