package model;

import java.util.Calendar;
import java.util.Date;

// Reference from CPSC210 AlarmSystem
// Represents a team event

/**
 * Represents a team event.
 */
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     * @param description  a description of the event
     */
    //EFFECTS: creates a new event with time and description
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     * Gets the date of this event (includes time).
     * @return  the date of the event
     */
    //EFFECTS: returns the date of the event
    public Date getDate() {
        return dateLogged;
    }

    /**
     * Gets the description of this event.
     * @return  the description of the event
     */
    //EFFECTS: returns the description of the event
    public String getDescription() {
        return description;
    }

    //EFFECTS: overrides equals method
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    //EFFECTS: overrides hashCode method
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    //EFFECTS: overrides toString method
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
