package VIEW_CREATOR;

import INTERFACE_ADAPTER.search.SearchController;
import INTERFACE_ADAPTER.search.SearchPresenter;
import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInteractor;
import USE_CASE.search.SearchOutputBoundary;
import VIEW.SearchEventsView;

public class SearchEventsViewFactory {
    /**
     * This method takes a LoadMapViewModel object and appropriately creates a SearchEventsView object,
     * with all the necessary classes like the controller, interactor and presenter connected to the view.
     * @param viewModel the LoadMapViewModel object that is currently being used
     * @return a SearchEventsView object that is connected to the program
     */
    public SearchEventsView create(LoadMapViewModel viewModel){
        SearchEventsViewModel searchEventsViewModel = new SearchEventsViewModel();
        SearchOutputBoundary presenter = new SearchPresenter(viewModel.getMapKit());
        SearchInputBoundary interactor = new SearchInteractor(presenter);
        SearchController controller = new SearchController(interactor);
        return new SearchEventsView(controller, viewModel, searchEventsViewModel);
    }
}
