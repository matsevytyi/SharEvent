package VIEW;


import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsController;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsPresenter;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

/**
 * This view is responsible for showing events, embedded on the interactive map
 *
 * Is initially automatically being called when Map is loaded
 *
 * Is a frontEnd paty of LoadEvents Use Case
 *
 * It has a feature to load events in 5km area around the user
 * so that he can see events he may need and not wait for ages
 *
 * This implied usage of reloadEvents function
 **/
public class LoadEventsView {

    @Getter
    private static LoadEventsPresenter presenter;

    @Getter
    private LoadEventsController controller;

    private JXMapViewer mapViewer;

    private LoadMapView loadMapView;

    /**
     * Retrieves the initial StackPane for the JavaFX application.
     *
     * @return  the StackPane containing the initial view
     */

    public StackPane getInitialPane() {
        return loadMapView.getStackPane();
    }

    public LoadEventsView(LoadMapView loadMapView) {

        this.mapViewer = loadMapView.getViewModel().getMapKit().getMainMap();
        this.controller = new LoadEventsController();
        this.loadMapView = loadMapView;
    }

    /**
     * Makes a request for reloading events in a new 5km area, selected by a user
     *
     * @param  loadMapView  the load map view
     */
    public void reloadEvents(VIEW.LoadMapView loadMapView){
        controller.execute(loadMapView);
    }


}
