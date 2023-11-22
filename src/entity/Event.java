package entity;

import org.jxmapviewer.viewer.DefaultWaypoint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Event  extends DefaultWaypoint implements EventInterface{

   private final int eventId;

    @Override
    public int getEventId() {
        return eventId;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public long getLatitude() {
        return latitude;
    }

    @Override
    public long getLongtitude() {
        return longtitude;
    }

    @Override
    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public User getCreator() {
        return creator;
    }

    public List<User> getEventAttendants() {
        return eventAttendants;
    }

    private final String eventName;
    private final long latitude;
    private final long longtitude;
    private final LocalDate eventDate ;


    private final LocalTime eventTime;
    private final String description;

    private final User creator;

    private List<User> eventAttendants;





    public Event(int eventId, String eventName, long latitude, long longtitude, LocalDate eventDate, LocalTime eventTime, String description, User creator, List<User> eventAttendants) {
        super();
        this.eventId = eventId;
        this.eventName = eventName;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
        this.creator = creator;
        this.eventAttendants = eventAttendants;
    }






}
