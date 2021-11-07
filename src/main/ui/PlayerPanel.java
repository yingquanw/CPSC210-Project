package ui;

import javax.swing.*;
import java.awt.*;

import static ui.MyHomeTeamGUI.FRAME_WIDTH;
import static ui.MyHomeTeamGUI.FRAME_HEIGHT;

public class PlayerPanel extends JPanel {
    public static final int PlayerPanel_WIDTH = FRAME_WIDTH / 2;
    public static final int PlayerPanel_HEIGHT = FRAME_HEIGHT;

    JLabel label;

    public PlayerPanel() {
        setBackground(Color.LIGHT_GRAY);
        //setPreferredSize(new Dimension(PlayerPanel_WIDTH,PlayerPanel_HEIGHT));
        setBounds(0,0,PlayerPanel_WIDTH, PlayerPanel_HEIGHT);
        add(createLabel());
    }

    public JLabel createLabel() {
        label = new JLabel();
        label.setText("Player List");
        return label;
    }

}
