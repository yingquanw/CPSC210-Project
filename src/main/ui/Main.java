package ui;

import java.io.FileNotFoundException;

// The Main Class with a main method

public class Main {

    public static void main(String[] args) {
        try {
            new MyHomeTeamApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

}
