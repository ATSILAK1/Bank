package presentation.vue;

import presentation.vue.palette.CreateClientPanel;
import presentation.vue.palette.CreateComptePanel;

import javax.swing.*;
import java.awt.*;

public class CompteFrame extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 800 ;
    private int frameHeight = 600 ;
    private Container mainContainer ;
    private static final Color bgColor = new Color(100,100,99);
    private static final Font buttonsFont = new Font("optima",Font.BOLD , 17 );

    private CreateComptePanel createComptePanel = new CreateComptePanel();
    private void initpanel()
    {
        createComptePanel = new CreateComptePanel();
    }
    private void initcontainer()
    {
        initpanel();
        mainContainer = getContentPane();
        mainContainer.add(createComptePanel);

    }
    public CompteFrame()
    {
        initcontainer();
        setTitle("lgn");
        setLocation(0,0);
        setSize(frameWidth,frameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame cr = new CompteFrame();
    }
}
