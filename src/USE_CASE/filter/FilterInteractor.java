package USE_CASE.filter;

import ENTITY.Event;
import USE_CASE.FindEventsStrategy.FilterStrategy;
import USE_CASE.FindEventsStrategy.FindEventsStrategy;

import java.util.Set;

public class FilterInteractor implements FilterInputBoundary {
    final FilterOutputBoundary filterPresenter;
    final FindEventsStrategy findEventsStrategy;
    public FilterInteractor(FilterOutputBoundary filterOutputBoundary) {
        this.filterPresenter = filterOutputBoundary;
        this.findEventsStrategy = new FilterStrategy();
    }

    public void execute(FilterInputData filterInputData) {
        System.out.println("Filter used: " + filterInputData.getTypeInput());

        if (filterInputData.getTypeInput().equals("Show All")) {
            FilterOutputData filterOutputData = new FilterOutputData(filterInputData.getAllEvents());
            this.filterPresenter.prepareSuccessView(filterOutputData);
        } else {

            Set<Event> foundEvents = this.findEventsStrategy.findEvents(filterInputData.getAllEvents(), filterInputData.getTypeInput());

            if (foundEvents.isEmpty()) {
                this.filterPresenter.prepareFailView("No events matched your filters");
            } else {
                FilterOutputData filterOutputData = new FilterOutputData(foundEvents);
                this.filterPresenter.prepareSuccessView(filterOutputData);
            }
        }
    }
}
