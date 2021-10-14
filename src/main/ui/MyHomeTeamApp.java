package ui;

import model.Player;
import model.Team;
import java.util.Scanner;

import static model.Team.MAX_SIZE;

// My Home Team application

public class MyHomeTeamApp {
    private Team myTeam;
    private Scanner input;
    private String command;

    //EFFECTS: runs My Home Team application
    public MyHomeTeamApp() {
        runTeam();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runTeam() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayMainMenu();
            command = getInput();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }

        System.out.println("\nThank you for using My Home Team!");
    }

    //MODIFIES: this
    //EFFECTS: returns the command that the user entered
    private String getInput() {
        String command;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        command = input.next();
        return command;
    }

    //EFFECTS: prints the main menu
    private void displayMainMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\ta -> Check Team Statistics");
        System.out.println("\tb -> Check Player Statistics");
        System.out.println("\tc -> Change Player Statistics");
        System.out.println("\td -> Add or Remove Player");
        System.out.println("\te -> Change Team Name");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: initializes a new team and adds some players in
    private void init() {
        myTeam = new Team("Liverpool");
        System.out.println("\nHi! This is " + myTeam.getName() + " Soccer Club.");
        myTeam.addPlayer(createSalah());
        myTeam.addPlayer(createMane());
        myTeam.addPlayer(createJota());
        myTeam.addPlayer(createFabinho());
        myTeam.addPlayer(createMilner());
        myTeam.addPlayer(createVanDijk());
        myTeam.addPlayer(createMatip());
    }

    //EFFECTS: processes the main command given by the user
    private void processMainCommand(String command) {
        if (command.equals("a")) {
            checkTeamStats();
        } else if (command.equals("b")) {
            checkPlayerStats();
        } else if (command.equals("c")) {
            changePlayerStats();
        } else if (command.equals("d")) {
            addOrRemovePlayer();
        } else if (command.equals("e")) {
            changeTeamName();
        } else {
            System.out.println("Selection is invalid.");
        }
    }

    //MODIFIES: this
    //EFFECTS: lets users choose what team statistics they want to check
    private void checkTeamStats() {
        displayTeamStatsMenu();
        command = getInput();
        processTeamStatsCommand(command);
    }

    //EFFECTS: prints the team statistics menu
    private void displayTeamStatsMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\t1 -> List of all players");
        System.out.println("\t2 -> Total number of players");
        System.out.println("\t3 -> Overview of all players");
        System.out.println("\t4 -> Total goals");
        System.out.println("\t5 -> Total assists");
        System.out.println("\t6 -> Total passes");
        System.out.println("\t7 -> Total success passes");
        System.out.println("\t8 -> Total interceptions");
        System.out.println("\t9 -> Total tackles won");
        System.out.println("\tPress any other key to go back to main menu");
    }

    //EFFECTS: processes the team statistics command given by the user
    private void processTeamStatsCommand(String command) {
        if (command.equals("1")) {
            System.out.println("All the players in the team:");
            printPlayers();
        } else if (command.equals("2")) {
            System.out.println("Total number of players: " + myTeam.numberOfPlayers());
        } else if (command.equals("3")) {
            printStatsOfAllPlayers();
        } else if (command.equals("4")) {
            System.out.println("Total goals: " + myTeam.getTotalGoals());
        } else if (command.equals("5")) {
            System.out.println("Total assists: " + myTeam.getTotalAssists());
        } else if (command.equals("6")) {
            System.out.println("Total passes: " + myTeam.getTotalPasses());
        } else if (command.equals("7")) {
            System.out.println("Total success passes: " + myTeam.getTotalSuccessPasses());
        } else if (command.equals("8")) {
            System.out.println("Total interceptions: " + myTeam.getTotalInterceptions());
        } else if (command.equals("9")) {
            System.out.println("Total tackles won: " + myTeam.getTotalTacklesWon());
        }
    }

    //EFFECTS: prints the name of all players in the team
    private void printPlayers() {
        for (String next : myTeam.playerNameList()) {
            System.out.println(next);
        }
    }

    //EFFECTS: prints the summary statistics of all players in the team
    private void printStatsOfAllPlayers() {
        System.out.println("Overview of all players:");
        for (String next : myTeam.statsOfAllPlayers()) {
            System.out.println(next);
        }
    }

    //MODIFIES: this
    //EFFECTS: enables users to check player statistics
    private void checkPlayerStats() {
        System.out.println("Please enter the name of the player you want to check: ");
        printPlayers();
        String name = getInput();
        if (myTeam.containsPlayerName(name)) {
            displayPlayerStatsMenu(name);
            Player selectedPlayer = myTeam.getPlayer(name);
            command = getInput();
            showPlayerStats(selectedPlayer, command);
        } else {
            System.out.println("The player you are looking for is not in the team.");
        }
    }

    //EFFECTS: prints the summary statistics of a player
    public void displayPlayerStatsMenu(String name) {
        System.out.println("You are now selecting " + name);
        System.out.println("Please then select from:");
        System.out.println("\tg -> Goals");
        System.out.println("\ta -> Assists");
        System.out.println("\tp -> Passes");
        System.out.println("\ts -> Success passes");
        System.out.println("\ti -> Interceptions");
        System.out.println("\tt -> Tackles won");
        System.out.println("\tPress any other key to go back to main menu");
    }

    //EFFECTS: processes the player statistics command given by the user
    public void showPlayerStats(Player p, String command) {
        if (command.equals("g")) {
            System.out.println("Goals: " + p.getGoals());
        } else if (command.equals("a")) {
            System.out.println("Assists: " + p.getAssists());
        } else if (command.equals("p")) {
            System.out.println("Passes: " + p.getPasses());
        } else if (command.equals("s")) {
            System.out.println("Success Passes: " + p.getSuccessPasses());
        } else if (command.equals("i")) {
            System.out.println("Interceptions: " + p.getInterceptions());
        } else if (command.equals("t")) {
            System.out.println("Tackles Won: " + p.getTacklesWon());
        }
    }

    //MODIFIES: this
    //EFFECTS: enables users to change statistics of a player
    private void changePlayerStats() {
        System.out.println("Please enter the name of the player you want to change statistics: ");
        printPlayers();
        String name = getInput();
        if (myTeam.containsPlayerName(name)) {
            displayPlayerStatsMenu(name);
            Player selectedPlayer = myTeam.getPlayer(name);
            String stats = getInput();
            showPlayerStats(selectedPlayer, stats);
            System.out.println("Please enter the amount you want to add: ");
            String amount = getInput();
            int amountInt = Integer.parseInt(amount);
            makeChanges(selectedPlayer, stats, amountInt);
        } else {
            System.out.println("The player you are looking for is not in the team.");
        }
    }

    //MODIFIES: this
    //EFFECTS: processes the change player statistics command given by the user
    private void makeChanges(Player p, String stats, int amountInt) {
        if (stats.equals("g")) {
            p.addGoals(amountInt);
            System.out.println("You have added " + amountInt + " goals to " + p.getName());
        } else if (stats.equals("a")) {
            p.addAssists(amountInt);
            System.out.println("You have added " + amountInt + " assists to " + p.getName());
        } else if (stats.equals("p")) {
            p.addPasses(amountInt);
            System.out.println("You have added " + amountInt + " passes to " + p.getName());
        } else if (stats.equals("s")) {
            p.addSuccessPasses(amountInt);
            System.out.println("You have added " + amountInt + " success passes to " + p.getName());
        } else if (stats.equals("i")) {
            p.addInterceptions(amountInt);
            System.out.println("You have added " + amountInt + " interceptions to " + p.getName());
        } else if (stats.equals("t")) {
            p.addTacklesWon(amountInt);
            System.out.println("You have added " + amountInt + " tackles won to " + p.getName());
        }
    }

    //MODIFIES: this
    //EFFECTS: enables users to add a new player or to remove an existing player
    private void addOrRemovePlayer() {
        displayAddOrRemoveMenu();
        command = getInput();
        if (command.equals("a")) {
            addPlayer();
        } else if (command.equals("r")) {
            removePlayer();
        } else {
            System.out.println("Invalid Command");
        }
    }

    //EFFECTS: prints adds or removes player menu
    private void displayAddOrRemoveMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\ta -> Add a player");
        System.out.println("\tr -> Remove a player");
    }

    //MODIFIES: this
    //EFFECTS: adds a new player to the team
    private void addPlayer() {
        Player newPlayer = createPlayer();
        attemptToAddPlayer(newPlayer);
    }

    //EFFECTS: creates a new player
    private Player createPlayer() {
        System.out.println("Please Enter the name:");
        String name = getInput();
        System.out.println("Please Enter the number:");
        String number = getInput();
        int numberInt = Integer.parseInt(number);
        System.out.println("Please Enter the age:");
        String age = getInput();
        int ageInt = Integer.parseInt(age);
        System.out.println("Please Enter the position played:");
        String position = getInput();
        Player newPlayer = new Player(name,numberInt,ageInt,position);
        return newPlayer;
    }

    //MODIFIES: this
    //EFFECTS: attempts to add a player to the team, if succeed, prints confirmation sentence
    //         otherwise prints the reason why adding failed
    private void attemptToAddPlayer(Player p) {
        if (myTeam.playerNameList().size() == MAX_SIZE) {
            System.out.println("Failed to add this player, the team was full.");
        } else if (myTeam.containsPlayerName(p.getName())) {
            System.out.println("Failed to add this player, you had another player with same name in team.");
        } else if (myTeam.containsPlayerNumber(p.getNumber())) {
            System.out.println("Failed to add this player, you had another player with same number in team.");
        } else {
            myTeam.addPlayer(p);
            System.out.println("You have successfully added " + p.getName() + " to your team!");
        }
    }

    //MODIFIES: this
    //EFFECTS: attempts to remove a given player from the team, if succeed, prints confirmation sentence
    //         otherwise prints the reason why removing failed
    private void removePlayer() {
        System.out.println("Please enter the name of the player you want to remove: ");
        printPlayers();
        String name = getInput();
        if (myTeam.containsPlayerName(name)) {
            myTeam.removePlayer(name);
            System.out.println("You have successfully removed " + name + " from your team!");
        } else {
            System.out.println("Failed to remove the player, because you do not have this player in your team.");
        }
    }

    //MODIFIES: this
    //EFFECTS: changes the team name to a new name given by the user
    private void changeTeamName() {
        System.out.println("Please enter the new name:");
        String name = getInput();
        myTeam.changeName(name);
        System.out.println("Successfully changed team name to " + myTeam.getName() + " Soccer Club.");
    }

    //EFFECTS: creates a default player in the team
    private Player createSalah() {
        Player salah = new Player("Mohamed Salah",11,29, "ATT");
        salah.addGoals(22);
        salah.addAssists(5);
        salah.addPasses(1263);
        salah.addSuccessPasses(1060);
        salah.addInterceptions(18);
        salah.addTacklesWon(10);
        return salah;
    }

    //EFFECTS: creates a default player in the team
    private Player createMane() {
        Player mane = new Player("Sadio Mane",10,29, "ATT");
        mane.addGoals(11);
        mane.addAssists(7);
        mane.addPasses(1006);
        mane.addSuccessPasses(801);
        mane.addInterceptions(23);
        mane.addTacklesWon(9);
        return mane;
    }

    //EFFECTS: creates a default player in the team
    private Player createJota() {
        Player jota = new Player("Diogo Jota",20,24, "ATT");
        jota.addGoals(9);
        jota.addAssists(0);
        jota.addPasses(426);
        jota.addSuccessPasses(350);
        jota.addInterceptions(18);
        jota.addTacklesWon(4);
        return jota;
    }

    //EFFECTS: creates a default player in the team
    private Player createFabinho() {
        Player fabinho = new Player("Fabinho Tavares",3,27, "MID");
        fabinho.addGoals(0);
        fabinho.addAssists(0);
        fabinho.addPasses(2109);
        fabinho.addSuccessPasses(1982);
        fabinho.addInterceptions(32);
        fabinho.addTacklesWon(42);
        return fabinho;
    }

    //EFFECTS: creates a default player in the team
    private Player createMilner() {
        Player milner = new Player("James Milner",7,35, "MID");
        milner.addGoals(6);
        milner.addAssists(7);
        milner.addPasses(1465);
        milner.addSuccessPasses(1265);
        milner.addInterceptions(17);
        milner.addTacklesWon(11);
        return milner;
    }

    //EFFECTS: creates a default player in the team
    private Player createVanDijk() {
        Player vanDijk = new Player("Virgil Van Dijk",4,30, "DEF");
        vanDijk.addGoals(5);
        vanDijk.addAssists(1);
        vanDijk.addPasses(3253);
        vanDijk.addSuccessPasses(2852);
        vanDijk.addInterceptions(10);
        vanDijk.addTacklesWon(23);
        return vanDijk;
    }

    //EFFECTS: creates a default player in the team
    private Player createMatip() {
        Player matip = new Player("Joe Matip",32,27, "DEF");
        matip.addGoals(1);
        matip.addAssists(2);
        matip.addPasses(2123);
        matip.addSuccessPasses(1892);
        matip.addInterceptions(9);
        matip.addTacklesWon(18);
        return matip;
    }

}
