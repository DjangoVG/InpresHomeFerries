/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import BeanSymetricKey.BeanHMACKey;
import BeanSymetricKey.BeanSessionKey;
import RequeteCONTROLID.ReponseCONTROLID;
import RequeteCONTROLID.RequeteCONTROLID;
import static RequeteCONTROLID.RequeteCONTROLID.BeanCléHMAC;
import static RequeteCONTROLID.RequeteCONTROLID.BeanCléSession;
import SecurityCrypto.Certif;
import SecurityCrypto.KeyStoreClass;
import java.io.ByteArrayInputStream;
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
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.bouncycastle.jcajce.provider.digest.GOST3411;

public class FenetreControleConducteur extends javax.swing.JDialog {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Certif cer;
    private FenetreControlePassagers fenetreControlePassagers;
    private KeyStoreClass MyKeyStore;
    private Certif certificat;
    private byte [] encryptedMessage;
    private final SecretKey cléSession;
    private final SecretKey cléHMAC;
    private byte [] decrpytedMessage;
    private String DateMaxValiditeIdentite;
    private String DateNaissance;
    private String Nom;
    private String NumeroNational;
    private String Prenom;
    private byte[] MessDecrypter;
    private byte[] bytDLMuncryppted;
    private String DateMaxValiditePermis;
    private String NuméroPermis;
    
   
    public FenetreControleConducteur (java.awt.Frame parent, boolean modal, ObjectInputStream Objectis, ObjectOutputStream Objectos ,SecretKey cléSession, SecretKey cléHMAC)
    {
        super(parent, modal);
        initComponents();
        setTitle("Controle du conducteur");
        System.out.println("Constructeur du FenetreControleConducteur");
        this.oos = Objectos;
        this.ois = Objectis;
        this.BoutonVerifPassagers.setEnabled(false);
        this.cléSession = cléSession;
        this.cléHMAC = cléHMAC;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        LabelTitre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePermisDeConduire = new javax.swing.JTable();
        BoutonQuitter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextCarteIdentite = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextPermis = new javax.swing.JTextField();
        BoutonVerifier = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        BoutonVerifPassagers = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableIdentite = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        CheckBoxBelge = new javax.swing.JCheckBox();
        CheckBoxEtranger = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelTitre.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        LabelTitre.setForeground(new java.awt.Color(0, 51, 255));
        LabelTitre.setText("Controle du conducteur");

        TablePermisDeConduire.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro du permis", "Nom", "Prénom", "Date max. de validité"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablePermisDeConduire);

        BoutonQuitter.setText("Quitter");
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        jLabel1.setText("Carte d'identité/Passeport du conducteur :");

        jLabel2.setText("Permis du conducteur :");

        BoutonVerifier.setText("Vérifier les données");
        BoutonVerifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonVerifierActionPerformed(evt);
            }
        });

        BoutonVerifPassagers.setText("Vérifiez les passagers du véhicule");
        BoutonVerifPassagers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonVerifPassagersActionPerformed(evt);
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

        jLabel3.setText("Nationalité du conducteur :");

        buttonGroup1.add(CheckBoxBelge);
        CheckBoxBelge.setText("Belge");

        buttonGroup1.add(CheckBoxEtranger);
        CheckBoxEtranger.setText("Étrangère");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextPermis, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BoutonVerifPassagers, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(BoutonQuitter)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LabelTitre)
                        .addGap(69, 69, 69)
                        .addComponent(BoutonVerifier))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckBoxEtranger)
                            .addComponent(TextCarteIdentite, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CheckBoxBelge))
                        .addGap(200, 200, 200)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTitre)
                    .addComponent(BoutonVerifier))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CheckBoxBelge))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckBoxEtranger)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextCarteIdentite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextPermis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BoutonVerifPassagers, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BoutonQuitter))
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
            if (!TextCarteIdentite.getText().equals("") && !TextPermis.getText().equals("")) 
            {
               
                    if (!CheckBoxBelge.isSelected() && !CheckBoxEtranger.isSelected())
                    {
                        String msg;
                        msg = "Vous devez choisir la nationalité du conducteur !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                    }
                    else
                    {
                        byte [] bytIdentite = GenerateCrypt(TextCarteIdentite.getText());
                        byte [] bytPermis = GenerateCrypt(TextPermis.getText());
                        byte [] bytType = GenerateCrypt(Integer.toString(4));
                        byte [] bytPays = null;
                        if (CheckBoxBelge.isSelected())
                            bytPays = GenerateCrypt("Belgique");
                        else
                            bytPays = GenerateCrypt("Etranger");
                        

                        req = new RequeteCONTROLID(bytType, bytIdentite,bytPays, bytPermis, null, null, null);
                        oos.writeObject(req);
                        System.out.println("Envoi de la req");
                        reponse = (ReponseCONTROLID)ois.readObject();
                        
                        if (reponse.getCode() == ReponseCONTROLID.LISTEVEHICULES_OK)
                        {
                            if(CheckBoxBelge.isSelected())
                            {
                                NumeroNational = new String(DeCrypteFromNational(reponse.getNumeroNational()));
                            
                                NuméroPermis = new String(DeCrypteFromNational(reponse.getNumeroPermis()));

                                Nom = new String(DeCrypteFromNational(reponse.getNom()));

                                Prenom = new String(DeCrypteFromNational(reponse.getPrenom()));

                                DateNaissance = new String(DeCrypteFromNational(reponse.getDateNaissance()));

                                DateMaxValiditeIdentite= new String(DeCrypteFromNational(reponse.getDateMaxValiditeIdentite()));

                                DateMaxValiditePermis = new String(DeCrypteFromNational(reponse.getDateMaxValiditePermis()));
                            }
                            else
                            {
                                NumeroNational = new String(DeCrypteFromInter(reponse.getNumeroNational()));
                            
                                NuméroPermis = new String(DeCrypteFromInter(reponse.getNumeroPermis()));

                                Nom = new String(DeCrypteFromInter(reponse.getNom()));

                                Prenom = new String(DeCrypteFromInter(reponse.getPrenom()));

                                DateNaissance = new String(DeCrypteFromInter(reponse.getDateNaissance()));

                                DateMaxValiditeIdentite= new String(DeCrypteFromInter(reponse.getDateMaxValiditeIdentite()));

                                DateMaxValiditePermis = new String(DeCrypteFromInter(reponse.getDateMaxValiditePermis()));
                            }
                            
                            
                            
                            DefaultTableModel ModelNumNational= new DefaultTableModel(new String[] {"Numéro national", "Nom", "Prénom", "Date de naissance", "Date max de validité" }, 0);
                            ModelNumNational.addRow(new String [] {NumeroNational.toString(), Nom.toString(), Prenom.toString(), DateNaissance.toString(), DateMaxValiditeIdentite.toString() });
                            TableIdentite.setModel(ModelNumNational);
                            
                            DefaultTableModel ModelNumPermis= new DefaultTableModel(new String[] {"Numéro du permis", "Nom", "Prénom", "Date max de validité" }, 0);
                            ModelNumPermis.addRow(new String [] {NuméroPermis.toString(), Nom.toString(), Prenom.toString(), DateMaxValiditePermis.toString() });
                            TablePermisDeConduire.setModel(ModelNumPermis);
                            this.BoutonVerifPassagers.setEnabled(true);
                        }
                        else
                        {
                            if (reponse.getCode() == ReponseCONTROLID.LISTIDENTITEPERMIS_KO)
                            {
                                String msg;
                                msg = "Le numéro de registre national et le numéro du permis de conduire est inexistant ! ";
                                JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);                                
                            }
                            else
                            {
                                if (reponse.getCode() == ReponseCONTROLID.LISTNUMNATIONAL_KO)
                                {
                                    String msg;
                                    msg = "Le numéro de registre national est inexistant ! ";
                                    JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);                                
                                }
                                else
                                {
                                    if (reponse.getCode() == ReponseCONTROLID.LISTPERMIS_KO)
                                    {
                                        String msg;
                                        msg = "Le numéro de permis de conduire est inexistant ! ";
                                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);                                
                                    }
                                }
                            }
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
            Logger.getLogger(FenetreControleConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BoutonVerifierActionPerformed
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
    private void BoutonVerifPassagersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonVerifPassagersActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            fenetreControlePassagers = new FenetreControlePassagers(null,true, this.ois, this.oos,cléSession,cléHMAC);
            fenetreControlePassagers.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            fenetreControlePassagers.setVisible(true);
        });    
    }//GEN-LAST:event_BoutonVerifPassagersActionPerformed

    private byte [] GenerateCrypt(String AHashed) {
        
        try
        {
            encryptedMessage = null;

            Cipher encrypt = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            encrypt.init(Cipher.ENCRYPT_MODE, cléSession);
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
        
    private byte [] DecryptCode(byte[] ADecrypted) 
    {
        byte[] DecryptedArray = null;
        try {
            
            Cipher decrypt = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            decrypt.init(Cipher.DECRYPT_MODE, this.cléSession);
            
            DecryptedArray = decrypt.doFinal(ADecrypted);
            System.out.println("JE DECRYPTE OK");
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(RequeteCONTROLID.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(FenetreControleConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DecryptedArray;
    }
    
    private Object deserialize(byte [] BytDLM) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(BytDLM);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
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
                FenetreControleConducteur dialog = new FenetreControleConducteur(new javax.swing.JFrame(), true, null, null,null,null);
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
    private javax.swing.JButton BoutonVerifPassagers;
    private javax.swing.JButton BoutonVerifier;
    private javax.swing.JCheckBox CheckBoxBelge;
    private javax.swing.JCheckBox CheckBoxEtranger;
    private javax.swing.JLabel LabelTitre;
    public javax.swing.JTable TableIdentite;
    public javax.swing.JTable TablePermisDeConduire;
    public javax.swing.JTextField TextCarteIdentite;
    public javax.swing.JTextField TextPermis;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
