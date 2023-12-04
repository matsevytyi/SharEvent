
package INTERFACE_ADAPTER.loadmap_adapter;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.FilterEventsDataAccessInterface;
import DATA_ACCESS.SearchEventsDataAccessInterface;
import ENTITY.Event;

import USE_CASE.loadmap.LoadMapOutputData;
import VIEW.*;
import VIEW_CREATOR.LoadMapViewModel;

import USE_CASE.loadmap.LoadMapInputBoundary;
import USE_CASE.loadmap.LoadMapInteractor;

import VIEW_CREATOR.SearchEventsViewFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
public class LoadMapController {

    LoadMapOutputData loadMapOutputData; //test in ViewTest
    @Setter
    LoadMapInputBoundary interactor; //test in ViewTest
    FilterEventsDataAccessInterface filterEventsDAO;
    Set<Event> allEvents;

    SearchEventsView searchEventsView;
    SearchEventsDataAccessInterface searchEventsDAO;
    SearchEventsViewFactory searchEventsViewFactory;
    LoadEventsView view; //test in ViewTest

    public void execute(LoadMapViewModel viewModel){
        loadMapOutputData = new LoadMapOutputData();
        interactor = new LoadMapInteractor();

        interactor.execute(loadMapOutputData, viewModel);
    }

    public void filterEvents(FilterEventsView filterEventsView){
        filterEventsView.showMenu();
        filterEventsDAO = new DatabaseDAO();
        allEvents = filterEventsDAO.FilterEvents("");
        filterEventsView.getController().setEvents(allEvents);
    }

    public void searchEvents(LoadMapViewModel viewModel){
        searchEventsViewFactory = new SearchEventsViewFactory();
        searchEventsView = searchEventsViewFactory.create(viewModel);
        searchEventsDAO = new DatabaseDAO();
        allEvents = searchEventsDAO.SearchEvent("");
        searchEventsView.getController().setEvents(allEvents);

    }


    public void updateEvents(LoadMapView loadMapView){
        view = new LoadEventsView(loadMapView);
        view.reloadEvents(loadMapView);
    }
}
