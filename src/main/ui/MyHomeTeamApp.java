package ui;

import model.Player;
import model.Team;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

// My home team application
public class MyHomeTeamApp {
    private Team myTeam;
    private Scanner input;
    private String command;

    // EFFECTS: runs My Home team application
    public MyHomeTeamApp() {
        runTeam();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTeam() {
        boolean keepGoing = true;
        //String command = null;

        init();

        while (keepGoing) {
            displayMainMenu();
            //command = input.next();
            //command = command.toLowerCase();
            command = getInput();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }

        System.out.println("\nThank you for using My Home Team!");
    }

    private String getInput() {
        String command = null;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        command = input.next();
        return command;
    }

    private void displayMainMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\ta -> Check Team Statistics");
        System.out.println("\tb -> Check Player Statistics");
        System.out.println("\tc -> Add or Remove Player");
        System.out.println("\tq -> quit");
    }

    private void init() {
        myTeam = new Team("Liverpool");
        myTeam.addPlayer(addSalah());
        myTeam.addPlayer(addMane());
        myTeam.addPlayer(addJota());
        myTeam.addPlayer(addFabinho());
        myTeam.addPlayer(addVanDijk());
    }

    private void processMainCommand(String command) {
        if (command.equals("a")) {
            checkTeamStats();
        } else if (command.equals("b")) {
            checkPlayerStats();
        } else if (command.equals("c")) {
            changePlayerStats();
        } else if (command.equals("d")) {
            addOrRemovePlayer();
        } else {
            System.out.println("Selection is invalid.");
        }
    }

    private void checkTeamStats() {
        displayTeamStatsMenu();
        command = getInput();
        processTeamStatsCommand(command);
    }

    private void displayTeamStatsMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\t1 -> List of all players");
        System.out.println("\t2 -> Total number of players");
        System.out.println("\t3 -> Overview of all players");
        System.out.println("\t4 -> Key statistics of attackers");
        System.out.println("\t5 -> Key statistics of midfielders");
        System.out.println("\t6 -> Key statistics of defenders");
        System.out.println("\t7 -> Total goals");
        System.out.println("\t8 -> Total assists");
        System.out.println("\t9 -> Total passes");
        System.out.println("\t10 -> Total success passes");
        System.out.println("\t11 -> Total interceptions");
        System.out.println("\t12 -> Total tackles won");
        System.out.println("\tPress any other key to go back to main menu");
    }

    private void processTeamStatsCommand(String command) {
        if (command.equals("1")) {
            printPlayers();
        } else if (command.equals("2")) {
            System.out.println(myTeam.numberOfPlayers());
        } else if (command.equals("3")) {
            printStatsOfAllPlayers();
        } else if (command.equals("4")) {
            printKeyStatsOfAttackers();
        } else if (command.equals("5")) {
            printKeyStatsOfMidfielders();
        } else if (command.equals("6")) {
            printKeyStatsOfDefenders();
        } else if (needsTeamTotal(command)) {
            printRequestedTeamTotal(command);
        } else {
            System.out.println("");
        }
    }

    private boolean needsTeamTotal(String command) {
        for (int i = 7; i < 13; i++) {
            String j = Integer.toString(i);
            if (command.equals(j)) {
                return true;
            }
        }
        return false;
    }

    private void printRequestedTeamTotal(String command) {
        if (command.equals("7")) {
            System.out.println(myTeam.getTotalGoals());
        } else if (command.equals("8")) {
            System.out.println(myTeam.getTotalAssists());
        } else if (command.equals("9")) {
            System.out.println(myTeam.getTotalPasses());
        } else if (command.equals("10")) {
            System.out.println(myTeam.getTotalSuccessPasses());
        } else if (command.equals("11")) {
            System.out.println(myTeam.getTotalInterceptions());
        } else {
            System.out.println(myTeam.getTotalTacklesWon());
        }
    }


    private void printPlayers() {
        for (String next : myTeam.playerNameList()) {
            System.out.println(next);
        }
    }

    private void printStatsOfAllPlayers() {
        for (String next : myTeam.statsOfAllPlayers()) {
            System.out.println(next);
        }
    }

    private void printKeyStatsOfAttackers() {
        for (String next : myTeam.keyStatsOfAttackers()) {
            System.out.println(next);
        }
    }

    private void printKeyStatsOfMidfielders() {
        for (String next : myTeam.keyStatsOfMidfielders()) {
            System.out.println(next);
        }
    }

    private void printKeyStatsOfDefenders() {
        for (String next : myTeam.keyStatsOfDefenders()) {
            System.out.println(next);
        }
    }

    private void checkPlayerStats() {
        System.out.println("Please enter the name of the player you want to check.");
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

    public void displayPlayerStatsMenu(String name) {
        System.out.println("You are now checking " + name);
        System.out.println("\nPlease select from:");
        System.out.println("\tg -> Goals");
        System.out.println("\ta -> Assists");
        System.out.println("\tp -> Passes");
        System.out.println("\ts -> Success passes");
        System.out.println("\ti -> Interceptions");
        System.out.println("\tt -> Tackles won");
        System.out.println("\tPress any other key to go back to main menu");
    }

    public void showPlayerStats(Player p,String command) {
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
        }  else {
            System.out.println("");
        }
    }

    private void changePlayerStats() {

    }

    private void addOrRemovePlayer() {
    }


    private Player addSalah() {
        Player salah = new Player("Mohamed Salah",11,29, "ATT");
        salah.addGoals(22);
        salah.addAssists(5);
        salah.addPasses(1263);
        salah.addSuccessPasses(1060);
        salah.addInterceptions(18);
        salah.addTacklesWon(10);
        return salah;
    }

    private Player addMane() {
        Player mane = new Player("Sadio Mane",10,29, "ATT");
        mane.addGoals(11);
        mane.addAssists(7);
        mane.addPasses(1006);
        mane.addSuccessPasses(801);
        mane.addInterceptions(23);
        mane.addTacklesWon(9);
        return mane;
    }

    private Player addJota() {
        Player jota = new Player("Diogo Jota",20,24, "ATT");
        jota.addGoals(9);
        jota.addAssists(0);
        jota.addPasses(426);
        jota.addSuccessPasses(350);
        jota.addInterceptions(18);
        jota.addTacklesWon(4);
        return jota;
    }

    private Player addFabinho() {
        Player fabinho = new Player("Fabinho Tavares",3,27, "MID");
        fabinho.addGoals(0);
        fabinho.addAssists(0);
        fabinho.addPasses(2109);
        fabinho.addSuccessPasses(1982);
        fabinho.addInterceptions(32);
        fabinho.addTacklesWon(42);
        return fabinho;
    }

    private Player addVanDijk() {
        Player vanDijk = new Player("Virgil Van Dijk",4,30, "DEF");
        vanDijk.addGoals(5);
        vanDijk.addAssists(1);
        vanDijk.addPasses(3253);
        vanDijk.addSuccessPasses(2852);
        vanDijk.addInterceptions(10);
        vanDijk.addTacklesWon(23);
        return vanDijk;
    }

}
