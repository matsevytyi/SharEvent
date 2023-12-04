
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
import lombok.Setter;
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

    @Getter
    private GeoPosition currentGeoposition;

    @Getter
    @Setter
    String problem;


    public LoadEventsInteractor(LoadEventsOuputData loadEventsOuputData) {
        events = new HashSet<>();
        currentGeoposition = loadEventsOuputData.getNewLocationPoint();
        problem = "";
    }


@Override
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView) {

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

        presenter.PrepareSuccesView();
    }
}
