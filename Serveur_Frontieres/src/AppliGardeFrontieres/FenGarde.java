package AppliGardeFrontieres;

import guis.FenetreLoginGarde;
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


public class FenGarde extends javax.swing.JFrame 
{
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    Socket cliSock = null;
    StringBuffer message = null;
    FenetreAchatTicket fenetreAchat;
    private Properties propServeur;
    boolean fini = false;
    public static FichierLog fichierLog;
    private LibrairieJDBC LibJDBC;
    private guis.FenetreLoginGarde fenlogin;
    
    public FenGarde() {
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
        PanelBienvenueGarde = new javax.swing.JLabel();
        BoutonControleVehicule = new javax.swing.JButton();
        BoutonQuitter = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        GrosMenuGarde = new javax.swing.JMenu();
        MenuSeConnecter = new javax.swing.JMenuItem();
        MenuSeDeconnecter = new javax.swing.JMenuItem();
        MenuNewUser = new javax.swing.JMenuItem();
        GrosMenuApropos = new javax.swing.JMenu();
        MenuAuteurs = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageFond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        PanelBienvenueGarde.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        PanelBienvenueGarde.setForeground(new java.awt.Color(0, 0, 255));
        PanelBienvenueGarde.setText("Application Frontières");

        BoutonControleVehicule.setBackground(new java.awt.Color(204, 204, 204));
        BoutonControleVehicule.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonControleVehicule.setForeground(new java.awt.Color(0, 51, 255));
        BoutonControleVehicule.setText("Controle d'un véhicule");
        BoutonControleVehicule.setEnabled(false);
        BoutonControleVehicule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonControleVehiculeActionPerformed(evt);
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

        GrosMenuGarde.setText("Garde");

        MenuSeConnecter.setText("Se connecter");
        MenuSeConnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSeConnecterActionPerformed(evt);
            }
        });
        GrosMenuGarde.add(MenuSeConnecter);

        MenuSeDeconnecter.setText("Se deconnecter");
        MenuSeDeconnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSeDeconnecterActionPerformed(evt);
            }
        });
        GrosMenuGarde.add(MenuSeDeconnecter);

        MenuNewUser.setText("Ajouter un nouveau garde");
        MenuNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuNewUserActionPerformed(evt);
            }
        });
        GrosMenuGarde.add(MenuNewUser);

        jMenuBar1.add(GrosMenuGarde);

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ImageFond, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(PanelBienvenueGarde))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(BoutonControleVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(BoutonQuitter)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(PanelBienvenueGarde)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ImageFond)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonControleVehicule, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BoutonQuitter)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonControleVehiculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonControleVehiculeActionPerformed

        java.awt.EventQueue.invokeLater(() -> {
            new FenetreControleVehicule(null,true,fenlogin.cliSock, this.ois, this.oos).setVisible(true);
        });
    }//GEN-LAST:event_BoutonControleVehiculeActionPerformed

    private void MenuSeConnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeConnecterActionPerformed
        
        java.awt.EventQueue.invokeLater(() -> {
                fenlogin = new FenetreLoginGarde(null,true, this, cliSock);
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
            Logger.getLogger(FenGarde.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MenuSeConnecter.setEnabled(true);
        MenuSeDeconnecter.setEnabled(false);
        BoutonQuitter.setEnabled(true);
        BoutonControleVehicule.setEnabled(false);
        
    }//GEN-LAST:event_MenuSeDeconnecterActionPerformed

    private void MenuNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNewUserActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new FenetreNewGarde(fenlogin.cliSock, this.ois, this.oos).setVisible(true);
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
            new FenGarde().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BoutonControleVehicule;
    public javax.swing.JButton BoutonQuitter;
    private javax.swing.JMenu GrosMenuApropos;
    private javax.swing.JMenu GrosMenuGarde;
    private javax.swing.JLabel ImageFond;
    private javax.swing.JMenuItem MenuAuteurs;
    public javax.swing.JMenuItem MenuNewUser;
    public javax.swing.JMenuItem MenuSeConnecter;
    public javax.swing.JMenuItem MenuSeDeconnecter;
    private javax.swing.JLabel PanelBienvenueGarde;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
