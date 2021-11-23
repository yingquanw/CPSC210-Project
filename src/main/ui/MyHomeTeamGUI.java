package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// My Home Team application graphic user interface

public class MyHomeTeamGUI extends JFrame {

    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;
    private PlayerPanel pp;
    private ManagerPanel mp;
    private EventLog eventLog;

    //MODIFIES: this
    //EFFECTS: creates the frame and adds panels to it
    //         runs the application
    public MyHomeTeamGUI() {
        setTitle("My Home Team");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLUE);
        setLayout(null);
        pp = new PlayerPanel();
        mp = new ManagerPanel(pp);
        add(pp);
        add(mp);
        //from https://kodejava.org/how-do-i-handle-a-window-closing-event/
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("WindowClosingDemo.windowClosing");
                printLog();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    //EFFECTS: prints the EventLog to the console
    private void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new MyHomeTeamGUI();
    }
}
