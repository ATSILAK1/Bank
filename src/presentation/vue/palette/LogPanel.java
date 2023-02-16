package presentation.vue.palette;

import Dao.Files.ClientDao;
import Dao.Files.FileBasePaths;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LogPanel extends JPanel {

    private JTextArea jTextArea ;
    private Compte compte ;
    private JButton btn_back ;
    private JScrollPane jScrollPane;
    private void initComponent ()  {

    List<String> Lines = new ArrayList<>() ;

        var element  = "";
        try {
           Lines = Files.readAllLines(Path.of(FileBasePaths.LOGS_FOLDER + "/logFile" + compte.getNumeroCompte()));
        }catch (IOException e)
        {
            System.out.println("pas de fichier");
        }
        Lines.remove(0);
        for (var line : Lines)
        {
            element += line +"\n";
        }
        jTextArea = new JTextArea( element);
        btn_back = new JButton("retour");
        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }
    private void initpanel()  {
        setBounds(0,0,800,800);
        initComponent();
        setLayout(null);

        jScrollPane.setBounds(0,0,600,600);
        jTextArea.setEditable(false);
        add(jScrollPane);

        btn_back.setBounds(650,600,100,100);

        add(btn_back);
        initaction();
    }
    LogPanel(Compte compte) {
        this.compte = compte;
        initpanel();
    }

    private void initaction()
    {
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(new OperationPanel(compte));
                revalidate();
                repaint();
            }
        });
    }
}
