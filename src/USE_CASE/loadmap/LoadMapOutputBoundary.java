package USE_CASE.loadmap;

import INTERFACE_ADAPTER.loadmap_adapter.LoadMapInputData;
import VIEW_CREATOR.LoadMapViewModel;

public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
