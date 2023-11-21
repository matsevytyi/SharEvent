package interface_adapter;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

import java.util.HashSet;
import java.util.Set;

class event extends DefaultWaypoint {}

public class LoadMapPresenter {

    private static JXMapKit mapKit;

    public LoadMapPresenter() {

        mapKit = new JXMapKit();
        mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        mapKit.setZoom(4);

        mapKit.setAddressLocation(new GeoPosition(43.651575, -79.345010));
        //TODO: before the release uncommwnt the next line and delete the upper line
        //mapKit.setAddressLocation(GetCoordByIP_API.getCoord());
    }

    public JXMapKit getMapKit() {
        return mapKit;
    }

    public boolean LoadEvents() {

        Set<event> localEvents = new HashSet<>(); //instead of this here Set<Events> from UseCaseInteractor should be used

        WaypointPainter<event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

        return true;
    }

}
