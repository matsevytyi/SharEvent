package INTERFACE_ADAPTER.filter;
import USE_CASE.filter.FilterInputData;
import USE_CASE.filter.FilterInputBoundary;
import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.viewer.GeoPosition;

public class FilterController {
    final FilterInputBoundary filterUseCaseInteractor;
    public FilterController(FilterInputBoundary filterUseCaseInteractor) {
        this.filterUseCaseInteractor = filterUseCaseInteractor;
    }

    public void execute(String type, LoadMapViewModel viewModel) {
        FilterInputData filterInputData = new FilterInputData(type, viewModel.getMapKit().getCenterPosition());

        filterUseCaseInteractor.execute(filterInputData);
    }
}
