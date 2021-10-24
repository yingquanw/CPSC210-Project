package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class for checking a player

public class JsonTest {
    protected void checkPlayer(String name, int number, int age, String position, int goals, int assists,
                               int passes, int successPasses, int interceptions, int tacklesWon, Player player) {
        assertEquals(name, player.getName());
        assertEquals(number, player.getNumber());
        assertEquals(age, player.getAge());
        assertEquals(position, player.getPosition());
        assertEquals(goals, player.getGoals());
        assertEquals(assists, player.getAssists());
        assertEquals(passes, player.getPasses());
        assertEquals(successPasses, player.getSuccessPasses());
        assertEquals(interceptions, player.getInterceptions());
        assertEquals(tacklesWon, player.getTacklesWon());
    }
}
