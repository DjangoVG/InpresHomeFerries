package guis;

import Outils.LibrairieJDBC;
import RequeteReponseANASTAT.ReponseANASTAT;
import RequeteReponseANASTAT.RequeteANASTAT;
import com.sun.xml.internal.ws.util.StringUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.general.Dataset;

/**
 *
 * @author yanni
 */
public class FenetreRequete extends javax.swing.JDialog {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sockClient;
    private LibrairieJDBC LibJDBC;
    private FenetreJFreeChart fenjfreechart;
    private Connection ConnexionBDAideDecision ;
    private Dataset dsa = null;
    public FenetreRequete(java.awt.Frame parent, boolean modal,Socket SocketClient, ObjectInputStream Objectis, ObjectOutputStream Objectos,int NumRequete) {
        super(parent, modal);
        initComponents();
        LabelNumRequete.setText(String.valueOf(NumRequete));
        if(NumRequete == 1 || NumRequete == 3 || NumRequete == 5 || NumRequete == 6)
        {
            JFreeChart.setEnabled(true);
        }
        sockClient = SocketClient;
        this.oos = Objectos;
        this.ois = Objectis;
        LibJDBC = new LibrairieJDBC();
        ConnexionBDAideDecision = LibJDBC.ConnexionToBDAide();
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TAFenRequete = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        LabelNumRequete = new javax.swing.JLabel();
        BoutonQuitter = new javax.swing.JButton();
        BoutonLancer = new javax.swing.JButton();
        JFreeChart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgFenRequete.jpeg"))); // NOI18N

        TAFenRequete.setEditable(false);
        TAFenRequete.setColumns(20);
        TAFenRequete.setRows(5);
        jScrollPane1.setViewportView(TAFenRequete);

        jLabel2.setText("Vous êtes en train de traiter la requête : ");

        BoutonQuitter.setBackground(new java.awt.Color(204, 204, 204));
        BoutonQuitter.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BoutonQuitter.setForeground(new java.awt.Color(51, 0, 255));
        BoutonQuitter.setText("Quitter");
        BoutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonQuitterActionPerformed(evt);
            }
        });

        BoutonLancer.setText("Lancer");
        BoutonLancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonLancerActionPerformed(evt);
            }
        });

        JFreeChart.setText("JFreeChart");
        JFreeChart.setEnabled(false);
        JFreeChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFreeChartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LabelNumRequete, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BoutonLancer)
                        .addComponent(BoutonQuitter))
                    .addComponent(JFreeChart))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(LabelNumRequete, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BoutonQuitter))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BoutonLancer)
                                .addGap(18, 18, 18)
                                .addComponent(JFreeChart))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonQuitterActionPerformed
        setVisible(false);
    }//GEN-LAST:event_BoutonQuitterActionPerformed

    private void BoutonLancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonLancerActionPerformed
        RequeteANASTAT req = null;
        ReponseANASTAT reponse = null;
        String tmp = LabelNumRequete.getText();
        int NumRequete = Integer.parseInt(tmp) ;
        String query = "INSERT INTO `bd_aide_decision`.`donnees` (`Id`,`PValue`, `Difference`, `NumRequete`) values (null, ?, ?, ?)";
        PreparedStatement preparedStmt = null;
        if(NumRequete == 1)
        {
            try {
                System.out.println("NumRequete : " + NumRequete);
                 req = new RequeteANASTAT(NumRequete);
                 oos.writeObject(req);
                 System.out.println("J'ai envoyé la requete de traitement A");

                 System.out.println("Avant de lire la réponse de la requete de traitement A");
                 reponse = (ReponseANASTAT)ois.readObject();
                 System.out.println("Je lis la réponse de la requete de traitement A");

                 
                if (reponse.getCode() == ReponseANASTAT.REQUETE_A_OK)
                {
                    Interpretationrequete(reponse);
                    
                }
                else
                {
                    TAFenRequete.setText("Erreur lors du traitement de la requête");
                }       
            } 
            catch (IOException e) 
            {
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRequete.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(NumRequete == 2)
        {
            try {
                System.out.println("NumRequete : " + NumRequete);
                 req = new RequeteANASTAT(NumRequete);
                 oos.writeObject(req);
                 System.out.println("J'ai envoyé la requete de traitement A");

                 System.out.println("Avant de lire la réponse de la requete de traitement A");
                 reponse = (ReponseANASTAT)ois.readObject();
                 System.out.println("Je lis la réponse de la requete de traitement A");


                if (reponse.getCode() == ReponseANASTAT.REQUETE_B_OK)
                {
                    Interpretationrequete(reponse);
                }
                else
                {
                    TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete");
                }       
            } 
            catch (IOException e) 
            {
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRequete.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(NumRequete == 3)
        {
            try {
                System.out.println("NumRequete : " + NumRequete);
                 req = new RequeteANASTAT(NumRequete);
                 oos.writeObject(req);
                 System.out.println("J'ai envoyé la requete de traitement C");

                 System.out.println("Avant de lire la réponse de la requete de traitement C");
                 reponse = (ReponseANASTAT)ois.readObject();
                 System.out.println("Je lis la réponse de la requete de traitement C");


                if (reponse.getCode() == ReponseANASTAT.REQUETE_C_OK)
                {
                    Interpretationrequete(reponse);
                        
                }
                else
                {
                    TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Erreur dans le script R)");
                }
            } 
            catch (IOException e) 
            {
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRequete.class.getName()).log(Level.SEVERE, null, ex);
                TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Class NotFound) ");
            }
        }
        if(NumRequete == 4)
        {
            try {
                System.out.println("NumRequete : " + NumRequete);
                 req = new RequeteANASTAT(NumRequete);
                 oos.writeObject(req);
                 System.out.println("J'ai envoyé la requete de traitement D");

                 System.out.println("Avant de lire la réponse de la requete de traitement D");
                 reponse = (ReponseANASTAT)ois.readObject();
                 System.out.println("Je lis la réponse de la requete de traitement D");


                if (reponse.getCode() == ReponseANASTAT.REQUETE_D_OK)
                {
                    Interpretationrequete(reponse);
                        
                }
                else
                {
                    TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Erreur dans le script R)");
                }
            } 
            catch (IOException e) 
            {
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRequete.class.getName()).log(Level.SEVERE, null, ex);
                TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Class NotFound) ");
            }
        }
        if(NumRequete == 5)
        {
            try {
                System.out.println("NumRequete : " + NumRequete);
                 req = new RequeteANASTAT(NumRequete);
                 oos.writeObject(req);
                 System.out.println("J'ai envoyé la requete de traitement E");

                 System.out.println("Avant de lire la réponse de la requete de traitement E");
                 reponse = (ReponseANASTAT)ois.readObject();
                 System.out.println("Je lis la réponse de la requete de traitement E");


                if (reponse.getCode() == ReponseANASTAT.REQUETE_E_OK)
                {
                    Interpretationrequete(reponse);
                        
                }
                else
                {
                    TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Erreur dans le script R)");
                }
            } 
            catch (IOException e) 
            {
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRequete.class.getName()).log(Level.SEVERE, null, ex);
                TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Class NotFound) ");
            }
        }
        if(NumRequete == 6)
        {
            try {
                System.out.println("NumRequete : " + NumRequete);
                 req = new RequeteANASTAT(NumRequete);
                 oos.writeObject(req);
                 System.out.println("J'ai envoyé la requete de traitement F");

                 System.out.println("Avant de lire la réponse de la requete de traitement F");
                 reponse = (ReponseANASTAT)ois.readObject();
                 System.out.println("Je lis la réponse de la requete de traitement F");


                if (reponse.getCode() == ReponseANASTAT.REQUETE_F_OK)
                {
                    Interpretationrequete(reponse);
                }
                else
                {
                    TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Erreur dans le script R)");
                }     
                
            } 
            catch (IOException e) 
            {
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRequete.class.getName()).log(Level.SEVERE, null, ex);
                TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Class NotFound) ");
            }
        }
        if(NumRequete == 7)
        {
            try {
                System.out.println("NumRequete : " + NumRequete);
                 req = new RequeteANASTAT(NumRequete);
                 oos.writeObject(req);
                 System.out.println("J'ai envoyé la requete de traitement G");

                 System.out.println("Avant de lire la réponse de la requete de traitement G");
                 reponse = (ReponseANASTAT)ois.readObject();
                 System.out.println("Je lis la réponse de la requete de traitement G");


                if (reponse.getCode() == ReponseANASTAT.REQUETE_G_OK)
                {
                    Interpretationrequete(reponse);
                }
                else
                {
                    TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Erreur dans le script R)");
                }       
            } 
            catch (IOException e) 
            {
                System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FenetreRequete.class.getName()).log(Level.SEVERE, null, ex);
                TAFenRequete.setText("Erreur lors du traitement de la requête Fenetre Requete ( Class NotFound) ");
            }
        }
    }//GEN-LAST:event_BoutonLancerActionPerformed

    private void JFreeChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JFreeChartActionPerformed
        fenjfreechart = new FenetreJFreeChart(null, true, LabelNumRequete.getText());
            fenjfreechart.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    fenjfreechart.setVisible(false);
                }
            });
            fenjfreechart.setVisible(true);
    }//GEN-LAST:event_JFreeChartActionPerformed

    void Interpretationrequete(ReponseANASTAT reponse)
    {
        if(reponse.getDiff() == 1)
        {
            TAFenRequete.setText("p-value du modele : " + reponse.getS() + "\n" + "Il y a une différence significative !");
        }
        else
        {
            TAFenRequete.setText(reponse.getS()+ "\n" + "Il n'y a pas de différence significative !");
        }
        
    }
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
            java.util.logging.Logger.getLogger(FenetreRequete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreRequete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreRequete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreRequete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenetreRequete dialog = new FenetreRequete(new javax.swing.JFrame(), true,null,null,null,0);
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
    private javax.swing.JButton BoutonLancer;
    public javax.swing.JButton BoutonQuitter;
    private javax.swing.JButton JFreeChart;
    private javax.swing.JLabel LabelNumRequete;
    private javax.swing.JTextArea TAFenRequete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
