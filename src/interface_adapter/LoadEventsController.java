package interface_adapter;

import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;
import java.util.Set;

import use_case.LoadEventsInputBoundary;
import use_case.LoadEventsInteractor;

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
