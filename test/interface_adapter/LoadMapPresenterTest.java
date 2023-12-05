package interface_adapter;

import INTERFACE_ADAPTER.loadmap_adapter.LoadMapPresenter;
import USE_CASE.loadmap.LoadMapOutputBoundary;
import VIEW_CREATOR.LoadMapViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class LoadMapPresenterTest {
    @Test
    public void LoadMapPresenter_testFailViews() {
        LoadMapViewModel loadMapViewModel = new LoadMapViewModel();
        LoadMapOutputBoundary loadMapPresenter = new LoadMapPresenter();
        loadMapPresenter.PrepareFailView("Map_Load_Error", loadMapViewModel);
        assertEquals("Map_Load_Error", loadMapViewModel.getMap_Load_Error());

        loadMapPresenter.PrepareFailView("API_error", loadMapViewModel);
        assertEquals("API_error", loadMapViewModel.getAPI_error());
    }
}
