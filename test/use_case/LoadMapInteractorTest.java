package use_case;

import API.LoadMapAPIAccessInterface;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapOutputData;
import USE_CASE.loadmap.LoadMapInputBoundary;
import USE_CASE.loadmap.LoadMapInteractor;
import VIEW_CREATOR.LoadMapViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.JXMapKit;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

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



}
