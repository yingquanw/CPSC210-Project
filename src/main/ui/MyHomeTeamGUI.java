package ui;

import model.Team;

import javax.swing.*;
import java.awt.*;

public class MyHomeTeamGUI extends JFrame {
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;


    public MyHomeTeamGUI() {

        setTitle("My Home Team");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        getContentPane().setBackground(Color.BLUE);
        setLayout(null);
        add(new PlayerPanel());
        add(new ManagerPanel());
        setVisible(true);


    }

    public static void main(String[] args) {
        MyHomeTeamGUI myHomeTeam = new MyHomeTeamGUI();

    }
}
