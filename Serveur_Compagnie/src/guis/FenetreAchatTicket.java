package guis;

import AppliClientCompagnie.FenClient;
import RequeteReponseCINAP.ReponseCINAP;
import RequeteReponseCINAP.RequeteCINAP;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FenetreAchatTicket extends javax.swing.JDialog 
{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ArrayList Combox;
    StringBuffer message = null;
    Socket cliSock = null;
    
    public FenetreAchatTicket(java.awt.Frame parent, boolean modal, Socket SocketClient, ObjectInputStream Objectis, ObjectOutputStream Objectos)
    {
        super(parent, modal);
        initComponents();
        cliSock = SocketClient;
        this.oos = Objectos;
        this.ois = Objectis;
        RequeteCINAP req = null;
        ReponseCINAP reponse = null;
        
        try {
            
            
            req = new RequeteCINAP(5);
            oos.writeObject(req);
            reponse = (ReponseCINAP)ois.readObject();
            
            if (reponse.getCode() == ReponseCINAP.ACHATTICKET_LISTEOK)
            {
                Combox = (ArrayList<String>)reponse.getArray();
                for (int i=0; i< Combox.size(); i++)
                {
                    String cs = (String)Combox.get(i);
                    ComboBoxListeTraversees.addItem(cs);
                }
            }
            else
            {
                    String msg;
                    msg = "Il n'y a aucune traversée programmée !";
                    JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null); 
            }
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoutonViderChamps = new javax.swing.JButton();
        BoutonAnnuler = new javax.swing.JButton();
        BoutonAcheter = new javax.swing.JButton();
        TextNumCarteCredit = new javax.swing.JTextField();
        TextNbrPassagers = new javax.swing.JTextField();
        TextNumImmat = new javax.swing.JTextField();
        TextPrenomConducteur = new javax.swing.JTextField();
        LabelCarteCredit = new javax.swing.JLabel();
        LabelNbrPassagers = new javax.swing.JLabel();
        LabelNumImmatriculation = new javax.swing.JLabel();
        LabelNomConducteur = new javax.swing.JLabel();
        NomFenetre = new javax.swing.JLabel();
        Icon = new javax.swing.JLabel();
        ComboBoxListeTraversees = new javax.swing.JComboBox<>();
        LabelChoixTraversee = new javax.swing.JLabel();
        LabelNomConducteur1 = new javax.swing.JLabel();
        TextNomConducteur = new javax.swing.JTextField();
        LabelNumClient = new javax.swing.JLabel();
        TextNumClient = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(475, 550));

        BoutonViderChamps.setForeground(new java.awt.Color(51, 51, 51));
        BoutonViderChamps.setText("Vider les champs");
        BoutonViderChamps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonViderChampsActionPerformed(evt);
            }
        });

        BoutonAnnuler.setForeground(new java.awt.Color(51, 51, 51));
        BoutonAnnuler.setText("Annuler");
        BoutonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonAnnulerActionPerformed(evt);
            }
        });

        BoutonAcheter.setForeground(new java.awt.Color(51, 51, 51));
        BoutonAcheter.setText("Acheter !");
        BoutonAcheter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonAcheterActionPerformed(evt);
            }
        });

        LabelCarteCredit.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelCarteCredit.setForeground(new java.awt.Color(51, 153, 255));
        LabelCarteCredit.setText("Numéro de carte de crédit :");

        LabelNbrPassagers.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelNbrPassagers.setForeground(new java.awt.Color(51, 153, 255));
        LabelNbrPassagers.setText("Nombres de passagers : ");

        LabelNumImmatriculation.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelNumImmatriculation.setForeground(new java.awt.Color(51, 153, 255));
        LabelNumImmatriculation.setText("Num. Immatriculation de la voiture : ");

        LabelNomConducteur.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelNomConducteur.setForeground(new java.awt.Color(51, 153, 255));
        LabelNomConducteur.setText("Prénom du conducteur :");

        NomFenetre.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        NomFenetre.setForeground(new java.awt.Color(0, 153, 255));
        NomFenetre.setText("Achat d'un ticket");

        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ticket.jpg"))); // NOI18N

        ComboBoxListeTraversees.setMaximumRowCount(100);
        ComboBoxListeTraversees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxListeTraverseesActionPerformed(evt);
            }
        });

        LabelChoixTraversee.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelChoixTraversee.setForeground(new java.awt.Color(51, 153, 255));
        LabelChoixTraversee.setText("Choix de la traversée : ");

        LabelNomConducteur1.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelNomConducteur1.setForeground(new java.awt.Color(51, 153, 255));
        LabelNomConducteur1.setText("Nom du conducteur :");

        LabelNumClient.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        LabelNumClient.setForeground(new java.awt.Color(51, 153, 255));
        LabelNumClient.setText("Numéro de client : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Icon)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(NomFenetre))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(LabelChoixTraversee, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(ComboBoxListeTraversees, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(LabelNbrPassagers)
                .addGap(98, 98, 98)
                .addComponent(TextNbrPassagers, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(BoutonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(BoutonViderChamps, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BoutonAcheter, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(LabelCarteCredit)
                    .addGap(80, 80, 80)
                    .addComponent(TextNumCarteCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelNumImmatriculation)
                    .addComponent(LabelNomConducteur))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextPrenomConducteur, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNumImmat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelNumClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TextNumClient, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelNomConducteur1)
                        .addGap(116, 116, 116)
                        .addComponent(TextNomConducteur, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Icon)
                .addGap(11, 11, 11)
                .addComponent(NomFenetre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelChoixTraversee, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxListeTraversees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LabelNumClient)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(TextNumClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelNomConducteur1)
                    .addComponent(TextNomConducteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(LabelNomConducteur))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(TextPrenomConducteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelNumImmatriculation)
                    .addComponent(TextNumImmat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextNbrPassagers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNbrPassagers))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(LabelCarteCredit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(TextNumCarteCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonAcheter)
                    .addComponent(BoutonViderChamps)
                    .addComponent(BoutonAnnuler))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonViderChampsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonViderChampsActionPerformed
        TextPrenomConducteur.setText("");
        TextNumImmat.setText("");
        TextNbrPassagers.setText("");
        TextNumCarteCredit.setText("");
        TextNumClient.setText("");
        TextNomConducteur.setText("");
    }//GEN-LAST:event_BoutonViderChampsActionPerformed

    private void BoutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonAnnulerActionPerformed
        setVisible(false);
    }//GEN-LAST:event_BoutonAnnulerActionPerformed

    private void BoutonAcheterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonAcheterActionPerformed
        RequeteCINAP req = null;
        ReponseCINAP reponse = null;
        
        try {
            
            if (!TextNomConducteur.getText().equals("") && !TextNbrPassagers.getText().equals("") && !TextPrenomConducteur.getText().equals("") && !TextNumCarteCredit.getText().equals("") && !TextNumImmat.getText().equals(""))
            {
                String pattern= "^[0-9]*";
                if (TextNbrPassagers.getText().matches(pattern) == false)
                {
                    String msg;
                    msg = "Le champ du nombre passagers est invalide !";
                    JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                    TextNbrPassagers.setText("");
                }  
                else
                {
                    req = new RequeteCINAP(2, ComboBoxListeTraversees.getSelectedItem().toString(), TextNumClient.getText(), TextPrenomConducteur.getText(), TextNomConducteur.getText(), TextNumImmat.getText(), TextNbrPassagers.getText(), TextNumCarteCredit.getText());
                    oos.writeObject(req);
                    try
                    {
                        reponse = (ReponseCINAP)ois.readObject();
                    }
                    catch (IOException e)
                    { 
                        System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); 
                    } 
                    catch (ClassNotFoundException ex)
                    {
                        Logger.getLogger(FenClient.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (reponse.getCode() == ReponseCINAP.ACHATTICKET_OK)
                    {
                        String msg;
                        msg = "Achat effectué ! \nVotre numéro de reservation : "+reponse.getRes()+"\nLe nom de votre Ferry : "+reponse.getNomFerry()+"\nL'heure de départ du Ferry : "+reponse.getHeure()+"";
                        JOptionPane.showMessageDialog(this, msg, "Validé !", JOptionPane.INFORMATION_MESSAGE, null);
                        this.dispose();
                    }
                    if (reponse.getCode() == ReponseCINAP.ACHATTICKET_NOMVOYAGEUR_ERROR)
                    {
                        String msg;
                        msg = "Il n'y a aucun voyageur sous ce nom OU le numéro client ne correspond pas aux prénom/nom !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
                        TextNomConducteur.setText("");
                        TextPrenomConducteur.setText("");
                    }
                    if (reponse.getCode() == ReponseCINAP.ACHATTICKET_CARTENONTROUVE)
                    {
                        String msg;
                        msg = "Aucune carte de crédit trouvé sous ce numéro !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);             
                        TextNumCarteCredit.setText("");
                    }
                    if (reponse.getCode() == ReponseCINAP.ACHATTICKET_DEBITIMPOSSIBLE)
                    {
                        String msg;
                        msg = "Le montant de la carte est insuffisant !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);             
                        TextNumCarteCredit.setText("");
                    }
                    if (reponse.getCode() == ReponseCINAP.ACHATTICKET_DEBITTROPELEVE)
                    {
                        String msg;
                        msg = "Le montant du débit est supérieur à 5000 euros !";
                        JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);             
                        TextNumCarteCredit.setText("");
                    }
                }
            }
            else
            {
                String msg;
                msg = "Un des champs est vide !";
                JOptionPane.showMessageDialog(this, msg, "Attention !", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        
    }//GEN-LAST:event_BoutonAcheterActionPerformed

    private void ComboBoxListeTraverseesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxListeTraverseesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxListeTraverseesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonAcheter;
    private javax.swing.JButton BoutonAnnuler;
    private javax.swing.JButton BoutonViderChamps;
    private javax.swing.JComboBox<String> ComboBoxListeTraversees;
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel LabelCarteCredit;
    private javax.swing.JLabel LabelChoixTraversee;
    private javax.swing.JLabel LabelNbrPassagers;
    private javax.swing.JLabel LabelNomConducteur;
    private javax.swing.JLabel LabelNomConducteur1;
    private javax.swing.JLabel LabelNumClient;
    private javax.swing.JLabel LabelNumImmatriculation;
    private javax.swing.JLabel NomFenetre;
    public javax.swing.JTextField TextNbrPassagers;
    public javax.swing.JTextField TextNomConducteur;
    public javax.swing.JTextField TextNumCarteCredit;
    public javax.swing.JTextField TextNumClient;
    public javax.swing.JTextField TextNumImmat;
    public javax.swing.JTextField TextPrenomConducteur;
    // End of variables declaration//GEN-END:variables
}
