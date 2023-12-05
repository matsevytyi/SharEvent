package VIEW;

import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsController;
import USE_CASE.loadevents.LoadEventsInputData;
import USE_CASE.loadevents.LoadEventsOuputData;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsPresenter;
import USE_CASE.loadevents.LoadEventsInteractor;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;

import java.lang.reflect.Field;
import java.util.HashSet;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoadEventsViewTest {


    private LoadMapView loadMapView;

    private LoadEventsView loadEventsView;

    @BeforeEach
    public void setUp() {
        LoadMapViewTest loadMapViewTest = new LoadMapViewTest();
        loadMapViewTest.setUp();

        loadMapView = loadMapViewTest.getLoadMapView();

        loadEventsView = new LoadEventsView(loadMapView);
    }

    @Test
    public void getInitialPane_shouldNotBeNull() {
        assertNotNull(loadEventsView.getInitialPane());
    }

    @Test
    public void test_Getters_Setters() {
        loadEventsView.reloadEvents(loadMapView);
        assertNotNull(loadEventsView.getController());
        System.out.println("first test passed");
        assertNull(loadEventsView.getPresenter());
    }

    @Test
    public void LoadEvents_test_prepareFailView() {
        LoadEventsOuputData loadEventsOuputData = new LoadEventsOuputData(new GeoPosition(0, 0));
        LoadEventsInteractor loadEventsInteractor = new LoadEventsInteractor(loadEventsOuputData);
        loadEventsInteractor.execute(loadEventsOuputData, loadMapView);
        System.out.println("0 test passed");
        loadEventsInteractor.setProblem("Database_Error");
        loadEventsInteractor.execute(loadEventsOuputData, loadMapView);
    }

    @Test
    public void test_no_events(){
        LoadEventsInteractor loadEventsInteractor = new LoadEventsInteractor(new LoadEventsOuputData(new GeoPosition(0, 0)));
        try {
            loadEventsInteractor.execute(new LoadEventsOuputData(new GeoPosition(0, 0)), loadMapView);
            Field eventsField = LoadEventsInteractor.class.getDeclaredField("events");
            eventsField.setAccessible(true);
            eventsField.set(loadEventsInteractor, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Assert.assertNull(loadEventsInteractor.getEvents());
        Assert.assertNotNull(loadEventsInteractor.getCurrentGeoposition());
        Assert.assertNotNull(loadEventsInteractor.getProblem());
    }

    @Test
    public void test_controller_getters() {
        LoadEventsController loadEventsController = new LoadEventsController();
        Assert.assertNull(loadEventsController.getLoadEventsInteractor());
    }

    @Test
    public void test_presenter_getters_setters() {
        LoadEventsPresenter loadEventsPresenter = new LoadEventsPresenter(new LoadEventsInputData(new HashSet<>()), loadMapView);
        Assert.assertNotNull(loadEventsPresenter.getMapKit());
        loadEventsPresenter.setInitialPane(loadMapView.getStackPane());
        Assert.assertNotNull(loadEventsPresenter.getInitialPane());
        loadEventsPresenter.PrepareFailView("Database_error", loadMapView);
        Assert.assertTrue(loadEventsPresenter.getInitialPane().getChildren().size() > 0);
        loadEventsPresenter.PrepareFailView("No_events", loadMapView);
        Assert.assertTrue(loadEventsPresenter.getInitialPane().getChildren().size() > 0);
    }
}

