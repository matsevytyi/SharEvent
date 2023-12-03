package USE_CASE.FindEventsStrategy;

import ENTITY.Event;

import java.util.HashSet;
import java.util.Set;

public class FilterStrategy implements FindEventsStrategy{

    @Override
    public Set<Event> findEvents(Set<Event> allEvents, String filter) {
        Set<Event> foundEvents = new HashSet<>();
        for (Event event : allEvents) {
            if (event.getType().equals(filter)) {
                foundEvents.add(event);
            }
        }
        return foundEvents;
    }
}
