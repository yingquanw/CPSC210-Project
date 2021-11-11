package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Reference from CPSC210 JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Test class for Json writer

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Team t = new Team("My team");
            JsonWriter writer = new JsonWriter("./data/thisisa\0illegalfileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            Team t = new Team("My team");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeam.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeam.json");
            t = reader.read();
            assertEquals("My team", t.getName());
            assertEquals(0, t.numberOfPlayers());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTeam() {
        try {
            Team t = new Team("My team");
            t.addPlayer(new Player("Sadio Mane",10,29, "ATT",
                    11,7,1006,801,23,9));
            t.addPlayer(new Player("Joe Matip",32,27, "DEF",
                    1,2,2123,1892,9,18));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeam.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTeam.json");
            t = reader.read();
            assertEquals("My team", t.getName());
            List<Player> players = t.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Sadio Mane",10,29, "ATT",
                    11,7,1006,801,23,9, players.get(0));
            checkPlayer("Joe Matip",32,27, "DEF",
                    1,2,2123,1892,9,18, players.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
