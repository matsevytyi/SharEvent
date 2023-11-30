package INTERFACE_ADAPTER.search;

import ENTITY.EventInterface;
import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInputData;
import VIEW.LoadMapView;
import VIEW_CREATOR.LoadMapViewModel;

import java.util.ArrayList;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(String search_input){
        SearchInputData searchInputData = new SearchInputData(search_input);

        searchUseCaseInteractor.execute(searchInputData);
    }
}