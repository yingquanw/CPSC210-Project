package model;

// Represents a team having a name, players
// with maximum number of players

import java.util.ArrayList;
import java.util.List;

public class Team {
    public static final int MAX_SIZE = 20;
    private String name;
    private List<Player> players;

    //EFFECTS: Create a new team with its name
    public Team(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    //EFFECTS: return the list of all players in the team
    public List<Player> allPlayers() {
        return players;
    }

    //EFFECTS: Produce a list of names for all the player in the team
    public ArrayList<String> playerNameList() {
        ArrayList<String> names = new ArrayList<>();
        for (Player next : players) {
            names.add(next.getName());
        }
        return names;
    }

    //EFFECTS: return the total numbers of players in the team
    public int numberOfPlayers() {
        int num = 0;
        for (Player next : players) {
            num++;
        }
        return num;
    }


    //MODIFIES: this
    //EFFECTS: if the total number of players < MAX_SIZE, return true and add a player,
    //         otherwise return false
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
    //EFFECTS: if the player is in the team, remove it and return true,
    //         otherwise return false
    public boolean removePlayer(String name) {
        for (Player next : players) {
            if (name.equals(next.getName())) {
                players.remove(next);
                return true;
            }
        }
        return false;
    }


    //EFFECTS: return the detailed statistics of all the players in the team
    public ArrayList<String> statsOfAllPlayers() {
        ArrayList<String> statsOfAllPlayer = new ArrayList<>();
        for (Player next : players) {
            statsOfAllPlayer.add(next.statsSummary());
        }
        return statsOfAllPlayer;
    }

    //EFFECTS: return the list of all attackers with names, numbers, goals and assists.
    public ArrayList<String> keyStatsOfAttackers() {
        ArrayList<String> keyStatsOfAttackers = new ArrayList<>();
        for (Player next : players) {
            if (next.getPosition().equals("ATT")) {
                String attackerStats = next.getName() + " No." + next.getNumber() + " Goals: " + next.getGoals()
                        + " Assists: " + next.getAssists();
                keyStatsOfAttackers.add(attackerStats);
            }
        }
        return keyStatsOfAttackers;
    }

    //EFFECTS: return the list of all midfielders with names, numbers, passes and passing accuracy.
    public ArrayList<String> keyStatsOfMidfielders() {
        ArrayList<String> keyStatsOfMidfielders = new ArrayList<>();
        for (Player next : players) {
            if (next.getPosition().equals("MID")) {
                String midfielderStats = next.getName() + " No." + next.getNumber() + " Passes: "
                        + next.getPasses() + " Success Passes: " + next.getSuccessPasses() + " Passing Accuracy: "
                        + next.passingAccuracy();
                keyStatsOfMidfielders.add(midfielderStats);
            }
        }
        return keyStatsOfMidfielders;
    }

    //EFFECTS: return the list of all defenders with names, numbers, interceptions and tackles won.
    public ArrayList<String> keyStatsOfDefenders() {
        ArrayList<String> keyStatsOfDefenders = new ArrayList<>();
        for (Player next : players) {
            if (next.getPosition().equals("DEF")) {
                String defenderStats = next.getName() + " No." + next.getNumber() + " Interceptions: "
                        + next.getInterceptions() + " Tackles Won: " + next.getTacklesWon();
                keyStatsOfDefenders.add(defenderStats);
            }
        }
        return keyStatsOfDefenders;
    }

    //EFFECTS: return turn if the team already have the player with the name provided
    //         return false otherwise
    public boolean containsPlayerName(String name) {
        for (Player next : players) {
            if (name.equals(next.getName())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: return turn if the team already have the player with the number provided
    //         return false otherwise
    public boolean containsPlayerNumber(int num) {
        for (Player next : players) {
            if (num == next.getNumber()) {
                return true;
            }
        }
        return false;
    }

    //REQUIRE: the name of player can be found, which is previously checked by containsPlayerName(String name)
    //EFFECTS: return the player with the provided name
    public Player getPlayer(String name) {
        for (Player next : players) {
            if (name.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    //EFFECTS: return the total goals of the team
    public int getTotalGoals() {
        int totalGoals = 0;
        for (Player next : players) {
            totalGoals = totalGoals + next.getGoals();
        }
        return totalGoals;
    }

    //EFFECTS: return the total assists of the team
    public int getTotalAssists() {
        int totalAssists = 0;
        for (Player next : players) {
            totalAssists = totalAssists + next.getAssists();
        }
        return totalAssists;
    }

    //EFFECTS: return the total passes of the team
    public int getTotalPasses() {
        int totalPasses = 0;
        for (Player next : players) {
            totalPasses = totalPasses + next.getPasses();
        }
        return totalPasses;
    }

    //EFFECTS: return the total success passes of the team
    public int getTotalSuccessPasses() {
        int totalSuccessPasses = 0;
        for (Player next : players) {
            totalSuccessPasses = totalSuccessPasses + next.getSuccessPasses();
        }
        return totalSuccessPasses;
    }

    //EFFECTS: return the total interceptions of the team
    public int getTotalInterceptions() {
        int totalInterceptions = 0;
        for (Player next : players) {
            totalInterceptions = totalInterceptions + next.getInterceptions();
        }
        return totalInterceptions;
    }

    //EFFECTS: return the total tackles won of the team
    public int getTotalTacklesWon() {
        int totalTacklesWon = 0;
        for (Player next : players) {
            totalTacklesWon = totalTacklesWon + next.getTacklesWon();
        }
        return totalTacklesWon;
    }


}
