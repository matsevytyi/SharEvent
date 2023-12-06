package USE_CASE.loadmap;


import VIEW_CREATOR.LoadMapViewModel;

/**
 * Interface for accessing the Presenter from Interctor
 * Helps to adhere to Open-Closed SOLID principle
 *
 * */
public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
