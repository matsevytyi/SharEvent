package entity;

import java.util.Date;

public class EventFactory implements EventFactoryInterface{

    @Override
    public Event create(int eventId, String eventName, long latitude, long longtitude, Date eventDate, String description, User creator) {
        return new Event(eventId, eventName,  latitude, longtitude,  eventDate, description, creator);
    }
}
