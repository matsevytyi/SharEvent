
package INTERFACE_ADAPTER.loadevents_adapter;

import USE_CASE.loadevents.LoadEventsOuputData;
import VIEW.LoadMapView;
import lombok.Getter;

import USE_CASE.loadevents.LoadEventsOutputBoundary;
import USE_CASE.loadevents.LoadEventsInteractor;


public class LoadEventsController {

    @Getter
    LoadEventsOutputBoundary loadEventsInteractor;

    /**
     * Responses for passing user interaction from LoadMapview to LoadEventsInteractor
     * Passes data in separate class to fully adhere with Single Responsibility SOLID principle
     *
     * @param  loadMapView  the LoadMapView object, that will be passed to LoadEventsInteractor and later to LoadEventsPresenter
     *  because events are displayed directly on the map from the LoadMapView
     * that is updated by LoadEvents Use Case (it is the only way to insert them on the map)
     */
    public void execute(LoadMapView loadMapView){
        LoadEventsOuputData loadEventsOuputData = new LoadEventsOuputData(loadMapView.getViewModel().getMapKit().getMainMap().getCenterPosition());
        loadEventsInteractor = new LoadEventsInteractor(loadEventsOuputData);
        loadEventsInteractor.execute(loadEventsOuputData, loadMapView);}


}
