package USE_CASE;

import API.LoadMapAPIAccessInterface;
import USE_CASE.loadmap.LoadMapOutputData;
import USE_CASE.loadmap.LoadMapInteractor;
import VIEW_CREATOR.LoadMapViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.JXMapKit;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;

public class LoadMapInteractorTest {


    @Mock
    LoadMapViewModel loadMapViewModel;

    private LoadMapInteractor loadMapInteractor;

    @Mock
    LoadMapAPIAccessInterface loadmapAPI;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute_test_getMapKit() {
        LoadMapOutputData loadMapOutputData = new LoadMapOutputData();
        loadMapInteractor = new LoadMapInteractor();
        loadMapInteractor.execute(loadMapOutputData, loadMapViewModel);
        Assert.assertEquals(JXMapKit.DefaultProviders.OpenStreetMaps, loadMapInteractor.getMapKit().getDefaultProvider());
        Assert.assertNotNull(loadMapInteractor.getMapKit());
        loadMapInteractor.setMapKit(new JXMapKit());
        Assert.assertNotNull(loadMapInteractor.getLoadmapAPI());
    }

    @Test
    public void execute_test_getters_setters() {
        LoadMapOutputData loadMapOutputData = new LoadMapOutputData();
        LoadMapInteractor loadMapInteractor = new LoadMapInteractor();
        loadMapInteractor.execute(loadMapOutputData, loadMapViewModel);
        Assert.assertNotNull(loadMapInteractor.getLoadmapAPI());
        loadMapInteractor.setMapKit(new JXMapKit());
        Assert.assertNotNull(loadMapInteractor.getLoadmapAPI());
    }



}
