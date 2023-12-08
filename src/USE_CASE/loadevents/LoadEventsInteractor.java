
package USE_CASE.loadevents;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_InputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_OutputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;

import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsPresenter;
import VIEW.LoadMapView;

import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.viewer.GeoPosition;


import java.util.HashSet;
import java.util.Set;


import ENTITY.Event;


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

    /**
     *
     * Only here is only core LoadEvents Use Case logic implemened
     * (adheres to SOLID SRP)
     *
     * Interactor tries to access the database. He passes there center position
     * of the part of the map that user looks on.
     * From the database he updates List<Event> that later will be passed to presenter
     * to be loaded on the map.
     *
     * If there is a problem with database,
     * presenter will work on showing the appropriate fail view
     *
     * @param  loadEventsOuputData   The output data from user actions
     * @param  loadMapView          The map view for loading events.
     */
@Override
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView) {

        try{
            LoadEventsDataAccessInterface databaseAccess = (LoadEventsDataAccessInterface) new DatabaseDAO();
            LoadEventsDAO_InputData inputDatabaseData = new LoadEventsDAO_InputData(loadEventsOuputData.getNewLocationPoint());
            LoadEventsDAO_OutputData outputDatabaseData = databaseAccess.getEventsInRange(inputDatabaseData);
            events = outputDatabaseData.getEvents();
        } catch (Exception e) {
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

        System.out.println("here");

        presenter.PrepareSuccesView();
    }
}
