package presentation.vue.palette;

import Dao.Files.ClientDao;
import Dao.Files.CompteDao;
import Dao.Files.FileBasePaths;
import presentation.modele.Compte;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablePanel extends JPanel {

    private Color backGroundColor ;
    private JTable table ;
    private TableModele tableModele;
    private JScrollPane scrollPanel ;
    private TableAccountPanel tableAccountPanel ;
private SideMenuPanel sideMenuPanel ;
    ClientDao clientDao = new ClientDao()   ;
    private void initPanel()
    {
        List<String> buttonsNames = new ArrayList<>();
        buttonsNames.add("Ajouter CLient");
        buttonsNames.add("Liste client");
        buttonsNames.add("Liste Comptes");
        buttonsNames.add("Ajouter compte");
        buttonsNames.add("Modifier client");
        buttonsNames.add("Supprimer client");
        buttonsNames.add("Supprimer compte");
        sideMenuPanel = new SideMenuPanel(null,null,new Font("OPTIMA", Font.PLAIN,15),buttonsNames);
        tableModele = new TableModele("ID","Nom","Prenom","Email","Tel","Cin","Login");
        tableAccountPanel = new TableAccountPanel();
        tableModele.initClientData(clientDao.findAll());
        table = new JTable(tableModele);

        scrollPanel = new JScrollPane(table);
        initaction();
    }

    private void initaction()
    {
        sideMenuPanel.getBtn("Liste client").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(sideMenuPanel,BorderLayout.WEST);
                add(scrollPanel,BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        sideMenuPanel.getBtn("Liste Comptes").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("buttons compte");
            removeAll();
            add(sideMenuPanel,BorderLayout.WEST);
            add(tableAccountPanel,BorderLayout.CENTER);

            revalidate();
            repaint();
            }
        });

        sideMenuPanel.getBtn("Ajouter CLient").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(sideMenuPanel,BorderLayout.WEST);
                add(new CreateClientPanel(),BorderLayout.CENTER);
                revalidate();
                repaint();

            }
        });

        sideMenuPanel.getBtn("Ajouter compte").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(sideMenuPanel,BorderLayout.WEST);
                add(new CreateComptePanel(),BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        sideMenuPanel.getBtn("Modifier client").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();
            if(row == -1)
            {
                JOptionPane.showMessageDialog(null,"choisir une ligne");
            }else
            {
                long id = (long)table.getValueAt(row,0);

                removeAll();
                add(new UpdateClientPane(clientDao.findById(id)),BorderLayout.CENTER);
                add(sideMenuPanel,BorderLayout.WEST);
                revalidate();
                repaint();
            }


            }
        });
        sideMenuPanel.getBtn("Supprimer client").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(null,"choisir une ligne");
                }else
                {
                    long id = (long)table.getValueAt(row,0);
                    System.out.println(id);
                    clientDao.delete(clientDao.findById(id));
                    removeAll();
                    add(new TablePanel(),BorderLayout.CENTER);

                    revalidate();
                    repaint();
                }

            }
        });

        sideMenuPanel.getBtn("Supprimer compte").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CompteDao compteDao = new CompteDao();
                int row = tableAccountPanel.getTable().getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(null,"choisir une ligne");
                }else
                {
                    long id = (long)table.getValueAt(row,0);
                    System.out.println(id);
                    compteDao.delete(new CompteDao().findById(String.valueOf(id)));
                    try
                    {
                        Files.deleteIfExists(Path.of(FileBasePaths.LOGS_FOLDER + "/logFile" + String.valueOf(id)));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    removeAll();
                    add(new TablePanel(),BorderLayout.CENTER);

                    revalidate();
                    repaint();
                }

            }
        });

    }
    public TablePanel (){
    initPanel();
    setLayout(new BorderLayout());

    add(scrollPanel,BorderLayout.CENTER);
    add(sideMenuPanel,BorderLayout.WEST);

    }



}
