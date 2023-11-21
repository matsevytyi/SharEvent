package interface_adapter;

import Entities.Temporary_entites.Event;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;
import use_case.LoadEventsInteractor;
import org.jxmapviewer.viewer.WaypointPainter;
import view.LoadMapView;

import java.util.Set;

public class LoadEventsPresenter {

    private LoadEventsInteractor loadEventsInteractor;

    private static JXMapKit mapKit;
    private GeoPosition initialGeo;
    private LoadMapView loadMapView;

    public LoadEventsPresenter(LoadMapView loadMapViewResult) {
        loadMapView = loadMapViewResult;
        mapKit = loadMapView.getPresenter().getMapKit();
        initialGeo = loadMapView.getPresenter().getInitialGeo();
        loadEventsInteractor = new LoadEventsInteractor(initialGeo);
        PrepareSuccesView();
    }
    public boolean PrepareSuccesView() {

        Set<Event> localEvents = loadEventsInteractor.updateEvents(initialGeo);

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

        return true;
    }

    public void PrepareFailView(String reason){

    }
}
