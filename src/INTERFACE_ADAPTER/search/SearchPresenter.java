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

    /**
     * This method shows all the events that are in the OutputData on the map,
     * given that there is at least one event in the OutputData.
     * @param searchOutputData the searchOutputData prepared by the interactor.
     */
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

    /**
     * This method opens a new Java Swing message dialog to show when an error has occured,
     * along with the appropriate error message.
     * @param error error message to display
     */
    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), error);
    }

}
