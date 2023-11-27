package ENTITY;


import lombok.Getter;
import org.jxmapviewer.viewer.DefaultWaypoint;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
public class Event extends DefaultWaypoint implements EventInterface {

    private static int lastAssignedId = 0;

    private int eventId;
    private final String eventName;
    private final String type;
    private final LocalDate eventDate;
    private final LocalTime eventTime;
    private final String description;
    private final User creator;
    private final List<User> eventAttendants;
    private final double latitude;
    private final double longitude;

    public Event(String eventName, String type, String description, LocalDate eventDate,
                 LocalTime eventTime, User creator, List<User> eventAttendants,
                 double latitude, double longitude) {
        this.eventId = ++lastAssignedId;
        this.eventName = eventName;
        this.type = type;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
        this.creator = creator;
        this.eventAttendants = eventAttendants;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Event(int eventId, String eventName, String type, String description, LocalDate eventDate, LocalTime eventTime, User creator, List<User> eventAttendants, double latitude, double longitude) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.type = type;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
        this.creator = creator;
        this.eventAttendants = eventAttendants;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    // Other methods and getters as needed
}
