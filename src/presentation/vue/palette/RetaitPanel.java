package presentation.vue.palette;

import Dao.Files.CompteDao;
import Dao.Files.FileBasePaths;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;

public class RetaitPanel extends JPanel {


   private  JButton btn_valider ;

    private JTextField fld_solde ;
    private Compte compte ;

    private void initcomponents()
    {
        btn_valider = new JButton("valdier");
        fld_solde = new JTextField();

        btn_valider.setBounds(200,200,100,25);
        fld_solde.setBounds(200,250,100,25);
    }
    public void setCompte (Compte compte)
    {
        this.compte = compte;
    }
    private void initaction()
    {
     btn_valider.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

             if(compte.getSolde() - Double.parseDouble(fld_solde.getText()) < 0)
                 JOptionPane.showMessageDialog(null,"solde inssufisant ");
             else if (Double.parseDouble(fld_solde.getText()) < 0)
                 JOptionPane.showMessageDialog(null,"montant negatif");
             else
             {
                 try {
                     Files.writeString(Path.of(FileBasePaths.LOGS_FOLDER+"/logFile"+compte.getNumeroCompte()),new Log(LocalDate.now(), LocalTime.now(), TypeLog.RETRAIT,"Retrait effectuer de "+fld_solde.getText()).toString(), StandardOpenOption.APPEND);
                 } catch (IOException ex) {
                     throw new RuntimeException(ex);
                 }
                 compte.setSolde(compte.getSolde() -Double.parseDouble(fld_solde.getText()));
                 new CompteDao().update(compte);

             }
         }
     });



    }
    private void initpanel()
    {
        setLayout(null);
        initcomponents();
        add(btn_valider);
        add(fld_solde);
        initaction();
    }
    public RetaitPanel()
    {
        initpanel();
    }
}
