package use_case.load_events;


import interface_adapter.load_events.LoadEventsOuputData;
import org.jxmapviewer.JXMapViewer;
import view.LoadMapView;

import java.awt.*;

public interface LoadEventsOutputBoundary {
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView);

}
