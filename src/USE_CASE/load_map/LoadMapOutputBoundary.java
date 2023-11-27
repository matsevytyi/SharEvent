package USE_CASE.load_map;


import INTERFACE_ADAPTER.load_map.LoadMapInputData;
import VIEW_CREATOR.LoadMapViewModel;

import org.jxmapviewer.JXMapKit;

public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
