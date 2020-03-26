/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import Authentification.VerificateurUsersPasswordHash;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import applic_datamining.*;
import RequeteReponseANASTAT.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FenetreNewUser extends javax.swing.JFrame {

    Applic_datamining fenclient;
    private Socket cliSock;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    public FenetreNewUser(Socket SocketClient, ObjectInputStream Objectis, ObjectOutputStream Objectos) {
        initComponents();
        cliSock = SocketClient;
        this.oos = Objectos;
        this.ois = Objectis;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Icon = new javax.swing.JLabel();
        LabelPanel = new javax.swing.JLabel();
        LabelNewUser = new javax.swing.JLabel();
        LabelNewMdp = new javax.swing.JLabel();
        LabelConfirm = new javax.swing.JLabel();
        TextNewUser = new javax.swing.JTextField();
        TextNewPwd = new javax.swing.JPasswordField();
        TextConfirmNewPwd = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        BoutonCreer = new javax.swing.JButton();
        BoutonAnnuler = new javax.swing.JButton();
        BoutonQuitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        LabelPanel.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        LabelPanel.setForeground(new java.awt.Color(51, 102, 255));
        LabelPanel.setText("Ajout d'un nouvel agent");

        LabelNewUser.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        LabelNewUser.setForeground(new java.awt.Color(51, 102, 255));
        LabelNewUser.setText("Nouveau nom d'utilisateur :");

        LabelNewMdp.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        LabelNewMdp.setForeground(new java.awt.Color(51, 102, 255));
        LabelNewMdp.setText("Nouveau mot de passe : ");

        LabelConfirm.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        LabelConfirm.setForeground(new java.awt.Color(51, 102, 255));
        LabelConfirm.setText("Confirmer le nouveau mot de passe : ");

        BoutonCreer.setForeground(new java.awt.Color(0, 102, 255));
        BoutonCreer.setText("Créer");
        BoutonCreer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonCreerActionPerformed(evt);
            }
        });

        BoutonAnnuler.setForeground(new java.awt.Color(0, 102, 255));
        BoutonAnnuler.setText("Annuler");
        BoutonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonAnnulerActionPerformed(evt);
            }
        });

        BoutonQuitter.setForeground(new java.awt.Color(0, 102, 255));
        BoutonQuitter.setText("Quitter");
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelNewUser)
                            .addComponent(LabelNewMdp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextNewUser)
                            .addComponent(TextNewPwd, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelConfirm)
                            .addComponent(BoutonQuitter))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BoutonCreer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BoutonAnnuler))
                            .addComponent(TextConfirmNewPwd))))
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(LabelPanel)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelPanel)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNewUser)
                    .addComponent(TextNewUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNewMdp)
                    .addComponent(TextNewPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelConfirm)
                    .addComponent(TextConfirmNewPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonCreer)
                    .addComponent(BoutonAnnuler)
                    .addComponent(BoutonQuitter))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonAnnulerActionPerformed
        TextNewUser.setText("");
        TextNewPwd.setText("");
        TextConfirmNewPwd.setText("");
    }//GEN-LAST:event_BoutonAnnulerActionPerformed

    private void BoutonCreerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonCreerActionPerformed

        String password1;
        String password2;
        password1 = TextNewPwd.getText(); password2 = TextConfirmNewPwd.getText();
        
        if (password1.equalsIgnoreCase(password2))
        {
            
            RequeteANASTAT req = null;
            ReponseANASTAT reponse = null;
            
            try
            {
                req = new RequeteANASTAT(0, TextNewUser.getText(), password1);
                oos.writeObject(req);
                
                reponse = (ReponseANASTAT)ois.readObject();

                if (reponse.getCode() == ReponseANASTAT.NEWUSER_OK)
                {
                        String msg;
                        msg = "Nouvel agent encodé !";
                        JOptionPane.showMessageDialog(this, msg, "Validation !", JOptionPane.INFORMATION_MESSAGE, null); 
                        this.dispose();
                }
                else
                {
                        String msg;
                        msg = "Problème lors de l'ajout !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null); 
                        TextNewUser.setText("");
                        TextNewPwd.setText("");
                        TextConfirmNewPwd.setText("");
                }
            }
            catch (IOException e)
            { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } 
            catch (ClassNotFoundException ex) 
            { Logger.getLogger(FenetreNewUser.class.getName()).log(Level.SEVERE, null, ex); }
        }
        else
        {
            TextNewPwd.setText("");
            TextConfirmNewPwd.setText("");
            String msg;
            msg = "Les mots de passe ne correspondent pas !";
            JOptionPane.showMessageDialog(this, msg, "A propos", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }//GEN-LAST:event_BoutonCreerActionPerformed

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreNewUser(null, null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonAnnuler;
    private javax.swing.JButton BoutonCreer;
    private javax.swing.JButton BoutonQuitter;
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel LabelConfirm;
    private javax.swing.JLabel LabelNewMdp;
    private javax.swing.JLabel LabelNewUser;
    private javax.swing.JLabel LabelPanel;
    private javax.swing.JPasswordField TextConfirmNewPwd;
    private javax.swing.JPasswordField TextNewPwd;
    private javax.swing.JTextField TextNewUser;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    public VerificateurUsersPasswordHash Sub;
}
