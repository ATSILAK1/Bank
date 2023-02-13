package presentation.vue.palette;

import javax.swing.*;

public class OperationPanel extends JPanel {

   private JButton btn_virement , btn_retrait , btn_veresemnt , btn_log;

   public OperationPanel()
   {
       initPanel();
   }
   private void initComponents()
   {
       btn_retrait = new JButton("retrait");
       btn_veresemnt = new JButton("versement");
       btn_virement = new JButton("Virement");
       btn_log = new JButton("Voir Log");
   }


    public JButton getBtn_virement() {
        return btn_virement;
    }

    public JButton getBtn_retrait() {
        return btn_retrait;
    }

    public JButton getBtn_veresemnt() {
        return btn_veresemnt;
    }

    public JButton getBtn_log() {
        return btn_log;
    }

    private void initPanel()
   {
       initComponents();
       setLayout(null);
       add(btn_retrait);
       add(btn_virement);
       add(btn_veresemnt);
       add(btn_log);
       btn_retrait.setBounds(150,200,100,25);
       btn_virement.setBounds(260,200,100,25);
       btn_veresemnt.setBounds(370,200,100,25);
       btn_log.setBounds(480,200,100,25);
   }
}
