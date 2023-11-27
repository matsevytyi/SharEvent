package ENTITY;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventFactory implements EventFactoryInterface{

    public Event create( String eventName, String type, String description,  LocalDate eventDate, LocalTime eventTime,  User creator, List<User> attendants, double latitude, double longitude) {
        return new Event(0, eventName, type,  description,   eventDate, eventTime,   creator, attendants, latitude, longitude);
    }


}
