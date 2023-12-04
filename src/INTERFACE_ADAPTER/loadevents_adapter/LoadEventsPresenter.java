
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

    public boolean PrepareSuccesView() {
        System.out.println("LoadEvents mapkit Before: " + mapKit);

        Set<Event> localEvents = loadEventsInputData.getEvents();

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

        System.out.println("LoadEvents mapkit: " + mapKit);
        return true;
    }

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
