package presentation.vue;

import Dao.Files.CompteDao;
import Dao.Files.FileBasePaths;
import presentation.modele.Compte;
import presentation.vue.palette.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainFrame extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = screenSize.width - 500;
    private int frameHeight = screenSize.height - 400;
    private Container mainContainer ;
    private static final Color bgColor = new Color(100,100,99);
    private static final Font buttonsFont = new Font("optima",Font.BOLD , 17 );

    private void changePanel(JPanel panel)
    {
        getContentPane().removeAll();
        setContentPane(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private Compte compte ;

    private SideMenuPanel menuPanel ;
    private LoginPanel loginPanel;
    private CreateClientPanel createClientPanel ;
    private AccountChoicePanel accountChoicePanel ;

    private CreateComptePanel createComptePanel ;
    private static OperationPanel operationPanel ;
    private TablePanel tablePanel ;
    private CreateComptePanel comptePanel;
    static public OperationPanel getOperationPanel()
    {
        return operationPanel;
    }
    private void initPanel()
    {


        loginPanel = new LoginPanel(frameWidth,frameHeight);
        createClientPanel = new CreateClientPanel();
        tablePanel = new TablePanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(0,0,screenSize.width,screenSize.height);
        loginPanel.getBtn_login().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String login = loginPanel.getFld_login().getText();
                    char[] test =loginPanel.getFld_password().getPassword();
                    String pass = new String(test );
                    System.out.println(login +" "+ pass);
                    var user = loginPanel.getLoginFormValidator().validerSession(login,pass);
                    System.out.println(user);

                    if(user == null)
                        JOptionPane.showMessageDialog(null ,"identifiant incorrecte" );
                    if(Objects.equals(user.getRole(), "Admin"))
                    {
                        comptePanel = new CreateComptePanel();
                        changePanel(tablePanel);
                    }
                    else if ( user.getRole() == "Client")
                    {

                        accountChoicePanel = new AccountChoicePanel(user.getId());

                        changePanel(accountChoicePanel);
                        accountChoicePanel.getBtn_valider().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                            compte = new CompteDao().findById(String.valueOf(accountChoicePanel.getComboBoxAccount().getSelectedItem()));
                            operationPanel = new OperationPanel(compte);
                            changePanel(operationPanel);
                            }
                        });


                    }


            }
        });

    }



    private void initcontainer()
{
    initPanel();
    mainContainer = getContentPane();
    mainContainer.setBackground(bgColor);
    mainContainer.setLayout(new BorderLayout());
    mainContainer.add(loginPanel,BorderLayout.CENTER);



}
    public MainFrame(String title)
{
    initcontainer();
    setTitle(title);
    setLocation(0,0);
    setSize(frameWidth,frameHeight);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);
}

    public static void main(String[] args) {
        FileBasePaths.creeDB();
        JFrame frame = new MainFrame("ma fenetre");


    }

}
