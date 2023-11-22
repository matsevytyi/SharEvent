package INTERFACE_ADAPTER;

import USE_CASE.LoadMapOutputBoundary;
import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.JXMapKit;

public class LoadMapPresenter implements LoadMapOutputBoundary {

    public LoadMapPresenter() {
    }

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel){
        loadMapViewModel.setMapKit(loadMapInputData.getMapKit());
    }

    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel){
        if(reason == "Map_Load_Error") {
            //TODO: SHOW BLOCK ABOVE THE STACKPANE WITH ERROR
        }
        else if (reason == "API_error"){
            //TODO: SHOW BLOCK ABOVE THE STACKPANE WITH ERROR + buttons
        }
    }

}
