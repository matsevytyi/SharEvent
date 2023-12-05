
package USE_CASE.loadmap;

import API.LoadMapAPIAccessInterface;
import API.LoadMap_API;

import INTERFACE_ADAPTER.loadmap_adapter.LoadMapPresenter;
import VIEW_CREATOR.LoadMapViewModel;

import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

import java.io.IOException;

public class LoadMapInteractor implements LoadMapInputBoundary {

    public LoadMapInteractor() {
    }

    JXMapKit mapKit;

    LoadMapAPIAccessInterface loadmapAPI;
    @Override
    public void execute(LoadMapOutputData loadMapOutputData, LoadMapViewModel loadMapViewModel){

        LoadMapOutputBoundary loadMapPresenter = new LoadMapPresenter();

        mapKit = loadMapOutputData.getMapKit();
        mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        mapKit.setZoom(4);
        try {
            loadmapAPI = new LoadMap_API();
            GeoPosition initialGeo = loadmapAPI.getCoord();
            System.out.println(initialGeo);
            mapKit.setAddressLocation(initialGeo);
            loadMapPresenter.PrepareSuccessView(new LoadMapInputData(mapKit), loadMapViewModel);
        } catch (IOException e) {
            loadMapPresenter.PrepareFailView("API_error", loadMapViewModel);
        }
    }

    public JXMapKit getMapKit(){
        return mapKit;
    }

    public void setMapKit(JXMapKit mapKit){
        this.mapKit = mapKit;
    }

    public LoadMapAPIAccessInterface getLoadmapAPI(){
        return loadmapAPI;
    }




}
