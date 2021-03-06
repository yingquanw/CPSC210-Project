package ui;

import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.MyHomeTeamGUI.FRAME_WIDTH;
import static ui.MyHomeTeamGUI.FRAME_HEIGHT;

// The panel with information of players, users can select and remove player
// Users can choose to load and save the team

public class PlayerPanel extends JPanel implements ListSelectionListener, ActionListener {

    private static final int PlayerPanel_WIDTH = FRAME_WIDTH / 2;
    private static final int PlayerPanel_HEIGHT = FRAME_HEIGHT;
    private static final String JSON_STORE = "./data/myTeam.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Team myTeam;
    private JLabel playerListLabel;
    private JLabel saveLabel;
    private JLabel playerStats;
    private JButton loadButton;
    private JButton saveButton;
    private JButton removeButton;
    private JList<Player> playerList;
    private DefaultListModel<Player> playerListModel;
    private JScrollPane playerListScrollPanel;

    //EFFECTS: creates the player panel
    public PlayerPanel() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.myTeam = new Team("Liverpool");
        setBackground(Color.LIGHT_GRAY);
        setBounds(0,0,PlayerPanel_WIDTH, PlayerPanel_HEIGHT);
        setSize(new Dimension(PlayerPanel_WIDTH, PlayerPanel_HEIGHT));
        addLoadButton();
        addSaveButton();
        addSaveLabel();
        addPlayerListLabel();
        addPlayerListScrollPanel();
        addRemoveButton();
        addPlayerStats();
    }

    //MODIFIES: this
    //EFFECTS: adds load button to the panel
    private void addLoadButton() {
        loadButton = new JButton();
        loadButton.setBounds(50, 20, 30, 20);
        loadButton.addActionListener(this);
        loadButton.setText("Load");
        add(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: adds save button to the panel
    private void addSaveButton() {
        saveButton = new JButton();
        saveButton.setBounds(100, 20, 30, 20);
        saveButton.addActionListener(this);
        saveButton.setText("Save");
        add(saveButton);
    }

    //MODIFIES: this
    //EFFECTS: adds save label to the panel to notify the user changes are saved
    private void addSaveLabel() {
        saveLabel = new JLabel();
        saveLabel.setText("Saved!");
        add(saveLabel);
        saveLabel.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: adds remove button to the panel
    private void addRemoveButton() {
        removeButton = new JButton();
        removeButton.setBounds(100,400, 30, 20);
        removeButton.addActionListener(this);
        removeButton.setText("Remove");
        add(removeButton);
    }

    //MODIFIES: this
    //EFFECTS: adds player list label to the panel
    private void addPlayerListLabel() {
        playerListLabel = new JLabel();
        playerListLabel.setText("               Player List:                ");
        playerListLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        add(playerListLabel);
    }

    //MODIFIES: this
    //EFFECTS: adds scroll panel that contains players to the panel
    private void addPlayerListScrollPanel() {
        JComponent newContentPane = createPlayerList();
        newContentPane.setOpaque(true);
        newContentPane.setPreferredSize(new Dimension(PlayerPanel_WIDTH, PlayerPanel_HEIGHT / 2));
        add(newContentPane);
    }

    //MODIFIES: this
    //EFFECTS: returns JScrollPane that contains players
    private JScrollPane createPlayerList() {
        playerList = new JList(createModel());
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        playerList.setSelectedIndex(0);
        playerList.addListSelectionListener(this);
        playerList.setVisibleRowCount(15);
        playerListScrollPanel = new JScrollPane(playerList);
        return playerListScrollPanel;
    }

    //MODIFIES: this
    //EFFECTS: returns the DefaultListModel of players
    private DefaultListModel createModel() {
        playerListModel = new DefaultListModel<>();
        for (Player next : myTeam.getPlayers()) {
            playerListModel.addElement(next);
        }
        return playerListModel;
    }

    //MODIFIES: this
    //EFFECTS: updates the model when new player is added
    public void updateModel(Player p) {
        playerListModel.addElement(p);
        int index = playerList.getSelectedIndex();
        if (index == -1) {
            playerList.setSelectedIndex(0);
        }
        playerList.updateUI();
    }

    //MODIFIES: this
    //EFFECTS: updates the model when loaded a team
    private void updateModel() {
        for (Player next : myTeam.getPlayers()) {
            playerListModel.addElement(next);
        }
        if (myTeam.getPlayers() != null) {
            playerList.setSelectedIndex(0);
        }
        updatePlayerStats();
        revalidate();
        repaint();
    }

    //MODIFIES: this
    //EFFECTS: prints text on panel about the detailed statistics of the selected player
    private void addPlayerStats() {
        playerStats = new JLabel();
        int index = playerList.getSelectedIndex();
        if (index != -1) {
            Player p = myTeam.getPlayers().get(index);
            playerStats.setText("<html>" + p.getName() + "<br>" + "No." + p.getNumber() + "<br>"
                    + "Pos: " + p.getPosition() + "<br>" + "G: " + p.getGoals() + "<br>" + "A: " + p.getAssists()
                    + "<br>" + "P: " + p.getPasses() + "<br>" + "SP: " + p.getSuccessPasses() + "<br>"
                    + "I: " + p.getInterceptions() + "<br>" + "TW: " + p.getTacklesWon() + "</html>");
            playerStats.setPreferredSize(new Dimension(200,150));
            playerStats.setBounds(150, 450, 250, 150);
            add(playerStats);
        }
    }

    //MODIFIES: this
    //EFFECTS: updates player statistics when a different player is selected,
    //         clear all the text in the playerStats label if no player is selected
    private void updatePlayerStats() {
        int index = playerList.getSelectedIndex();
        if (index != -1) {
            Player p = myTeam.getPlayers().get(index);
            playerStats.setText("<html>" + p.getName() + "<br>" + "No." + p.getNumber() + "<br>"
                        + "Pos: " + p.getPosition() + "<br>" + "G: " + p.getGoals() + "<br>" + "A: " + p.getAssists()
                        + "<br>" + "P: " + p.getPasses() + "<br>" + "SP: " + p.getSuccessPasses() + "<br>"
                        + "I: " + p.getInterceptions() + "<br>" + "TW: " + p.getTacklesWon() + "</html>");
        } else {
            playerStats.setText("");
        }
        playerStats.setPreferredSize(new Dimension(200,150));
        add(playerStats);
    }

    //MODIFIES: this
    //EFFECTS: loads team from JSON_STORE
    private Team loadTeam() {
        try {
            myTeam = jsonReader.read();
            return myTeam;
        } catch (IOException e) {
            e.printStackTrace();
            return myTeam;
        }
    }

    //EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTeam);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //getter of the team of PlayerPanel
    public Team getTeam() {
        return myTeam;
    }

    //MODIFIES: this
    //EFFECTS: handles the changes when a different player is selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (playerList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
            updatePlayerStats();
        }
    }

    //EFFECTS: handles the commands from all the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            removePlayer();
        }
        if (e.getSource() == loadButton) {
            myTeam = loadTeam();
            updateModel();
            loadButton.setEnabled(false);
        }
        if (e.getSource() == saveButton) {
            saveTeam();
            saveLabel.setVisible(true);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the selected player from the team
    //         if there is no player in the team, disables the remove button
    private void removePlayer() {
        int index = playerList.getSelectedIndex();
        String playerName = myTeam.getPlayers().get(index).getName();
        myTeam.removePlayer(playerName);
        if (playerListModel.getSize() > 1) {
            playerList.setSelectedIndex(index - 1);
        }
        playerListModel.remove(index);
        int size = playerListModel.getSize();
        if (size == 0) {
            removeButton.setEnabled(false);
        }
        playerList.ensureIndexIsVisible(index);
    }

}
