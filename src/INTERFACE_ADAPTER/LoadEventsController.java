package INTERFACE_ADAPTER;

import VIEW.LoadMapView;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

import USE_CASE.LoadEventsOutputBoundary;
import USE_CASE.LoadEventsInteractor;

public class LoadEventsController {

    @Getter
    LoadEventsOutputBoundary loadEventsInteractor;

    //TODO: ALSO SHOULD BE MOVED TO VIEW_EVENT USE CASE

    public void checkForEvent(Point clickPoint, JXMapViewer mapViewer){
        loadEventsInteractor.checkForClickOnEvent(clickPoint, mapViewer);
    }

    public void execute(LoadMapView loadMapView){
        LoadEventsOuputData loadEventsOuputData = new LoadEventsOuputData(loadMapView.getViewModel().getMapKit().getMainMap().getCenterPosition());
        loadEventsInteractor = new LoadEventsInteractor(loadEventsOuputData);
        loadEventsInteractor.execute(loadEventsOuputData, loadMapView);
    }


}
