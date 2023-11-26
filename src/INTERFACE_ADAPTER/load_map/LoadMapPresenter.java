package interface_adapter.load_map;


import VIEW_CREATOR.LoadMapViewModel;
import use_case.load_map.LoadMapOutputBoundary;

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
