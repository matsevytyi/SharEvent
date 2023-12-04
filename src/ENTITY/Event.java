package ENTITY;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Event extends DefaultWaypoint implements EventInterface {

    private int eventId;
    private final List<User> eventAttendants;

    @Getter
    private final String eventName;
    private final String type;
    private final LocalDate eventDate;
    private final LocalTime eventTime;
    private final String description;
    private final User creator;

    private final double latitude;
    private final double longitude;
    GeoPosition geoPosition;
    /**
     * Constructor for Event with specified properties.
     * @param eventName      The name of the event.
     * @param type           The type/category of the event.
     * @param description    The description of the event.
     * @param eventDate      The date of the event.
     * @param eventTime      The time of the event.
     * @param creator        The user who created the event.
     * @param eventAttendants The list of users attending the event.
     * @param latitude       The latitude of the event location.
     * @param longitude      The longitude of the event location.
     */
    public Event(String eventName, String type, String description, LocalDate eventDate,
                 LocalTime eventTime, User creator, List<User> eventAttendants,
                 double latitude, double longitude) {
        super(latitude, longitude);
        this.eventName = eventName;
        this.type = type;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
        this.creator = creator;
        this.eventAttendants = eventAttendants;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geoPosition = new GeoPosition(latitude, longitude);
    }
    /**
     * Constructor for Events with specified properties, including the event ID.
     *
     * @param eventId        The unique identifier for the event.
     * @param eventName      The name of the event.
     * @param type           The type/category of the event.
     * @param description    The description of the event.
     * @param eventDate      The date of the event.
     * @param eventTime      The time of the event.
     * @param creator        The user who created the event.
     * @param eventAttendants The list of users attending the event.
     * @param latitude       The latitude of the event location.
     * @param longitude      The longitude of the event location.
     */
    public Event(int eventId, String eventName, String type, String description, LocalDate eventDate, LocalTime eventTime, User creator, List<User> eventAttendants, double latitude, double longitude) {
        super(latitude, longitude);
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
        this.geoPosition = new GeoPosition(latitude, longitude);
    }

}
