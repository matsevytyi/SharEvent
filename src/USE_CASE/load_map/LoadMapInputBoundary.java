package use_case.load_map;


import VIEW_CREATOR.LoadMapViewModel;
import interface_adapter.load_map.LoadMapOutputData;

public interface LoadMapInputBoundary {
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel);
}
