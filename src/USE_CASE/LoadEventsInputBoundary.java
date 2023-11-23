package USE_CASE;

import VIEW.LoadMapView;

public interface LoadEventsInputBoundary {
    public boolean PrepareSuccesView();

    public void PrepareFailView(String reason, LoadMapView loadMapView);
}
