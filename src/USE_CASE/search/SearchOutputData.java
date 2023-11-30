package USE_CASE.search;

import ENTITY.Event;
import java.util.Set;

public class SearchOutputData{
    private final Set<Event> foundEvents;


    public SearchOutputData(Set<Event> foundEvents) {
        this.foundEvents = foundEvents;
    }

    public Set<Event> getFoundEvents() {return foundEvents;}

}
