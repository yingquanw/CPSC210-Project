package ui;

import javax.swing.*;
import java.awt.*;

// My Home Team application graphic user interface

public class MyHomeTeamGUI extends JFrame {

    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;
    private PlayerPanel pp;
    private ManagerPanel mp;

    //MODIFIES: this
    //EFFECTS: creates the frame and adds panels to it
    //         runs the application
    public MyHomeTeamGUI() {
        setTitle("My Home Team");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLUE);
        setLayout(null);
        pp = new PlayerPanel();
        mp = new ManagerPanel(pp);
        add(pp);
        add(mp);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyHomeTeamGUI();
    }
}
