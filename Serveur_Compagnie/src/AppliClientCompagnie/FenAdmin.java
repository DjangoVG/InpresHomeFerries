package AppliClientCompagnie;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseCINAP.RequeteCINAP;
import RequeteReponseHAFICSA.RequeteHAFICSA;
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


public class FenAdmin extends javax.swing.JFrame 
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
    private FenetreLoginAdmin fenlogin;
    private FenetreListingClientConnectes fenetreClientConnectes;
    private FenetreStopServeur fenStopServeur;
    
    public FenAdmin() {
        initComponents();
        setTitle("Panel d'administration de Home-Ferries");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        MenuSeDeconnecter.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageFond = new javax.swing.JLabel();
        PanelBienvenueClient = new javax.swing.JLabel();
        BoutonListeClients = new javax.swing.JButton();
        BoutonPauseServeur = new javax.swing.JButton();
        BoutonQuitter = new javax.swing.JButton();
        BoutonStopServeur = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        GrosMenuAgent = new javax.swing.JMenu();
        MenuSeConnecter = new javax.swing.JMenuItem();
        MenuSeDeconnecter = new javax.swing.JMenuItem();
        GrosMenuApropos = new javax.swing.JMenu();
        MenuAuteurs = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageFond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        PanelBienvenueClient.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        PanelBienvenueClient.setForeground(new java.awt.Color(0, 0, 255));
        PanelBienvenueClient.setText("Panel d'administration du serveur Compagnie");

        BoutonListeClients.setBackground(new java.awt.Color(204, 204, 204));
        BoutonListeClients.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonListeClients.setForeground(new java.awt.Color(0, 51, 255));
        BoutonListeClients.setText("Liste des clients connectés");
        BoutonListeClients.setEnabled(false);
        BoutonListeClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonListeClientsActionPerformed(evt);
            }
        });

        BoutonPauseServeur.setBackground(new java.awt.Color(204, 204, 204));
        BoutonPauseServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonPauseServeur.setForeground(new java.awt.Color(0, 51, 255));
        BoutonPauseServeur.setText("Mettre en pause le serveur");
        BoutonPauseServeur.setEnabled(false);
        BoutonPauseServeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonPauseServeurActionPerformed(evt);
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

        BoutonStopServeur.setBackground(new java.awt.Color(204, 204, 204));
        BoutonStopServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonStopServeur.setForeground(new java.awt.Color(0, 51, 255));
        BoutonStopServeur.setText("Stopper le serveur");
        BoutonStopServeur.setEnabled(false);
        BoutonStopServeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonStopServeurActionPerformed(evt);
            }
        });

        GrosMenuAgent.setText("Administrateur");

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(ImageFond, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(PanelBienvenueClient)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BoutonQuitter))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BoutonListeClients)
                        .addGap(18, 18, 18)
                        .addComponent(BoutonPauseServeur, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BoutonStopServeur, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(ImageFond)
                .addGap(6, 6, 6)
                .addComponent(PanelBienvenueClient, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonPauseServeur, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonListeClients, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonStopServeur, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BoutonQuitter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonPauseServeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonPauseServeurActionPerformed
        RequeteHAFICSA req = null;
        req = new RequeteHAFICSA(2);
        try {
            this.oos.writeObject(req);
        } catch (IOException ex) {
            Logger.getLogger(FenAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BoutonPauseServeurActionPerformed

    private void BoutonListeClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonListeClientsActionPerformed
            java.awt.EventQueue.invokeLater(() -> {
                fenetreClientConnectes = new FenetreListingClientConnectes(null,true, this.oos, this.ois);
                fenetreClientConnectes.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                fenetreClientConnectes.setVisible(true);
            });
    }//GEN-LAST:event_BoutonListeClientsActionPerformed

    private void MenuSeConnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeConnecterActionPerformed
        
        java.awt.EventQueue.invokeLater(() -> {
                fenlogin = new FenetreLoginAdmin(null,true, this, cliSock);
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
            Logger.getLogger(FenAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MenuSeConnecter.setEnabled(true);
        MenuSeDeconnecter.setEnabled(false);
        BoutonQuitter.setEnabled(true);
        BoutonPauseServeur.setEnabled(false);
        BoutonListeClients.setEnabled(false);
        
    }//GEN-LAST:event_MenuSeDeconnecterActionPerformed

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

    private void BoutonStopServeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonStopServeurActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
                fenStopServeur = new FenetreStopServeur(null,true, cliSock, this.oos, this.ois);
                fenStopServeur.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                fenStopServeur.setVisible(true);
            });  
    }//GEN-LAST:event_BoutonStopServeurActionPerformed

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
            new FenAdmin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BoutonListeClients;
    public javax.swing.JButton BoutonPauseServeur;
    public javax.swing.JButton BoutonQuitter;
    public javax.swing.JButton BoutonStopServeur;
    private javax.swing.JMenu GrosMenuAgent;
    private javax.swing.JMenu GrosMenuApropos;
    private javax.swing.JLabel ImageFond;
    private javax.swing.JMenuItem MenuAuteurs;
    public javax.swing.JMenuItem MenuSeConnecter;
    public javax.swing.JMenuItem MenuSeDeconnecter;
    private javax.swing.JLabel PanelBienvenueClient;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
