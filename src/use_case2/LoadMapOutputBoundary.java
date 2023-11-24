package use_case2;

import interface_adapter2.LoadMapInputData;
import VIEW_CREATOR.LoadMapViewModel;

public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
