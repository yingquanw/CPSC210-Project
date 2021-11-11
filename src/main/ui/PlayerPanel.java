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

    public PlayerPanel() throws FileNotFoundException {
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

    private void addPlayerListScrollPanel() {
        JComponent newContentPane = createPlayerList();
        newContentPane.setOpaque(true);
        newContentPane.setPreferredSize(new Dimension(PlayerPanel_WIDTH, PlayerPanel_HEIGHT / 2));
        add(newContentPane);
    }

    private void addPlayerListLabel() {
        playerListLabel = new JLabel();
        playerListLabel.setText("               Player List:                ");
        playerListLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        add(playerListLabel);
    }

    private void addSaveLabel() {
        saveLabel = new JLabel();
        saveLabel.setText("Saved!");
        add(saveLabel);
        saveLabel.setVisible(false);
    }

    private DefaultListModel createModel() {
        playerListModel = new DefaultListModel<>();
        //myTeam = loadTeam();
        for (Player next : myTeam.allPlayers()) {
            playerListModel.addElement(next);
        }
        return playerListModel;
    }

    public void updateModel(Player p) {
        playerListModel.addElement(p);
        int index = playerList.getSelectedIndex();
        if (index == -1) {
            playerList.setSelectedIndex(0);
        }
        playerList.updateUI();
    }

    private void updateModel() {
        for (Player next : myTeam.allPlayers()) {
            playerListModel.addElement(next);
        }
        if (myTeam.allPlayers() != null) {
            playerList.setSelectedIndex(0);
        }
        updatePlayerStats();
        revalidate();
        repaint();
    }

    private void addRemoveButton() {
        removeButton = new JButton();
        removeButton.setBounds(100,400, 30, 20);
        removeButton.addActionListener(this);
        removeButton.setText("Remove");
        this.add(removeButton);
    }

    private void addLoadButton() {
        loadButton = new JButton();
        loadButton.setBounds(50, 20, 30, 20);
        loadButton.addActionListener(this);
        loadButton.setText("Load");
        this.add(loadButton);
    }

    private void addSaveButton() {
        saveButton = new JButton();
        saveButton.setBounds(100, 20, 30, 20);
        saveButton.addActionListener(this);
        saveButton.setText("Save");
        this.add(saveButton);
    }


    private Team loadTeam() {
        try {
            myTeam = jsonReader.read();
            return myTeam;
        } catch (IOException e) {
            e.printStackTrace();
            return myTeam;
        }
    }

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


    private void addPlayerStats() {
        playerStats = new JLabel();
        int index = playerList.getSelectedIndex();
        if (index != -1) {
            Player p = myTeam.allPlayers().get(index);
            playerStats.setText("<html>" + p.getName() + "<br>" + "No." + p.getNumber() + "<br>"
                    + "Pos: " + p.getPosition() + "<br>" + "G: " + p.getGoals() + "<br>" + "A: " + p.getAssists()
                    + "<br>" + "P: " + p.getPasses() + "<br>" + "SP: " + p.getSuccessPasses() + "<br>"
                    + "I: " + p.getInterceptions() + "<br>" + "TW: " + p.getTacklesWon() + "</html>");
            playerStats.setPreferredSize(new Dimension(200,150));
            playerStats.setBounds(150, 450, 250, 150);
            add(playerStats);
        }
    }

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

    private void updatePlayerStats() {
        int index = playerList.getSelectedIndex();
        Player p = myTeam.allPlayers().get(index);
        playerStats.setText("<html>" + p.getName() + "<br>" + "No." + p.getNumber() + "<br>"
                + "Pos: " + p.getPosition() + "<br>" + "G: " + p.getGoals() + "<br>" + "A: " + p.getAssists()
                + "<br>" + "P: " + p.getPasses() + "<br>" + "SP: " + p.getSuccessPasses() + "<br>"
                + "I: " + p.getInterceptions() + "<br>" + "TW: " + p.getTacklesWon() + "</html>");
        playerStats.setPreferredSize(new Dimension(200,150));
        add(playerStats);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            int index = playerList.getSelectedIndex();
            myTeam.allPlayers().remove(index);
            playerListModel.remove(index);
            //saveTeam();
            int size = playerListModel.getSize();
            if (size == 0) {
                removeButton.setEnabled(false);
            }
            playerList.setSelectedIndex(index);
            playerList.ensureIndexIsVisible(index);
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

    // EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTeam);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Team getTeam() {
        return myTeam;
    }


}
