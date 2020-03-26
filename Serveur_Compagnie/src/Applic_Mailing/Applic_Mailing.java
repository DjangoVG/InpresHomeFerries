package Applic_Mailing;

import RequeteReponseCINAP.RequeteCINAP;


import guis.FenetreLoginAdmin;
import guis.FenetreLoginMailing;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Applic_Mailing extends javax.swing.JDialog {

    private FenetreLoginMailing fenlogin;
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    Socket cliSock = null;
    public Applic_Mailing(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MailEnvoyeur = new javax.swing.JTextField();
        MailDestinataire = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ContenuMail = new javax.swing.JTextField();
        ButtonEnvoyerMail = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        ObjetMail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ButtonJoindreFichier = new javax.swing.JButton();
        CBFichiersJoints = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        PasswordEnvoyeur = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        GrosMenuAgent = new javax.swing.JMenu();
        MenuSeConnecter = new javax.swing.JMenuItem();
        MenuSeDeconnecter = new javax.swing.JMenuItem();
        GrosMenuApropos = new javax.swing.JMenu();
        MenuAuteurs = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Applic Mailing");

        jLabel2.setText("Entrer votre adresse : ");

        jLabel3.setText("Entrer l'adresse du destinataire :");

        jLabel4.setText("Entrer votre message : ");

        ButtonEnvoyerMail.setText("Envoyer Mail");
        ButtonEnvoyerMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEnvoyerMailActionPerformed(evt);
            }
        });

        jLabel5.setText("Entrer l'objet : ");

        jButton1.setText("Afficher liste des mails");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ButtonJoindreFichier.setText("Joindre des fichiers");
        ButtonJoindreFichier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonJoindreFichierActionPerformed(evt);
            }
        });

        jLabel6.setText("Entrer votre mot de passe : ");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(ButtonEnvoyerMail))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ButtonJoindreFichier)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(CBFichiersJoints, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 160, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(235, 235, 235))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(ContenuMail, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MailEnvoyeur, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(ObjetMail))
                                .addGap(34, 34, 34)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PasswordEnvoyeur)))
                        .addGap(80, 80, 80))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MailDestinataire, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(MailEnvoyeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(PasswordEnvoyeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(MailDestinataire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ObjetMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonJoindreFichier)
                    .addComponent(CBFichiersJoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContenuMail, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonEnvoyerMail)
                    .addComponent(jButton1))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuSeConnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeConnecterActionPerformed

        java.awt.EventQueue.invokeLater(() -> {
            fenlogin = new FenetreLoginMailing(null,true, this, cliSock);
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
            Logger.getLogger(Applic_Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }

        MenuSeConnecter.setEnabled(true);
        MenuSeDeconnecter.setEnabled(false);

    }//GEN-LAST:event_MenuSeDeconnecterActionPerformed

    private void MenuAuteursMenuAuteur(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAuteursMenuAuteur
        String msg;
        msg = "Home Ferries est un projet qui permet de gérer un quai de Ferries !\nCe projet a été réalisé par Régis Evrard et Yannis Zekri.";
        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_MenuAuteursMenuAuteur

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String msg;
        msg = "On peut pas faire plus facile.. Nous sommes désolé.";
        JOptionPane.showMessageDialog(this, msg, "Ah !", JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ButtonEnvoyerMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEnvoyerMailActionPerformed
        String Envoyeur = MailEnvoyeur.getText();
        String Destinataire = MailDestinataire.getText();
        
        String password = PasswordEnvoyeur.getText();
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("file.encoding", "iso-8859-1");
        
        Session session = Session.getInstance(prop,new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Envoyeur,password);
            }
        });
        session.setDebug(true);

        try{
            System.out.println("Création du message");
                
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom (new InternetAddress(Envoyeur));
            msg.setRecipient (Message.RecipientType.TO, new InternetAddress (Destinataire));
            msg.setSubject(ObjetMail.getText());

            //Récupérer tout le message Text
            Multipart msgMP = new MimeMultipart();
            MimeBodyPart msgBP = new MimeBodyPart();
            msgBP.setText(ContenuMail.getText());
            msgMP.addBodyPart(msgBP);

            //Récupérer toute les pièces jointes
            for(int i=0; i<CBFichiersJoints.getItemCount();i++)
            {
                msgBP = new MimeBodyPart();
                DataSource so = new FileDataSource (CBFichiersJoints.getItemAt(i));
                msgBP.setDataHandler (new DataHandler (so));
                msgBP.setFileName("Attach-"+i+CBFichiersJoints.getItemAt(i).substring(CBFichiersJoints.getItemAt(i).indexOf('.')));
                msgMP.addBodyPart(msgBP);
            }

            System.out.println("Envoi du message");
            
            msg.setContent(msgMP);
            Transport.send(msg);
            System.out.println("Message envoyé");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ButtonEnvoyerMailActionPerformed

    private void ButtonJoindreFichierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonJoindreFichierActionPerformed
        JFileChooser fc = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir")+"/dataFiles");
        fc.setCurrentDirectory(workingDirectory);
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg/jpeg/gif/csv/txt/doc/xls/docx/xlsx", "jpg", "jpeg", "gif","csv","txt","doc","xls","docx","xlsx");
        fc.setFileFilter(filter);
        fc.showDialog(this, "Ajouter");

        if(fc.getSelectedFile()!= null){
            CBFichiersJoints.addItem(fc.getSelectedFile().toPath().toString());
        }
    }//GEN-LAST:event_ButtonJoindreFichierActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AfficherContenuBoiteMail fenAffBoiteMail = new AfficherContenuBoiteMail(this, true,MailEnvoyeur.getText(),PasswordEnvoyeur.getText());
        fenAffBoiteMail.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Applic_Mailing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Applic_Mailing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Applic_Mailing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Applic_Mailing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Applic_Mailing dialog = new Applic_Mailing(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton ButtonEnvoyerMail;
    private javax.swing.JButton ButtonJoindreFichier;
    private javax.swing.JComboBox<String> CBFichiersJoints;
    private javax.swing.JTextField ContenuMail;
    private javax.swing.JMenu GrosMenuAgent;
    private javax.swing.JMenu GrosMenuApropos;
    private javax.swing.JTextField MailDestinataire;
    private javax.swing.JTextField MailEnvoyeur;
    private javax.swing.JMenuItem MenuAuteurs;
    public javax.swing.JMenuItem MenuSeConnecter;
    public javax.swing.JMenuItem MenuSeDeconnecter;
    private javax.swing.JTextField ObjetMail;
    private javax.swing.JPasswordField PasswordEnvoyeur;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
