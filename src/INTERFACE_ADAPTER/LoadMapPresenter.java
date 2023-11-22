package INTERFACE_ADAPTER;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

import java.io.IOException;

import API.LoadMap_API;

public class LoadMapPresenter {

    private static JXMapKit mapKit;
    private GeoPosition initialGeo;



    public LoadMapPresenter() {
        mapKit = new JXMapKit();
        try {
            initialGeo = LoadMap_API.getCoord();
        } catch (IOException e) {
            System.out.println(e);
            //TODO: PREPARE FAILED VIEW AND PASS IT TO THE LOAD MAP INTERACTOR
        }
        PrepareSuccesView();
    }

    public GeoPosition getInitialGeo() {
        return initialGeo;
    }



    public void PrepareSuccesView(){
        mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        mapKit.setZoom(4);

        //mapKit.setAddressLocation(new GeoPosition(43.651575, -79.345010));
        mapKit.setAddressLocation(initialGeo);
    }

    public void PrepareFailView(String reason){
        if(reason == "Map_Load_Error") {
            //TODO: SHOW BLOCK ABOVE THE STACKPANE WITH ERROR
        }
        else if (reason == "geoIP_API_error"){
            //TODO: SHOW BLOCK ABOVE THE STACKPANE WITH ERROR + buttons
        }
    }



    public static JXMapKit getMapKit() {
        return mapKit;
    }


}
