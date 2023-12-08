package USE_CASE.loadmap;

import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.JXMapKit;


/**
 * Interface for accessing the Interactor from Controller
 * Helps to adhere to Open-Closed SOLID principle
 *
 * */
public interface LoadMapInputBoundary {

    JXMapKit getMapKit();
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel);
}
