package ENTITY;

import java.util.List;

public class InteractiveMap implements InteractiveMapInterface{
    private final List<Event> events;

    public InteractiveMap(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}
