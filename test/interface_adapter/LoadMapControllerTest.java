package interface_adapter;

import INTERFACE_ADAPTER.filter.FilterController;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapController;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapOutputData;
import USE_CASE.loadmap.LoadMapInputBoundary;
import VIEW.FilterEventsView;
import VIEW.LoadMapView;
import VIEW_CREATOR.LoadMapViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class LoadMapControllerTest {

    @Mock
    private LoadMapViewModel viewModel;

    @Mock
    FilterController filterController;

    @InjectMocks
    private FilterEventsView filterEventsView;

    private LoadMapController loadMapController = new LoadMapController();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void filterEvents_test() {
        loadMapController.filterEvents(filterEventsView);

        Assert.assertNotNull(loadMapController.getFilterEventsDAO());
        Assert.assertNotNull(loadMapController.getAllEvents());
    }

    @Test
    public void searchEvents_test() {
        loadMapController.searchEvents(viewModel);

        Assert.assertNotNull(loadMapController.getSearchEventsDAO());
        Assert.assertNotNull(loadMapController.getAllEvents());
        Assert.assertNotNull(loadMapController.getSearchEventsView());
        Assert.assertNotNull(loadMapController.getSearchEventsViewFactory());
    }

}
