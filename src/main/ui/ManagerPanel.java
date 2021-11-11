package ui;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static ui.MyHomeTeamGUI.FRAME_HEIGHT;
import static ui.MyHomeTeamGUI.FRAME_WIDTH;

public class ManagerPanel extends JPanel implements ActionListener {

    public static final int ManagerPanel_WIDTH = FRAME_WIDTH / 2;
    public static final int ManagerPanel_HEIGHT = FRAME_HEIGHT;
    private JLabel textLabel;
    private JLabel playerFeedbackLabel;
    private JLabel teamNameChanged;
    private JTextField name;
    private JTextField number;
    private JTextField age;
    private JTextField position;
    private JTextField goals;
    private JTextField assists;
    private JTextField passes;
    private JTextField successPasses;
    private JTextField interceptions;
    private JTextField tacklesWon;
    private JTextField newTeamName;
    private JButton addButton;
    private JButton changeNameButton;
    private String teamName;
    private PlayerPanel playerPanel;

    public ManagerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
        setLayout(new FlowLayout());
        setBackground(Color.ORANGE);
        setBounds(FRAME_WIDTH / 2,0, ManagerPanel_WIDTH, ManagerPanel_HEIGHT);
        addInstructionLabel();
        addNameTextField();
        addNumberTextField();
        addAgeTextField();
        addPositionTextField();
        addGoalsTextField();
        addAssistsTextField();
        addPassesTextField();
        addSuccessPassesTextField();
        addInterceptionsTextField();
        addTacklesWonTextField();
        addAddButton();
        addPlayerFeedbackLabel();
        addTeamNameTextField();
        addChangeNameButton();
    }

    private void addInstructionLabel() {
        JLabel instructionLabel = new JLabel();
        instructionLabel.setText("Create and add a new player here:");
        instructionLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(instructionLabel);
    }

    private void addChangeNameButton() {
        changeNameButton = new JButton();
        changeNameButton.setBounds(ManagerPanel_WIDTH + 100,400, 30, 20);
        changeNameButton.setLocation(ManagerPanel_WIDTH + 100, 500);
        changeNameButton.addActionListener(this);
        changeNameButton.setText("Change");
        this.add(changeNameButton);
    }

    private void addNameTextField() {
        name = new PlayerTextField();
        textLabel = new JLabel("     Name:         ");
        name.add(textLabel);
        add(textLabel);
        add(name);
    }

    private void addNumberTextField() {
        number = new PlayerTextField();
        textLabel = new JLabel("   Number:       ");
        number.add(textLabel);
        add(textLabel);
        add(number);
    }

    private void addAgeTextField() {
        age = new PlayerTextField();
        textLabel = new JLabel("       Age:          ");
        age.add(textLabel);
        add(textLabel);
        add(age);
    }

    private void addPositionTextField() {
        position = new PlayerTextField();
        textLabel = new JLabel("    Position:      ");
        position.add(textLabel);
        add(textLabel);
        add(position);
    }

    private void addGoalsTextField() {
        goals = new PlayerTextField();
        textLabel = new JLabel("     Goals:         ");
        goals.add(textLabel);
        add(textLabel);
        add(goals);
    }

    private void addAssistsTextField() {
        assists = new PlayerTextField();
        textLabel = new JLabel("     Assists:       ");
        assists.add(textLabel);
        add(textLabel);
        add(assists);
    }

    private void addPassesTextField() {
        passes = new PlayerTextField();
        textLabel = new JLabel("     Passes:        ");
        passes.add(textLabel);
        add(textLabel);
        add(passes);
    }

    private void addSuccessPassesTextField() {
        successPasses = new PlayerTextField();
        textLabel = new JLabel("Success Passes:");
        successPasses.add(textLabel);
        add(textLabel);
        add(successPasses);
    }

    private void addInterceptionsTextField() {
        interceptions = new PlayerTextField();
        textLabel = new JLabel("  Interceptions: ");
        interceptions.add(textLabel);
        add(textLabel);
        add(interceptions);
    }

    private void addTacklesWonTextField() {
        tacklesWon = new PlayerTextField();
        textLabel = new JLabel("  Tackles Won:  ");
        tacklesWon.add(textLabel);
        add(textLabel);
        add(tacklesWon);
    }

    private void addAddButton() {
        addButton = new JButton();
        addButton.setBounds(ManagerPanel_WIDTH + 100,400, 30, 20);
        addButton.setLocation(ManagerPanel_WIDTH + 100, 400);
        addButton.addActionListener(this);
        addButton.setText("Add");
        this.add(addButton);
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

    private void addPlayerFeedbackLabel() {
        playerFeedbackLabel = new JLabel();
        //playerFeedbackLabel.setBounds(ManagerPanel_WIDTH + 100,450, 30, 20);
        playerFeedbackLabel.setPreferredSize(new Dimension(250, 20));
        this.add(playerFeedbackLabel);
    }

    private void addTeamNameChangedLabel() {
        teamNameChanged = new JLabel();
        teamName = playerPanel.getTeam().getName();
        teamNameChanged.setText("Team name changed to: " + teamName);
        add(teamNameChanged);
    }

    private void addTeamNameTextField() {
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
            addPlayer();
        }
        if (e.getSource() == changeNameButton) {
            changeTeamName();
        }
    }

    private void addPlayer() {
        Player newPlayer = createNewPlayer();
        if (playerPanel.getTeam().addPlayer(newPlayer)) {
            playerFeedbackLabel.setText("Successfully added " + newPlayer.getName() + " to the team.");
            playerPanel.updateModel(newPlayer);

        } else {
            playerFeedbackLabel.setText("<html>Unable to add new player:                              "
                    + "<br> name and number are not unique.</html>");
        }
    }

    private void changeTeamName() {
        teamName = newTeamName.getText();
        playerPanel.getTeam().changeName(teamName);
        addTeamNameChangedLabel();
        revalidate();
        repaint();
    }

}
