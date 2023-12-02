package INTERFACE_ADAPTER.search;

import ENTITY.Event;
import ENTITY.EventInterface;
import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInputData;
import USE_CASE.search.SearchShowAllData;
import VIEW.LoadMapView;
import VIEW_CREATOR.LoadMapViewModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SearchController {
    @Getter
    final SearchInputBoundary searchUseCaseInteractor;

    Set<Event> allEvents;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
        this.allEvents = new HashSet<>();
    }

    public void execute(String search_input){
        try {
            while (allEvents.isEmpty()) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        }
        SearchInputData searchInputData = new SearchInputData(search_input, allEvents);

        searchUseCaseInteractor.execute(searchInputData);
    }

    public void executeShowAll(){
        try {
            while (allEvents.isEmpty()) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        }
        SearchShowAllData searchShowAllData = new SearchShowAllData(allEvents);

        searchUseCaseInteractor.executeShowAll(searchShowAllData);
    }


    public void setEvents(Set<Event> allEvents){
        this.allEvents = allEvents;
    }
}