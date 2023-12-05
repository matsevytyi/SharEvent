package VIEW;


import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsController;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsPresenter;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

public class LoadEventsView {

    @Getter
    private static LoadEventsPresenter presenter;

    @Getter
    private LoadEventsController controller;

    private JXMapViewer mapViewer;

    private LoadMapView loadMapView;


    public StackPane getInitialPane() {
        return loadMapView.getStackPane();
    }

    public LoadEventsView(LoadMapView loadMapView) {

        this.mapViewer = loadMapView.getViewModel().getMapKit().getMainMap();
        this.controller = new LoadEventsController();
        this.loadMapView = loadMapView;
    }

    public void reloadEvents(VIEW.LoadMapView loadMapView){
        controller.execute(loadMapView);
    }


}
