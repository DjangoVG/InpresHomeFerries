/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityCrypto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Regis
 */
public class Certif 
{

    private X509Certificate MyCertif;
    
    public Certif(){}
    
    public X509Certificate getCertif(String Cert) {
        InputStream inStream = null;

        try 
        {
            String repertoireCourant = System.getProperty("user.dir");
            String separateur = System.getProperty("file.separator");
            
            inStream = new FileInputStream(repertoireCourant + separateur + Cert);
            CertificateFactory cf = null;
            MyCertif = null;
            cf = CertificateFactory.getInstance("X.509");
            MyCertif = (X509Certificate)cf.generateCertificate(inStream);
            System.out.println("Classe instanciée : " + MyCertif.getClass().getName());
            System.out.println("Type de certificat : " + MyCertif.getType());
            System.out.println("Nom du propriétaire du certificat : " +
            MyCertif.getSubjectDN().getName());
            System.out.println("Dates limites de validité : [" + MyCertif.getNotBefore() + " - " +
            MyCertif.getNotAfter() + "]");
            System.out.println("Signataire du certificat : " + MyCertif.getIssuerDN().getName());

            System.out.println("Algo de signature : " + MyCertif.getSigAlgName());
            System.out.println("Signature : " + MyCertif.getSignature());
            MyCertif.checkValidity();
            inStream.close(); 
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Certif.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(Certif.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Certif.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MyCertif;        
    }

}
