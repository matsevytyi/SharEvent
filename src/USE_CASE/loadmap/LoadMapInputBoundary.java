package USE_CASE.loadmap;

import INTERFACE_ADAPTER.loadmap_adapter.LoadMapOutputData;
import VIEW_CREATOR.LoadMapViewModel;

public interface LoadMapInputBoundary {
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel);
}
