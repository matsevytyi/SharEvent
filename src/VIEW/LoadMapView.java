package VIEW;

import INTERFACE_ADAPTER.LoadMapPresenter;
import INTERFACE_ADAPTER.LoadMapController;
import VIEW_CREATOR.LoadMapViewFactory;
import VIEW_CREATOR.LoadMapViewModel;

import javafx.scene.layout.StackPane;

import lombok.Getter;

public class LoadMapView {

    @Getter
    private LoadMapPresenter presenter;

    @Getter
    private LoadMapController controller;

    @Getter
    private LoadMapViewModel viewModel;

    @Getter
    private static StackPane pane;


    public LoadMapView() {

        viewModel = new LoadMapViewModel();

        presenter = new LoadMapPresenter();
        controller = new LoadMapController(viewModel.getMapKit().getMainMap(), this);

        pane = new StackPane();
        pane = new LoadMapViewFactory().createView(pane, viewModel, controller);

        controller.updateEvents();

    }

    public StackPane getStackPane() {
        return pane;
    }
}


