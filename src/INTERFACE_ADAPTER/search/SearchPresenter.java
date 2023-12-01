package INTERFACE_ADAPTER.search;

import ENTITY.Event;
import USE_CASE.search.SearchOutputBoundary;
import USE_CASE.search.SearchOutputData;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.util.Set;

public class SearchPresenter implements SearchOutputBoundary {

    private final JXMapKit mapKit;

    public SearchPresenter(JXMapKit mapKit) {
        this.mapKit = mapKit;
    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        Set<Event> localEvents = searchOutputData.getFoundEvents();
        for (Event event : localEvents) {
            System.out.println(event.getEventName());
        }

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), error);
    }

}
