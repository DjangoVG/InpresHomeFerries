package Authentification;

import guis.FenetreLogin;

public class ValidationLogin {

     public boolean isValid (String Password, String log)
    {
        if (log.length() <= 1){
            System.out.println("Le login doit faire plus de 2 caracteres !");
            return false;
        }

        String pattern= "^[a-zA-Z]*$";
        if(!log.matches(pattern)){
            System.out.println("La login ne peut contenir que des lettres et vous \naviez entrés : " + log);
            return false;
        }   
        
        if (Password.length() < 3){
            System.out.println("Le mot de passe ne peut être plus petit que 8 caractères \net vous en aviez entrés : " + Password.length());
            return false;
        }
        else
            return true;
    } 
}
