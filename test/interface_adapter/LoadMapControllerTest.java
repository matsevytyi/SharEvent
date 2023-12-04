package interface_adapter;

import INTERFACE_ADAPTER.filter.FilterController;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapController;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapOutputData;
import USE_CASE.loadmap.LoadMapInputBoundary;
import USE_CASE.loadmap.LoadMapInteractor;
import VIEW.FilterEventsView;
import VIEW.LoadMapView;
import VIEW_CREATOR.FilterEventsViewModel;
import VIEW_CREATOR.LoadMapViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoadMapControllerTest {

    private LoadMapController loadMapController;

    private FilterEventsView filterEventsView;

    private LoadMapViewModel viewModel;


    @Before
    public void setUp() {
        loadMapController = new LoadMapController();
        viewModel = new LoadMapViewModel();
    }
    @Test
    public void test_getter_setter() {
        loadMapController = new LoadMapController();
        loadMapController.setInteractor(new LoadMapInteractor());
        Assert.assertNotNull(loadMapController.getInteractor());
        Assert.assertNull(loadMapController.getAllEvents());
        Assert.assertNull(loadMapController.getLoadMapOutputData());
        Assert.assertNull(loadMapController.getFilterEventsDAO());
        Assert.assertNull(loadMapController.getSearchEventsDAO());
        Assert.assertNull(loadMapController.getSearchEventsView());
        Assert.assertNull(loadMapController.getSearchEventsViewFactory());
    }

    /*@Test
    public void filterEvents_test() {
        loadMapController.filterEvents(filterEventsView);

        Assert.assertNotNull(loadMapController.getFilterEventsDAO());
        Assert.assertNotNull(loadMapController.getAllEvents());
    }*/

    @Test
    public void searchEvents_test() {
        loadMapController = new LoadMapController();
        viewModel = new LoadMapViewModel();

        loadMapController.searchEvents(viewModel);
        Assert.assertNotNull(loadMapController.getSearchEventsDAO());
        Assert.assertNotNull(loadMapController.getAllEvents());
        Assert.assertNotNull(loadMapController.getSearchEventsView());
        Assert.assertNotNull(loadMapController.getSearchEventsViewFactory());
    }
}
