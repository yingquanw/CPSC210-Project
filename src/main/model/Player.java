package model;

// Represents a player having a name, number, age, position played, goal scored, assists, passing
// success pass, interceptions, and number of tackles won.

public class Player {
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

    //EFFECTS: Create a player with name, number, age, and position
    public Player(String name, int number, int age, String position) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.position = position;
        this.goals = 0;
        this.assists = 0;
        this.passes = 0;
        this.successPasses = 0;
        this.interceptions = 0;
        this.tacklesWon = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public int getGoals() {
        return goals;
    }

    //REQUIRES: g is greater than 0
    //MODIFIES: this
    //EFFECTS: add g goal(s) to the total goals of the player
    public void addGoals(int g) {
        goals = goals + g;
    }

    public int getAssists() {
        return assists;
    }

    //REQUIRES: a is greater than 0
    //MODIFIES: this
    //EFFECTS: add a assist(s) to the total assists of the player
    public void addAssists(int a) {
        assists = assists + a;
    }

    public int getPasses() {
        return passes;
    }

    //REQUIRES: p is greater than 0
    //MODIFIES: this
    //EFFECTS: add p pass(s) to the total passes of the player
    public void addPasses(int p) {
        passes = passes + p;
    }

    public int getSuccessPasses() {
        return successPasses;
    }

    //REQUIRES: sp is greater than 0
    //MODIFIES: this
    //EFFECTS: add sp success pass(s) to the total success passes of the player
    public void addSuccessPasses(int sp) {
        successPasses = successPasses + sp;
    }

    public int getInterceptions() {
        return interceptions;
    }

    //REQUIRES: i is greater than 0
    //MODIFIES: this
    //EFFECTS: add i interception(s) to the total interceptions of the player
    public void addInterceptions(int i) {
        interceptions = interceptions + i;
    }

    public int getTacklesWon() {
        return tacklesWon;
    }

    //REQUIRES: t is greater than 0
    //MODIFIES: this
    //EFFECTS: add t time(s) of tackle won to the total times of tackle won of the player
    public void addTacklesWon(int t) {
        tacklesWon = tacklesWon + t;
    }

    //REQUIRES: Pass >= 0 and successPass >= 0, Pass >= successPass
    //EFFECTS: Return the passing accuracy of the player.
    public int passingAccuracy() {
        if (passes != 0) {
            return (successPasses * 100) / passes;
        } else {
            return 0;
        }
    }

    //EFFECTS: Return a string of the summary of the player
    public String statsSummary() {
        return name + " No." + number + " Age: " + age + " Position: " + position + " Goals: " + goals
                + " Assists: " + assists + " Passes: " + passes + " Success passes: " + successPasses
                + " Interceptions: " + interceptions + " Tackles Won: " + tacklesWon;
    }

}
