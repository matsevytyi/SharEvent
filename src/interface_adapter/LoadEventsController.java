package interface_adapter;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import Entities.Temporary_entites.Event;
import org.jxmapviewer.viewer.Waypoint;
import use_case.LoadEventsInteractor;

public class LoadEventsController {
    JXMapViewer mapViewer;
    Set<Event> events;

    LoadEventsInteractor loadEventsInteractor;

    public LoadEventsController(JXMapViewer mapViewer) {
        this.mapViewer = mapViewer;
        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Point clickPoint = e.getPoint();
                loadEventsInteractor.checkForClickOnEvent(clickPoint, mapViewer);
            }
        });
    }


}
