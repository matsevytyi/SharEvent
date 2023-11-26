package use_case.load_map;


import VIEW_CREATOR.LoadMapViewModel;
import interface_adapter.load_map.LoadMapInputData;
import org.jxmapviewer.JXMapKit;

public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
