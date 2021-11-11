package ui;

import java.io.FileNotFoundException;

public class MainGUI {
    public static void main(String[] args) {
        try {
            new MyHomeTeamGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
