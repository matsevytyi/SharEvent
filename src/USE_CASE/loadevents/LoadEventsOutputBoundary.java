package USE_CASE.loadevents;

import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsOuputData;
import VIEW.LoadMapView;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

public interface LoadEventsOutputBoundary {
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView);

    public boolean checkForClickOnEvent(Point clickPoint, JXMapViewer mapViewer);
}
