package VIEW_CREATOR;

import INTERFACE_ADAPTER.filter.FilterController;
import INTERFACE_ADAPTER.filter.FilterPresenter;
import USE_CASE.filter.FilterInputBoundary;
import USE_CASE.filter.FilterInteractor;
import USE_CASE.filter.FilterOutputBoundary;
import VIEW.FilterEventsView;

import java.util.Set;

public class FilterEventsViewFactory {

    /**
     * This method takes a LoadMapViewModel object and appropriately creates a FilterEventsView object,
     * with all the necessary classes like the controller, interactor and presenter connected to the view.
     * @param viewModel the LoadMapViewModel object that is currently being used
     * @return a FilterEventsView object that is connected to the program
     */
    public FilterEventsView create(LoadMapViewModel viewModel){
        FilterEventsViewModel filterEventsViewModel = new FilterEventsViewModel();
        FilterOutputBoundary presenter = new FilterPresenter(viewModel.getMapKit());

        FilterInputBoundary interactor = new FilterInteractor(presenter);
        FilterController controller = new FilterController(interactor);
        return new FilterEventsView(controller, viewModel, filterEventsViewModel);
    }

}
