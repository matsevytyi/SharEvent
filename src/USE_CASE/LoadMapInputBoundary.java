package USE_CASE;

import INTERFACE_ADAPTER.LoadMapOutputData;
import VIEW_CREATOR.LoadMapViewModel;

public interface LoadMapInputBoundary {
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel);
}
