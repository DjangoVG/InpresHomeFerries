/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Servlet.LoginCheck;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import JavaBean.UserBean;
import Utilities.SocketHandler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import Outils.LibrairieJDBC;
import java.sql.Connection;

public final class ConnexionForm {

    private String resultat;
    private Map<String, String> erreurs= new HashMap<String, String>();
    private Properties propServeur;
    private Socket MaSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private LibrairieJDBC LibJDBC;

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public UserBean connecterUtilisateur( HttpServletRequest request ) {
        String NumClient = request.getParameter("Username");
        String Password = request.getParameter("Password");
        String NomClient = request.getParameter("NomClient");
        String PrenomClient = request.getParameter("PrenomClient");
        String AdresseClient = request.getParameter("AdresseClient");
        String EmailClient = request.getParameter("EmailClient");

        UserBean user = new UserBean();

        /* Validation du champ email. */
        try {
            validationUsername(NumClient);
        } catch ( Exception e ) {
            setErreur( NumClient, e.getMessage() );
        }
        user.setUsernameUser(NumClient);

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse(Password);
        } catch ( Exception e ) {
            setErreur( Password, e.getMessage() );
        }
        
        try 
        {
            RequeteCINAP req = null;
            ReponseCINAP rep = null;
            LibJDBC = new LibrairieJDBC(); propServeur = LibJDBC.RecupPropertiesCompagnie();/* Récupération des champs du formulaire */
            
            MaSocket = new Socket(propServeur.getProperty("adresse"), Integer.valueOf((String) propServeur.getProperty("portEcoute"))); 
            SocketHandler.setSocket(MaSocket);

            this.oos = new ObjectOutputStream(MaSocket.getOutputStream()); SocketHandler.setOos(oos);
            this.ois = new ObjectInputStream(MaSocket.getInputStream()); SocketHandler.setOis(ois);
            if (NomClient != null)
                req = new RequeteCINAP(7, NumClient, Password, NomClient, PrenomClient, EmailClient, AdresseClient);
            else
                req = new RequeteCINAP(7, NumClient, Password);
            
            this.oos.writeObject(req);
            rep = (ReponseCINAP)ois.readObject();
            if (rep.getCode() == ReponseCINAP.MAKEBOOKING_NEWCLIENT)
            {
                int iNumClient = rep.getNumFile();
                String sNumClient = String.valueOf(iNumClient);
                user.setUsernameUser(sNumClient);
                user.setPasswordUser(Password);
                user.setNumReponse(1);
            }
            if (rep.getCode() == ReponseCINAP.MAKEBOOKING_EXISTCLIENT)
            {
                user.setUsernameUser(NumClient);
                user.setPasswordUser(Password);
                user.setNumReponse(2);
            }
            if (rep.getCode() == ReponseCINAP.MAKEBOOKING_LOGINFAILED)
            {
                request.setAttribute("LoginFailed", "");
                setErreur( Password, "" );
                user.setNumReponse(3);
            }
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(LoginCheck.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return user;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationUsername( String username ) throws Exception {
        /*if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {*/
        if (username != null && !username.matches("^[0-9]*$"))
        {
            throw new Exception( "Merci de saisir un numéro utilisateur valide." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception 
    {
        if ( motDePasse != null ) 
        {
            if ( motDePasse.length() < 3 ) 
            {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } 
        else 
        {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}