package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Test Class for Player

class PlayerTest {
    private Player myPlayer;

    @BeforeEach
    public void setUp(){
        myPlayer = new Player("Lionel Messi",30,34, "ATT",
                0,0,0,0,0,0);
    }

    @Test
    public void getNameTest() {
        assertEquals("Lionel Messi", myPlayer.getName());
    }

    @Test
    public void getNumberTest() {
        assertEquals(30, myPlayer.getNumber());
    }

    @Test
    public void getAgeTest() {
        assertEquals(34, myPlayer.getAge());
    }

    @Test
    public void getPositionTest() {
        assertEquals("ATT", myPlayer.getPosition());
    }

    @Test
    public void addGoalsTest() {
        assertEquals(0, myPlayer.getGoals());
        myPlayer.addGoals(8);
        assertEquals(8, myPlayer.getGoals());
        myPlayer.addGoals(3);
        assertEquals(11, myPlayer.getGoals());
    }

    @Test
    public void addAssistsTest() {
        assertEquals(0, myPlayer.getAssists());
        myPlayer.addAssists(3);
        assertEquals(3, myPlayer.getAssists());
        myPlayer.addAssists(2);
        assertEquals(5, myPlayer.getAssists());
    }

    @Test
    public void addPassesTest() {
        assertEquals(0, myPlayer.getPasses());
        myPlayer.addPasses(3);
        assertEquals(3, myPlayer.getPasses());
        myPlayer.addPasses(4);
        assertEquals(7, myPlayer.getPasses());
    }

    @Test
    public void addSuccessPassesTest() {
        assertEquals(0, myPlayer.getSuccessPasses());
        myPlayer.addSuccessPasses(1);
        assertEquals(1, myPlayer.getSuccessPasses());
        myPlayer.addSuccessPasses(3);
        assertEquals(4, myPlayer.getSuccessPasses());
    }

    @Test
    public void addInterceptionsTest() {
        assertEquals(0, myPlayer.getInterceptions());
        myPlayer.addInterceptions(2);
        assertEquals(2, myPlayer.getInterceptions());
        myPlayer.addInterceptions(4);
        assertEquals(6, myPlayer.getInterceptions());
    }

    @Test
    public void addTacklesWonTest() {
        assertEquals(0, myPlayer.getTacklesWon());
        myPlayer.addTacklesWon(7);
        assertEquals(7, myPlayer.getTacklesWon());
        myPlayer.addTacklesWon(1);
        assertEquals(8, myPlayer.getTacklesWon());
    }

    @Test
    public void toStringTest() {
        assertEquals("Lionel Messi", myPlayer.toString());
    }

    @Test
    public  void statsSummaryTest() {
        assertEquals( "Lionel Messi No.30 Age: 34 Position: ATT Goals: 0 Assists: 0 Passes: 0 " +
                "Success passes: 0 Interceptions: 0 Tackles Won: 0", myPlayer.statsSummary());
    }


}







