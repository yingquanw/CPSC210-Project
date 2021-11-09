package ui;

import javax.swing.*;

import java.awt.*;

import static ui.ManagerPanel.ManagerPanel_HEIGHT;
import static ui.ManagerPanel.ManagerPanel_WIDTH;

public class PlayerTextField extends JTextField {

    JLabel textLabel;

    public PlayerTextField() {
        textLabel = new JLabel();
        setPreferredSize(new Dimension(ManagerPanel_WIDTH - 150, 20));
    }
}
