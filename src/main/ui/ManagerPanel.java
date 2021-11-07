package ui;

import javax.swing.*;
import java.awt.*;

import static ui.MyHomeTeamGUI.FRAME_HEIGHT;
import static ui.MyHomeTeamGUI.FRAME_WIDTH;

public class ManagerPanel extends JPanel {
    public static final int PlayerPanel_WIDTH = FRAME_WIDTH / 2;
    public static final int PlayerPanel_HEIGHT = FRAME_HEIGHT;

    JLabel label;

    public ManagerPanel() {
        setBackground(Color.ORANGE);
        //setPreferredSize(new Dimension(PlayerPanel_WIDTH,PlayerPanel_HEIGHT));
        setBounds(FRAME_WIDTH / 2,0, PlayerPanel_WIDTH,PlayerPanel_HEIGHT);
        add(createLabel());
    }

    public JLabel createLabel() {
        label = new JLabel();
        label.setText("Manager List");
        return label;
    }
}
