package presentation.vue.palette;

import Dao.Files.ClientDao;
import presentation.modele.Client;
import presentation.modele.Sexe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablePanel extends JPanel {

    private Color backGroundColor ;
    private JTable table ;
    private TableModele tableModele;
    private JScrollPane scrollPanel ;

    ClientDao clientDao = new ClientDao()   ;
    private void initPanel()
    {

        tableModele = new TableModele("ID","Nom","Prenom","Email","Tel","Cin","Solde","Login");

        tableModele.initClientData(clientDao.findAll());
        table = new JTable(tableModele);

        scrollPanel = new JScrollPane(table);
    }
    public TablePanel (){
    initPanel();
    setLayout(new BorderLayout());
    setBackground(new Color(250, 94, 94));
    add(scrollPanel,BorderLayout.CENTER);

    }
}
