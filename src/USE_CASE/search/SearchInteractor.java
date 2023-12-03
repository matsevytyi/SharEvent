package USE_CASE.search;

import ENTITY.Event;
import USE_CASE.FindEventsStrategy.SearchStrategy;
import USE_CASE.FindEventsStrategy.FindEventsStrategy;

import java.util.Set;

public class SearchInteractor implements SearchInputBoundary{
    final SearchOutputBoundary searchPresenter;
    final FindEventsStrategy findEventsStrategy;

    public SearchInteractor(SearchOutputBoundary searchOutputBoundary) {
        this.searchPresenter = searchOutputBoundary;
        this.findEventsStrategy = new SearchStrategy();
    }

    public void execute(SearchInputData searchInputData) {

        Set<Event> foundEvents = this.findEventsStrategy.findEvents(searchInputData.getAllEvents(), searchInputData.getSearchInput());

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
