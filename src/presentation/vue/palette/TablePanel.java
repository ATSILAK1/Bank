package presentation.vue.palette;

import Dao.Files.ClientDao;
import presentation.modele.Client;
import presentation.modele.Sexe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablePanel extends JPanel {

    private Color backGroundColor ;
    private JTable table ;
    private TableModele tableModele;
    private JScrollPane scrollPanel ;
private SideMenuPanel sideMenuPanel ;
    ClientDao clientDao = new ClientDao()   ;
    private void initPanel()
    {
        List<String> buttonsNames = new ArrayList<>();
        buttonsNames.add("Comptes");
        buttonsNames.add("Logs");
        sideMenuPanel = new SideMenuPanel(null,null,new Font("OPTIMA", Font.PLAIN,15),buttonsNames);
        tableModele = new TableModele("ID","Nom","Prenom","Email","Tel","Cin","Solde","Login");

        tableModele.initClientData(clientDao.findAll());
        table = new JTable(tableModele);

        scrollPanel = new JScrollPane(table);
    }

    private void initaction()
    {
        sideMenuPanel.getBtn("Logs").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public TablePanel (){
    initPanel();
    setLayout(new BorderLayout());
    setBackground(new Color(250, 94, 94));
    add(scrollPanel,BorderLayout.CENTER);
    add(sideMenuPanel,BorderLayout.WEST);

    }



}
