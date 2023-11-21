package interface_adapter;

import org.jxmapviewer.JXMapViewer;
import use_case.LoadEventsInputBoundary;
import use_case.LoadEventsInteractor;
import view.LoadEventsView;
import view.LoadMapView;

public class LoadMapController {

    //TODO: initialize it

    JXMapViewer mapViewer;

    LoadMapView loadMapView;

    public LoadMapController(JXMapViewer mapViewer, LoadMapView loadMapView) {
        this.mapViewer = mapViewer;
        this.loadMapView = loadMapView;
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

    public void viewEvents(){
        //TODO: switch to another Usecase (VIEW_EVENTS)
    }

    public void addEvent(){
        //TODO: switch to another Usecase (ADD_EVENT)
    }

    public void updateEvents(){
        LoadEventsView loadEventsView = new LoadEventsView(loadMapView);
        LoadEventsInputBoundary loadEventsInteractor = new LoadEventsInteractor(mapViewer.getCenterPosition(), loadEventsView.getPresenter());
        loadEventsInteractor.execute();
    }
}
