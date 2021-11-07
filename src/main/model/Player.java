package model;

// Represents a player having a name, number, age, position played, goal scored, assists, passes
// success passes, interceptions, and number of tackles won.

import org.json.JSONObject;
import persistence.Writable;

public class Player implements Writable {
    private String name;
    private int number;
    private int age;
    private String position;
    private int goals;
    private int assists;
    private int passes;
    private int successPasses;
    private int interceptions;
    private int tacklesWon;

    //EFFECTS: creates a player with name, number, age, and position
    public Player(String name, int number, int age, String position, int goals, int assists, int passes,
                  int successPasses, int interceptions, int tacklesWon) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.position = position;
        this.goals = goals;
        this.assists = assists;
        this.passes = passes;
        this.successPasses = successPasses;
        this.interceptions = interceptions;
        this.tacklesWon = tacklesWon;
    }

    //getter of player's name
    public String getName() {
        return name;
    }

    //getter of player's number
    public int getNumber() {
        return number;
    }

    //getter of player's age
    public int getAge() {
        return age;
    }

    //getter of player's position
    public String getPosition() {
        return position;
    }

    //getter of player's goals
    public int getGoals() {
        return goals;
    }

    //REQUIRES: g is greater than or equals to 0
    //MODIFIES: this
    //EFFECTS: adds g goal(s) to the total goals of the player
    public void addGoals(int g) {
        goals = goals + g;
    }

    //getter of player's assists
    public int getAssists() {
        return assists;
    }

    //REQUIRES: a is greater than or equals to 0
    //MODIFIES: this
    //EFFECTS: adds a assist(s) to the total assists of the player
    public void addAssists(int a) {
        assists = assists + a;
    }

    //getter of player's passes
    public int getPasses() {
        return passes;
    }

    //REQUIRES: p is greater than or equals to 0
    //MODIFIES: this
    //EFFECTS: adds p pass(s) to the total passes of the player
    public void addPasses(int p) {
        passes = passes + p;
    }

    //getter of player's success passes
    public int getSuccessPasses() {
        return successPasses;
    }

    //REQUIRES: sp is greater than or equals to 0
    //MODIFIES: this
    //EFFECTS: adds sp success pass(s) to the total success passes of the player
    public void addSuccessPasses(int sp) {
        successPasses = successPasses + sp;
    }

    //getter of player's interceptions
    public int getInterceptions() {
        return interceptions;
    }

    //REQUIRES: i is greater than or equals to 0
    //MODIFIES: this
    //EFFECTS: adds i interception(s) to the total interceptions of the player
    public void addInterceptions(int i) {
        interceptions = interceptions + i;
    }

    //getter of player's tackles won
    public int getTacklesWon() {
        return tacklesWon;
    }

    //REQUIRES: is greater than or equals to 0
    //MODIFIES: this
    //EFFECTS: adds t time(s) of tackle won to the total times of tackle won of the player
    public void addTacklesWon(int t) {
        tacklesWon = tacklesWon + t;
    }

    //EFFECTS: returns a string of statistics summary of the player
    public String statsSummary() {
        return name + " No." + number + " Age: " + age + " Position: " + position + " Goals: " + goals
                + " Assists: " + assists + " Passes: " + passes + " Success passes: " + successPasses
                + " Interceptions: " + interceptions + " Tackles Won: " + tacklesWon;
    }

    //EFFECTS: returns Json representation of a Player
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("number", number);
        json.put("age", age);
        json.put("position", position);
        json.put("goals", goals);
        json.put("assists", assists);
        json.put("passes", passes);
        json.put("successPasses", successPasses);
        json.put("interceptions", interceptions);
        json.put("tacklesWon", tacklesWon);
        return json;
    }

    @Override
    public String toString() {
        String name = this.name;
        return name;
    }
}
