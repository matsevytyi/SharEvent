package USE_CASE.search;

import ENTITY.Event;

import java.util.Set;

public class SearchShowAllData {
    final private Set<Event> allEvents;

    public SearchShowAllData(Set<Event> allEvents) {
        this.allEvents = allEvents;
    }
    Set<Event> getAllEvents(){return this.allEvents;}
}
