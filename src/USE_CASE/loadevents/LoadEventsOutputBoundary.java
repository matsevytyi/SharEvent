package USE_CASE.loadevents;



import VIEW.LoadMapView;

/**
 * Interface for accessing the Presenter from Interctor
 * Helps to adhere to Open-Closed SOLID principle
 *
 * */
public interface LoadEventsOutputBoundary {
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView);

}
