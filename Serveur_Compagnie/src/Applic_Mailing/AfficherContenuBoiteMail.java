/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Mailing;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.table.DefaultTableModel;

public class AfficherContenuBoiteMail extends javax.swing.JDialog {
    String AddresseMail = null;
    String Password = null;
    DefaultTableModel ModelMail= new DefaultTableModel(new String[] {"Env", "DateEnvoie", "Objet", "Contenu", "NbrPiècesjointes" }, 0);
    
    public AfficherContenuBoiteMail(Applic_Mailing parent, boolean modal,String AddresseMail,String Password) {
        super(parent, modal);
        initComponents();
        this.AddresseMail = AddresseMail;
        this.Password = Password;
        RecupMailBoiteMail();
    }

    private void RecupMailBoiteMail()
    {
        try {
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
                    return new PasswordAuthentication(AddresseMail,Password);
                }
            });
            session.setDebug(true);
            
            Store boite = session.getStore("imaps");
            boite.connect("imap.gmail.com",AddresseMail,Password);
            
            Folder dossierBoite = boite.getFolder("inbox");
            dossierBoite.open(Folder.READ_ONLY);
            
            
            Message [] msg = dossierBoite.getMessages();
            
            int CompteurMail = 0;
            
            for(Message m : msg)
            {
                String Env = null;
                String Objet = null;
                String Contenu = null;
                String NbrPiècesjointes = null;
                String DateEnvoie = null;
                int NombreJoint = 0;
                Env = m.getFrom()[0].toString();
                DateEnvoie = m.getSentDate().toString();
                Objet = m.getSubject().toString();
                
                if(m.isMimeType("text/html") || m.isMimeType("text/plain"))
                {
                    Contenu = m.getContent().toString();
                } 
                else 
                {
                    Multipart mMP = (Multipart)m.getContent(); // Récupérer les bodyPart
                    for (int i=0; i<mMP.getCount(); i++) //Pour tt les parties si en +ieurs parties
                    {
                        Part p = mMP.getBodyPart(i);
                        if (p.isMimeType("text/plain") || m.isMimeType("text/html"))
                        {
                            if(Contenu == null)
                                Contenu = p.getContent().toString();
                            else
                                Contenu = Contenu + "\n" + p.getContent().toString();
                        }
                        NombreJoint++;
                    }
                    
                }
                if(NombreJoint == 0)
                    NbrPiècesjointes = Integer.toString(NombreJoint);
                else
                    NbrPiècesjointes = Integer.toString(NombreJoint-1);
                CompteurMail++;
                System.out.println("NbreMail + " + CompteurMail);
                
                ModelMail.addRow(new String [] {Env,DateEnvoie,Objet,Contenu, NbrPiècesjointes });
                
            }
            TableMail.setModel((DefaultTableModel)ModelMail);
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableMail = new javax.swing.JTable();
        ButtonPieceJointes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Contenu de la boite mail");

        TableMail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Envoyeur", "DateEnvoie", "Objet", "Contenu", "Nombre Pièces jointes"
            }
        ));
        jScrollPane1.setViewportView(TableMail);

        ButtonPieceJointes.setText("Récupérer les pièces jointes");
        ButtonPieceJointes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPieceJointesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ButtonPieceJointes)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonPieceJointes)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPieceJointesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPieceJointesActionPerformed
        
        try 
        {
            int NumMailVoulu = TableMail.getSelectedRow();
            System.out.println("Numéro du mail  : "  + NumMailVoulu);
            if(!(boolean)TableMail.getValueAt(NumMailVoulu, 4).equals("0"))
            {
                TableMail.getValueAt(TableMail.getSelectedRow(),1);
            
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
                        return new PasswordAuthentication(AddresseMail,Password);
                    }
                });
                session.setDebug(true);

                Store boite = session.getStore("imaps");
                boite.connect("imap.gmail.com",AddresseMail,Password);

                Folder dossierBoite = boite.getFolder("inbox");
                dossierBoite.open(Folder.READ_ONLY);

                Message [] msg = dossierBoite.getMessages();

                int CompteurMail = 0;
                for(Message m : msg)
                {
                    if(!(m.isMimeType("text/plain") || m.isMimeType("text/html")))
                    {
                        Multipart mMP = (Multipart)m.getContent(); // Récup des bodyPart
                        for (int i=0; i<mMP.getCount(); i++)
                        { 
                            if(i == NumMailVoulu)
                            {
                                Part p = mMP.getBodyPart(i);
                                if (p.getDisposition()!=null && p.getDisposition().equalsIgnoreCase(Part.ATTACHMENT))
                                {
                                    InputStream is = p.getInputStream();
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    int c;
                                    while ((c = is.read()) != -1){
                                        baos.write(c);
                                    }
                                    baos.flush();
                                    String nf = p.getFileName();
                                    FileOutputStream fos =new FileOutputStream(System.getProperty("user.dir")+"/dataFiles/"+nf);
                                    baos.writeTo(fos);
                                    fos.close();
                                    baos.close();
                                }
                            }
                        }
                    }
                    CompteurMail++;
                    System.out.println("Compteur Mail DL PIECE JOINTE : " + CompteurMail);
                }
                
            }
            
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonPieceJointesActionPerformed

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
            java.util.logging.Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AfficherContenuBoiteMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AfficherContenuBoiteMail dialog = new AfficherContenuBoiteMail(null, true,null,null);
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
    private javax.swing.JButton ButtonPieceJointes;
    private javax.swing.JTable TableMail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
