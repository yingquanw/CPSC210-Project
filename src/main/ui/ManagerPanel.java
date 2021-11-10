package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.MyHomeTeamGUI.FRAME_HEIGHT;
import static ui.MyHomeTeamGUI.FRAME_WIDTH;

public class ManagerPanel extends JPanel implements ActionListener {
    public static final int ManagerPanel_WIDTH = FRAME_WIDTH / 2;
    public static final int ManagerPanel_HEIGHT = FRAME_HEIGHT;

    JLabel greeting;
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
    JTextField newTeamName;
    JButton addButton;
    JLabel textLabel;
    JButton changeNameButton;
    JLabel playerFeedbackLabel;
    String teamName;
    private PlayerPanel playerPanel;


    public ManagerPanel(PlayerPanel playerPanel) {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.playerPanel = playerPanel;
        setLayout(new FlowLayout());
        setBackground(Color.ORANGE);
        //setPreferredSize(new Dimension(PlayerPanel_WIDTH,PlayerPanel_HEIGHT));
        setBounds(FRAME_WIDTH / 2,0, ManagerPanel_WIDTH, ManagerPanel_HEIGHT);
        addGreetingLabel();
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
        addPlayerFeedbackLabel();
        enterTeamName();
        addChangeNameButton();
    }

    public void addGreetingLabel() {
        greeting = new JLabel();
        teamName = playerPanel.getTeam().getName();
        greeting.setText("Welcome to " + teamName + " Football Club:");
        greeting.setFont(new Font("Verdana", Font.PLAIN, 15));
        add(greeting);
    }

    public void addPlayerFeedbackLabel() {
        playerFeedbackLabel = new JLabel();
        //playerFeedbackLabel.setBounds(ManagerPanel_WIDTH + 100,450, 30, 20);
        playerFeedbackLabel.setPreferredSize(new Dimension(250, 20));
        this.add(playerFeedbackLabel);
    }

    public void addChangeNameButton() {
        changeNameButton = new JButton();
        changeNameButton.setBounds(ManagerPanel_WIDTH + 100,400, 30, 20);
        changeNameButton.setLocation(ManagerPanel_WIDTH + 100, 500);
        changeNameButton.addActionListener(this);
        changeNameButton.setText("Change");
        this.add(changeNameButton);
    }

    public void addAddButton() {
        addButton = new JButton();
        addButton.setBounds(ManagerPanel_WIDTH + 100,400, 30, 20);
        addButton.setLocation(ManagerPanel_WIDTH + 100, 400);
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

    public void enterTeamName() {
        newTeamName = new JTextField();
        newTeamName.setPreferredSize(new Dimension(ManagerPanel_WIDTH - 150, 28));
        textLabel = new JLabel("Enter new team name:       ");
        textLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        add(textLabel);
        add(newTeamName);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            Player newPlayer = createNewPlayer();
            if (playerPanel.getTeam().addPlayer(newPlayer)) {
                playerFeedbackLabel.setText("Successfully added " + newPlayer.getName() + " to the team.");
                playerPanel.updateModel(newPlayer);

            } else {
                playerFeedbackLabel.setText("<html>Unable to add new player:                              "
                        + "<br> name and number are not unique.</html>");
            }
        }
        if (e.getSource() == changeNameButton) {
            teamName = newTeamName.getText();
            playerPanel.getTeam().changeName(teamName);
            greeting.setText("Welcome to " + teamName + " Football Club:");
        }
    }

    private Player createNewPlayer() {
        String plName = name.getText();
        Integer plNumber = Integer.parseInt(number.getText());
        Integer plAge = Integer.parseInt(age.getText());
        String plPosition = position.getText();
        Integer plGoals = Integer.parseInt(goals.getText());
        Integer plAssists = Integer.parseInt(assists.getText());
        Integer plPasses = Integer.parseInt(passes.getText());
        Integer plSuccessPasses = Integer.parseInt(successPasses.getText());
        Integer plInterceptions = Integer.parseInt(interceptions.getText());
        Integer plTacklesWon = Integer.parseInt(tacklesWon.getText());
        Player newPlayer = new Player(plName, plNumber, plAge, plPosition, plGoals, plAssists, plPasses,
                plSuccessPasses, plInterceptions, plTacklesWon);
        return newPlayer;
    }
}
