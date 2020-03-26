package guis;

import applic_datamining.JApplic_Chat;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteReponseFECOP.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Applic_Infos extends javax.swing.JDialog {

    public Socket cliSock;
    private Properties propServeur;
    private LibrairieJDBC LibJDBC;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    public Applic_Infos() 
    {
        initComponents();
        Text2.setEnabled(false);
        setTitle("Application d'informations");
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        try 
        {
                cliSock = new Socket(propServeur.getProperty("adresse"), Integer.valueOf((String) propServeur.getProperty("portEcoute")));
                this.oos = new ObjectOutputStream(cliSock.getOutputStream()); 
                this.ois = new ObjectInputStream(cliSock.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Applic_Infos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoutonQuitter = new javax.swing.JButton();
        BoutonObtenirCours = new javax.swing.JButton();
        PanelMessageBienvenue = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        RetourValidationLogin = new javax.swing.JLabel();
        Text1 = new javax.swing.JTextField();
        Text2 = new javax.swing.JTextField();
        ComboBox1 = new javax.swing.JComboBox<>();
        ComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setMinimumSize(new java.awt.Dimension(610, 420));

        BoutonQuitter.setText("Quitter");
        BoutonQuitter.setMaximumSize(new java.awt.Dimension(0, 23));
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        BoutonObtenirCours.setText("Obtenir le cours monétaire");
        BoutonObtenirCours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonObtenirCoursActionPerformed(evt);
            }
        });

        PanelMessageBienvenue.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        PanelMessageBienvenue.setForeground(new java.awt.Color(0, 51, 204));
        PanelMessageBienvenue.setText("Application d'informations");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        RetourValidationLogin.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        RetourValidationLogin.setForeground(new java.awt.Color(255, 0, 51));

        ComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Euro", "Dollar US", "Yen", "Franc Suisse", "Livre Sterling" }));

        ComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Euro", "Dollar US", "Yen", "Franc Suisse", "Livre Sterling" }));
        ComboBox2.setSelectedItem(2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RetourValidationLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BoutonObtenirCours)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelMessageBienvenue)
                .addGap(151, 151, 151))
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Text1)
                    .addComponent(Text2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(PanelMessageBienvenue)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RetourValidationLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BoutonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BoutonObtenirCours))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonObtenirCoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonObtenirCoursActionPerformed
        
        try 
        {
            RequeteFECOP req = null;
            ReponseFECOP reponse = null;
            
            req = new RequeteFECOP(5, Text1.getText(), this.ComboBox1.getSelectedItem().toString(), this.ComboBox2.getSelectedItem().toString());
            
            this.oos.writeObject(req);
            System.out.println("Jenvoi la demande de connexion");
            reponse = (ReponseFECOP)this.ois.readObject();
            
            Text2.setText(String.valueOf(reponse.getCalculMonetaire()));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Applic_Infos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BoutonObtenirCoursActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Applic_Infos dialog = new Applic_Infos();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonObtenirCours;
    private javax.swing.JButton BoutonQuitter;
    private javax.swing.JComboBox<String> ComboBox1;
    private javax.swing.JComboBox<String> ComboBox2;
    private javax.swing.JLabel PanelMessageBienvenue;
    private javax.swing.JLabel RetourValidationLogin;
    private javax.swing.JTextField Text1;
    private javax.swing.JTextField Text2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
