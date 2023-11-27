package INTERFACE_ADAPTER.loadmap_adapter;

import USE_CASE.loadmap.LoadMapOutputBoundary;
import VIEW_CREATOR.LoadMapViewModel;

public class LoadMapPresenter implements LoadMapOutputBoundary {

    public LoadMapPresenter() {
    }

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel){
        loadMapViewModel.setMapKit(loadMapInputData.getMapKit());
    }

    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel){
        if(reason == "Map_Load_Error") {
            loadMapViewModel.setMap_Load_Error("Map_Load_Error");
        }
        else if (reason == "API_error"){
            loadMapViewModel.setAPI_error("API_error");
        }
    }

}
