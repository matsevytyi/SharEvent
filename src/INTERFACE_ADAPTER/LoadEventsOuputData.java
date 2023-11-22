package INTERFACE_ADAPTER;

import lombok.Getter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

public class LoadEventsOuputData {

    @Getter
    private GeoPosition newLocationPoint;

    public LoadEventsOuputData(GeoPosition newLocationPoint) {
        this.newLocationPoint = newLocationPoint;
    }
}
