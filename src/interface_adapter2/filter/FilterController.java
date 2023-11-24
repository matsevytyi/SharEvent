package interface_adapter2.filter;
import use_case2.filter.FilterInputBoundary;
import use_case2.filter.FilterInputData;

public class FilterController {
    final FilterInputBoundary filterUseCaseInteractor;
    public FilterController(FilterInputBoundary filterUseCaseInteractor) {
        this.filterUseCaseInteractor = filterUseCaseInteractor;
    }

    public void execute(String type, String time, Array<EventInterface> events) {
        FitlerInputData fitlerInputData = new FilterInputData(
                type, time, events);

        filterUseCaseInteractor.execute(filterInputData);
    }
}
