package USE_CASE.search;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.SearchEventsDAO;
import ENTITY.Event;
import USE_CASE.FindEventsStrategy.FindBySearch;
import USE_CASE.FindEventsStrategy.FindEventsStrategy;

import java.util.HashSet;
import java.util.Set;

public class SearchInteractor implements SearchInputBoundary{
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchOutputBoundary) {
        this.searchPresenter = searchOutputBoundary;
    }

    public void execute(SearchInputData searchInputData) {

        FindEventsStrategy findEventsStrategy =  new FindBySearch();
        Set<Event> foundEvents = findEventsStrategy.findEvents(searchInputData.getAllEvents(), searchInputData.getSearchInput());

        if (foundEvents.isEmpty()) {
            this.searchPresenter.prepareFailView("No events matched your search");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(foundEvents);
            this.searchPresenter.prepareSuccessView(searchOutputData);
        }

    }

    public void executeShowAll(SearchShowAllData searchShowAllData){
        SearchOutputData searchOutputData = new SearchOutputData(searchShowAllData.getAllEvents());
        this.searchPresenter.prepareSuccessView(searchOutputData);
    }
}
