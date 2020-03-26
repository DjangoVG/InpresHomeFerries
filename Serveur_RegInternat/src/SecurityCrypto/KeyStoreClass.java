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
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Regis
 */
public class KeyStoreClass 
{
    private KeyStore ks = null;
    
    public KeyStore getMyKeyStore() 
    {
        InputStream inStream = null;

        try 
        {
            String repertoireCourant = System.getProperty("user.dir");
            String separateur = System.getProperty("file.separator");
            
            ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(repertoireCourant + separateur + "CertificatFrontieres"), "1234".toCharArray());
            
        } catch (KeyStoreException ex) {
            Logger.getLogger(KeyStoreClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KeyStoreClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KeyStoreClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyStoreClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(KeyStoreClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ks;
    }
    
}
