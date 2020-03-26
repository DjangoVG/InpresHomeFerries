package applic_datamining;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseANASTAT.RequeteANASTAT;
import guis.FenetreLogin;
import guis.FenetreNewUser;
import guis.FenetreRequete;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Applic_datamining extends javax.swing.JFrame {
    
    
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    Socket cliSock = null;
    private Properties propServeur;
    boolean fini = false;
    public static FichierLog fichierLog;
    private LibrairieJDBC LibJDBC;
    private FenetreLogin fenlogin;
    
    public Applic_datamining() {
        initComponents();
        setTitle("Panel de l'application de Home-Ferries");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        MenuSeDeconnecter.setEnabled(false);
        RequeteA.setEnabled(false);
        RequeteB.setEnabled(false);
        RequeteC.setEnabled(false);
        RequeteD.setEnabled(false);
        RequeteE.setEnabled(false);
        RequeteF.setEnabled(false);
        RequeteG.setEnabled(false);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        ImageFondClient = new javax.swing.JLabel();
        Titre = new javax.swing.JLabel();
        RequeteA = new javax.swing.JButton();
        RequeteB = new javax.swing.JButton();
        RequeteC = new javax.swing.JButton();
        RequeteD = new javax.swing.JButton();
        RequeteE = new javax.swing.JButton();
        RequeteF = new javax.swing.JButton();
        RequeteG = new javax.swing.JButton();
        BoutonQuitter = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        GrosMenuAgent = new javax.swing.JMenu();
        MenuSeConnecter = new javax.swing.JMenuItem();
        MenuSeDeconnecter = new javax.swing.JMenuItem();
        GrosMenuApropos = new javax.swing.JMenu();
        MenuAuteurs = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ImageFondClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ClientDataMining.jpg"))); // NOI18N

        Titre.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Titre.setForeground(new java.awt.Color(51, 102, 255));
        Titre.setText("Application DataMining Home Ferries");

        RequeteA.setText("a)");
        RequeteA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequeteAActionPerformed(evt);
            }
        });

        RequeteB.setText("b)");
        RequeteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequeteBActionPerformed(evt);
            }
        });

        RequeteC.setText("c)");
        RequeteC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequeteCActionPerformed(evt);
            }
        });

        RequeteD.setText("d)");
        RequeteD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequeteDActionPerformed(evt);
            }
        });

        RequeteE.setText("e)");
        RequeteE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequeteEActionPerformed(evt);
            }
        });

        RequeteF.setText("f)");
        RequeteF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequeteFActionPerformed(evt);
            }
        });

        RequeteG.setText("g)");
        RequeteG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequeteGActionPerformed(evt);
            }
        });

        BoutonQuitter.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
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

        jMenuBar1.add(GrosMenuAgent);

        GrosMenuApropos.setText("A propos");

        MenuAuteurs.setText("Auteurs");
        MenuAuteurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAuteursMenuAuteur(evt);
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
                .addComponent(ImageFondClient)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Titre)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RequeteA)
                            .addComponent(RequeteD))
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(RequeteB)
                            .addComponent(RequeteE)
                            .addComponent(RequeteG))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RequeteC)
                            .addComponent(RequeteF)
                            .addComponent(BoutonQuitter))))
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ImageFondClient, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Titre)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RequeteA)
                    .addComponent(RequeteB)
                    .addComponent(RequeteC))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RequeteD)
                    .addComponent(RequeteE)
                    .addComponent(RequeteF))
                .addGap(18, 18, 18)
                .addComponent(RequeteG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(BoutonQuitter)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RequeteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequeteBActionPerformed
        new FenetreRequete(null,true,fenlogin.cliSock, this.ois, this.oos,2).setVisible(true);
    }//GEN-LAST:event_RequeteBActionPerformed

    private void RequeteCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequeteCActionPerformed
        new FenetreRequete(null,true,fenlogin.cliSock, this.ois, this.oos,3).setVisible(true);
    }//GEN-LAST:event_RequeteCActionPerformed

    private void RequeteAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequeteAActionPerformed
        new FenetreRequete(null,true,fenlogin.cliSock, this.ois, this.oos,1).setVisible(true);

    }//GEN-LAST:event_RequeteAActionPerformed

    private void RequeteDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequeteDActionPerformed
        new FenetreRequete(null,true,fenlogin.cliSock, this.ois, this.oos,4).setVisible(true);
    }//GEN-LAST:event_RequeteDActionPerformed

    private void RequeteEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequeteEActionPerformed
        new FenetreRequete(null,true,fenlogin.cliSock, this.ois, this.oos,5).setVisible(true);
    }//GEN-LAST:event_RequeteEActionPerformed

    private void RequeteFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequeteFActionPerformed
        new FenetreRequete(null,true,fenlogin.cliSock, this.ois, this.oos,6).setVisible(true);
    }//GEN-LAST:event_RequeteFActionPerformed

    private void RequeteGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequeteGActionPerformed
        new FenetreRequete(null,true,fenlogin.cliSock, this.ois, this.oos,7).setVisible(true);
    }//GEN-LAST:event_RequeteGActionPerformed

    private void MenuSeConnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeConnecterActionPerformed

            fenlogin = new FenetreLogin(null,true, this, cliSock);
            fenlogin.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            fenlogin.setVisible(true);
    }//GEN-LAST:event_MenuSeConnecterActionPerformed

    private void MenuSeDeconnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeDeconnecterActionPerformed
        RequeteANASTAT req = null;
        req = new RequeteANASTAT(3);

        try {
            this.oos.writeObject(req);
        } catch (IOException ex) {
            Logger.getLogger(Applic_datamining.class.getName()).log(Level.SEVERE, null, ex);
        }

        MenuSeConnecter.setEnabled(true);
        MenuSeDeconnecter.setEnabled(false);
        BoutonQuitter.setEnabled(true);
        RequeteA.setEnabled(false);
        RequeteB.setEnabled(false);
        RequeteC.setEnabled(false);
        RequeteD.setEnabled(false);
        RequeteE.setEnabled(false);
        RequeteF.setEnabled(false);
        RequeteG.setEnabled(false);

    }//GEN-LAST:event_MenuSeDeconnecterActionPerformed

    private void MenuAuteursMenuAuteur(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAuteursMenuAuteur
        String msg;
        msg = "Applic DataMining est un projet qui permet de réaliser des opérations statistiques en raccord avec Home Ferries !\nCe projet a été réalisé par Régis Evrard et Yannis Zekri.";
        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_MenuAuteursMenuAuteur

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String msg;
        msg = "Veuillez vous connecter ! Après cela, cliquer sur le bouton souhaité afin d'avoir le résultat de la requête !";
        JOptionPane.showMessageDialog(this, msg, "Ah !", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    public static void main(String args[]) {
         try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Applic_datamining().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BoutonQuitter;
    private javax.swing.JMenu GrosMenuAgent;
    private javax.swing.JMenu GrosMenuApropos;
    private javax.swing.JLabel ImageFondClient;
    private javax.swing.JMenuItem MenuAuteurs;
    public javax.swing.JMenuItem MenuSeConnecter;
    public javax.swing.JMenuItem MenuSeDeconnecter;
    public javax.swing.JButton RequeteA;
    public javax.swing.JButton RequeteB;
    public javax.swing.JButton RequeteC;
    public javax.swing.JButton RequeteD;
    public javax.swing.JButton RequeteE;
    public javax.swing.JButton RequeteF;
    public javax.swing.JButton RequeteG;
    private javax.swing.JLabel Titre;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
