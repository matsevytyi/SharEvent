package INTERFACE_ADAPTER.search;

import ENTITY.EventInterface;
import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInputData;

import java.util.ArrayList;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(String search_input, ArrayList<EventInterface> events) {
        SearchInputData searchInputData = new SearchInputData(
                search_input, events);

        searchUseCaseInteractor.execute(searchInputData);
    }
}