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

    /**
     * This public method prepares the InputData for the interactor
     * and calls the interactor to execute with the given input. It waits for the
     * all events parameter to be non-empty.
     * @param type  type that the user wants to filter with
     * @param viewModel  the loadMapViewModel that the program is currently running with
     */

    public void execute(String type, LoadMapViewModel viewModel) {

        try {
            while (allEvents.isEmpty()) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {System.out.println("Exception: " + e);}

        FilterInputData filterInputData = new FilterInputData(type, allEvents);

        filterUseCaseInteractor.execute(filterInputData);
    }

    /**
     * This public method sets the allEvents parameter of the controller
     * to the set input. Should be called when the database is done finding all events
     * @param allEvents the set of all events in the database
     */
    public void setEvents(Set<Event> allEvents){
        this.allEvents = allEvents;
    }
}
