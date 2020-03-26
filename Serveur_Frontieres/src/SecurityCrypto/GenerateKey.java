/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityCrypto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.*;

/**
 *
 * @author Regis
 */
public class GenerateKey {
    private static String codeProvider = "BC"; //CryptixCrypto";
    private static final SecureRandom prng = new SecureRandom();
    
    public void GenerateKeyPaire() 
    {
        String separateur = System.getProperty("file.separator");
        String repertoireCourant = System.getProperty("user.dir");
        System.out.println("GENERATION D'UNE PAIRE DE CLÉ");
        try
        {
        // Génération des clés

        System.out.println("Tentative d'obtention d'un generateur de cle");
        KeyPairGenerator genCles = KeyPairGenerator.getInstance("RSA",
        codeProvider);
        System.out.println("Tentative d'initialisation du generateur de cle");
        int se = 512; // par exemple
        genCles.initialize(se, prng);
        System.out.println("Tentative d'obtention de cles");
        KeyPair deuxCles = genCles.generateKeyPair();
        PublicKey cléPublique = deuxCles.getPublic();
        PrivateKey cléPrivee = deuxCles.getPrivate();
        System.out.println(" *** Cle publique generee = " + cléPublique);
        System.out.println(" *** Cle privee generee = " + cléPrivee);
        // Sérialisation de clés
        System.out.println(" *** Cle publique generee serialisee");
            System.out.println("Chemin : " + repertoireCourant + separateur);
        ObjectOutputStream cléPubliqueFich = new ObjectOutputStream(new FileOutputStream(repertoireCourant +  separateur + "xp.ser"));
        System.out.println("fichier ouvert");
        cléPubliqueFich.writeObject(cléPublique);
        System.out.println("cle ecrite");
        cléPubliqueFich.close();
        System.out.println(" *** Cle privee generee serialisee ***");
        ObjectOutputStream cléPrivéeFich = new ObjectOutputStream(new FileOutputStream(repertoireCourant +  separateur + "xs.ser"));
        cléPrivéeFich.writeObject(cléPrivee);
        cléPrivéeFich.close();
        }
        catch (NoSuchAlgorithmException e)
        { System.out.println("Aie aie " + e.getMessage()); }
        catch (FileNotFoundException e)
        { System.out.println("Aie aie fichier non trouvé " + e.getMessage()); }
        catch (Exception e)
        { System.out.println("Aie aie imprévut ou " + e.getMessage()+ " -- " + e.getClass());}
    }
}
