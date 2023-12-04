package USE_CASE.loadmap;


import VIEW_CREATOR.LoadMapViewModel;

public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
