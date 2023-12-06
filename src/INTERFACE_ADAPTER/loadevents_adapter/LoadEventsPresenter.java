
package INTERFACE_ADAPTER.loadevents_adapter;

import ENTITY.Event;
import USE_CASE.loadevents.LoadEventsInputBoundary;

import USE_CASE.loadevents.LoadEventsInputData;
import VIEW_CREATOR.FailViewFactory;
import javafx.scene.layout.StackPane;
import lombok.Getter;

import lombok.Setter;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;

import VIEW.LoadMapView;


import java.util.Set;

public class LoadEventsPresenter implements LoadEventsInputBoundary {

    @Getter
    private final JXMapKit mapKit;

    @Getter
    @Setter
    StackPane initialPane;

    private LoadEventsInputData loadEventsInputData;

    public LoadEventsPresenter(LoadEventsInputData loadEventsInputData, LoadMapView loadMapView) {
        mapKit = loadMapView.getViewModel().getMapKit();
        this.loadEventsInputData = loadEventsInputData;
    }

    /**
     * Prepares the success view by loading events onto the map
     *
     * Is invoked by LoadEventsInteractor
     *
     * Uses mapKit, accessed from LoadMapView in the creator function,
     * because I used library functionality to add events
     * by having event entity as a child class of DefaultWaypoint,
     * that has the appropriate functionality
     *
     * @return  true if the success view is prepared successfully
     */
    public boolean PrepareSuccesView() {
        System.out.println("LoadEvents mapkit Before: " + mapKit);

        Set<Event> localEvents = loadEventsInputData.getEvents();

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

        System.out.println("LoadEvents mapkit: " + mapKit);
        return true;
    }

    /**
     * Prepares a fail view based on the given reason and loadMapView
     *
     * Fail view is shown over the map to make it obvious for user
     * that something went wrong (it will also advice user what to do)
     *
     * @param  reason         the reason for the fail view, is created in LoadEventsInteractor
     * @param  loadMapView    the loadMapView object
     */
    public void PrepareFailView(String reason, LoadMapView loadMapView) {
        FailViewFactory failViewFactory = new FailViewFactory();

        initialPane = loadMapView.getStackPane();

        if (reason == "Database_error") {
            failViewFactory.createFailView(initialPane, "Database error", "Error connecting to the database. Check your connection and if your app is up-to-date");
            System.out.println("Error connecting to the database. Check your connection and if your app is up-to-date");
        } else if (reason == "No_events") {
            failViewFactory.createFailView(initialPane, "No events at this location", "NTry to search in another location or be the first to create your own event here");
            System.out.println("No events at this location");
        }

    }
}
