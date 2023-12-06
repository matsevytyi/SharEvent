
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

    /**
     * Implements core logic of the LoadMap Use Case
     *
     * Interactor tries to access the API to get user geolocation, based on his API
     * Them it adjusts map so that it will a user-friendly view
     *
     * This is achieved by:
     * a) setting appropriate map provider
     * b) setting appropriate zoom
     * c) setting map center position to user geolocation
     *      so that user will see map of his local places
     *
     * If Interactor has no issues while executed,
     * it calls presenter to prepare success view
     * and passes ready-to-use in another class (LoadMapOutputData)
     * to adhere to Single Responsibility SOLID
     *
     * If there appear any problems, Interactor calls presenter
     * to prepare fail view where
     * user gets notified about the problem and how to fix it
     *
     * @param  loadMapOutputData      the output data of the load map operation
     * @param  loadMapViewModel       the view model for the load map operation
     */
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

    /**
     * Getter for mapKit
     *
     * @return  the JXMapKit object
     */
    public JXMapKit getMapKit(){
        return mapKit;
    }

    /**
     * Setter for mapKit (for applying changes)
     *
     * @param mapKit  the JXMapKit object
     */
    public void setMapKit(JXMapKit mapKit){
        this.mapKit = mapKit;
    }

    /**
     * getter for LoadMapAPI*/
    public LoadMapAPIAccessInterface getLoadmapAPI(){
        return loadmapAPI;
    }




}
