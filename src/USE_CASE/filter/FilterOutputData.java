package USE_CASE.filter;

import java.util.ArrayList;

public class FilterOutputData {
    private final List<EventInterface> foundEvents;

    private boolean useCaseFailed;

    public FilterOutputData(List<Event> foundEvents) {
        this.foundEvents = foundEvents;
    }

    public ArrayList<EventInterface> getFoundEvents() {return foundEvents;}
}
