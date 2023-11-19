package entity;

import java.util.Date;
import java.util.List;

public class EventFactory implements EventFactoryInterface{

    public Event create(int eventId, String eventName, long latitude, long longtitude, Date eventDate, String description, User creator, List<User> attendants) {
        return new Event(eventId, eventName,  latitude, longtitude,  eventDate, description, creator, null);
    }


}
