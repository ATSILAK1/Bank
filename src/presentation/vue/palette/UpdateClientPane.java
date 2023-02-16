package presentation.vue.palette;

import Dao.Files.ClientDao;
import Dao.Files.FileBasePaths;
import metier.form.ClientFromValidator;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Sexe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class UpdateClientPane extends JPanel {


    private JTextField  fld_cin, fld_Login , fld_Email , fld_nom , fld_prenom, fld_tel ;
    private JPasswordField fld_pass ;

    private JLabel lbl_cin, lbl_login ,lbl_pass, lbl_Email , lbl_nom , lbl_prenom, lbl_tel , lbl_sexe;
    private JComboBox comboBox_sexe ;
    private JButton btn_create ;
    private Client clientpa ;
    private void initComponent ()
    {
        fld_cin = new JTextField(clientpa.getCin());
        lbl_cin = new JLabel("cin");
        fld_Login = new JTextField(clientpa.getLogin());
        lbl_login = new JLabel("login");
        fld_nom = new JTextField (clientpa.getNom());
        lbl_nom = new JLabel ("nom");
        lbl_pass = new JLabel ("password");
        lbl_Email = new JLabel ("Email");
        lbl_sexe = new JLabel ("Sexe");
        lbl_tel = new JLabel ("tel");
        lbl_prenom = new JLabel ("prenom");
        fld_prenom = new JTextField (clientpa.getPrenom());
        fld_pass = new JPasswordField (clientpa.getMotDePasse());
        fld_Email = new JTextField (clientpa.getEmail());
        comboBox_sexe = new JComboBox (Sexe.values());
        fld_tel = new JTextField (clientpa.getTel());
        btn_create = new JButton("Update");
    }
    private void initpanel()
    {

        initComponent();
        setLayout(null);
        add(lbl_cin) ;add(fld_cin);
        add(lbl_login) ; add(fld_Login);
        add(lbl_pass)  ;add(fld_pass);
        add(lbl_Email) ;add(fld_Email);
        add(lbl_nom)   ;add(fld_nom);
        add(lbl_prenom);add(fld_prenom);
        add(lbl_tel)   ;add(fld_tel);
        add(lbl_sexe)  ;add(comboBox_sexe);
        add(btn_create);

        lbl_cin.setBounds(115,60 , 100 ,25);
        fld_cin.setBounds(230,60, 140 ,25);
        lbl_login.setBounds(115,90 , 100 ,25);
        fld_Login.setBounds(230,90,140,25);
        lbl_nom.setBounds (115, 120, 100, 25);
        fld_nom.setBounds (230, 120, 140, 25);
        lbl_prenom.setBounds (110, 150, 100, 25);
        fld_prenom.setBounds (230, 150, 140, 25);
        lbl_pass.setBounds (105, 180, 100, 25);
        fld_pass.setBounds (230, 180, 140, 25);
        lbl_Email.setBounds (105, 210, 100, 25);
        fld_Email.setBounds (230, 210, 140, 25);
        lbl_sexe.setBounds (110, 240, 100, 25);
        comboBox_sexe.setBounds (245, 240, 100, 25);
        lbl_tel.setBounds (110, 270, 100, 25);
        fld_tel.setBounds (230, 270, 140, 25);
        btn_create.setBounds(160,300,100,25);
        btn_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFromValidator validator = new ClientFromValidator();
                ClientDao clientDao = new ClientDao();
                String pass = new String(fld_pass.getPassword());
                Client client ;

                validator.validerClient(fld_cin.getText(),
                        fld_Email.getText(),
                        pass,
                        fld_tel.getText(),
                        fld_nom.getText(),
                        fld_prenom.getText(),
                        fld_Login.getText());
                if (validator.getErrors().isEmpty())
                {
                    client = new Client(
                            fld_Login.getText(),
                            pass,
                            fld_nom.getText(),
                            fld_prenom.getText(),
                            fld_Email.getText(),
                            fld_cin.getText(),
                            fld_tel.getText(),
                            (Sexe) comboBox_sexe.getSelectedItem()
                    );
                    client.setId(clientpa.getId());
                        clientDao.update(client);


                }else {
                    String errors = "";

                    for (Map.Entry<String, String> error : validator.getErrors().entrySet()) {
                        errors= error.getKey()+""+error.getValue()+"\n";

                    }
                    JOptionPane.showMessageDialog(null,"Erreur dans la creation de client\n "+errors);
                    System.out.println("impossible de cree le client");
                }
            }
        });
    }
    public UpdateClientPane(Client client)
    {
        this.clientpa = client;
        initpanel();
    }

}

