package ui;

import model.Team;

import javax.swing.*;
import java.awt.*;

public class MyHomeTeamGUI extends JFrame {
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;

    private PlayerPanel pp;
    private ManagerPanel mp;
    private Team myTeam;


    public MyHomeTeamGUI() {

        setTitle("My Home Team");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLUE);
        setLayout(null);
        myTeam = new Team("Liverpool");
        pp = new PlayerPanel();
        mp = new ManagerPanel(pp);
        add(pp);
        add(mp);
        setVisible(true);


    }

    public static void main(String[] args) {
        MyHomeTeamGUI myHomeTeam = new MyHomeTeamGUI();

    }


}
