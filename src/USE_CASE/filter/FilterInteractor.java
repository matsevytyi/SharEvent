package USE_CASE.filter;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.FilterEventsDAO;
import ENTITY.Event;
import USE_CASE.FindEventsStrategy.FindByFilter;
import USE_CASE.FindEventsStrategy.FindEventsStrategy;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FilterInteractor implements FilterInputBoundary {
    final FilterOutputBoundary filterPresenter;

    public FilterInteractor(FilterOutputBoundary filterOutputBoundary) {
        this.filterPresenter = filterOutputBoundary;
    }

    public void execute(FilterInputData filterInputData) {
        System.out.println("Filter used: " + filterInputData.getTypeInput());

        if (filterInputData.getTypeInput().equals("Show All")) {
            FilterOutputData filterOutputData = new FilterOutputData(filterInputData.getAllEvents());
            this.filterPresenter.prepareSuccessView(filterOutputData);
        } else {

            FindEventsStrategy findEventsStrategy = new FindByFilter();
            Set<Event> foundEvents = findEventsStrategy.findEvents(filterInputData.getAllEvents(), filterInputData.getTypeInput());

            if (foundEvents.isEmpty()) {
                this.filterPresenter.prepareFailView("No events matched your filters");
            } else {
                FilterOutputData filterOutputData = new FilterOutputData(foundEvents);
                this.filterPresenter.prepareSuccessView(filterOutputData);
            }
        }
    }
}
