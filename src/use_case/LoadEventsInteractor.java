package use_case;

import database.LoadEventsDataAccessInterface;
import database.DatabaseDAO;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Set;

import Entities.Temporary_entites.Event;



public class LoadEventsInteractor {
    @Getter
    Set<Event> events;
    LoadEventsDataAccessInterface loadEventsDataAccessInterface;

    public LoadEventsInteractor(GeoPosition initialPoint) {
        loadEventsDataAccessInterface = new DatabaseDAO();
        events = loadEventsDataAccessInterface.getEventsInRange(String.valueOf(initialPoint.getLatitude() - 5./111), String.valueOf(initialPoint.getLatitude() + 5./111), String.valueOf(initialPoint.getLongitude() - 5./111), String.valueOf(initialPoint.getLongitude() + 5./111));
    }

    public Set<Event> updateEvents(GeoPosition newStartPoint) {
        events = loadEventsDataAccessInterface.getEventsInRange(String.valueOf(newStartPoint.getLatitude() - 5./111), String.valueOf(newStartPoint.getLatitude() + 5./111), String.valueOf(newStartPoint.getLongitude() - 5./111), String.valueOf(newStartPoint.getLongitude() + 5./111));
        return events;
    }

    public boolean checkForClickOnEvent(Point clickPoint, JXMapViewer mapViewer) {

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
}
