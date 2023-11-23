package USE_CASE;

import INTERFACE_ADAPTER.LoadMapInputData;
import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.JXMapKit;

public interface LoadMapOutputBoundary {
    public void PrepareFailView(String reason, LoadMapViewModel loadMapViewModel);

    public void PrepareSuccessView(LoadMapInputData loadMapInputData, LoadMapViewModel loadMapViewModel);
}
