package AppliClientCompagnie;

import Outils.FichierLog;
import Outils.LibrairieJDBC;
import guis.FenetreAchatTicket;
import java.net.Socket;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FenClientTest extends javax.swing.JFrame 
{
    Socket cliSock = null;
    StringBuffer message = null;
    FenetreAchatTicket fenetreAchat;
    private Properties propServeur;
    boolean fini = false;
    public static FichierLog fichierLog;
    private LibrairieJDBC LibJDBC;
    private Connection con;
    private Statement instruct;
    
    public FenClientTest() {
        initComponents();
        setTitle("Panel de l'application de Home-Ferries");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();   
    }
    
    public String getTextRequete()
    {
        return TextRequete.getText();
    }
    public void setTextRequete(String s)
    {
        TextRequete.setText(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Icon = new javax.swing.JLabel();
        LabelBienvenue = new javax.swing.JLabel();
        LabelRequete = new javax.swing.JLabel();
        LabelSelect = new javax.swing.JRadioButton();
        LabelUpdate = new javax.swing.JRadioButton();
        LabelInsert = new javax.swing.JRadioButton();
        LabelDelete = new javax.swing.JRadioButton();
        TextRequete = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        BoutonEnvoyer = new javax.swing.JButton();
        ScroolPanel = new javax.swing.JScrollPane();
        TableRequetes = new javax.swing.JTable();
        BoutonQuitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        LabelBienvenue.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        LabelBienvenue.setForeground(new java.awt.Color(0, 51, 255));
        LabelBienvenue.setText("Fenêtre Client pour les requêtes SQL");

        LabelRequete.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelRequete.setForeground(new java.awt.Color(0, 51, 255));
        LabelRequete.setText("Requête à effectuer : ");

        buttonGroup1.add(LabelSelect);
        LabelSelect.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelSelect.setForeground(new java.awt.Color(0, 51, 255));
        LabelSelect.setText("SELECT");

        buttonGroup1.add(LabelUpdate);
        LabelUpdate.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelUpdate.setForeground(new java.awt.Color(0, 51, 255));
        LabelUpdate.setText("UPDATE");

        buttonGroup1.add(LabelInsert);
        LabelInsert.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelInsert.setForeground(new java.awt.Color(0, 51, 255));
        LabelInsert.setText("INSERT");

        buttonGroup1.add(LabelDelete);
        LabelDelete.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelDelete.setForeground(new java.awt.Color(0, 51, 255));
        LabelDelete.setText("DELETE");

        BoutonEnvoyer.setText("Envoyer la requête");
        BoutonEnvoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonEnvoyerActionPerformed(evt);
            }
        });

        TableRequetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ScroolPanel.setViewportView(TableRequetes);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Icon)
                                .addGap(122, 122, 122))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(LabelBienvenue)
                                .addGap(218, 218, 218))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScroolPanel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 778, Short.MAX_VALUE)
                                .addComponent(BoutonQuitter)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(LabelRequete)
                .addGap(18, 18, 18)
                .addComponent(LabelSelect)
                .addGap(18, 18, 18)
                .addComponent(LabelUpdate)
                .addGap(18, 18, 18)
                .addComponent(LabelInsert)
                .addGap(18, 18, 18)
                .addComponent(LabelDelete)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BoutonEnvoyer)
                    .addComponent(TextRequete, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Icon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelBienvenue)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelRequete)
                    .addComponent(LabelSelect)
                    .addComponent(LabelUpdate)
                    .addComponent(LabelInsert)
                    .addComponent(LabelDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextRequete, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(BoutonEnvoyer)
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScroolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BoutonQuitter)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonEnvoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonEnvoyerActionPerformed
        con = LibJDBC.ConnexionToBDCompagnie();
        instruct = LibJDBC.CreationStatementCompagnie(con);
        if (LabelSelect.isSelected())
        {
            LibJDBC.PreparedSelect(this);
        }
        if (LabelUpdate.isSelected() || LabelDelete.isSelected())
        {
            LibJDBC.PreparedDeleteAndUpdate(this);
        }
        if (LabelInsert.isSelected())
        {
            LibJDBC.PreparedInsert(this);
        }
        if (!LabelSelect.isSelected() && !LabelUpdate.isSelected() && !LabelDelete.isSelected() && !LabelInsert.isSelected())
        {
            String msg;
            msg = "Aucune case cochée !";
            JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
        }                          
    }//GEN-LAST:event_BoutonEnvoyerActionPerformed

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenClientTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonEnvoyer;
    private javax.swing.JButton BoutonQuitter;
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel LabelBienvenue;
    private javax.swing.JRadioButton LabelDelete;
    private javax.swing.JRadioButton LabelInsert;
    private javax.swing.JLabel LabelRequete;
    private javax.swing.JRadioButton LabelSelect;
    private javax.swing.JRadioButton LabelUpdate;
    public javax.swing.JScrollPane ScroolPanel;
    public javax.swing.JTable TableRequetes;
    private javax.swing.JTextField TextRequete;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
