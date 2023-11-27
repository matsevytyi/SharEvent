package INTERFACE_ADAPTER.loadevents_adapter;

import ENTITY.Event;
import USE_CASE.loadevents.LoadEventsInputBoundary;
import VIEW_CREATOR.FailViewFactory;
import VIEW.LoadMapView;

import javafx.scene.layout.StackPane;
import lombok.Getter;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;


import java.util.Set;

public class LoadEventsPresenter implements LoadEventsInputBoundary {

    @Getter
    private final JXMapKit mapKit;

    private LoadEventsInputData loadEventsInputData;

    public LoadEventsPresenter(LoadEventsInputData loadEventsInputData, LoadMapView loadMapView) {
        mapKit = loadMapView.getViewModel().getMapKit();
        this.loadEventsInputData = loadEventsInputData;
    }
    public boolean PrepareSuccesView() {

        Set<Event> localEvents = loadEventsInputData.getEvents();

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

        return true;
    }

    public void PrepareFailView(String reason, LoadMapView loadMapView) {
        FailViewFactory failViewFactory = new FailViewFactory();

        StackPane initialPane = loadMapView.getStackPane();

        if(reason == "Database_error") {
            failViewFactory.createFailView(initialPane, "Database error", "Error connecting to the database. Check your connection and if your app is up-to-date");
            System.out.println("Error connecting to the database. Check your connection and if your app is up-to-date");
        }
        else if (reason == "No_events") {
            failViewFactory.createFailView(initialPane, "No events at this location", "NTry to search in another location or be the first to create your own event here");
            System.out.println("No events at this location");
        }
    }


}
