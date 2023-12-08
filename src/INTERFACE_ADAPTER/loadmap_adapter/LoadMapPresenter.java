
package INTERFACE_ADAPTER.loadmap_adapter;


import USE_CASE.loadmap.LoadMapInputData;
import USE_CASE.loadmap.LoadMapOutputBoundary;
import VIEW_CREATOR.LoadMapViewModel;


public class LoadMapPresenter implements LoadMapOutputBoundary {

    public LoadMapPresenter() {
    }

    /**
     * Prepares the success view by displasing
     * the prepared in interactor map
     *
     * @param  loadMapInputData    the input data for loading the map
     * @param  loadMapViewModel    the view model for loading the map
     */
    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel){
        loadMapViewModel.setMapKit(loadMapInputData.getMapKit());
    }

    /**
     * Prepares a fail view to notify user that something went wrong
     *          and to advice user how to fix it
     *
     * @param  reason                  a String representing the reason for failure
     * @param  loadMapViewModel        a LoadMapViewModel object to be modified
     */
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel){
        if(reason == "Map_Load_Error") {
            loadMapViewModel.setMap_Load_Error("Map_Load_Error");
        }
        else if (reason == "API_error"){
            loadMapViewModel.setAPI_error("API_error");
        }
    }

}
