package entity;

import java.util.Date;
import java.util.List;

public class Event implements EventInterface{

   private final int eventId;
    private final String eventName;
    private final long latitude;
    private final long longtitude;
    private final Date eventDate;
    private final String description;
    private final User creator;

    private List<User> eventAttendants;

    public List<User> getEventAttendants() {
        return eventAttendants;
    }

    public void setEventAttendants(List<User> eventAttendants) {
        this.eventAttendants = eventAttendants;
    }



    public Event(int eventId, String eventName, long latitude, long longtitude, Date eventDate, String description, User creator, List<User> eventAttendants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.eventDate = eventDate;
        this.description = description;
        this.creator = creator;
        this.eventAttendants = eventAttendants;
    }


    public int getEventId() {
        return eventId;
    }


    public String getEventName() {
        return eventName;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongtitude() {
        return longtitude;
    }


    public Date getEventDate() {
        return eventDate;
    }

    public String getDescription() {
        return description;
    }


    public User getCreator() {
        return creator;
    }



}
