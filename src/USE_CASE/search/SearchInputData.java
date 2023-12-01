package USE_CASE.search;

import ENTITY.Event;

import java.util.Set;

public class SearchInputData {

    final private String searchInput;
    final private Set<Event> allEvents;


    public SearchInputData(String searchInput, Set<Event> allEvents) {
        this.searchInput = searchInput;
        this.allEvents = allEvents;
    }

    String getSearchInput() {
        return this.searchInput;
    }

    Set<Event> getAllEvents(){return this.allEvents;}

}
