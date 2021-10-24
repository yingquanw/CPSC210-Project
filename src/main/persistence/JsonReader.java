package persistence;

import model.Player;
import model.Team;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads team from JSON data stored in file locates at data folder

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads team from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team t = new Team(name);
        addPlayers(t, jsonObject);
        return t;
    }

    // MODIFIES: team
    // EFFECTS: parses players from JSON object and adds them to team
    private void addPlayers(Team t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(t, nextPlayer);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses player from JSON object and adds it to team
    private void addPlayer(Team t, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int number = jsonObject.getInt("number");
        int age = jsonObject.getInt("age");
        String position = jsonObject.getString("position");
        int goals = jsonObject.getInt("goals");
        int assists = jsonObject.getInt("assists");
        int passes = jsonObject.getInt("passes");
        int successPasses = jsonObject.getInt("successPasses");
        int interceptions = jsonObject.getInt("interceptions");
        int tacklesWon = jsonObject.getInt("tacklesWon");
        Player player = new Player(name, number, age, position, goals, assists,
                passes, successPasses, interceptions, tacklesWon);
        t.addPlayer(player);
    }
}