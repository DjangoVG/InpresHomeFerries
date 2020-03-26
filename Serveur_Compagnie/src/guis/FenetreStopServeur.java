package guis;

import AppliClientCompagnie.FenClient;
import Authentification.VerificateurUsersPasswordHash;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import RequeteReponseHAFICSA.RequeteHAFICSA;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FenetreStopServeur extends javax.swing.JDialog implements Serializable
{
    public Socket cliSock;
    private LibrairieJDBC LibJDBC;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    
    public FenetreStopServeur(java.awt.Frame parent, boolean modal, Socket SocketClient, ObjectOutputStream oos, ObjectInputStream ois) {
        
        super(parent, modal);
        cliSock = SocketClient;
        this.oos = oos;
        this.ois = ois;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoutonQuitter = new javax.swing.JButton();
        BoutonValider = new javax.swing.JButton();
        Username = new javax.swing.JLabel();
        TextNombresSecondes = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setMaximumSize(new java.awt.Dimension(277, 88));
        setMinimumSize(new java.awt.Dimension(277, 88));

        BoutonQuitter.setText("Quitter");
        BoutonQuitter.setMaximumSize(new java.awt.Dimension(0, 23));
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        BoutonValider.setText("Valider");
        BoutonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonValiderActionPerformed(evt);
            }
        });

        Username.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Username.setText("Seconde(s) avant arrêt :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(Username)
                        .addGap(7, 7, 7)
                        .addComponent(TextNombresSecondes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(BoutonValider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BoutonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNombresSecondes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonValider))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonValiderActionPerformed

        RequeteHAFICSA req = null;
        
        if (this.TextNombresSecondes.getText().equals(""))
        {
            String msg;
            msg = "Le champ secondes est vide !";
            JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
        }
        else
        {
            try 
            {
                System.out.println("J'envoie la requete de fermeture serveur");
                req = new RequeteHAFICSA(3, Integer.valueOf(TextNombresSecondes.getText()));
                this.oos.writeObject(req);
                this.dispose();
            }
            catch (IOException e) { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } 
        }
    }//GEN-LAST:event_BoutonValiderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonQuitter;
    private javax.swing.JButton BoutonValider;
    private javax.swing.JTextField TextNombresSecondes;
    private javax.swing.JLabel Username;
    // End of variables declaration//GEN-END:variables
}
