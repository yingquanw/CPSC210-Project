package ui;

import model.Team;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MyHomeTeamGUI extends JFrame {

    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;
    private PlayerPanel pp;
    private ManagerPanel mp;

    public MyHomeTeamGUI() throws FileNotFoundException {
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
}
