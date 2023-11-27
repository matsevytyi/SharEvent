package USE_CASE.loadmap;

import API.LoadMap_API;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapInputData;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapOutputData;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapPresenter;
import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

import java.io.IOException;

public class LoadMapInteractor implements LoadMapInputBoundary {

    public LoadMapInteractor() {
    }
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel){

        LoadMapOutputBoundary loadMapPresenter = new LoadMapPresenter();

        JXMapKit mapKit = loadMapOutputData.getMapKit();
        mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        mapKit.setZoom(4);
        try {
            GeoPosition initialGeo = LoadMap_API.getCoord();
            mapKit.setAddressLocation(initialGeo);
            loadMapPresenter.PrepareSuccessView(new LoadMapInputData(mapKit), loadMapViewModel);
        } catch (IOException e) { //adjust for API error
            System.out.println(e);
            System.out.println("API Error");
            loadMapPresenter.PrepareFailView("API_error", loadMapViewModel);
        } catch (Exception e) { //adjust for network error
            System.out.println(e);
            System.out.println("Network Error");
            loadMapPresenter.PrepareFailView("Map_Load_Error", loadMapViewModel);
        }
    }


}
