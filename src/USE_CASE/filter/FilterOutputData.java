package USE_CASE.filter;

import java.util.ArrayList;

public class FilterOutputData {
    private final ArrayList<EventInterface> foundEvents;

    private boolean useCaseFailed;

    public FilterOutputData(ArrayList<EventInterface> foundEvents, boolean useCaseFailed) {
        this.foundEvents = foundEvents;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<EventInterface> getFoundEvents() {return foundEvents;}
}
