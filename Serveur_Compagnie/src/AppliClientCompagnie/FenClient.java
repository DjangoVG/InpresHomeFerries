package AppliClientCompagnie;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseCINAP.RequeteCINAP;
import guis.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class FenClient extends javax.swing.JFrame 
{
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    public Socket cliSock = null;
    StringBuffer message = null;
    FenetreAchatTicket fenetreAchat;
    private Properties propServeur;
    boolean fini = false;
    public static FichierLog fichierLog;
    private LibrairieJDBC LibJDBC;
    private FenetreLogin fenlogin;
    
    public FenClient() {
        initComponents();
        setTitle("Panel de l'application de Home-Ferries");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        MenuSeDeconnecter.setEnabled(false);
        MenuNewUser.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageFond = new javax.swing.JLabel();
        PanelBienvenueClient = new javax.swing.JLabel();
        BoutonVerifReservation = new javax.swing.JButton();
        BoutonAcheterTicket = new javax.swing.JButton();
        BoutonQuitter = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        GrosMenuAgent = new javax.swing.JMenu();
        MenuSeConnecter = new javax.swing.JMenuItem();
        MenuSeDeconnecter = new javax.swing.JMenuItem();
        MenuNewUser = new javax.swing.JMenuItem();
        GrosMenuApropos = new javax.swing.JMenu();
        MenuAuteurs = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageFond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        PanelBienvenueClient.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        PanelBienvenueClient.setForeground(new java.awt.Color(0, 0, 255));
        PanelBienvenueClient.setText("Application d'Home-Ferries");

        BoutonVerifReservation.setBackground(new java.awt.Color(204, 204, 204));
        BoutonVerifReservation.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonVerifReservation.setForeground(new java.awt.Color(0, 51, 255));
        BoutonVerifReservation.setText("Vérifier une réservation");
        BoutonVerifReservation.setEnabled(false);
        BoutonVerifReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonVerifReservationActionPerformed(evt);
            }
        });

        BoutonAcheterTicket.setBackground(new java.awt.Color(204, 204, 204));
        BoutonAcheterTicket.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonAcheterTicket.setForeground(new java.awt.Color(0, 51, 255));
        BoutonAcheterTicket.setText("Acheter un ticket");
        BoutonAcheterTicket.setEnabled(false);
        BoutonAcheterTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonAcheterTicketActionPerformed(evt);
            }
        });

        BoutonQuitter.setBackground(new java.awt.Color(204, 204, 204));
        BoutonQuitter.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonQuitter.setForeground(new java.awt.Color(51, 0, 255));
        BoutonQuitter.setText("Quitter");
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        GrosMenuAgent.setText("Agent");

        MenuSeConnecter.setText("Se connecter");
        MenuSeConnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSeConnecterActionPerformed(evt);
            }
        });
        GrosMenuAgent.add(MenuSeConnecter);

        MenuSeDeconnecter.setText("Se deconnecter");
        MenuSeDeconnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSeDeconnecterActionPerformed(evt);
            }
        });
        GrosMenuAgent.add(MenuSeDeconnecter);

        MenuNewUser.setText("Ajouter un nouvel agent");
        MenuNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuNewUserActionPerformed(evt);
            }
        });
        GrosMenuAgent.add(MenuNewUser);

        jMenuBar1.add(GrosMenuAgent);

        GrosMenuApropos.setText("A propos");

        MenuAuteurs.setText("Auteurs");
        MenuAuteurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAuteur(evt);
            }
        });
        GrosMenuApropos.add(MenuAuteurs);

        jMenuItem1.setText("Aide");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        GrosMenuApropos.add(jMenuItem1);

        jMenuBar1.add(GrosMenuApropos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImageFond, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(PanelBienvenueClient))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(BoutonVerifReservation)
                .addGap(18, 18, 18)
                .addComponent(BoutonAcheterTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(506, 506, 506)
                .addComponent(BoutonQuitter))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(ImageFond)
                .addGap(6, 6, 6)
                .addComponent(PanelBienvenueClient)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BoutonVerifReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonAcheterTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BoutonQuitter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonAcheterTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonAcheterTicketActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new FenetreAchatTicket(null,true,fenlogin.cliSock, this.ois, this.oos).setVisible(true);
        });
    }//GEN-LAST:event_BoutonAcheterTicketActionPerformed

    private void BoutonVerifReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonVerifReservationActionPerformed

        java.awt.EventQueue.invokeLater(() -> {
            new FenetreListingReservation(null,true,fenlogin.cliSock, this.ois, this.oos).setVisible(true);
        });
    }//GEN-LAST:event_BoutonVerifReservationActionPerformed

    private void MenuSeConnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeConnecterActionPerformed
        
        java.awt.EventQueue.invokeLater(() -> {
                fenlogin = new FenetreLogin(null,true, this, cliSock);
                fenlogin.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                fenlogin.setVisible(true);
            });  
    }//GEN-LAST:event_MenuSeConnecterActionPerformed

    private void MenuSeDeconnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeDeconnecterActionPerformed
        RequeteCINAP req = null;
        req = new RequeteCINAP(3);
        
        try {
            this.oos.writeObject(req);
        } catch (IOException ex) {
            Logger.getLogger(FenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MenuSeConnecter.setEnabled(true);
        MenuSeDeconnecter.setEnabled(false);
        BoutonQuitter.setEnabled(true);
        BoutonAcheterTicket.setEnabled(false);
        BoutonVerifReservation.setEnabled(false);
        
    }//GEN-LAST:event_MenuSeDeconnecterActionPerformed

    private void MenuNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNewUserActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new FenetreNewUser(fenlogin.cliSock, this.ois, this.oos).setVisible(true);
        });
    }//GEN-LAST:event_MenuNewUserActionPerformed

    private void MenuAuteur(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAuteur
        String msg;
        msg = "Home Ferries est un projet qui permet de gérer un quai de Ferries !\nCe projet a été réalisé par Régis Evrard et Yannis Zekri.";
        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_MenuAuteur

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String msg;
        msg = "On peut pas faire plus facile.. Nous sommes désolé.";
        JOptionPane.showMessageDialog(this, msg, "Ah !", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new FenClient().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BoutonAcheterTicket;
    public javax.swing.JButton BoutonQuitter;
    public javax.swing.JButton BoutonVerifReservation;
    private javax.swing.JMenu GrosMenuAgent;
    private javax.swing.JMenu GrosMenuApropos;
    private javax.swing.JLabel ImageFond;
    private javax.swing.JMenuItem MenuAuteurs;
    public javax.swing.JMenuItem MenuNewUser;
    public javax.swing.JMenuItem MenuSeConnecter;
    public javax.swing.JMenuItem MenuSeDeconnecter;
    private javax.swing.JLabel PanelBienvenueClient;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
