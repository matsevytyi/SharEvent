package INTERFACE_ADAPTER.filter;
import ENTITY.Event;
import USE_CASE.filter.FilterInputData;
import USE_CASE.filter.FilterInputBoundary;
import VIEW_CREATOR.LoadMapViewModel;

import java.util.HashSet;
import java.util.Set;

public class FilterController {
    final FilterInputBoundary filterUseCaseInteractor;

    Set<Event> allEvents;
    public FilterController(FilterInputBoundary filterUseCaseInteractor) {
        this.filterUseCaseInteractor = filterUseCaseInteractor;
        this.allEvents = new HashSet<>();
    }

    public void execute(String type, LoadMapViewModel viewModel) {

        try {
            while (allEvents.isEmpty()) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {System.out.println("Exception: " + e);}

        FilterInputData filterInputData = new FilterInputData(type, allEvents);

        filterUseCaseInteractor.execute(filterInputData);
    }

    public void setEvents(Set<Event> allEvents){
        this.allEvents = allEvents;
    }
}
