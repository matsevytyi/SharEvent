package USE_CASE.FindEventsStrategy;

import ENTITY.Event;

import java.util.HashSet;
import java.util.Set;

public class FilterStrategy implements FindEventsStrategy{

    /**
     * This method finds the events in the first parameter that match the filter mentioned in the second parameter
     * and returns the events that matched.
     * @param allEvents all the events we want to filter
     * @param filter the filter we want to use to find the events
     * @return the events in allEvents that matched the filter
     */
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
