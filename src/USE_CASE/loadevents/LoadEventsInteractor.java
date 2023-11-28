
package USE_CASE.loadevents;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_InputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_OutputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;

import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsInputData;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsOuputData;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsPresenter;
import VIEW.LoadMapView;

import lombok.Getter;
import org.jxmapviewer.viewer.GeoPosition;


import java.util.HashSet;
import java.util.Set;

import VIEW.LoadMapView;


import ENTITY.Event;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Set;


public class LoadEventsInteractor implements LoadEventsOutputBoundary {

    @Getter
    private Set<ENTITY.Event> events;
//    LocalDate localDate = LocalDate.of(2023, 12, 3);
//    LocalTime localTime = LocalTime.of(12, 12, 12);
//    Event event = new Event("music show", "music", "jdhjvhf", localDate, localTime, new User("ff","ff","ff", "ff"), null, 43.66171701890102, -79.40012991428375
//    );

    @Getter
    private GeoPosition currentGeoposition;


    public LoadEventsInteractor(LoadEventsOuputData loadEventsOuputData) {
        events = new HashSet<>();
        currentGeoposition = loadEventsOuputData.getNewLocationPoint();
    }


@Override
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView) {
        String problem = "";

        try{
            LoadEventsDataAccessInterface databaseAccess = (LoadEventsDataAccessInterface) new DatabaseDAO();
            LoadEventsDAO_InputData inputDatabaseData = new LoadEventsDAO_InputData(loadEventsOuputData.getNewLocationPoint());
            LoadEventsDAO_OutputData outputDatabaseData = databaseAccess.getEventsInRange(inputDatabaseData);
            events = outputDatabaseData.getEvents();
        } catch (Exception e) {
            System.out.println("Exception while loading events from DB\n" + e.getMessage());
            problem = "Database_error";
        }

        System.out.println("events are in interactor");

        LoadEventsInputData loadEventsInputData = new LoadEventsInputData(events);
        System.out.println("loadEventsInputData: ");
        LoadEventsInputBoundary presenter = new LoadEventsPresenter(loadEventsInputData, loadMapView);
        System.out.println("presenter: ");

        for(Event event : events) {
            System.out.println(event.getEventName()+" "+event.getGeoPosition().toString());
        }

        if(problem == "Database_error") {
            presenter.PrepareFailView(problem, loadMapView);
            return;
        }

        if(events == null) {
            problem = "No_events";
            System.out.println("No events at this location");
            presenter.PrepareFailView(problem, loadMapView);
            return;
        }

        presenter.PrepareSuccesView();
    }
}
