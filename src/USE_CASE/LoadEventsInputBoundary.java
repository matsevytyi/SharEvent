package USE_CASE;

import INTERFACE_ADAPTER.LoadEventsOuputData;
import VIEW.LoadMapView;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

public interface LoadEventsInputBoundary {
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView);

    public boolean checkForClickOnEvent(Point clickPoint, JXMapViewer mapViewer);
}
