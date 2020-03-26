package Serveur;

import java.net.*;
import java.awt.*;
import java.io.*;
import javax.swing.JTextArea;

public class ThreadReception extends Thread{
    private String nom;
    private MulticastSocket socketGroupe;
    private JTextArea LMsgRecus;
    
    public ThreadReception (String n, MulticastSocket ms, JTextArea l)
    {
        nom = n; socketGroupe = ms; LMsgRecus = l;
    }
    
    public void run()
    {
        boolean enMarche = true;
        while (enMarche)
        {
            try
            {
                byte[] buf = new byte[1000];
                DatagramPacket dtg = new DatagramPacket(buf, buf.length);
                socketGroupe.receive(dtg);
                LMsgRecus.append(new String (buf).trim());
            }
            catch (IOException e)
            {
                System.out.println("Erreur dans le thread :-( :" + e.getMessage());
                enMarche = false; // fin
            }
        }
    }
    
    
    
    
    
    
    
    
    
}
