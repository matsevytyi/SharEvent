package INTERFACE_ADAPTER.view_event;


import USE_CASE.view_event.ViewEventInputBoundary;
import USE_CASE.view_event.ViewEventInputData;
import USE_CASE.view_profile.ViewProfileInputBoundary;
import org.jxmapviewer.JXMapViewer;


import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * The class is the controller for the "view event" use case.
 * It makes the communication between the external interface and the use case interactor.
 * This class receives input parameters related to the location (latitude and longitude) and the map viewer,
 * then delegates the execution of the use case to the provided ViewEventInputBoundary instance.
 */
public class ViewEventController  {

    /**
     * Constructs a constructor with the specified  ViewEventInputBoundary.
     * @param viewEventUseCaseInteractor The use case input boundary  for the "view event" functionality.
     */
    public ViewEventController(ViewEventInputBoundary viewEventUseCaseInteractor) {
        this.viewEventUseCaseInteractor = viewEventUseCaseInteractor;
    }

    public final ViewEventInputBoundary viewEventUseCaseInteractor;

    /**
     * Executes the "view event" use case with the provided latitude, longitude, and map viewer.
     * @param latitude   The latitude coordinate of the location to view events.
     * @param longitude  The longitude coordinate of the location to view events.
     * @param mapViewer  The JXMapViewer used to display the map.
     */
    public void execute(double latitude, double longitude, JXMapViewer mapViewer) {
        ViewEventInputData inputData= new ViewEventInputData(latitude, longitude, mapViewer);
        viewEventUseCaseInteractor.execute(inputData);
    }


}
