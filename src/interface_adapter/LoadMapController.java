package interface_adapter;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

//TODO: temporary measure, remove when Event entity will be added
class Event extends DefaultWaypoint {
    public Event(double longitude, double latitude) {
        super(longitude, latitude);
    }
}

public class LoadMapController {

    JXMapViewer mapViewer;

    public LoadMapController(JXMapViewer mapViewer) {
        this.mapViewer = mapViewer;
    }

    public boolean checkForClickOnEvent(Point clickPoint) {

        //TODO: events are received from UseCaseInteractor
        // In the line below instead of this here Set<Events> from UseCaseInteractor should be used

        Set<Event> events = new HashSet<>();

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

    public void viewProfile(){
        //TODO: switch to another Usecase (VIEW_PROFILE)
    }

    public void filterEvents(){
        //TODO: switch to another Usecase (FILTER_EVENTS)
    }

    public void viewFriends(){
        //TODO: switch to another Usecase (VIEW_FRIENDS)
    }

    public void viewEvents(){
        //TODO: switch to another Usecase (VIEW_EVENTS)
    }

    public void addEvent(){
        //TODO: switch to another Usecase (ADD_EVENT)
    }

    public void updateEvents(){
        //TODO: switch to another Usecase (UPDATE_EVENTS)
    }
}
