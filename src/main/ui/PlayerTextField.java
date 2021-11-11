package ui;

import javax.swing.*;
import java.awt.*;
import static ui.ManagerPanel.ManagerPanel_WIDTH;

// The JTextField class for the statistics of the player

public class PlayerTextField extends JTextField {

    JLabel textLabel;

    //MODIFIES: this
    //EFFECTS: creates text label for player statistics
    public PlayerTextField() {
        textLabel = new JLabel();
        setPreferredSize(new Dimension(ManagerPanel_WIDTH - 150, 29));
    }
}
