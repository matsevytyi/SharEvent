package USE_CASE;

import DATA_ACCESS.LoadEventsDataAccessInterface;
import DATA_ACCESS.DatabaseDAO;
import INTERFACE_ADAPTER.LoadEventsPresenter;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Set;

import ENTITY.Temporary_entites.Event;



public class LoadEventsInteractor implements LoadEventsInputBoundary {
    @Getter
    Set<Event> events;
    GeoPosition newStartPoint;
    LoadEventsDataAccessInterface loadEventsDataAccessInterface;
    LoadEventsPresenter presenter;

    public LoadEventsInteractor(GeoPosition initialPoint, LoadEventsPresenter presenter) {
        loadEventsDataAccessInterface = new DatabaseDAO();
        newStartPoint = initialPoint;
        this.presenter = presenter;
    }

    public boolean checkForClickOnEvent(Point clickPoint, JXMapViewer mapViewer) {

        System.out.println("checkForClickOnEvent");

        if(events == null) return false;

        // Check if any waypoints are clicked
        for (Event event : events) {
            GeoPosition eventPosition = event.getPosition();
            GeoPosition clickedPosition = mapViewer.convertPointToGeoPosition(clickPoint);

            // Check if the click is within a certain distance of the waypoint
            if (isClickNearWaypoint(clickedPosition, eventPosition, mapViewer)) {
                //TODO: switch to another UseCase (VIEW_EVENT)
                return true;
            }
        }
        return false;
    }

    private boolean isClickNearWaypoint(GeoPosition clickPosition, GeoPosition waypointPosition, JXMapViewer mapViewer) {
        // Convert GeoPositions to screen coordinates
        Point2D.Double clickPoint = (Point2D.Double) mapViewer.getTileFactory().geoToPixel(clickPosition, mapViewer.getZoom());
        Point2D.Double waypointPoint = (Point2D.Double) mapViewer.getTileFactory().geoToPixel(waypointPosition, mapViewer.getZoom());

        // Define a threshold for considering the click as near the waypoint
        int threshold = 23;

        // Check if the click is within the threshold distance of the waypoint
        return clickPoint.distance(waypointPoint) < threshold;
    }

    public void execute() {
        try{
            events = loadEventsDataAccessInterface.getEventsInRange(String.valueOf(newStartPoint.getLatitude() - 5./111), String.valueOf(newStartPoint.getLatitude() + 5./111), String.valueOf(newStartPoint.getLongitude() - 5./111), String.valueOf(newStartPoint.getLongitude() + 5./111));
        } catch (Exception e) {
            System.out.println("Exception while loading events from DB\n" + e.getMessage());
            presenter.PrepareFailView("Database_error");
        }
        if(events.isEmpty()) presenter.PrepareFailView("No_events");
        else presenter.PrepareSuccesView(events);
    }
}
