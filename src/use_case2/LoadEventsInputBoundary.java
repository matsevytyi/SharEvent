package use_case2;

import view2.LoadMapView;

public interface LoadEventsInputBoundary {
    public boolean PrepareSuccesView();

    public void PrepareFailView(String reason, LoadMapView loadMapView);
}
