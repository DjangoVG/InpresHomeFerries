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
import SecurityCrypto.Certif;
import SecurityCrypto.KeyStoreClass;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class FenetreControleVehicule extends javax.swing.JDialog {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sockClient;
    private FenetreControleConducteur fenetreControlePassagers;
    private static String codeProvider = "BC"; // "BC";
    private Certif cer;
    private KeyStoreClass keystore;
    private KeyStore ks;
    private KeyStoreClass MyKeyStore;
    private Certif certificat;
    private byte[] encryptedMessage;
    private byte[] bytDLM;
    private Object obj;
    private byte[] bytDLMuncryppted;
    private byte[] bytSignature;
    private byte[] ByteSignature;
    private int ReponseType;
    private byte[] decryptedMessage;
    public BeanHMACKey cléHMACBean = null;
    public BeanSessionKey cléSessionBean = null;
    private static SecretKey sk1C;
    private static SecretKey cléHMACC;
    
    public FenetreControleVehicule (java.awt.Frame parent, boolean modal, Socket SocketClient, ObjectInputStream Objectis, ObjectOutputStream Objectos)
    {
        super(parent, modal);
        initComponents();
        setTitle("Controle du véhicule");
        System.out.println("Constructeur du FenControleVehicule");
        sockClient = SocketClient;
        this.oos = Objectos;
        this.ois = Objectis;
        this.BoutonVerifPassagers.setEnabled(false);
        this.cléHMACBean = new BeanHMACKey();
        this.cléSessionBean = new BeanSessionKey();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelTitre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableReservation = new javax.swing.JTable();
        BoutonQuitter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextNumImmatriculation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextPays = new javax.swing.JTextField();
        BoutonRechercher = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        BoutonVerifPassagers = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelTitre.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        LabelTitre.setForeground(new java.awt.Color(0, 51, 255));
        LabelTitre.setText("Controle du véhicule");

        TableReservation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro d'immatriculation", "Assurance", "Propriétaire", "Carte verte"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        jScrollPane1.setViewportView(TableReservation);

        BoutonQuitter.setText("Quitter");
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        jLabel1.setText("Numéro d'immatriculation :  ");

        jLabel2.setText("Pays : ");

        BoutonRechercher.setText("Rechercher");
        BoutonRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonRechercherActionPerformed(evt);
            }
        });

        BoutonVerifPassagers.setText("Vérifiez les passagers du véhicule");
        BoutonVerifPassagers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonVerifPassagersActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kia-rio-750x410.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LabelTitre)
                .addGap(242, 242, 242))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BoutonRechercher)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextNumImmatriculation)
                                    .addComponent(TextPays, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(45, Short.MAX_VALUE))
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BoutonVerifPassagers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonQuitter)
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
                    .addComponent(jLabel1)
                    .addComponent(TextNumImmatriculation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TextPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BoutonRechercher)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonQuitter)
                    .addComponent(BoutonVerifPassagers))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonRechercherActionPerformed
        RequeteCONTROLID req = null;
        ReponseCONTROLID reponse = null;
        
        Security.addProvider(new BouncyCastleProvider());
        try
        {
            if (!TextNumImmatriculation.getText().equals("") && !TextPays.getText().equals("")) 
            {
                    System.out.println("Véhicule !");
                    cer = new Certif();
                    
                    keystore = new KeyStoreClass();
                    ks = keystore.getMyKeyStore();
                    
                    PrivateKey cléPrivée;
                    cléPrivée = (PrivateKey) ks.getKey("regisyannis","1234".toCharArray());
                    
                    Signature s = Signature.getInstance("SHA256withRSA",codeProvider);
                    s.initSign(cléPrivée);
                    s.update(TextNumImmatriculation.getText().getBytes());
                    byte[] signature = s.sign();
                    
                    req = new RequeteCONTROLID(30, TextNumImmatriculation.getText(), TextPays.getText(), signature);
                    
                    oos.writeObject(req);
                    System.out.println("Envoi de la req avec signature");
                    
                    reponse = (ReponseCONTROLID)ois.readObject();
                    ReponseType = reponse.getCode();
                    System.out.println("Reponsecode : " + ReponseType);
                    ByteSignature = reponse.getSignature();
                    System.out.println("ByteSignature : " + ByteSignature);
                    Boolean Check = false;
                    if (TextPays.getText().equalsIgnoreCase("Belgique"))
                        Check = VerifNationalSignature();
                    else
                        Check = VerifInterSignature();
                    
                    if (Check == false)
                    {
                        String msg;
                        msg = "La signature du serveur n'est pas bonne !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                    }
                    else
                    {
                        System.out.println("Recup de la rep avec signature");
                        if (reponse.getCode() == ReponseCONTROLID.LISTEVEHICULES_OK)
                        {
                            System.out.println("-- Je récupere les deux clés générées par le serveur --");
                           
                            
                            byte [] cléHMACDecrypted = DeCrypteFromNationale(reponse.getCléHMACCrypté());
                            byte [] cléSessionDecrpyted = DeCrypteFromNationale(reponse.getCléSecreteCrypté());
                            
                            byte[] decodedHMACKey = Base64.getDecoder().decode(cléHMACDecrypted);
                            byte[] decodedSessionKey = Base64.getDecoder().decode(cléSessionDecrpyted);
                            
                            
                            // rebuild key using SecretKeySpec
                            SecretKey cléSession = new SecretKeySpec(decodedSessionKey, 0, decodedSessionKey.length, "DES"); 
                            sk1C = cléSession;
                            SecretKey cléHMAC = new SecretKeySpec(decodedHMACKey, 0, decodedHMACKey.length, "DES"); 
                            cléHMACC = cléHMAC;
                            TableReservation.setModel(reponse.getModele());
                            this.BoutonVerifPassagers.setEnabled(true);
                        }
                        else
                        {
                            String msg;
                            msg = "L'immatriculation n'existe pas !";
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
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BoutonRechercherActionPerformed

    private byte [] GenerateCrypt(String AHashed) {
        
        try
        {
            encryptedMessage = null;
            MyKeyStore = new KeyStoreClass();
            certificat = new Certif();
            KeyStore ks = MyKeyStore.getMyKeyStore();
            System.out.println("Keystore récupéré : " + ks);
            System.out.println("Obtention de le clé privée du garde");
            PrivateKey cléPrivée;
            cléPrivée = (PrivateKey) ks.getKey("regisyannis", "1234".toCharArray());
            System.out.println(" *** Cle privee recuperee = " + cléPrivée.toString());

            Cipher encrypt=Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, cléPrivée);
            encryptedMessage=encrypt.doFinal(AHashed.getBytes());
            System.out.println("JE CRYPTE OK");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FenetreLoginGarde.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(FenetreControleVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedMessage;
    }
    
    private void BoutonVerifPassagersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonVerifPassagersActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            fenetreControlePassagers = new FenetreControleConducteur(null,true, this.ois, this.oos,sk1C,cléHMACC);
            fenetreControlePassagers.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            fenetreControlePassagers.setVisible(true);
        });  
    }//GEN-LAST:event_BoutonVerifPassagersActionPerformed

    public static void main(String args[]) {

        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenetreControleVehicule dialog = new FenetreControleVehicule(new javax.swing.JFrame(), true, null, null, null);
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
    private javax.swing.JButton BoutonRechercher;
    private javax.swing.JButton BoutonVerifPassagers;
    private javax.swing.JLabel LabelTitre;
    public javax.swing.JTable TableReservation;
    public javax.swing.JTextField TextNumImmatriculation;
    public javax.swing.JTextField TextPays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    private void DeCrypteFromInter(byte [] bytDLM) {
        
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
            bytDLMuncryppted = decrypt.doFinal(bytDLM);
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
    }
    
    private byte [] DeCrypteFromNationale(byte [] CryptedByte) {
        decryptedMessage = null;
        try 
        {
            certificat = new Certif();
            System.out.println("Obtention de le clé publique du serveur national");
            X509Certificate certif = certificat.getCertif("serveurfrontieres.cer");
            
            PublicKey clePublic = certif.getPublicKey();
            System.out.println(" *** Cle public recuperee = " + clePublic.toString() +  " ***");
            certif.verify(clePublic);
            
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, clePublic);
            decryptedMessage = decrypt.doFinal(CryptedByte);
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
        return decryptedMessage;
    }
    
    private Object deserialize(byte [] BytDLM) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(BytDLM);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    
    private Boolean VerifInterSignature() 
    {
        Boolean TestSign = false;
        try {
            certificat = new Certif();
            System.out.println("Obtention de le clé publique du serveur");
            X509Certificate certif = certificat.getCertif("serveurinternational.cer");
            
            PublicKey clePublic = certif.getPublicKey();
            System.out.println(" *** Cle public recuperee = " + clePublic.toString());
            certif.verify(clePublic); 
            
            // Création en local d'une signature
            Signature s = Signature.getInstance("SHA256withRSA", "BC");
            s.initVerify(clePublic);
            s.update(String.valueOf(ReponseType).getBytes());
            
            TestSign = s.verify(ByteSignature);
            System.out.println("Boolean après test ClientControleVehicule : " + TestSign);
        } catch (CertificateException ex) {
            return false;
        } catch (NoSuchAlgorithmException ex) {
            return false;
        } catch (InvalidKeyException ex) {
            return false;
        } catch (NoSuchProviderException ex) {
            return false;
        } catch (SignatureException ex) {
            return false;
        }
        return TestSign;
    }
    
    private Boolean VerifNationalSignature() 
    {
        Boolean TestSign = false;
        try {
            certificat = new Certif();
            System.out.println("Obtention de le clé publique du serveur");
            X509Certificate certif = certificat.getCertif("serveurfrontieres.cer");
            
            PublicKey clePublic = certif.getPublicKey();
            System.out.println(" *** Cle public recuperee = " + clePublic.toString());
            certif.verify(clePublic); 
            
            // Création en local d'une signature
            Signature s = Signature.getInstance("SHA256withRSA", "BC");
            s.initVerify(clePublic);
            s.update(String.valueOf(ReponseType).getBytes());
            
            TestSign = s.verify(ByteSignature);
            System.out.println("Boolean après test ClientControleVehicule : " + TestSign);
        } catch (CertificateException ex) {
            return false;
        } catch (NoSuchAlgorithmException ex) {
            return false;
        } catch (InvalidKeyException ex) {
            return false;
        } catch (NoSuchProviderException ex) {
            return false;
        } catch (SignatureException ex) {
            return false;
        }
        return TestSign;
    }
}
