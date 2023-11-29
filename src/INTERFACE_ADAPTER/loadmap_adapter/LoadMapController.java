
package INTERFACE_ADAPTER.loadmap_adapter;

import INTERFACE_ADAPTER.filter.FilterController;
import VIEW_CREATOR.LoadMapViewModel;

import USE_CASE.loadmap.LoadMapInputBoundary;
import USE_CASE.loadmap.LoadMapInteractor;

import VIEW.FilterEventsView;
import VIEW.LoadEventsView;
import VIEW.LoadMapView;

import VIEW_CREATOR.FilterEventsViewFactory;
import VIEW_CREATOR.LoadMapViewModel;

public class LoadMapController {

    public void execute(LoadMapViewModel viewModel){
        LoadMapOutputData loadMapOutputData = new LoadMapOutputData();
        LoadMapInputBoundary interactor = new LoadMapInteractor();

        interactor.execute(loadMapOutputData, viewModel);
    }

    public void viewProfile(){
        //TODO: switch to another Usecase (VIEW_PROFILE)
    }

    public void filterEvents(LoadMapView loadMapView, FilterEventsView filterEventsView){
        filterEventsView.showMenu();

        //TODO: switch to another Usecase (FILTER_EVENTS)
    }

    public void viewFriends(){
        //TODO: switch to another Usecase (VIEW_FRIENDS)
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
