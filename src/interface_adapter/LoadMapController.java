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
