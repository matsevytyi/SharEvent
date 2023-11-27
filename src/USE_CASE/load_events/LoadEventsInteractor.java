package use_case.load_events;


import data_access.DatabaseDAO;
import data_access.LoadEventsDAO_InputData;
import data_access.LoadEventsDAO_OutputData;
import data_access.LoadEventsDataAccessInterface;
import entity.User;
import interface_adapter.load_events.LoadEventsInputData;
import interface_adapter.load_events.LoadEventsOuputData;
import interface_adapter.load_events.LoadEventsPresenter;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import entity.Event;
import view.LoadMapView;


public class LoadEventsInteractor implements LoadEventsOutputBoundary {

    @Getter
    private Set<entity.Event> events;
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

    //TODO: this code may be moved to another UseCaseInteractor (VIEW_EVENT)



    //TODO: --- till here ---
@Override
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