package serveur_compagnie;

import Outils.AffichageFichierLog;
import PoolDeThreads.ThreadProgressBar;
import PoolDeThreads.ThreadServeur;
import PoolDeThreads.ThreadDate;
import Outils.FichierLog;
import Outils.LibrairieJDBC;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.UIManager;
import java.util.Properties;
import DemandeThreadsCards.*;
import DemandeThreadsEmbarquements.*;
import PoolDeThreads.ThreadServeurAdmin;

public class FenServer extends java.awt.Frame implements ConsoleServeur
{
    private ThreadProgressBar tp;
    private ThreadDate td;
    private ThreadServeur ts;
    private FichierLog fichierLogFenServer;
    private Properties propServeur;
    
    public FenServer()
    {
        initComponents(); setTitle("Serveur de Home-Ferries");
        fichierLogFenServer = new FichierLog("FichierLog.txt");
        td = new ThreadDate(this);
        td.start();
        ArretServeur.setEnabled(false);
        LibrairieJDBC LibJDBC = new LibrairieJDBC();
        propServeur = LibJDBC.RecupPropertiesCompagnie();
    }

    public static void main(String args[]) 
    {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        
        FenServer fenserver = new FenServer();
        fenserver.addWindowListener(new java.awt.event.WindowAdapter() 
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        fenserver.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelServeurBienvenue = new javax.swing.JLabel();
        LabelEtatServeur = new javax.swing.JLabel();
        ArretServeur = new javax.swing.JButton();
        DemarrageServeur = new javax.swing.JButton();
        ImageFond = new javax.swing.JLabel();
        ClientConnectes = new javax.swing.JLabel();
        ProgressDemarrage = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableClientsConnectes = new javax.swing.JTable();
        LabelMessageRecu = new javax.swing.JLabel();
        TableMessageRecu = new javax.swing.JScrollPane();
        TableMessageRecus = new javax.swing.JTable();
        LabelDate = new javax.swing.JLabel();
        BoutonLog = new javax.swing.JButton();

        LabelServeurBienvenue.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        LabelServeurBienvenue.setForeground(new java.awt.Color(0, 51, 255));
        LabelServeurBienvenue.setText("Serveur de Home-Ferries");

        LabelEtatServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        LabelEtatServeur.setText("Serveur en attente");

        ArretServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ArretServeur.setText("Arrêt du serveur");
        ArretServeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArretServeurActionPerformed(evt);
            }
        });

        DemarrageServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        DemarrageServeur.setText("Démarrage du serveur");
        DemarrageServeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemarrageServeurActionPerformed(evt);
            }
        });

        ImageFond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        ClientConnectes.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        ClientConnectes.setText("Client connecté(s) :");

        ProgressDemarrage.setStringPainted(true);

        TableClientsConnectes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Adresse IP", "Port", "SocketAdress"
            }
        ));
        TableClientsConnectes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableClientsConnectes.setSelectionForeground(new java.awt.Color(153, 153, 255));
        jScrollPane1.setViewportView(TableClientsConnectes);

        LabelMessageRecu.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        LabelMessageRecu.setText("Message reçu(s) : ");

        TableMessageRecus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Heure", "Adresse IP", "Message"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableMessageRecus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableMessageRecus.setSelectionForeground(new java.awt.Color(153, 153, 255));
        TableMessageRecu.setViewportView(TableMessageRecus);
        if (TableMessageRecus.getColumnModel().getColumnCount() > 0) {
            TableMessageRecus.getColumnModel().getColumn(0).setMinWidth(100);
            TableMessageRecus.getColumnModel().getColumn(0).setPreferredWidth(100);
            TableMessageRecus.getColumnModel().getColumn(0).setMaxWidth(100);
            TableMessageRecus.getColumnModel().getColumn(1).setMinWidth(150);
            TableMessageRecus.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        BoutonLog.setText("Log");
        BoutonLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ImageFond, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(ClientConnectes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(LabelMessageRecu))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(TableMessageRecu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelServeurBienvenue))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(DemarrageServeur)
                                .addGap(18, 18, 18)
                                .addComponent(ArretServeur, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelEtatServeur)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ProgressDemarrage, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BoutonLog, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ImageFond, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelServeurBienvenue)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProgressDemarrage, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEtatServeur, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DemarrageServeur)
                    .addComponent(ArretServeur))
                .addGap(20, 20, 20)
                .addComponent(ClientConnectes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelMessageRecu)
                .addGap(18, 18, 18)
                .addComponent(TableMessageRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoutonLog, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ArretServeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArretServeurActionPerformed
        LabelEtatServeur.setText("Serveur à l'arrêt");
        ProgressDemarrage.setValue(0);
        DemarrageServeur.setEnabled(true);
        ArretServeur.setEnabled(false);
        ts.interrupt();
    }//GEN-LAST:event_ArretServeurActionPerformed

    
    private void DemarrageServeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemarrageServeurActionPerformed
        ts = new PoolDeThreads.ThreadServeur(new ClientsList(), this);
        ts.start();
        tp = new ThreadProgressBar(this);
        tp.start();
        DemarrageServeur.setEnabled(false);
        ArretServeur.setEnabled(true);
    }//GEN-LAST:event_DemarrageServeurActionPerformed

    private void BoutonLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonLogActionPerformed
        String sLog = fichierLogFenServer.litFichier();
        java.awt.EventQueue.invokeLater(() -> {
            new AffichageFichierLog(sLog).setVisible(true);
        }); 
    }//GEN-LAST:event_BoutonLogActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ArretServeur;
    private javax.swing.JButton BoutonLog;
    private javax.swing.JLabel ClientConnectes;
    private javax.swing.JButton DemarrageServeur;
    private javax.swing.JLabel ImageFond;
    public javax.swing.JLabel LabelDate;
    public javax.swing.JLabel LabelEtatServeur;
    private javax.swing.JLabel LabelMessageRecu;
    private javax.swing.JLabel LabelServeurBienvenue;
    public javax.swing.JProgressBar ProgressDemarrage;
    public javax.swing.JTable TableClientsConnectes;
    public javax.swing.JScrollPane TableMessageRecu;
    public javax.swing.JTable TableMessageRecus;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    
    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        ArretServeurActionPerformed(null);
        System.exit(0);
    }
    public void TraceEvenements(String commentaire)
    {
        Vector ligne = new Vector();
        StringTokenizer parser = new StringTokenizer(commentaire,"#");
        while (parser.hasMoreTokens())
            ligne.add(parser.nextToken());
        //DefaultTableModel dtm = (DefaultTableModel)jTextAreaFichierLog.getModel();
        //dtm.insertRow(dtm.getRowCount(),ligne);
    }
}
