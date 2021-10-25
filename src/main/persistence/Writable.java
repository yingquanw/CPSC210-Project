package persistence;

import org.json.JSONObject;

// Reference from CPSC210 JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// The interface of Writable extends by Player and Team

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
