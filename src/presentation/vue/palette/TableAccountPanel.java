package presentation.vue.palette;

import Dao.Files.ClientDao;
import Dao.Files.CompteDao;

import javax.swing.*;
import java.awt.*;

public class TableAccountPanel extends JPanel {

    private Color backGroundColor ;
    private JTable table ;
    private TableAccountModel tableModele;
    private JScrollPane scrollPanel ;

    public JTable getTable() {
        return table;
    }

    public TableAccountModel getTableModele() {
        return tableModele;
    }

    public JScrollPane getScrollPanel() {
        return scrollPanel;
    }

    CompteDao compteDao = new CompteDao();

    private void initpanel()
    {
        tableModele = new TableAccountModel("ID","Date_creation","solde","id_proprietaire");
        tableModele.initCompteData(compteDao.findAll());
        table = new JTable(tableModele);
        scrollPanel = new JScrollPane(table);
    }
    public TableAccountPanel()
    {
        initpanel();
        setLayout(new BorderLayout());
        setBackground(new Color(250,94,94));
        add(scrollPanel);
    }
}
