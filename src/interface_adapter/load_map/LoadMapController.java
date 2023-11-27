package INTERFACE_ADAPTER.load_map;

import USE_CASE.load_map.LoadMapInputBoundary;
import USE_CASE.load_map.LoadMapInteractor;
import VIEW_CREATOR.LoadMapViewModel;

import VIEW.LoadEventsView;
import VIEW.LoadMapView;


public class LoadMapController {

    public void execute(LoadMapViewModel viewModel){
        LoadMapOutputData loadMapOutputData = new LoadMapOutputData();
        LoadMapInputBoundary interactor = new LoadMapInteractor();

        interactor.execute(loadMapOutputData, viewModel);
    }

    public void viewProfile(){
        //TODO: switch to another Usecase (VIEW_PROFILE)
    }

    public void filterEvents(){
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
