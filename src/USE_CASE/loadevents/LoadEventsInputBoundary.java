
package USE_CASE.loadevents;

import VIEW.LoadMapView;

/**
 * Interface for accessing the Interactor from Controller
 * Helps to adhere to Open-Closed SOLID principle
 *
 * */
public interface LoadEventsInputBoundary {
    public boolean PrepareSuccesView();

    public void PrepareFailView(String reason, LoadMapView loadMapView);
}
