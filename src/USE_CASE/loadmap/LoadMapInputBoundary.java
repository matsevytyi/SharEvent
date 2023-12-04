package USE_CASE.loadmap;

import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.JXMapKit;


public interface LoadMapInputBoundary {

    JXMapKit getMapKit();
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel);
}
