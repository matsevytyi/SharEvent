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

    /**
     * This method executes with the given InputData. Appropriately calls the presenter to either
     * show all the events that match the search input, or to prepare fail view for when
     * there are no events that match the search input.
     * @param searchInputData the searchInputData prepared by the controller
     */
    public void execute(SearchInputData searchInputData) {

        Set<Event> foundEvents = this.findEventsStrategy.findEvents(searchInputData.getAllEvents(), searchInputData.getSearchInput());

        if (foundEvents.isEmpty()) {
            this.searchPresenter.prepareFailView("No events matched your search");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(foundEvents);
            this.searchPresenter.prepareSuccessView(searchOutputData);
        }

    }

    /**
     * This method calls the presenter to show all the events in the database.
     * Should be called when the clear button is pressed, or when we no longer want to search for
     * specific events.
     * @param searchShowAllData the inputData prepared by the controller, should contain all the events to show
     */
    public void executeShowAll(SearchShowAllData searchShowAllData){
        SearchOutputData searchOutputData = new SearchOutputData(searchShowAllData.getAllEvents());
        this.searchPresenter.prepareSuccessView(searchOutputData);
    }
}
