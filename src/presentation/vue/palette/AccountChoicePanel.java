package presentation.vue.palette;

import Dao.Files.CompteDao;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AccountChoicePanel extends JPanel {

    private JComboBox comboBoxAccount ;
    private CompteDao compteDao ;
    private JButton btn_valider ;


    private long id ;


    public JButton getBtn_valider()
    {
        return btn_valider;
    }

    public void setId(long id) {
        this.id = id;

    }

    public AccountChoicePanel( long id)
    {
    this.id= id ;
    initpanel();
    }

    private void initComponent()
    {

        btn_valider = new JButton("valider");
        compteDao = new CompteDao();
        List<Long> ids = new ArrayList<>();
        compteDao.findByIdProp(id).forEach(elt -> ids.add(Long.parseLong(elt.getNumeroCompte())));
        comboBoxAccount = new JComboBox<>(ids.toArray());
        compteDao.findByIdProp(id).forEach(System.out::println);
    }
    private void initpanel()
    {
        initComponent();
        setLayout(null);
        add(comboBoxAccount);
        add(btn_valider);
        comboBoxAccount.setBounds(300,200,150,25);
        btn_valider.setBounds(300,250,150,25);
    }
}
