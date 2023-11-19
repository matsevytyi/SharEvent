package data_access;

import entity.Event;

public interface EventDataAccessInterface {

    void addEvent(Event event);

    Event getEventById(int id);

}
