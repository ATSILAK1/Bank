package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class MainTableFrame extends JFrame {

    Container container ;
    TablePanel tablePanel ;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private void initPanels()
    {
        tablePanel = new TablePanel();

    }
    private void initContainer()
    {
        initPanels();
        container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(tablePanel);
    }
    public MainTableFrame(String title )
    {
        initContainer();
        setTitle("title");
        setLocation(0,0);
        setSize(screenSize);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainTableFrame mainTableFrame = new MainTableFrame("title");

    }
}
