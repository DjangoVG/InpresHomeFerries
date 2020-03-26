/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import AppliClientCompagnie.FenClient;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class FenetreListingReservation extends javax.swing.JDialog {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sockClient;
    
    public FenetreListingReservation (java.awt.Frame parent, boolean modal, Socket SocketClient, ObjectInputStream Objectis, ObjectOutputStream Objectos)
    {
        super(parent, modal);
        initComponents();
        setTitle("Home Ferries' DataBase");
        System.out.println("Constructeur du FenListingReservation");
        sockClient = SocketClient;
        this.oos = Objectos;
        this.ois = Objectis;
        this.BoutonValidate.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelFond = new javax.swing.JLabel();
        LabelTitre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableReservation = new javax.swing.JTable();
        BoutonQuitter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextNumReservation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextNbPassagers = new javax.swing.JTextField();
        BoutonRechercher = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        BoutonValidate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelFond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/servers.width-880.jpg"))); // NOI18N

        LabelTitre.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        LabelTitre.setForeground(new java.awt.Color(0, 51, 255));
        LabelTitre.setText("Home Ferries' Database");

        TableReservation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. Réservation", "Traversée", "Voyageur. Titulaire", "Payé ?", "Check-in Validé ?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableReservation);

        BoutonQuitter.setText("Quitter");
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        jLabel1.setText("Numéro de réservation : ");

        jLabel2.setText("Nombre de passagers : ");

        BoutonRechercher.setText("Rechercher");
        BoutonRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonRechercherActionPerformed(evt);
            }
        });

        BoutonValidate.setText("Valider le check-in");
        BoutonValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonValidateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelFond, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BoutonQuitter))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(LabelTitre))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BoutonRechercher)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TextNumReservation, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                            .addComponent(TextNbPassagers))))
                                .addGap(18, 18, 18)
                                .addComponent(BoutonValidate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LabelFond)
                .addGap(18, 18, 18)
                .addComponent(LabelTitre)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextNumReservation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonValidate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TextNbPassagers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BoutonRechercher)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonQuitter)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonRechercherActionPerformed
        RequeteCINAP req = null;
        ReponseCINAP reponse = null;
        
        try
        {
            if (!TextNumReservation.getText().equals("") && !TextNbPassagers.getText().equals("")) 
            {
                req = new RequeteCINAP(1, TextNumReservation.getText(), TextNbPassagers.getText());
                oos.writeObject(req);
                
                reponse = (ReponseCINAP)ois.readObject();
                
                if (reponse.getCode() == 101)
                {
                    TableReservation.setModel((DefaultTableModel)reponse.getModele2());
                    this.BoutonValidate.setEnabled(true);
                }
                else
                {
                    String msg;
                    msg = "La réservation n'existe pas !";
                    JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                }
            }
            else
            {
                String msg;
                msg = "Un des champs n'est pas rempli !";
                JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
            }
            
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } 
        catch (ClassNotFoundException ex) 
        { Logger.getLogger(FenetreListingReservation.class.getName()).log(Level.SEVERE, null, ex); }
    }//GEN-LAST:event_BoutonRechercherActionPerformed

    private void BoutonValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonValidateActionPerformed
        RequeteCINAP req = null;
        ReponseCINAP reponse = null;
        
        try
        {
            req = new RequeteCINAP(6, TextNumReservation.getText());
            oos.writeObject(req);
            
            reponse = (ReponseCINAP)ois.readObject();
            
            if (reponse.getCode() == 114)
            {
                int NumFile = reponse.getNumFile();
                TableReservation.setModel((DefaultTableModel)reponse.getModele2());
                String msg;
                msg = "Check-in validé ! - Emplacement réservé dans le ferry dans la file "+NumFile;
                JOptionPane.showMessageDialog(this, msg, "Validation !", JOptionPane.INFORMATION_MESSAGE, null);
            }
            if (reponse.getCode() == 115)
            {
                String msg;
                msg = "Le Ferry est complet !";
                JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
            }
            if (reponse.getCode() == 116)
            {
                String msg;
                msg = "Le Check-in a déja été effectué !";
                JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } 
        catch (ClassNotFoundException ex) 
        { Logger.getLogger(FenetreListingReservation.class.getName()).log(Level.SEVERE, null, ex); }        
    }//GEN-LAST:event_BoutonValidateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonQuitter;
    private javax.swing.JButton BoutonRechercher;
    private javax.swing.JButton BoutonValidate;
    private javax.swing.JLabel LabelFond;
    private javax.swing.JLabel LabelTitre;
    public javax.swing.JTable TableReservation;
    public javax.swing.JTextField TextNbPassagers;
    public javax.swing.JTextField TextNumReservation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
