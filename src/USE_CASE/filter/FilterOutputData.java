package USE_CASE.filter;

import ENTITY.Event;
import lombok.Getter;

import java.util.List;
import java.util.Set;

public class FilterOutputData {
    @Getter
    private final Set<Event> foundEvents;


    public FilterOutputData(Set<Event> foundEvents) {
        this.foundEvents = foundEvents;
    }

}
