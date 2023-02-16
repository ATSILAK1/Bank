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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class VirementPanel extends JPanel {

    private JButton btn_valider , btn_back;
    private JComboBox combo_compte;
    private JTextField fld_solde;
    private Compte compte1;

    public void setCompte1(Compte compte) {
        this.compte1 = compte;
    }


    private void initcomponent() {

        btn_valider = new JButton("valider operation");
        btn_back = new JButton("retour");
        List<String> ids = new ArrayList<>();
        for (Compte com: new CompteDao().findAll()) {
            if(!Objects.equals(compte1.getNumeroCompte(), com.getNumeroCompte())) {

                ids.add(com.getNumeroCompte());
            }
        }

        combo_compte = new JComboBox(ids.toArray());
        fld_solde = new JTextField();
        fld_solde.setBounds(150,200,100,25);
        combo_compte.setBounds(250,200,100,25);
        btn_valider.setBounds(225,225,200,25);
        btn_back.setBounds(350,225,100,25);
    }

    private void initaction() {
        CompteDao compteDao = new CompteDao();

        btn_valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (Double.parseDouble(fld_solde.getText()) < 0)
                    JOptionPane.showMessageDialog(null, "solde ne peut pas etre negatif");
                else if (compte1.getSolde()-Double.parseDouble(fld_solde.getText()) < 0)
                {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas de solde suffisant");
                }
                else {
                    var compte2 = compteDao.findById(String.valueOf(combo_compte.getSelectedItem()));


                    try{
                    Files.writeString(Path.of(FileBasePaths.LOGS_FOLDER+"/logfile"+compte1.getNumeroCompte()) ,new Log(LocalDate.now(), LocalTime.now(), TypeLog.VIREMENT,"virement effectuer "+fld_solde.getText()).toString(), StandardOpenOption.APPEND);
                    Files.writeString(Path.of(FileBasePaths.LOGS_FOLDER+"/logfile"+compte2.getNumeroCompte()) ,new Log(LocalDate.now(), LocalTime.now(), TypeLog.VIREMENT,"virement recu "+fld_solde.getText()).toString(), StandardOpenOption.APPEND);
                    compte1.setSolde(compte1.getSolde()-Double.parseDouble(fld_solde.getText()));
                    compteDao.update(compte1);
                    compte2.setSolde(compte2.getSolde()+Double.parseDouble(fld_solde.getText()));
                    compteDao.update(compte2);
                        removeAll();
                        add(new OperationPanel(compte1));
                        revalidate();
                        repaint();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void initPanel() {

        setLayout(null);
        setBounds(0,0,1200,1600);
        initcomponent();
        initaction();
        add(fld_solde);
        add(combo_compte);
        add(btn_valider);
        add(btn_back);

    }

    public VirementPanel(Compte compte) {
        this.compte1 = compte;
        initPanel();
    }



}
