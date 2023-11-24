package USE_CASE.search;

import entity.User;
import entity.UserFactory;

import java.util.ArrayList;

public class SearchInteractor implements SearchInputBoundary{
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchOutputBoundary) {
        this.searchPresenter = searchOutputBoundary;
    }

    public void execute(SearchInputData searchInputData) {
        ArrayList<EventInterface> foundEvents = new ArrayList<EventInterface>();
        searchInputData.getEvents().forEach((event) -> {
            if(event.getName.contains(searchInputData.getSearchInput())){
                foundEvents.add(event);
            }
        });

        if (foundEvents.isEmpty()){
            searchPresenter.prepareFailView("No events match your search.");
        } else {
            SearchOutputData searchOutputData = new SearchOutputData(foundEvents, false);
            searchPresenter.prepareSuccessView(searchOutputData);
        }

    }
}
