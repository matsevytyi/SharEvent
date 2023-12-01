
package INTERFACE_ADAPTER.loadmap_adapter;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.FilterEventsDAO;
import ENTITY.Event;
import INTERFACE_ADAPTER.filter.FilterController;
import VIEW.*;
import VIEW_CREATOR.LoadMapViewModel;

import USE_CASE.loadmap.LoadMapInputBoundary;
import USE_CASE.loadmap.LoadMapInteractor;

import VIEW_CREATOR.FilterEventsViewFactory;
import VIEW_CREATOR.LoadMapViewModel;
import VIEW_CREATOR.SearchEventsViewFactory;

import java.util.Set;

public class LoadMapController {

    public void execute(LoadMapViewModel viewModel){
        LoadMapOutputData loadMapOutputData = new LoadMapOutputData();
        LoadMapInputBoundary interactor = new LoadMapInteractor();

        interactor.execute(loadMapOutputData, viewModel);
    }

    public void viewProfile(){
        //TODO: switch to another Usecase (VIEW_PROFILE)
    }

    public void filterEvents(FilterEventsView filterEventsView){
        filterEventsView.showMenu();
        FilterEventsDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.FilterEvents("");
        filterEventsView.getController().setEvents(allEvents);

        //TODO: switch to another Usecase (FILTER_EVENTS)
    }

    public void searchEvents(LoadMapViewModel viewModel){
        SearchEventsViewFactory searchEventsViewFactory = new SearchEventsViewFactory();
        SearchEventsView searchEventsView = searchEventsViewFactory.create(viewModel);
        //TODO: switch to another Usecase (SEARCH_EVENTS)
    }

//    public void viewEvents(){
//        //TODO: switch to another Usecase (VIEW_EVENTS)
//    }

//    public void addEvent(){
//        //TODO: switch to another Usecase (ADD_EVENT)
//    }

    public void updateEvents(LoadMapView loadMapView){
        //TODO: switch to another Usecase (UPDATE_EVENTS)
        LoadEventsView view = new LoadEventsView(loadMapView); //TODO: consider moving just this line to another class so that view will not be created each time
        view.reloadEvents(loadMapView);
    }
}
