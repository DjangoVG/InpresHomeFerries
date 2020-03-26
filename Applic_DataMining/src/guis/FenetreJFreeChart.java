package guis;

import Outils.LibrairieJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.*;
import org.jfree.data.jdbc.JDBCPieDataset;

public class FenetreJFreeChart extends javax.swing.JDialog {

    private int NumRequete;
    Connection ConnexionBDCompagnie;
    LibrairieJDBC LibJDBC ;
    Properties propServeurCompagnie;
    public FenetreJFreeChart(java.awt.Frame parent, boolean modal,String numRequetelabel) {
        super(parent, modal);
        initComponents();
        NumRequete = Integer.parseInt(numRequetelabel);
        LibJDBC = new LibrairieJDBC();
        propServeurCompagnie = LibJDBC.RecupPropertiesCompagnie();
        //showPieChart();
        showPieChartJDBC(NumRequete);
    }

    
    private void showPieChart()
    {
        // 1. Définir un dataset qui contient les data
        DefaultPieDataset ds = new DefaultPieDataset();
        ds.setValue("Parti du progrès contrôlé", 22.36);
        ds.setValue("Parti démocrate conservateur", 27.69);
        ds.setValue("Intérêts des gens riches", 3.78);
        ds.setValue("Prolétariat uni", 7.85);
        ds.setValue("Mouvement des Gens Heureux", 35.12);
        ds.setValue("Autres", 3.2);
        
        // 2. Se fournir un JFreeChart
        JFreeChart jfc = ChartFactory.createPieChart ("Résulats des élections en Boursoulavie", ds, true, true, true);
        // 3. Fabriquer le Panel
        ChartPanel cp = new ChartPanel(jfc);
        setContentPane(cp);
    }
    
    private void showPieChartJDBC(int numreq)
    {
        ConnexionBDCompagnie = LibJDBC.ConnexionToBDCompagnie();
        JDBCPieDataset jds = new JDBCPieDataset(ConnexionBDCompagnie);
        
        if(numreq == 1)
        {
            try 
            {
                String query = "SELECT count(IdReservations) as NbReservations,traversees.IdTraversees from reservations inner join traversees on reservations.IdTraversee = traversees.IdTraversees\n" +
"            where reservations.AnneeReservations = YEAR(NOW()) group by traversees.IdTraversees,reservations.AnneeReservations;";
                jds.executeQuery(query);
                System.out.println("Dataset chargé : " + jds.getItemCount());
                ConnexionBDCompagnie.close();
                JFreeChart jfcbd = ChartFactory.createPieChart("Requete A : Nombre de réservations selon la destination de la traversée",jds,
                    true, // générer des légendes ?
                    true, // générer des tooltips ?
                    true); // générer des URLs?
                // 3. Fabriquer le Panel
                ChartPanel cpbd = new ChartPanel(jfcbd);
                setContentPane(cpbd);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(FenetreJFreeChart.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        if(numreq == 3)
        {
            try 
            {
                String query = "SELECT reservations.NbrVoyageur,voyageurs.Age from reservations,voyageurs where reservations.NumeroClient = voyageurs.NumeroClient order by voyageurs.Age asc;";
                jds.executeQuery(query);
                System.out.println("Dataset chargé : " + jds.getItemCount());
                ConnexionBDCompagnie.close();
                
                JFreeChart jfcbd = ChartFactory.createPieChart("Requete C : Nombre de voyageurs en fonction de l'age du conducteur",jds,
                    true, // générer des légendes ?
                    true, // générer des tooltips ?
                    true); // générer des URLs?
                // 3. Fabriquer le Panel
                ChartPanel cpbd = new ChartPanel(jfcbd);
                setContentPane(cpbd);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(FenetreJFreeChart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(numreq == 5)
        {
            try 
            {
                String query = "SELECT traversees.PortArrivee, Voyageurs.Age from Voyageurs,reservations,traversees where Voyageurs.NumeroClient = reservations.NumeroClient and reservations.IdTraversee = traversees.IdTraversees order by traversees.PortArrivee,Voyageurs.Age;";
                jds.executeQuery(query);
                System.out.println("Dataset chargé : " + jds.getItemCount());
                ConnexionBDCompagnie.close();
                
                JFreeChart jfcbd = ChartFactory.createPieChart("Requete E : L'âge des voyageurs selon la destination de la traversée",jds,
                    true, // générer des légendes ?
                    true, // générer des tooltips ?
                    true); // générer des URLs?
                // 3. Fabriquer le Panel
                ChartPanel cpbd = new ChartPanel(jfcbd);
                setContentPane(cpbd);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(FenetreJFreeChart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(numreq == 6)
        {
            try 
            {
                String query = "SELECT Voyageurs.Age, traversees.PortArrivee,traversees.MomentDepart,reservations.IdReservations from Voyageurs,reservations,traversees where Voyageurs.NumeroClient = reservations.NumeroClient\n" +
"and reservations.IdTraversee = traversees.IdTraversees order by traversees.MomentDepart,traversees.PortArrivee,Voyageurs.Age";
                jds.executeQuery(query);
                System.out.println("Dataset chargé : " + jds.getItemCount());
                ConnexionBDCompagnie.close();
                
                JFreeChart jfcbd = ChartFactory.createPieChart("Requete C : Nombre de voyageurs en fonction de l'age du conducteur",jds,
                    true, // générer des légendes ?
                    true, // générer des tooltips ?
                    true); // générer des URLs?
                // 3. Fabriquer le Panel
                ChartPanel cpbd = new ChartPanel(jfcbd);
                setContentPane(cpbd);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(FenetreJFreeChart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanGraph = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Graphiques : ");

        javax.swing.GroupLayout PanGraphLayout = new javax.swing.GroupLayout(PanGraph);
        PanGraph.setLayout(PanGraphLayout);
        PanGraphLayout.setHorizontalGroup(
            PanGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        PanGraphLayout.setVerticalGroup(
            PanGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(PanGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(234, 234, 234))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(PanGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FenetreJFreeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreJFreeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreJFreeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreJFreeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenetreJFreeChart dialog = new FenetreJFreeChart(new javax.swing.JFrame(), true,null);
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
    private javax.swing.JPanel PanGraph;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
