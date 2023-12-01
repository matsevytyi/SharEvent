package INTERFACE_ADAPTER.filter;

import ENTITY.Event;
import ENTITY.EventInterface;
import USE_CASE.filter.FilterOutputBoundary;
import VIEW.FilterEventsView;
import VIEW.LoadMapView;
import USE_CASE.filter.FilterOutputData;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Set;

public class FilterPresenter implements FilterOutputBoundary {
    private final JXMapKit mapKit;


    public FilterPresenter(JXMapKit mapKit) {
        this.mapKit = mapKit;
    }

    public void prepareSuccessView(FilterOutputData filterOutputData) {
        Set<Event> localEvents = filterOutputData.getFoundEvents();
//        for (Event event : localEvents) {
//            System.out.println(event.getEventName());
//        }

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Preparing Fail View for " + error);
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), error);
    }
}
