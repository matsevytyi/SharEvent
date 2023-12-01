package VIEW_CREATOR;

import INTERFACE_ADAPTER.filter.FilterController;
import INTERFACE_ADAPTER.filter.FilterPresenter;
import USE_CASE.filter.FilterInputBoundary;
import USE_CASE.filter.FilterInteractor;
import USE_CASE.filter.FilterOutputBoundary;
import VIEW.FilterEventsView;

import java.util.Set;

public class FilterEventsViewFactory {

    public FilterEventsView create(LoadMapViewModel viewModel){
        FilterOutputBoundary presenter = new FilterPresenter(viewModel.getMapKit());

        FilterInputBoundary interactor = new FilterInteractor(presenter);
        FilterController controller = new FilterController(interactor);
        return new FilterEventsView(controller, viewModel);
    }

}
