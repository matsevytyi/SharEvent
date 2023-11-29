package INTERFACE_ADAPTER.filter;

import ENTITY.Temporary_entites;
import USE_CASE.filter.FilterOutputBoundary;
import VIEW.LoadMapView;
import INTERFACE_ADAPTER.LoadEventsInputData;
import USE_CASE.filter.FilterOutputData;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;

import java.util.Set;

public class FilterPresenter implements FilterOutputBoundary {
    private final JXMapKit mapKit;


    public FilterPresenter(JXMapKit mapKit) {
        this.mapKit = mapKit;
    }

    public void prepareSuccessView(FilterOutputData filterOutputData) {

        Set<Temporary_entites.Event> localEvents = filterOutputData.getEvents();

        WaypointPainter<Temporary_entites.Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);
    }

    @Override
    public void prepareFailView(String error) {
        // TODO
    }
}
