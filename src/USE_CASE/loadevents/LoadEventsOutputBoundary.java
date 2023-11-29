package USE_CASE.loadevents;



import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsOuputData;
import VIEW.LoadMapView;

public interface LoadEventsOutputBoundary {
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView);

}
