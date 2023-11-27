package INTERFACE_ADAPTER.load_events;


import USE_CASE.load_events.LoadEventsInteractor;
import USE_CASE.load_events.LoadEventsOutputBoundary;
import lombok.Getter;


import VIEW.LoadMapView;

public class LoadEventsController {

    @Getter
    LoadEventsOutputBoundary loadEventsInteractor;


    public void execute(LoadMapView loadMapView){
        LoadEventsOuputData loadEventsOuputData = new LoadEventsOuputData(loadMapView.getViewModel().getMapKit().getMainMap().getCenterPosition());
        loadEventsInteractor = new LoadEventsInteractor(loadEventsOuputData);
        loadEventsInteractor.execute(loadEventsOuputData, loadMapView);}


}
