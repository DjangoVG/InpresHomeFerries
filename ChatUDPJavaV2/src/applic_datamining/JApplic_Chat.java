package applic_datamining;

import Outils.LibrairieJDBC;
import RequeteReponseFECOP.*;
import Serveur.ThreadClient;
import Serveur.ThreadReception;
import guis.FenetreLogin;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.*;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class JApplic_Chat extends javax.swing.JFrame {

    public static JApplic_Chat client;
    private InetAddress adresseGroupe;
    MulticastSocket socketGroupe;
    ThreadReception thr;
    int port = 50010;
    String NomClient ;
    ThreadClient thrcli ;
    Properties propServeur;
    public Socket cliSock;
    
    private LibrairieJDBC LibJDBC;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public DataInputStream dis;
    public DataOutputStream dos;
    private String NumClient;
    private FenetreLogin fenlogin;
    /** Creates new form Client */
    public JApplic_Chat() {
        initComponents();
        MenuSeConnecter.setEnabled(true);
        Security.addProvider(new BouncyCastleProvider());
        client = this;
        connect.setEnabled(true);
        nomClient.setEnabled(true);
        input.setEnabled(false);
        envoyer.setEnabled(false);
        Quitter.setEnabled(false);
        jtaMessage.setEnabled(false);
        LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
        try {
            dos = new DataOutputStream(cliSock.getOutputStream());
            dis = new DataInputStream(cliSock.getInputStream());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomClient = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        envoyer = new javax.swing.JButton();
        connect = new javax.swing.JButton();
        Quitter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMessage = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jtfNumClient = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        GrosMenuAgent = new javax.swing.JMenu();
        MenuSeConnecter = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Nom :");

        envoyer.setText("Envoyer");
        envoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envoyerActionPerformed(evt);
            }
        });

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        Quitter.setText("Quitter");
        Quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitterActionPerformed(evt);
            }
        });

        jtaMessage.setColumns(20);
        jtaMessage.setForeground(new java.awt.Color(0, 0, 0));
        jtaMessage.setRows(5);
        jtaMessage.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jtaMessage);

        jLabel1.setText("Numero Client : ");

        GrosMenuAgent.setText("Agent");

        MenuSeConnecter.setText("Se connecter");
        MenuSeConnecter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSeConnecterActionPerformed(evt);
            }
        });
        GrosMenuAgent.add(MenuSeConnecter);

        jMenuBar1.add(GrosMenuAgent);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(input, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(envoyer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(nomClient))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfNumClient, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(connect)
                        .addGap(27, 27, 27)
                        .addComponent(Quitter)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Quitter)
                            .addComponent(connect))))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfNumClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(envoyer)
                    .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        
        RequeteFECOP req = null;
        ReponseFECOP reponse = null;
        NomClient = nomClient.getText();  
        if(NomClient.equals(""))
        {
            String msg;
            msg = "Un des deux champs est vide !";
            JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
        }
        else
        {
            try 
            {
                
                //Début Création Digest
                MessageDigest md = MessageDigest.getInstance("SHA-256", "BC");
                System.out.println("md : " + md);
                System.out.println("NomClient : " + NomClient);
                md.update(NomClient.getBytes());
                //md.update(NumClient.getBytes());
                
                long temps = (new Date()).getTime();
                double alea = Math.random();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream bdos = new DataOutputStream(baos);
                bdos.writeLong(temps); bdos.writeDouble(alea);
                
                md.update(baos.toByteArray());
                byte[] msgD = md.digest();
                //Fin création Digest
                
                dos = new DataOutputStream(fenlogin.cliSock.getOutputStream());
                System.out.println("Envoi du message digest");
                dos.writeUTF(NomClient);
                //dos.writeUTF(NumClient); //A modifier en mdp ?
                dos.writeLong(temps);
                dos.writeDouble(alea);
                dos.writeInt(msgD.length);
                dos.write(msgD);
                System.out.println("issou");
                //Message Digest Envoyé*/
                System.out.println("issou2");
                oos = new ObjectOutputStream(fenlogin.cliSock.getOutputStream());
                req = new RequeteFECOP(0);
                oos.writeObject(req);
                
                System.out.println("Message Digest envoyé par le client !");
                
                dis = new DataInputStream(fenlogin.cliSock.getInputStream());
                String reponseServ = dis.readUTF();
                System.out.println("Réponse du serveur = " + reponseServ);
                
                if(reponseServ.equals("OK"))
                {
                    try {
                        adresseGroupe = InetAddress.getByName("234.5.5.9");
                        socketGroupe = new MulticastSocket(port);
                        socketGroupe.joinGroup(adresseGroupe);
                        thr = new ThreadReception(NomClient, socketGroupe,jtaMessage);
                        thr.start();
                    } catch (Exception e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }

                    NomClient = nomClient.getText();
                    String MessageDebutCo = "Hello " + NomClient;

                    try {
                        DatagramPacket dtg = new DatagramPacket(MessageDebutCo.getBytes(), MessageDebutCo.length(),adresseGroupe, port);
                        socketGroupe.send(dtg);
                        System.out.println("Client Connecté !");
                    } catch (IOException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }


                    connect.setEnabled(false);
                    nomClient.setEnabled(false);
                    input.setEnabled(true);
                    envoyer.setEnabled(true);
                    Quitter.setEnabled(true);
                }
                else
                {
                    jtaMessage.setText("La connexion a échouée !");
                }
                
                dos.close();dis.close();
                
            }
            catch (IOException e)
            { 
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(JApplic_Chat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(JApplic_Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_connectActionPerformed

    private void envoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envoyerActionPerformed
        
        String msg = NomClient + "> " + input.getText();
        DatagramPacket dtg = new DatagramPacket(msg.getBytes(), msg.length(),adresseGroupe, port);
        try
        {
            socketGroupe.send(dtg);
            System.out.println("Message Envoyé!");
        }
        catch (IOException e) { System.out.println("Erreur :-( : " + e.getMessage()); }
        
    }//GEN-LAST:event_envoyerActionPerformed

    private void QuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitterActionPerformed
        String msg = NomClient + " quitte le groupe";
        DatagramPacket dtg = new DatagramPacket(msg.getBytes(), msg.length(),adresseGroupe, port);
        try
        {
        socketGroupe.send(dtg);
        thr = new ThreadReception(NomClient,socketGroupe,jtaMessage);
        thr.stop();
        connect.setEnabled(true);nomClient.setEnabled(true);input.setEnabled(false);
        envoyer.setEnabled(false);Quitter.setEnabled(false);
        socketGroupe.leaveGroup(adresseGroupe); System.out.println("Après leaveGroup");
        socketGroupe.close();
        System.out.println("Après close");
        }
        catch (IOException e){ System.out.println("Erreur :-( : " + e.getMessage());}
    }//GEN-LAST:event_QuitterActionPerformed

    private void MenuSeConnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSeConnecterActionPerformed

        fenlogin = new FenetreLogin(null,true, this, cliSock);
        fenlogin.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        fenlogin.setVisible(true);
    }//GEN-LAST:event_MenuSeConnecterActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JApplic_Chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu GrosMenuAgent;
    public javax.swing.JMenuItem MenuSeConnecter;
    private javax.swing.JButton Quitter;
    public javax.swing.JButton connect;
    public javax.swing.JButton envoyer;
    public javax.swing.JTextField input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtaMessage;
    private javax.swing.JTextField jtfNumClient;
    public javax.swing.JTextField nomClient;
    // End of variables declaration//GEN-END:variables

}
