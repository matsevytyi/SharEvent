package INTERFACE_ADAPTER.search;

import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInputData;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(String search_input, Array<EventInterface> events) {
        SearchInputData searchInputData = new SearchInputData(
                search_input, events);

        searchUseCaseInteractor.execute(searchInputData);
    }
}