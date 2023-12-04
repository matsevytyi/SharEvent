package view;

import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsController;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsOuputData;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsPresenter;
import USE_CASE.loadevents.LoadEventsInteractor;
import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.JXMapViewer;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;
import org.mockito.Mock;
        import org.mockito.Mockito;
        import VIEW.LoadEventsView;
        import VIEW.LoadMapView;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

public class LoadEventsViewTest {

    @Mock
    private LoadEventsPresenter mockPresenter;

    @Mock
    private LoadEventsController mockController;


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
}

