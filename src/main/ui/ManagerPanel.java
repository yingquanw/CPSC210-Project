package ui;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static ui.MyHomeTeamGUI.FRAME_HEIGHT;
import static ui.MyHomeTeamGUI.FRAME_WIDTH;

// The manager panel of GUI, users can create and add new player to the team and change team name

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
    private JButton addPlayerButton;
    private JButton changeNameButton;
    private String teamName;
    private PlayerPanel playerPanel;

    //EFFECTS: creates the manager panel
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
        addAddPlayerButton();
        addPlayerFeedbackLabel();
        addTeamNameTextField();
        addChangeNameButton();
    }

    //MODIFIES: this
    //EFFECTS: adds Instruction Label to the panel
    private void addInstructionLabel() {
        JLabel instructionLabel = new JLabel();
        instructionLabel.setText("Create and add a new player here:");
        instructionLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(instructionLabel);
    }

    //MODIFIES: this
    //EFFECTS: adds change team name button to the panel
    private void addChangeNameButton() {
        changeNameButton = new JButton();
        changeNameButton.setBounds(ManagerPanel_WIDTH + 100,400, 30, 20);
        changeNameButton.setLocation(ManagerPanel_WIDTH + 100, 500);
        changeNameButton.addActionListener(this);
        changeNameButton.setText("Change");
        add(changeNameButton);
    }

    //MODIFIES: this
    //EFFECTS: adds name text field to the panel
    private void addNameTextField() {
        name = new PlayerTextField();
        textLabel = new JLabel("     Name:         ");
        name.add(textLabel);
        add(textLabel);
        add(name);
    }

    //MODIFIES: this
    //EFFECTS: adds number text field to the panel
    private void addNumberTextField() {
        number = new PlayerTextField();
        textLabel = new JLabel("   Number:       ");
        number.add(textLabel);
        add(textLabel);
        add(number);
    }

    //MODIFIES: this
    //EFFECTS: adds age text field to the panel
    private void addAgeTextField() {
        age = new PlayerTextField();
        textLabel = new JLabel("       Age:          ");
        age.add(textLabel);
        add(textLabel);
        add(age);
    }

    //MODIFIES: this
    //EFFECTS: adds position text field to the panel
    private void addPositionTextField() {
        position = new PlayerTextField();
        textLabel = new JLabel("    Position:      ");
        position.add(textLabel);
        add(textLabel);
        add(position);
    }

    //MODIFIES: this
    //EFFECTS: adds goals text field to the panel
    private void addGoalsTextField() {
        goals = new PlayerTextField();
        textLabel = new JLabel("     Goals:         ");
        goals.add(textLabel);
        add(textLabel);
        add(goals);
    }

    //MODIFIES: this
    //EFFECTS: adds assists text field to the panel
    private void addAssistsTextField() {
        assists = new PlayerTextField();
        textLabel = new JLabel("     Assists:       ");
        assists.add(textLabel);
        add(textLabel);
        add(assists);
    }

    //MODIFIES: this
    //EFFECTS: adds passes text field to the panel
    private void addPassesTextField() {
        passes = new PlayerTextField();
        textLabel = new JLabel("     Passes:        ");
        passes.add(textLabel);
        add(textLabel);
        add(passes);
    }

    //MODIFIES: this
    //EFFECTS: adds success passes text field to the panel
    private void addSuccessPassesTextField() {
        successPasses = new PlayerTextField();
        textLabel = new JLabel("Success Passes:");
        successPasses.add(textLabel);
        add(textLabel);
        add(successPasses);
    }

    //MODIFIES: this
    //EFFECTS: adds interceptions text field to the panel
    private void addInterceptionsTextField() {
        interceptions = new PlayerTextField();
        textLabel = new JLabel("  Interceptions: ");
        interceptions.add(textLabel);
        add(textLabel);
        add(interceptions);
    }

    //MODIFIES: this
    //EFFECTS: adds tackles won text field to the panel
    private void addTacklesWonTextField() {
        tacklesWon = new PlayerTextField();
        textLabel = new JLabel("  Tackles Won:  ");
        tacklesWon.add(textLabel);
        add(textLabel);
        add(tacklesWon);
    }

    //MODIFIES: this
    //EFFECTS: adds add player button to the panel
    private void addAddPlayerButton() {
        addPlayerButton = new JButton();
        addPlayerButton.setBounds(ManagerPanel_WIDTH + 100,400, 30, 20);
        addPlayerButton.setLocation(ManagerPanel_WIDTH + 100, 400);
        addPlayerButton.addActionListener(this);
        addPlayerButton.setText("Add");
        add(addPlayerButton);
    }

    //EFFECTS: creates a new player based on the inputs given by the user
    private Player createNewPlayer() {
        String plName = name.getText();
        int plNumber = Integer.parseInt(number.getText());
        int plAge = Integer.parseInt(age.getText());
        String plPosition = position.getText();
        int plGoals = Integer.parseInt(goals.getText());
        int plAssists = Integer.parseInt(assists.getText());
        int plPasses = Integer.parseInt(passes.getText());
        int plSuccessPasses = Integer.parseInt(successPasses.getText());
        int plInterceptions = Integer.parseInt(interceptions.getText());
        int plTacklesWon = Integer.parseInt(tacklesWon.getText());
        Player newPlayer = new Player(plName, plNumber, plAge, plPosition, plGoals, plAssists, plPasses,
                plSuccessPasses, plInterceptions, plTacklesWon);
        return newPlayer;
    }

    //MODIFIES: this
    //EFFECTS: prints text on panel to notify the user whether the new player is successfully added to the team
    private void addPlayerFeedbackLabel() {
        playerFeedbackLabel = new JLabel();
        playerFeedbackLabel.setPreferredSize(new Dimension(250, 40));
        add(playerFeedbackLabel);
    }

    //MODIFIES: this
    //EFFECTS: prints text on panel to notify the user both old and new name of the team
    private void addTeamNameChangedLabel(String oldTeamName) {
        teamNameChanged = new JLabel();
        String newTeamName = playerPanel.getTeam().getName();
        teamNameChanged.setText("<html>Old Team name: " + oldTeamName + "<br> New Team name: "
                + newTeamName + "</html>");
        add(teamNameChanged);
    }

    //MODIFIES: this
    //EFFECTS: adds new team name text field to the panel
    private void addTeamNameTextField() {
        newTeamName = new JTextField();
        newTeamName.setPreferredSize(new Dimension(ManagerPanel_WIDTH - 150, 28));
        textLabel = new JLabel("Enter new team name:       ");
        textLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        add(textLabel);
        add(newTeamName);
    }

    //EFFECTS: if addPlayer button is clicked, tries to add the new player
    //         if changeNameButton button is clicked, change the team name
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayerButton) {
            addPlayer();
        }
        if (e.getSource() == changeNameButton) {
            changeTeamName();
        }
    }

    //EFFECTS: creates a player and tries to add him to the team
    //         if failed, sets text of addPlayerFeedbackLabel to notify user
    private void addPlayer() {
        Player newPlayer = createNewPlayer();
        if (playerPanel.getTeam().addPlayer(newPlayer)) {
            playerFeedbackLabel.setText("<html>Successfully added " + newPlayer.getName()
                    + "<br> to the team.</html>");
            playerPanel.updateModel(newPlayer);

        } else {
            playerFeedbackLabel.setText("<html>Unable to add new player:                              "
                    + "<br> name and number are not unique.</html>");
        }
    }

    //EFFECTS: changes the name of the team
    private void changeTeamName() {
        String oldTeamName = playerPanel.getTeam().getName();
        teamName = newTeamName.getText();
        playerPanel.getTeam().changeName(teamName);
        addTeamNameChangedLabel(oldTeamName);
        revalidate();
        repaint();
    }

}
