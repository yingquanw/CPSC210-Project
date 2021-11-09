package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.MyHomeTeamGUI.FRAME_HEIGHT;
import static ui.MyHomeTeamGUI.FRAME_WIDTH;

public class ManagerPanel extends JPanel implements ActionListener {
    public static final int ManagerPanel_WIDTH = FRAME_WIDTH / 2;
    public static final int ManagerPanel_HEIGHT = FRAME_HEIGHT;

    JLabel label;
    JTextField name;
    JTextField number;
    JTextField age;
    JTextField position;
    JTextField goals;
    JTextField assists;
    JTextField passes;
    JTextField successPasses;
    JTextField interceptions;
    JTextField tacklesWon;
    JButton addButton;
    JLabel textLabel;


    public ManagerPanel() {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new FlowLayout());
        setBackground(Color.ORANGE);
        //setPreferredSize(new Dimension(PlayerPanel_WIDTH,PlayerPanel_HEIGHT));
        setBounds(FRAME_WIDTH / 2,0, ManagerPanel_WIDTH, ManagerPanel_HEIGHT);
        add(createLabel());
        enterName();
        enterNumber();
        enterAge();
        enterPosition();
        enterGoals();
        enterAssists();
        enterPasses();
        enterSuccessPasses();
        enterInterceptions();
        enterTacklesWon();
        addAddButton();
    }

    public JLabel createLabel() {
        label = new JLabel();
        label.setText("                      Manager List                   ");
        //label.setBounds(ManagerPanel_WIDTH, 0, ManagerPanel_WIDTH, 10);
        return label;
    }

    public void addAddButton() {
        addButton = new JButton();
        addButton.setBounds(ManagerPanel_WIDTH + 100,400, 30, 20);
        addButton.addActionListener(this);
        addButton.setText("Add");
        this.add(addButton);
    }

    public void enterName() {
        name = new PlayerTextField();
        textLabel = new JLabel("     Name:         ");
        name.add(textLabel);
        add(textLabel);
        add(name);
    }

    public void enterNumber() {
        number = new PlayerTextField();
        textLabel = new JLabel("   Number:       ");
        number.add(textLabel);
        add(textLabel);
        add(number);
    }

    public void enterAge() {
        age = new PlayerTextField();
        textLabel = new JLabel("       Age:          ");
        age.add(textLabel);
        add(textLabel);
        add(age);
    }

    public void enterPosition() {
        position = new PlayerTextField();
        textLabel = new JLabel("    Position:      ");
        position.add(textLabel);
        add(textLabel);
        add(position);
    }

    public void enterGoals() {
        goals = new PlayerTextField();
        textLabel = new JLabel("     Goals:         ");
        goals.add(textLabel);
        add(textLabel);
        add(goals);
    }

    public void enterAssists() {
        assists = new PlayerTextField();
        textLabel = new JLabel("     Assists:       ");
        assists.add(textLabel);
        add(textLabel);
        add(assists);
    }

    public void enterPasses() {
        passes = new PlayerTextField();
        textLabel = new JLabel("     Passes:        ");
        passes.add(textLabel);
        add(textLabel);
        add(passes);
    }

    public void enterSuccessPasses() {
        successPasses = new PlayerTextField();
        textLabel = new JLabel("Success Passes:");
        successPasses.add(textLabel);
        add(textLabel);
        add(successPasses);
    }

    public void enterInterceptions() {
        interceptions = new PlayerTextField();
        textLabel = new JLabel("  Interceptions: ");
        interceptions.add(textLabel);
        add(textLabel);
        add(interceptions);
    }

    public void enterTacklesWon() {
        tacklesWon = new PlayerTextField();
        textLabel = new JLabel("  Tackles Won:  ");
        tacklesWon.add(textLabel);
        add(textLabel);
        add(tacklesWon);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
