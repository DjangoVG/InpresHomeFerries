
package Serveur;

import javax.swing.UIManager;

public class Serveur_Information extends javax.swing.JFrame {

    ThreadServeur ts ;
    ThreadProgressBar tp;
    public Serveur_Information() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelDate = new javax.swing.JLabel();
        LabelServeurBienvenue = new javax.swing.JLabel();
        LabelEtatServeur = new javax.swing.JLabel();
        ProgressDemarrage = new javax.swing.JProgressBar();
        DemarrageServeur = new javax.swing.JButton();
        ArretServeur = new javax.swing.JButton();
        ImageFond = new javax.swing.JLabel();
        ClientConnectes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableClientsConnectes = new javax.swing.JTable();
        LabelMessageRecu = new javax.swing.JLabel();
        TableMessageRecu = new javax.swing.JScrollPane();
        TableMessageRecus = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelServeurBienvenue.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        LabelServeurBienvenue.setForeground(new java.awt.Color(0, 51, 255));
        LabelServeurBienvenue.setText("Serveur Information");

        LabelEtatServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        LabelEtatServeur.setText("Serveur en attente");

        ProgressDemarrage.setStringPainted(true);

        DemarrageServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        DemarrageServeur.setText("Démarrage du serveur");
        DemarrageServeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemarrageServeurActionPerformed(evt);
            }
        });

        ArretServeur.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ArretServeur.setText("Arrêt du serveur");
        ArretServeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArretServeurActionPerformed(evt);
            }
        });

        ImageFond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/header-image.jpg"))); // NOI18N

        ClientConnectes.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        ClientConnectes.setText("Client connecté(s) :");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(LabelServeurBienvenue)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(TableMessageRecu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(DemarrageServeur)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ArretServeur, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(LabelEtatServeur)
                                    .addGap(34, 34, 34)
                                    .addComponent(ProgressDemarrage, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ClientConnectes)
                        .addGap(199, 199, 199))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LabelMessageRecu)
                        .addGap(206, 206, 206))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(LabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(296, 296, 296))
                .addComponent(ImageFond, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProgressDemarrage, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEtatServeur, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(LabelServeurBienvenue)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(ArretServeur))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DemarrageServeur)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClientConnectes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelMessageRecu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TableMessageRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(ImageFond, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(LabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(529, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DemarrageServeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemarrageServeurActionPerformed

        ts = new ThreadServeur(new ClientsList(), this);
        ts.start();
        tp = new ThreadProgressBar(this);
        tp.start();
        LabelEtatServeur.setText("Serveur opérationnel !");
        DemarrageServeur.setEnabled(false);
        ArretServeur.setEnabled(true);
    }//GEN-LAST:event_DemarrageServeurActionPerformed

    private void ArretServeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArretServeurActionPerformed

        LabelEtatServeur.setText("Serveur à l'arrêt");
        ProgressDemarrage.setValue(0);
        DemarrageServeur.setEnabled(true);
        ArretServeur.setEnabled(false);
        ts.interrupt();
    }//GEN-LAST:event_ArretServeurActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Serveur_Information().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ArretServeur;
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
}
