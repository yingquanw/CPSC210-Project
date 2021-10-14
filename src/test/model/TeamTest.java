package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.Team.MAX_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


//Test Class for Team

public class TeamTest {
    private Team myTeam;

    @BeforeEach
    public void setUp() {
        myTeam = new Team("Liverpool");
        Player salah = new Player("Mohamed Salah",11,29, "ATT");
        salah.addGoals(12);
        salah.addAssists(8);
        salah.addPasses(120);
        salah.addSuccessPasses(90);
        salah.addInterceptions(5);
        myTeam.addPlayer(salah);
        Player thiago = new Player("Thiago Alcantara",6,30, "MID");
        thiago.addGoals(3);
        thiago.addAssists(12);
        thiago.addPasses(300);
        thiago.addSuccessPasses(260);
        thiago.addInterceptions(7);
        thiago.addTacklesWon(2);
        myTeam.addPlayer(thiago);
        Player matip = new Player("Joe Matip",32,27, "DEF");
        matip.addAssists(1);
        matip.addPasses(210);
        matip.addSuccessPasses(74);
        matip.addInterceptions(19);
        matip.addTacklesWon(8);
        myTeam.addPlayer(matip);
    }


    @Test
    public void changeNameTest() {
        assertEquals("Liverpool", myTeam.getName());
        myTeam.changeName("Chelsea");
        assertEquals("Chelsea", myTeam.getName());
    }


    @Test
    public void allPlayersTest() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(myTeam.getPlayer("Mohamed Salah"));
        playerList.add(myTeam.getPlayer("Thiago Alcantara"));
        playerList.add(myTeam.getPlayer("Joe Matip"));
        assertEquals(playerList, myTeam.allPlayers());
    }

    @Test
    public void playerNameListTest() {
        ArrayList<String> playerList = new ArrayList<>();
        playerList.add("Mohamed Salah");
        playerList.add("Thiago Alcantara");
        playerList.add("Joe Matip");
        assertEquals(playerList, myTeam.playerNameList());
    }

    @Test
    public void addPlayerSuccessTest() {
        assertEquals(3, myTeam.numberOfPlayers());
        Player mane = new Player("Sadio Mane",10,29, "ATT");
        assertTrue(myTeam.addPlayer(mane));
        assertEquals(4, myTeam.numberOfPlayers());
    }

    @Test
    public void addPlayerRepeatNameTest() {
        assertEquals(3, myTeam.numberOfPlayers());
        Player salahTwo = new Player("Mohamed Salah",2,21, "MID");
        assertFalse(myTeam.addPlayer(salahTwo));
        assertEquals(3, myTeam.numberOfPlayers());
    }

    @Test
    public void addPlayerRepeatNumberTest() {
        assertEquals(3, myTeam.numberOfPlayers());
        Player henderson = new Player("John Henderson",6,25, "DEF");
        assertFalse(myTeam.addPlayer(henderson));
        assertEquals(3, myTeam.numberOfPlayers());
    }

    @Test
    public void addPlayerExceedMaxTest() {
        Team testTeam = new Team("testMax");
        assertEquals(0, testTeam.numberOfPlayers());
        for (int i = 0; i < MAX_SIZE; i++) {
            String playerName = Integer.toString(i);
            Player p = new Player(playerName,i,1,"ATT");
            testTeam.addPlayer(p);
        }
        Player henderson = new Player("John Henderson",6,25, "DEF");
        assertFalse(testTeam.addPlayer(henderson));
        assertEquals(20, testTeam.numberOfPlayers());
    }

    @Test
    public void removePlayerSuccessTest() {
        assertEquals(3, myTeam.numberOfPlayers());
        assertTrue(myTeam.removePlayer("Mohamed Salah"));
        assertEquals(2, myTeam.numberOfPlayers());
    }

    @Test
    public void removePlayerFailTest() {
        assertEquals(3, myTeam.numberOfPlayers());
        assertFalse(myTeam.removePlayer("Mason Mount"));
        assertEquals(3, myTeam.numberOfPlayers());
    }

    @Test
    public void statsOfAllPlayersTest() {
        ArrayList<String> statsOfAllPlayers = new ArrayList<>();
        statsOfAllPlayers.add("Mohamed Salah No.11 Age: 29 Position: ATT Goals: 12 Assists: 8 Passes: 120 " +
                "Success passes: 90 Interceptions: 5 Tackles Won: 0");
        statsOfAllPlayers.add("Thiago Alcantara No.6 Age: 30 Position: MID Goals: 3 Assists: 12 Passes: 300 " +
                "Success passes: 260 Interceptions: 7 Tackles Won: 2");
        statsOfAllPlayers.add("Joe Matip No.32 Age: 27 Position: DEF Goals: 0 Assists: 1 Passes: 210 " +
                "Success passes: 74 Interceptions: 19 Tackles Won: 8");
        assertEquals(statsOfAllPlayers, myTeam.statsOfAllPlayers());
    }

    @Test
    public void containsPlayerNameYesTest() {
        assertTrue(myTeam.containsPlayerName("Thiago Alcantara"));
    }

    @Test
    public void containsPlayerNameNoTest() {
        assertFalse(myTeam.containsPlayerName("Jan Oblak"));
    }

    @Test
    public void containsPlayerNumberYesTest(){
        assertTrue(myTeam.containsPlayerNumber(11));
    }

    @Test
    public void containsPlayerNumberNoTest(){
        assertFalse(myTeam.containsPlayerNumber(9));
    }

    @Test
    public void getPlayerExistTest() {
        Player p = myTeam.getPlayer("Joe Matip");
        assertEquals("Joe Matip", p.getName());
    }

    @Test
    public void getPlayerDoesNotExistTest() {
        assertEquals(null, myTeam.getPlayer("Paul Pogba"));
    }

    @Test
    public void getTotalGoalsTest(){
        assertEquals(15,myTeam.getTotalGoals());
    }

    @Test
    public void getTotalAssistsTest(){
        assertEquals(21,myTeam.getTotalAssists());
    }

    @Test
    public void getTotalPassesTest(){
        assertEquals(630,myTeam.getTotalPasses());
    }

    @Test
    public void getTotalSuccessPassesTest(){
        assertEquals(424,myTeam.getTotalSuccessPasses());
    }

    @Test
    public void getTotalInterceptionsTest(){
        assertEquals(31,myTeam.getTotalInterceptions());
    }

    @Test
    public void getTotalTacklesWonTest(){
        assertEquals(10,myTeam.getTotalTacklesWon());
    }



}
