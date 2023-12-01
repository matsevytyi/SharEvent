package USE_CASE.FindEventsStrategy;

import ENTITY.Event;

import java.util.Set;

public interface FindEventsStrategy {
    public Set<Event> findEvents(Set<Event> allEvents, String parameter);
}
