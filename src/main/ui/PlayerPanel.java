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

    public static final int PlayerPanel_WIDTH = FRAME_WIDTH / 2;
    public static final int PlayerPanel_HEIGHT = FRAME_HEIGHT;
    private static final String JSON_STORE = "./data/myTeam.json";

    JLabel label;
    JList<Player> playerList;
    DefaultListModel<Player> playerListModel;
    JScrollPane playerListScrollPanel;
    JButton removeButton;
    JLabel playerStats;
    Team myTeam;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PlayerPanel() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setBackground(Color.LIGHT_GRAY);
        setBounds(0,0,PlayerPanel_WIDTH, PlayerPanel_HEIGHT);
        add(createLabel());
        //createPlayerList();
        JComponent newContentPane = createPlayerList();
        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setPreferredSize(new Dimension(PlayerPanel_WIDTH,PlayerPanel_HEIGHT / 2));
        add(newContentPane);
        addRemoveButton();
        addPlayerStats();
    }

    public JLabel createLabel() {
        label = new JLabel();
        label.setText("Player List");
        return label;
    }

    public DefaultListModel createModel() {
        playerListModel = new DefaultListModel<>();
        myTeam = loadTeam();
        for (Player next : myTeam.allPlayers()) {
            playerListModel.addElement(next);
        }
        return playerListModel;
    }

    public void addRemoveButton() {
        removeButton = new JButton();
        removeButton.setBounds(100,400, 30, 20);
        removeButton.addActionListener(this);
        removeButton.setText("Remove");
        this.add(removeButton);
    }


    private Team loadTeam() {
        try {
            myTeam = jsonReader.read();
            System.out.println("Loaded " + myTeam.getName() + " from " + JSON_STORE);
            return myTeam;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return myTeam;
        }
    }

    public JScrollPane createPlayerList() {
        playerList = new JList(createModel());
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerList.setSelectedIndex(0);
        playerList.addListSelectionListener(this);
        playerList.setVisibleRowCount(15);
        playerListScrollPanel = new JScrollPane(playerList);
        return playerListScrollPanel;
    }

//    public void addPlayerStats() {
//        playerStats = new JLabel();
//        int index = playerList.getSelectedIndex();
//        Player p = myTeam.allPlayers().get(index);
//        String playerInfo = p.statsSummary();
//        playerStats.setText(playerInfo);
//        playerStats.setBounds(100, 450, 250, 150);
//        this.add(playerStats);
//    }

    public void addPlayerStats() {
        playerStats = new JLabel();
        int index = playerList.getSelectedIndex();
        Player p = myTeam.allPlayers().get(index);
        //String playerInfo = p.statsSummary();
        playerStats.setText("<html>" + p.getName() + "<br>" + p.getNumber() + "<br>" + p.getPosition()
                + "<br>" + p.getGoals() + "<br>" + p.getAssists() + "<br>" + p.getPasses() + "<br>"
                + p.getSuccessPasses() + "<br>" + p.getInterceptions() + "<br>" + p.getTacklesWon() + "</html>");
        playerStats.setPreferredSize(new Dimension(200,150));
        playerStats.setBounds(150, 450, 250, 150);
        this.add(playerStats);
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
        //updatePlayerStats();

    }

    public void updatePlayerStats() {
        int index = playerList.getSelectedIndex();
        if (playerList.getSelectedIndex() != -1) {
            Player p = myTeam.allPlayers().get(index);
            playerStats.setText("<html>" + p.getName() + "<br>" + p.getNumber() + "<br>" + p.getPosition()
                    + "<br>" + p.getGoals() + "<br>" + p.getAssists() + "<br>" + p.getPasses() + "<br>"
                    + p.getSuccessPasses() + "<br>" + p.getInterceptions() + "<br>" + p.getTacklesWon() + "</html>");
        } else {
            playerStats.setText("");
        }
        playerStats.setPreferredSize(new Dimension(200,150));
        //playerStats.setBounds(100, 450, 250, 150);
        this.add(playerStats);
//        Player p = myTeam.allPlayers().get(index);
//        String playerInfo = p.statsSummary();
//        playerStats.setText(playerInfo);
//        playerStats.setPreferredSize(new Dimension(250,150));
//        //playerStats.setBounds(100, 450, 250, 150);
//        this.add(playerStats);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

    // EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTeam);
            jsonWriter.close();
            //System.out.println("Saved " + myTeam.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            //System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public Team getTeam() {
        return myTeam;
    }

    public JList<Player> getPlayerList() {
        return playerList;
    }

}
