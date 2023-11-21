package view;

import interface_adapter.LoadEventsController;
import interface_adapter.LoadEventsPresenter;

public class LoadEventsView {
    private LoadEventsPresenter presenter;
    private LoadEventsController controller;
    public LoadEventsView(LoadMapView loadMapViewResult) {
        presenter = new LoadEventsPresenter(loadMapViewResult);
        controller = new LoadEventsController(presenter.getMapKit().getMainMap());
    }
}
