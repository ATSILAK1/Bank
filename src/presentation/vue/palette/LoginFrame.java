package presentation.vue.palette;

import Dao.Files.FileBasePaths;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {


    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 800 ;
    private int frameHeight = 600 ;
    private Container mainContainer ;
    private static final Color bgColor = new Color(100,100,99);
    private static final Font buttonsFont = new Font("optima",Font.BOLD , 17 );

    private CreateClientPanel createClientPanel;

    private void initpanel()
    {
        createClientPanel = new CreateClientPanel();

    }
    private void initcontainer()
    {
        initpanel();
        mainContainer = getContentPane();
        mainContainer.add(createClientPanel);

    }
    public LoginFrame()
    {
        initcontainer();
        setTitle("lgn");
        setLocation(0,0);
        setSize(frameWidth,frameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
    public static void main(String[] args) {
        FileBasePaths.creeDB();
        JFrame frame1 = new LoginFrame();
    }
}

