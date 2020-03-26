package guis;

import AppliClientCompagnie.FenAdmin;
import AppliClientCompagnie.FenClient;
import Applic_Mailing.Applic_Mailing;
import Authentification.VerificateurUsersPasswordHash;
import Outils.LibrairieJDBC;
import RequeteReponseHAFICSA.ReponseHAFICSA;
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

public class FenetreLoginMailing extends javax.swing.JDialog implements Serializable
{

    private VerificateurUsersPasswordHash Sub;
    private Applic_Mailing fenetreClient;
    public Socket cliSock;
    private Properties propServeur;
    boolean fini = false;
    private LibrairieJDBC LibJDBC;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    
    public FenetreLoginMailing(java.awt.Frame parent, boolean modal, Applic_Mailing fenetreMailing, Socket SocketClient) {
        
        super(parent, modal);
        cliSock = SocketClient;
        initComponents();
        setTitle("Login de l'application de Home-Ferries");
        fenetreClient = fenetreMailing ;
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
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
        Connect = new javax.swing.JButton();
        TextMotDePasse = new javax.swing.JPasswordField();
        MotdePasse = new javax.swing.JLabel();
        Username = new javax.swing.JLabel();
        TextFieldUsername = new javax.swing.JTextField();
        PanelMessageBienvenue = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        RetourValidationLogin = new javax.swing.JLabel();

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

        Connect.setText("Se connecter");
        Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectActionPerformed(evt);
            }
        });

        MotdePasse.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        MotdePasse.setText("Mot de passe :");

        Username.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        Username.setText("Nom d'utilisateur :");

        PanelMessageBienvenue.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        PanelMessageBienvenue.setForeground(new java.awt.Color(0, 51, 204));
        PanelMessageBienvenue.setText("Panel d'identification sur Home-Ferries");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        RetourValidationLogin.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        RetourValidationLogin.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(PanelMessageBienvenue, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(TextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(MotdePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RetourValidationLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextMotDePasse, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Connect))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(BoutonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(PanelMessageBienvenue)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MotdePasse)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TextMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(Connect)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RetourValidationLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(BoutonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectActionPerformed

        RequeteHAFICSA req = null;
        ReponseHAFICSA reponse = null;
        
        if (this.TextFieldUsername.getText().equals("") && this.TextMotDePasse.getText().equals(""))
        {
            String msg;
            msg = "Un des deux champs est vide !";
            JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
        }
        else
        {
            this.oos = null;
            this.ois = null;
            try 
            {
                cliSock = new Socket(propServeur.getProperty("adresse"), Integer.valueOf((String) propServeur.getProperty("portEcouteMailing")));

                this.oos = new ObjectOutputStream(cliSock.getOutputStream()); fenetreClient.oos = this.oos;
                this.ois = new ObjectInputStream(cliSock.getInputStream()); fenetreClient.ois = this.ois;
                
                req = new RequeteHAFICSA(0, this.TextFieldUsername.getText(), this.TextMotDePasse.getText());

                this.oos.writeObject(req);
                System.out.println("Jenvoi la demande de connexion");

                reponse = (ReponseHAFICSA)this.ois.readObject();
                
                if (reponse.getCode() == ReponseHAFICSA.LOGIN_OK)
                {
                    fenetreClient.MenuSeDeconnecter.setEnabled(true);
                    fenetreClient.MenuSeConnecter.setEnabled(false);
                    this.dispose();
                }
                else
                {
                    if (reponse.getCode() == ReponseHAFICSA.LOGIN_NOTADMIN)
                    {
                            String msg;
                            msg = "Vous n'etes pas administrateur !";
                            JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                            this.TextFieldUsername.setText(""); 
                            this.TextMotDePasse.setText("");
                            cliSock.close();   
                    }
                    else
                    {
                        String msg;
                        msg = "Mot de passe invalide !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                        this.TextFieldUsername.setText(""); 
                        this.TextMotDePasse.setText("");
                        cliSock.close();                        
                    }

                }
            }
            catch (IOException e)
            { 
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } 
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(FenClient.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }//GEN-LAST:event_ConnectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonQuitter;
    private javax.swing.JButton Connect;
    private javax.swing.JLabel MotdePasse;
    private javax.swing.JLabel PanelMessageBienvenue;
    private javax.swing.JLabel RetourValidationLogin;
    private javax.swing.JTextField TextFieldUsername;
    private javax.swing.JPasswordField TextMotDePasse;
    private javax.swing.JLabel Username;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
