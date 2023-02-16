package presentation.vue.palette;

import presentation.modele.Compte;
import presentation.vue.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationPanel extends JPanel {

   private JButton btn_virement , btn_retrait , btn_veresemnt , btn_log;
   private Compte compte    ;

   private RetaitPanel retaitPanel;
   private VirementPanel virementPanel ;
   private VersementPanel versementPanel ;
   private LogPanel logPanel ;
   public OperationPanel(Compte compte)
   {
        this.compte = compte;
       initPanel();
   }

   public void setCompte(Compte compte)
   {
       this.compte = compte ;
   }
   private void initComponents()
   {
       btn_retrait = new JButton("retrait");
       btn_veresemnt = new JButton("versement");
       btn_virement = new JButton("Virement");
       btn_log = new JButton("Voir Log");

       retaitPanel = new RetaitPanel(compte);
       virementPanel = new VirementPanel(compte);
       versementPanel = new VersementPanel(compte);

       logPanel = new LogPanel(compte);
   }



    private void initPanel()
   {
       initComponents();
       initaction();
       setLayout(null);
       setBounds(0,0,800,800);
       add(btn_retrait);
       add(btn_virement);
       add(btn_veresemnt);
       add(btn_log);
       btn_retrait.setBounds(150,200,100,25);
       btn_virement.setBounds(260,200,100,25);
       btn_veresemnt.setBounds(370,200,100,25);
       btn_log.setBounds(480,200,100,25);

   }
    private void initaction()
    {
        btn_veresemnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();

                add(versementPanel);
                revalidate();
                repaint();

            }
        });

        btn_retrait.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(retaitPanel);
                revalidate();
                repaint();

            }
        });

        btn_virement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                virementPanel.setCompte1(compte);
                add(virementPanel);
                revalidate();
                repaint();

            }
        });

        btn_log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();

                add(logPanel);
                revalidate();
                repaint();
            }
        });
    }

public OperationPanel(){initPanel();}
}
