package INTERFACE_ADAPTER.filter;
import USE_CASE.filter.FilterInputData;
import USE_CASE.filter.FilterInputBoundary;
import org.jxmapviewer.viewer.GeoPosition;

public class FilterController {
    final FilterInputBoundary filterUseCaseInteractor;
    public FilterController(FilterInputBoundary filterUseCaseInteractor) {
        this.filterUseCaseInteractor = filterUseCaseInteractor;
    }

    public void execute(String type, GeoPosition geoPosition) {
        FilterInputData filterInputData = new FilterInputData(type, geoPosition);

        filterUseCaseInteractor.execute(filterInputData);
    }
}
