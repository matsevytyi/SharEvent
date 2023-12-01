package USE_CASE.search;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.SearchEventsDAO;
import ENTITY.Event;

import java.util.HashSet;
import java.util.Set;

public class SearchInteractor implements SearchInputBoundary{
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchOutputBoundary) {
        this.searchPresenter = searchOutputBoundary;
    }

    public void execute(SearchInputData searchInputData) {

        Set<Event> foundEvents = new HashSet<>();
        for (Event event : searchInputData.getAllEvents()) {
            if (event.getEventName().toLowerCase().contains(searchInputData.getSearchInput().toLowerCase())) {
                foundEvents.add(event);
            }
        }
        if (foundEvents.isEmpty()) {
            this.searchPresenter.prepareFailView("No events matched your search");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(foundEvents);
            this.searchPresenter.prepareSuccessView(searchOutputData);
        }

    }
}
