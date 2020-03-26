package guis;

import RequeteCONTROLID.ReponseCONTROLID;
import RequeteCONTROLID.RequeteCONTROLID;
import SecurityCrypto.Certif;
import SecurityCrypto.KeyStoreClass;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class FenetreControlePassagers extends javax.swing.JDialog {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private byte [] encryptedMessage;
    private Certif certificat;
    private KeyStoreClass MyKeyStore;
    private SecretKey cléSession;
    private SecretKey cléHMAC;
    private byte[] MessDecrypter;
    private String NumeroNational;
    private String Nom;
    private String Prenom;
    private String DateNaissance;
    private String DateMaxValiditeIdentite;
    
    public FenetreControlePassagers (java.awt.Frame parent, boolean modal, ObjectInputStream Objectis, ObjectOutputStream Objectos,SecretKey cléSession, SecretKey cléHMAC)
    {
        super(parent, modal);
        initComponents();
        setTitle("Controle des passagers");
        System.out.println("Constructeur du FenetreControlePassagers");
        this.oos = Objectos;
        this.ois = Objectis;
        this.cléSession = cléSession;
        this.cléHMAC = cléHMAC;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        LabelTitre = new javax.swing.JLabel();
        BoutonQuitter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextCarteIdentite = new javax.swing.JTextField();
        BoutonVerifier = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableIdentite = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        CheckBoxBelge = new javax.swing.JCheckBox();
        CheckBoxEtranger = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelTitre.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        LabelTitre.setForeground(new java.awt.Color(0, 51, 255));
        LabelTitre.setText("Controle des passagers à bord");

        BoutonQuitter.setText("Quitter");
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        jLabel1.setText("Carte d'identité/Passeport du passager :");

        BoutonVerifier.setText("Vérifier les données");
        BoutonVerifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonVerifierActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kia-rio-750x410.jpg"))); // NOI18N

        TableIdentite.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. Registre National", "Nom", "Prénom", "Date de naissance", "Date max de validité"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableIdentite);

        jLabel3.setText("Nationalité du passager :");

        buttonGroup1.add(CheckBoxBelge);
        CheckBoxBelge.setText("Belge");

        buttonGroup1.add(CheckBoxEtranger);
        CheckBoxEtranger.setText("Étrangère");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(665, Short.MAX_VALUE)
                .addComponent(BoutonQuitter)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CheckBoxBelge)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CheckBoxEtranger)
                                .addGap(212, 212, 212))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextCarteIdentite, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BoutonVerifier))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelTitre)
                                .addGap(153, 153, 153))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelTitre)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CheckBoxBelge)
                    .addComponent(CheckBoxEtranger))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextCarteIdentite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoutonVerifier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BoutonQuitter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonVerifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonVerifierActionPerformed
        RequeteCONTROLID req = null;
        ReponseCONTROLID reponse = null;
        
        try
        {
            if (!TextCarteIdentite.getText().equals("")) 
            {
                if (!CheckBoxBelge.isSelected() && !CheckBoxEtranger.isSelected())
                {
                    String msg;
                    msg = "Vous devez choisir la nationalité du passager !";
                    JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                }
                else
                {

                    byte [] bytIdentite = GenerateCrypt(TextCarteIdentite.getText());
                    byte [] bytType = GenerateCrypt(Integer.toString(5));
                    byte [] bytPays = null;
                    if (CheckBoxBelge.isSelected())
                        bytPays = GenerateCrypt("Belgique");
                    else
                        bytPays = GenerateCrypt("Etranger");
                    //byte [] bytCléSession = GenerateCrypt(cléSession.getEncoded().toString());
                    //byte [] bytCléHMAC = GenerateCrypt(cléHMAC.getEncoded().toString());

                    req = new RequeteCONTROLID(bytType, bytIdentite,bytPays, null, null, null, null,1);

                    oos.writeObject(req);
                    System.out.println("Envoi de la req");
                    reponse = (ReponseCONTROLID)ois.readObject();
                    System.out.println("Recup de la rep");
                    if (reponse.getCode() == ReponseCONTROLID.LISTPASSAGER_OK)
                    {
                       if(CheckBoxBelge.isSelected())
                       {
                            NumeroNational= new String(DeCrypteFromNational(reponse.getNumeroNational()));
                            Nom= new String(DeCrypteFromNational(reponse.getNom()));
                            Prenom= new String(DeCrypteFromNational(reponse.getPrenom()));
                            DateNaissance= new String(DeCrypteFromNational(reponse.getDateNaissance()));
                            DateMaxValiditeIdentite= new String(DeCrypteFromNational(reponse.getDateMaxValiditeIdentite()));
                       }
                       else
                       {
                           System.out.println("1");
                            NumeroNational= new String(DeCrypteFromInter(reponse.getNumeroNational()));System.out.println("2");
                            Nom= new String(DeCrypteFromInter(reponse.getNom()));System.out.println("11");
                            Prenom= new String(DeCrypteFromInter(reponse.getPrenom()));System.out.println("111");
                            DateNaissance= new String(DeCrypteFromInter(reponse.getDateNaissance()));System.out.println("1111");
                            DateMaxValiditeIdentite= new String(DeCrypteFromInter(reponse.getDateMaxValiditeIdentite()));System.out.println("11111");
                       }
                       

                        
                       DefaultTableModel ModelNumNational= new DefaultTableModel(new String[] {"Numéro national", "Nom", "Prénom", "Date de naissance", "Date max de validité" }, 0);
                       ModelNumNational.addRow(new String [] {NumeroNational.toString(), Nom.toString(), Prenom.toString(), DateNaissance.toString(), DateMaxValiditeIdentite.toString() });
                       TableIdentite.setModel((DefaultTableModel)ModelNumNational);
                    }
                    else
                    {
                        String msg;
                        msg = "Le numéro de registre national est inexistant ! ";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                    }
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
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); } catch (ClassNotFoundException ex) {
            Logger.getLogger(FenetreControlePassagers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BoutonVerifierActionPerformed

    private byte [] DeCrypteFromInter(byte [] Adecrypter) {
        
        try 
        {
            certificat = new Certif();
            System.out.println("Obtention de le clé publique du garde");
            X509Certificate certif = certificat.getCertif("serveurinternational.cer");
            
            PublicKey clePublic = certif.getPublicKey();
            System.out.println(" *** Cle public recuperee = " + clePublic.toString());
            certif.verify(clePublic);
            
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, clePublic);
            MessDecrypter = decrypt.doFinal(Adecrypter);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(SignatureException exs)
        {
            System.out.println("SignatureException : " + exs);
        } catch (CertificateException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MessDecrypter;
    }
    private byte [] DeCrypteFromNational(byte [] Adecrypter) {
        
        try 
        {
            certificat = new Certif();
            System.out.println("Obtention de le clé publique du garde");
            X509Certificate certif = certificat.getCertif("serveurfrontieres.cer");
            
            PublicKey clePublic = certif.getPublicKey();
            System.out.println(" *** Cle public recuperee = " + clePublic.toString());
            certif.verify(clePublic);
            
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, clePublic);
            MessDecrypter = decrypt.doFinal(Adecrypter);
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(SignatureException exs)
        {
            System.out.println("SignatureException : " + exs);
        } catch (CertificateException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MessDecrypter;
    }
     private byte [] GenerateCrypt(String AHashed) {
        
        try
        {
            encryptedMessage = null;

            Cipher encrypt = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            encrypt.init(Cipher.ENCRYPT_MODE, cléHMAC);
            encryptedMessage=encrypt.doFinal(AHashed.getBytes());
            System.out.println("JE CRYPTE OK");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FenetreLoginGarde.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(FenetreControleConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedMessage;
    }
    
    public static void main(String args[]) {

        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenetreControlePassagers dialog = new FenetreControlePassagers(new javax.swing.JFrame(), true, null, null,null,null);
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
    private javax.swing.JButton BoutonVerifier;
    private javax.swing.JCheckBox CheckBoxBelge;
    private javax.swing.JCheckBox CheckBoxEtranger;
    private javax.swing.JLabel LabelTitre;
    public javax.swing.JTable TableIdentite;
    public javax.swing.JTextField TextCarteIdentite;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
