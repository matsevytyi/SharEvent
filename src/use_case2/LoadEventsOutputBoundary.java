package use_case2;

import interface_adapter2.LoadEventsOuputData;
import view2.LoadMapView;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

public interface LoadEventsOutputBoundary {
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView);

    public boolean checkForClickOnEvent(Point clickPoint, JXMapViewer mapViewer);
}
