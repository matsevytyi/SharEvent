
package USE_CASE.loadmap;

import API.LoadMapAPIAccessInterface;
import API.LoadMap_API;

import INTERFACE_ADAPTER.loadmap_adapter.LoadMapInputData;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapOutputData;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapPresenter;
import USE_CASE.loadevents.LoadEventsInputBoundary;
import USE_CASE.loadmap.LoadMapOutputBoundary;
import VIEW_CREATOR.LoadMapViewModel;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

import java.io.IOException;

public class LoadMapInteractor implements LoadMapInputBoundary {

    public LoadMapInteractor() {
    }
    @Override
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel){

        LoadMapOutputBoundary loadMapPresenter = new LoadMapPresenter();

        JXMapKit mapKit = loadMapOutputData.getMapKit();
        mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        mapKit.setZoom(4);
        try {
            LoadMapAPIAccessInterface loadmapAPI = new LoadMap_API();
            GeoPosition initialGeo = loadmapAPI.getCoord();
            mapKit.setAddressLocation(initialGeo);
            loadMapPresenter.PrepareSuccessView(new LoadMapInputData(mapKit), loadMapViewModel);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("API Error");
            loadMapPresenter.PrepareFailView("API_error", loadMapViewModel);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Network Error");
            loadMapPresenter.PrepareFailView("Map_Load_Error", loadMapViewModel);
        }
    }


}
