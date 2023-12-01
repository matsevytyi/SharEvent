package VIEW_CREATOR;


import INTERFACE_ADAPTER.search.SearchController;
import INTERFACE_ADAPTER.search.SearchPresenter;
import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInteractor;
import USE_CASE.search.SearchOutputBoundary;
import VIEW.SearchEventsView;


public class SearchEventsViewFactory {

    public SearchEventsView create(LoadMapViewModel viewModel){
        SearchOutputBoundary presenter = new SearchPresenter(viewModel.getMapKit());
        SearchInputBoundary interactor = new SearchInteractor(presenter);
        SearchController controller = new SearchController(interactor);
        return new SearchEventsView(controller, viewModel);
    }
}
