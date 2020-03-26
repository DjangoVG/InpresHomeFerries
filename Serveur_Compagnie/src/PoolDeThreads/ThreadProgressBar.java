
package PoolDeThreads;

import java.util.logging.Level;
import java.util.logging.Logger;
import serveur_compagnie.FenServer;

public class ThreadProgressBar extends Thread {
    private FenServer fenetreServeur;
    
    
    public ThreadProgressBar(FenServer fs)
    {
        fenetreServeur = fs;
    }
    
    public void run()
    {
        for (int i=0; i<101; i++) 
        {
            fenetreServeur.LabelEtatServeur.setText("Démarrage du serveur...");
            try {
                this.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadProgressBar.class.getName()).log(Level.SEVERE, null, ex);
            }
            fenetreServeur.ProgressDemarrage.setValue(i);
            fenetreServeur.LabelEtatServeur.setText("Serveur en marche !");
        }
    }
}
