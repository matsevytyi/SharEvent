package USE_CASE.filter;

import ENTITY.Event;

import java.util.Set;

public class FilterInputData {

    final private String typeInput;

    final private Set<Event> allEvents;

    public FilterInputData(String typeInput, Set<Event> allEvents) {

        this.typeInput = typeInput;
        this.allEvents = allEvents;
    }

    String getTypeInput() {
        return this.typeInput;
    }

    Set<Event> getAllEvents(){return this.allEvents;}

}
