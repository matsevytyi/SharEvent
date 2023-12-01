
package INTERFACE_ADAPTER.loadevents_adapter;

import VIEW.LoadMapView;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

import USE_CASE.loadevents.LoadEventsOutputBoundary;
import USE_CASE.loadevents.LoadEventsInteractor;


public class LoadEventsController {

    @Getter
    LoadEventsOutputBoundary loadEventsInteractor;


    public void execute(LoadMapView loadMapView){
        LoadEventsOuputData loadEventsOuputData = new LoadEventsOuputData(loadMapView.getViewModel().getMapKit().getMainMap().getCenterPosition());
        loadEventsInteractor = new LoadEventsInteractor(loadEventsOuputData);
        loadEventsInteractor.execute(loadEventsOuputData, loadMapView);}


}
