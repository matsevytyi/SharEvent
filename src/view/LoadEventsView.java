package view;

import interface_adapter.LoadEventsController;
import interface_adapter.LoadEventsPresenter;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class LoadEventsView {

    @Getter
    private static LoadEventsPresenter presenter;

    @Getter
    private LoadEventsController controller;

    private LoadMapView loadMapViewResult;


    public StackPane getInitialPane() {
        return loadMapViewResult.getStackPane();
    }

    public LoadEventsView(LoadMapView loadMapViewResult) {
        presenter = new LoadEventsPresenter(loadMapViewResult, this);
        controller = new LoadEventsController(presenter.getMapKit().getMainMap());
        this.loadMapViewResult = loadMapViewResult;
    }
}
