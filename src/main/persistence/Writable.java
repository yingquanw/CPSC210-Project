package persistence;

import org.json.JSONObject;

// The interface of Writable extends by Player and Team

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
