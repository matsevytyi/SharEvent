package DATA_ACCESS;

import ENTITY.Event;

import java.util.Set;

public interface FilterEventsDataAccessInterface {
    Set<Event> FilterEvents(String type);
}
