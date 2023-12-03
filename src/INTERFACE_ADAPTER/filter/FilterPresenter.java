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

    /**
     * This method shows all the events that are in the OutputData on the map,
     * given that there is at least one event in the OutputData.
     * @param filterOutputData the filterOutputData prepared by the interactor.
     */
    @Override
    public void prepareSuccessView(FilterOutputData filterOutputData) {
        Set<Event> localEvents = filterOutputData.getFoundEvents();

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

    }

    /**
     * This method opens a new Java Swing message dialog to show when an error has occured,
     * along with the appropriate error message.
     * @param error error message to display
     */
    @Override
    public void prepareFailView(String error) {
        System.out.println("Preparing Fail View for " + error);
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), error);
    }
}