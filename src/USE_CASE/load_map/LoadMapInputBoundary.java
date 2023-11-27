package USE_CASE.load_map;


import INTERFACE_ADAPTER.load_map.LoadMapOutputData;
import VIEW_CREATOR.LoadMapViewModel;


public interface LoadMapInputBoundary {
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel);
}
