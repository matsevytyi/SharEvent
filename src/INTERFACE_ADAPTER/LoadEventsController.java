package INTERFACE_ADAPTER;

import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

import USE_CASE.LoadEventsInputBoundary;
import USE_CASE.LoadEventsInteractor;

public class LoadEventsController {

    @Getter
    LoadEventsInputBoundary loadEventsInteractor;

    public LoadEventsController(JXMapViewer mapViewer, LoadEventsPresenter presenter) {
        loadEventsInteractor = new LoadEventsInteractor(mapViewer.getCenterPosition(), presenter);
    }

    public void execute(Point clickPoint, JXMapViewer mapViewer){
        loadEventsInteractor.checkForClickOnEvent(clickPoint, mapViewer);
    }


}
