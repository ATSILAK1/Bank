package presentation.vue.palette;

import Dao.Files.CompteDao;
import Dao.Files.FileBasePaths;
import com.sun.tools.javac.Main;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;
import presentation.vue.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.rmi.MarshalledObject;
import java.time.LocalDate;
import java.time.LocalTime;

public class VersementPanel extends JPanel {


    private  JButton btn_valider ;
    private JLabel lbl_solde ;

    private JTextField fld_solde ;
    private Compte compte ;

    private void initcomponents()
    {
        btn_valider = new JButton("valider");
        fld_solde = new JTextField();
        lbl_solde = new JLabel("Montant a verser :");


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

                if (Double.parseDouble(fld_solde.getText()) < 0 )
                    JOptionPane.showMessageDialog(null,"montant negatif ou Format incorret");
                else
                {
                    try {
                        Files.writeString(Path.of(FileBasePaths.LOGS_FOLDER+"/logFile"+compte.getNumeroCompte()),new Log(LocalDate.now(), LocalTime.now(), TypeLog.VERSEMENT,"versement effectuer de "+fld_solde.getText()).toString(), StandardOpenOption.APPEND);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    compte.setSolde(compte.getSolde() + Double.parseDouble(fld_solde.getText()));
                    new CompteDao().update(compte);

                    removeAll();
                    add(new OperationPanel(compte));
                    revalidate();
                    repaint();

                }
            }
        });



    }
    private void initpanel()
    {
        initcomponents();
        initaction();
        setLayout(null);
        setBounds(0,0,800,600);
        btn_valider.setBounds(200,250,100,25);
        fld_solde.setBounds(230,200,200,25);
        lbl_solde.setBounds(90,200,120,25);
        add(btn_valider);
        add(fld_solde);
        add(lbl_solde);


    }
    public VersementPanel(Compte compte)
    {
        this.compte = compte;
        initpanel();
    }

}
