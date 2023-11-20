package interface_adapter;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

public class LoadMapPresenter {

    private static JXMapKit mapKit;

    LoadMapPresenter() {

        mapKit = new JXMapKit();
        mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        mapKit.setZoom(4);

        mapKit.setAddressLocation(new GeoPosition(43.651575, -79.345010));
        //TODO: before the release uncommwnt the next line and delete the upper line
        //mapKit.setAddressLocation(GetCoordByIP_API.getCoord());
    }

    public static JXMapKit getMapKit() {
        return mapKit;
    }

}
