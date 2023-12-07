
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

/**
 * This class is responsible for passing user interactions
 * from LoadMapView to */
@Getter
public class LoadMapController {

    LoadMapOutputData loadMapOutputData;
    @Setter
    LoadMapInputBoundary interactor;
    FilterEventsDataAccessInterface filterEventsDAO;
    Set<Event> allEvents;

    SearchEventsView searchEventsView;
    SearchEventsDataAccessInterface searchEventsDAO;
    SearchEventsViewFactory searchEventsViewFactory;
    LoadEventsView view;

    /**
     * After user logs in, controller works on moving
     * workflow to LoadMapInteractor
     *
     * mapKit, used as basement for interactive map
     * is passed via separate class
     * to adhere to Single Responsibility SOLID principle
     *
     * @param  viewModel  the LoadMapViewModel where changes wil be made
     */
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
