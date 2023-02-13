package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SideMenuPanel extends JPanel {
    private Color backGroundColor , buttonTextColor ;
    private Font panelFont ;
    private Map<String, JButton> buttons ;

    private void initbuttons(List<String> buttonsname)
    {
        buttons = new LinkedHashMap<>();
        buttonsname.forEach(nameOfButtons -> {
            JButton btn= new JButton(nameOfButtons);
            btn.setFont(panelFont);
            btn.setForeground(backGroundColor);
            buttons.put(nameOfButtons,btn);
        });
    }

    public JButton getBtn(String btnName)
    {
        for (var btn : buttons.entrySet()) {
            if (btn.getKey() == btnName)
                return btn.getValue();
        }
        return null ;
    }
    public SideMenuPanel(Color bgColor , Color buttonTextColor , Font font , List<String> buttonsName)
    {
        this.backGroundColor = bgColor ;
        this.buttonTextColor = buttonTextColor;
        this.panelFont = font ;
        initPanel(buttonsName);
    }

    private void initPanel(List<String> buttonsName)
    {
        initbuttons(buttonsName);
        setBackground(backGroundColor);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        buttons.forEach((name,buttons) -> add(buttons));
    }
}
