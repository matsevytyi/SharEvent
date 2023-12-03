package USE_CASE.FindEventsStrategy;

import ENTITY.Event;

import java.util.HashSet;
import java.util.Set;

public class SearchStrategy implements FindEventsStrategy{

    /**
     * This method finds the events in the first parameter that contain the string in the second parameter
     * in its name and returns the events that matched.
     * @param allEvents all the events we want to search in
     * @param search_input the name of the event we want to search for
     * @return the events in allEvents that matched the search
     */
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
