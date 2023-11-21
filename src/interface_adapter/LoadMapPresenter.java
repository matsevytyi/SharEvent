package interface_adapter;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

import java.util.HashSet;
import java.util.Set;

import Entities.Temporary_entites.Event;

import API_calls.getCoordByIP_API_call;

public class LoadMapPresenter {

    private static JXMapKit mapKit;
    private GeoPosition initialGeo;

    public LoadMapPresenter() {
        mapKit = new JXMapKit();
        initialGeo = getCoordByIP_API_call.getCoord(); //TODO: surround with try_catch for handling exceptions
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
