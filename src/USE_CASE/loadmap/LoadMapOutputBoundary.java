package USE_CASE.loadmap;


import VIEW_CREATOR.LoadMapViewModel;


import INTERFACE_ADAPTER.loadmap_adapter.LoadMapInputData;

public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
