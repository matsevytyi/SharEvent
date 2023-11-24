package use_case2;

import DATA_ACCESS.LoadEventsDAO_InputData;
import DATA_ACCESS.LoadEventsDAO_OutputData;
import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.LoadEventsDataAccessInterface;
import interface_adapter2.LoadEventsInputData;
import interface_adapter2.LoadEventsOuputData;
import interface_adapter2.LoadEventsPresenter;
import view2.LoadMapView;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Set;

import entity2.Temporary_entites.Event;



public class LoadEventsInteractor implements LoadEventsOutputBoundary {

    @Getter
    private Set<Event> events;

    @Getter
    private GeoPosition currentGeoposition;


    public LoadEventsInteractor(LoadEventsOuputData loadEventsOuputData) {
        events = null;
        currentGeoposition = loadEventsOuputData.getNewLocationPoint();
    }

    //TODO: this code may be moved to another UseCaseInteractor (VIEW_EVENT)

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

    //TODO: --- till here ---

    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView) {
        String problem = "";

        try{
            LoadEventsDataAccessInterface databaseAccess = new DatabaseDAO();
            LoadEventsDAO_InputData inputDatabaseData = new LoadEventsDAO_InputData(loadEventsOuputData.getNewLocationPoint());
            LoadEventsDAO_OutputData outputDatabaseData = databaseAccess.getEventsInRange(inputDatabaseData);
            events = outputDatabaseData.getEvents();
        } catch (Exception e) {
            System.out.println("Exception while loading events from DB\n" + e.getMessage());
            problem = "Database_error";
        }

        LoadEventsInputData loadEventsInputData = new LoadEventsInputData(events);
        LoadEventsInputBoundary presenter = new LoadEventsPresenter(loadEventsInputData, loadMapView);

        if(problem == "Database_error") {
            presenter.PrepareFailView(problem, loadMapView);
            return;
        }

        if(events == null) {
            problem = "No_events";
            presenter.PrepareFailView(problem, loadMapView);
            return;
        }

        presenter.PrepareSuccesView();
    }
}
