package use_case2;

import interface_adapter2.LoadMapOutputData;
import VIEW_CREATOR.LoadMapViewModel;

public interface LoadMapInputBoundary {
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel);
}
