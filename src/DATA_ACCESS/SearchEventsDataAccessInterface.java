package DATA_ACCESS;

import ENTITY.Event;

import java.util.Set;

public interface SearchEventsDataAccessInterface {
    Set<Event> SearchEvent(String searchInput);
}
