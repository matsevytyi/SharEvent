package use_case2.search;

import java.util.ArrayList;

public class SearchInputData {

    final private String searchInput;
    final private ArrayList<EventInterface> events;


    public SearchInputData(String searchInput, ArrayList<EventInterface> events) {
        this.searchInput = searchInput;
        this.events = events;
    }

    String getSearchInput() {
        return this.searchInput;
    }

    ArrayList<EventInterface> getEvents() {
        return this.events;
    }
}
