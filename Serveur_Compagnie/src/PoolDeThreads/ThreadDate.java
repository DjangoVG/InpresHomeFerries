package PoolDeThreads;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur_compagnie.FenServer;

public class ThreadDate extends Thread {
    private final FenServer fenetreServeur;
    
    
    public ThreadDate(FenServer fs)
    {
        fenetreServeur = fs;
    }
    
    public void run()
    {
        while (true) 
        {
            Date maintenant = new Date ();
            String d = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(maintenant);
            fenetreServeur.LabelDate.setText(d);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadDate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}