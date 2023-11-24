package use_case.filter;

import use_case.search.SearchInputData;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

import java.time.LocalDate;

import java.util.ArrayList;

public class FilterInteractor implements FilterInputBoundary{
    final FilterOutputBoundary filterPresenter;

    public FilterInteractor(FilterOutputBoundary filterOutputBoundary) {
        this.filterPresenter = filterOutputBoundary;
    }

    public void execute(FilterInputData filterInputData) {
        ArrayList<EventInterface> foundEvents = new ArrayList<EventInterface>();
        ArrayList<EventInterface> typeEvents = new ArrayList<EventInterface>();
        ArrayList<EventInterface> timeEvents = new ArrayList<EventInterface>();

        if (filterInputData.getTypeInput().isEmpty()){
            typeEvents = foundEvents;
        } else {
            filterInputData.getEvents().forEach((event) -> {
                if(event.getType.equals(filterInputData.getTypeInput())){
                    typeEvents.add(event);
                }
            });
        }

        if (filterInputData.getTimeInput().isEmpty()){
            timeEvents = foundEvents;
        } else {
            LocalDate today = LocalDate.now();
            LocalDate latest = GetLatestTime(today, filterInputData.getTimeInput());

            filterInputData.getEvents().forEach((event) -> {
                int comparison = event.getDate.compareTo(latest);
                if (comparison <= 0) {
                    timeEvents.add(event);
                }
            });
        }

        for (EventInterface event : typeEvents){
            if (timeEvents.contains(event)) {
                foundEvents.add(event);
            }
        }

        if (foundEvents.isEmpty()){
            filterPresenter.prepareFailView("No events match your filters.");
        } else {
            FilterOutputData filterOutputData = new FilterOutputData(foundEvents, false);
            filterPresenter.prepareSuccessView(filterOutputData);
        }

    }

    private LocalDate GetLatestTime(LocalDate today, String filterTime){
        if (filterTime.equals("today")) {
            return today;
        }
        if (filterTime.equals("tomorrow")) {
            return today.plusDays(1);
        }
        if (filterTime.equals("this week")) {
            // is this time until next week or + 7 days
            return today.plusWeeks(1);
        } else {
            return today.plusMonths(1);
        }

    }
}
