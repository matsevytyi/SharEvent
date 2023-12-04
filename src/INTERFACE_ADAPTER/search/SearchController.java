package INTERFACE_ADAPTER.search;

import ENTITY.Event;
import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInputData;
import USE_CASE.search.SearchShowAllData;

import java.util.HashSet;
import java.util.Set;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;

    Set<Event> allEvents;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
        this.allEvents = new HashSet<>();
    }

    /**
     * This public method prepares the InputData for the interactor
     * and calls the interactor to execute with the given input. It waits for the all events parameter
     * to be non-empty.
     * @param search_input  the string input that the user wants to search with
     */
    public void execute(String search_input){
        try {
            while (allEvents.isEmpty()) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {System.out.println("Exception: " + e);}
        SearchInputData searchInputData = new SearchInputData(search_input, allEvents);

        searchUseCaseInteractor.execute(searchInputData);
    }

    /**
     * This method shows all the events that are currently in the database on the map.
     * Should be called when the clear button is pressed, or when we no longer want to search for
     * any specific event.
     */
    public void executeShowAll(){
        try {
            while (allEvents.isEmpty()) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {System.out.println("Exception: " + e);}
        SearchShowAllData searchShowAllData = new SearchShowAllData(allEvents);

        searchUseCaseInteractor.executeShowAll(searchShowAllData);
    }

    /**
     * This public method sets the allEvents parameter of the controller
     * to the set input. Should be called when the database is done finding all events
     * @param allEvents the set of all events in the database
     */
    public void setEvents(Set<Event> allEvents){
        this.allEvents = allEvents;
    }
}