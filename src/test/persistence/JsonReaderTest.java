package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class for Json reader

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Team t = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeam.json");
        try {
            Team t = reader.read();
            assertEquals("My team", t.getName());
            assertEquals(0, t.numberOfPlayers());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTeam() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeam.json");
        try {
            Team t = reader.read();
            assertEquals("Liverpool", t.getName());
            List<Player> players = t.allPlayers();
            assertEquals(2, players.size());
            checkPlayer("Mohamed Salah", 11, 29, "ATT", 22, 5,1263,
                    1060,18,10, players.get(0));
            checkPlayer("James Milner", 7, 35, "MID", 6, 7,1465,
                    1265,17,11, players.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
