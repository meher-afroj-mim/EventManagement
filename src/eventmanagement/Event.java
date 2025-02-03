package eventmanagement;

import java.time.LocalDate;

public class Event {
    private int id;
    private String eventName;
    private LocalDate eventDate;
    private String location;

    // Constructor
    public Event(int id, String eventName, LocalDate eventDate, String location) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}