package guis;

import AppliGardeFrontieres.FenGarde;
import Authentification.VerificateurUsersPasswordHash;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import RequeteCONTROLID.ReponseCONTROLID;
import RequeteCONTROLID.RequeteCONTROLID;
import SecurityCrypto.GenerateKey;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.Date;

public class FenetreLoginGarde extends javax.swing.JDialog implements Serializable
{

    private VerificateurUsersPasswordHash Sub;
    private FenGarde fenetreClient;
    public Socket cliSock;
    private Properties propServeur;
    boolean fini = false;
    public static FichierLog fichierLog;
    private LibrairieJDBC LibJDBC;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    private GenerateKey generateKey;
    
    public FenetreLoginGarde(java.awt.Frame parent, boolean modal, FenGarde fenetreclient, Socket SocketClient) {
        
        super(parent, modal);
        cliSock = SocketClient;
        initComponents();
        setTitle("Login de l'application de Home-Ferries");
        fenetreClient = fenetreclient;
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesFrontieresNationale();
    }
    
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
        PanelMessageBienvenue.setText("Panel d'identification sur Home-Frontieres");

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

        RequeteCONTROLID req = null;
        ReponseCONTROLID reponse = null;
        
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
                cliSock = new Socket(propServeur.getProperty("adresse"), Integer.valueOf((String) propServeur.getProperty("portEcoute")));

                this.oos = new ObjectOutputStream(cliSock.getOutputStream()); fenetreClient.oos = this.oos;
                this.ois = new ObjectInputStream(cliSock.getInputStream()); fenetreClient.ois = this.ois;

                byte[] MdpHashé = GenerateSalt(this.TextMotDePasse.getText());
                
                req = new RequeteCONTROLID(2, this.TextFieldUsername.getText(),MdpHashé);

                this.oos.writeObject(req);
                System.out.println("Jenvoi la demande de connexion");

                reponse = (ReponseCONTROLID)this.ois.readObject();
                
                if (reponse.getCode() == ReponseCONTROLID.LOGIN_OK)
                {
                    fenetreClient.MenuSeDeconnecter.setEnabled(true);
                    fenetreClient.MenuSeConnecter.setEnabled(false);
                    fenetreClient.BoutonQuitter.setEnabled(false);
                    fenetreClient.MenuNewUser.setEnabled(true);
                    fenetreClient.BoutonControleVehicule.setEnabled(true);
                    this.dispose();
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
            catch (IOException e)
            { 
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } 
            catch (ClassNotFoundException ex)
            {
                System.out.println("1");
            }
        }
    }//GEN-LAST:event_ConnectActionPerformed

    private byte [] GenerateSalt(String Password) {
        byte[] hashedPassword = null;
        Integer HashCodePassword = Password.hashCode();
        System.out.println("Mot de passe du client après le hash code GenerateSalt FenLoginGarde : " + HashCodePassword);
        String tmp = Integer.toString(HashCodePassword);
        /*SecureRandom random = new SecureRandom();
        byte[]salt = new byte[16];
        random.nextBytes(salt);*/
        
        Date datesalt = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dat = dateFormat.format(datesalt);
        System.out.println("Date du jour : " + dat);
        byte[]salt = dat.getBytes();
        MessageDigest md;
        try 
        {
            md = MessageDigest.getInstance("MD5");
            md.update(salt);
            hashedPassword = md.digest(tmp.getBytes());
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FenetreLoginGarde.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Return du byte salé");
        return hashedPassword;
    }
    
    
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
                FenetreLoginGarde dialog = new FenetreLoginGarde(new javax.swing.JFrame(), true, null, null);
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
