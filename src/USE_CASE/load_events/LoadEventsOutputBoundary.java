package USE_CASE.load_events;


import INTERFACE_ADAPTER.load_events.LoadEventsOuputData;
import VIEW.LoadMapView;

public interface LoadEventsOutputBoundary {
    public void execute(LoadEventsOuputData loadEventsOuputData, LoadMapView loadMapView);

}
