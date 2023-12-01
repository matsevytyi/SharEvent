package USE_CASE.FindEventsStrategy;

import ENTITY.Event;

import java.util.HashSet;
import java.util.Set;

public class FindBySearch implements FindEventsStrategy{
    @Override
    public Set<Event> findEvents(Set<Event> allEvents, String search_input) {
        Set<Event> foundEvents = new HashSet<>();
        for (Event event : allEvents) {
            if (event.getEventName().toLowerCase().contains(search_input.toLowerCase())){
                foundEvents.add(event);
            }
        }
        return foundEvents;
    }
}
