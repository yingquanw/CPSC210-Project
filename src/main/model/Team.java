package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a team having a name, a list of players with maximum size

public class Team implements Writable {
    public static final int MAX_SIZE = 20;
    private String name;
    private List<Player> players;

    //EFFECTS: creates a new team with its name
    public Team(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    //getter of the team's name
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: changes the name of the team
    public void changeName(String name) {
        this.name = name;
    }

    //EFFECTS: returns the list of all players in the team
    public List<Player> allPlayers() {
        return players;
    }

    //EFFECTS: returns a list of names for all the players in the team
    public ArrayList<String> playerNameList() {
        ArrayList<String> names = new ArrayList<>();
        for (Player next : players) {
            names.add(next.getName());
        }
        return names;
    }

    //EFFECTS: returns the total number of players in the team
    public int numberOfPlayers() {
        int num = 0;
        for (Player next : players) {
            num++;
        }
        return num;
    }

    //MODIFIES: this
    //EFFECTS: if the total number of players < MAX_SIZE, no player in the team had the same
    //         name or had same number with this new player
    //         returns true and adds a player, otherwise returns false
    public boolean addPlayer(Player p) {
        if (players.size() < MAX_SIZE) {
            if (!containsPlayerName(p.getName()) && !containsPlayerNumber(p.getNumber())) {
                players.add(p);
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: if the player is in the team, removes it and returns true,
    //         otherwise returns false
    public boolean removePlayer(String name) {
        for (Player next : players) {
            if (name.equals(next.getName())) {
                players.remove(next);
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the detailed statistics of all the players in the team
    public ArrayList<String> statsOfAllPlayers() {
        ArrayList<String> statsOfAllPlayer = new ArrayList<>();
        for (Player next : players) {
            statsOfAllPlayer.add(next.statsSummary());
        }
        return statsOfAllPlayer;
    }

    //EFFECTS: returns ture if the team already had a player having same name with the name provided
    //         returns false otherwise
    public boolean containsPlayerName(String name) {
        for (Player next : players) {
            if (name.equals(next.getName())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns ture if the team already had a player having same number with the number provided
    //         returns false otherwise
    public boolean containsPlayerNumber(int num) {
        for (Player next : players) {
            if (num == next.getNumber()) {
                return true;
            }
        }
        return false;
    }

    //REQUIRE: the name of player can be found, which is previously checked by containsPlayerName(String name)
    //EFFECTS: returns the player having the provided name
    public Player getPlayer(String name) {
        for (Player next : players) {
            if (name.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    //EFFECTS: returns the total goals of the team
    public int getTotalGoals() {
        int totalGoals = 0;
        for (Player next : players) {
            totalGoals = totalGoals + next.getGoals();
        }
        return totalGoals;
    }

    //EFFECTS: returns the total assists of the team
    public int getTotalAssists() {
        int totalAssists = 0;
        for (Player next : players) {
            totalAssists = totalAssists + next.getAssists();
        }
        return totalAssists;
    }

    //EFFECTS: returns the total passes of the team
    public int getTotalPasses() {
        int totalPasses = 0;
        for (Player next : players) {
            totalPasses = totalPasses + next.getPasses();
        }
        return totalPasses;
    }

    //EFFECTS: returns the total success passes of the team
    public int getTotalSuccessPasses() {
        int totalSuccessPasses = 0;
        for (Player next : players) {
            totalSuccessPasses = totalSuccessPasses + next.getSuccessPasses();
        }
        return totalSuccessPasses;
    }

    //EFFECTS: returns the total interceptions of the team
    public int getTotalInterceptions() {
        int totalInterceptions = 0;
        for (Player next : players) {
            totalInterceptions = totalInterceptions + next.getInterceptions();
        }
        return totalInterceptions;
    }

    //EFFECTS: returns the total tackles won of the team
    public int getTotalTacklesWon() {
        int totalTacklesWon = 0;
        for (Player next : players) {
            totalTacklesWon = totalTacklesWon + next.getTacklesWon();
        }
        return totalTacklesWon;
    }

    //EFFECTS: returns Json representation of a Player
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns players in the team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : players) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
