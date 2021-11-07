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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (playerList.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                //fireButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                //fireButton.setEnabled(true);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = playerList.getSelectedIndex();
        myTeam.allPlayers().remove(index);
        playerListModel.remove(index);
        int size = playerListModel.getSize();
        if (size == 0) {
            removeButton.setEnabled(false);
        }
        playerList.setSelectedIndex(index);
        playerList.ensureIndexIsVisible(index);

    }

}
