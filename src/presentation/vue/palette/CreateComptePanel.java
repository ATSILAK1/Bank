package presentation.vue.palette;

import Dao.Files.ClientDao;
import Dao.Files.CompteDao;
import Dao.Files.FileBasePaths;
import presentation.modele.Client;
import presentation.modele.Compte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CreateComptePanel extends JPanel {

    private JLabel lbl_solde, lbl_prop;
    private JTextField fld_solde, fld_prop;
    private JComboBox<String> com_cinProprietaire;
    private JButton btn_valider;
    ClientDao clientDao = new ClientDao();
    CompteDao compteDao = new CompteDao();
    int posX = 300 , posY = 100 , width = 100 , height = 25 ;
    private void initcomponent() {


        lbl_solde = new JLabel("solde");
        lbl_prop = new JLabel("propietaire");
        fld_solde = new JTextField();
        fld_prop = new JTextField();
        btn_valider = new JButton("crée");
        com_cinProprietaire = new JComboBox<>();
        for (Client ele : clientDao.findAll()) {
            com_cinProprietaire.addItem(ele.getCin());
        }


    }
    private void initpanel()
    {
        initcomponent();
        setLayout(null);
        add(com_cinProprietaire);
        add(lbl_solde);
        add(fld_solde);
        add(lbl_prop);
        add(fld_prop);
        add(btn_valider);
        fld_prop.setEnabled(false);

        lbl_prop.setBounds(posX,posY,width,height);
        com_cinProprietaire.setBounds(posX+100 , posY , width , height);
        lbl_solde.setBounds(posX,posY+25,width,height);
        fld_solde.setBounds(posX+50,posY+25,width,height);
        fld_prop.setBounds(posX,posY+50,width*2,height);
        btn_valider.setBounds(posX,posY+75,width,height);
        com_cinProprietaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = clientDao.findByCin((String) com_cinProprietaire.getSelectedItem());
                fld_prop.setText(client.getNomComplet());



            }
        });
        btn_valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = clientDao.findByCin((String) com_cinProprietaire.getSelectedItem());
                try {
                    String id = Files.readString(FileBasePaths.INDEX_ACCOUNT);
                    if(Double.parseDouble(fld_solde.getText()) < 0)
                        JOptionPane.showMessageDialog(null, "Error : le solde doit etre positif !");
                    else {
                        Compte compte = new Compte(id, Double.parseDouble(fld_solde.getText()), client);

                        compteDao.save(compte);

                        long inc = Long.parseLong(id);
                        inc++;

                        Files.writeString(FileBasePaths.INDEX_ACCOUNT, Long.toString(inc));
                        Path logFile =Files.createFile(Path.of(FileBasePaths.LOGS_FOLDER + "/logFile" + id));
                        Files.deleteIfExists(Path.of(FileBasePaths.LOGS_FOLDER + "/logFile" + id));
                        Files.write(logFile, FileBasePaths.LOG_HEADER.getBytes());
                        Files.write(logFile, compte.getLogs().get(0).toString().getBytes(), StandardOpenOption.APPEND);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public CreateComptePanel()
    {
        initpanel();
    }
}