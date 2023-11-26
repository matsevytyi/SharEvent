package data_access;

import lombok.Getter;
import org.jxmapviewer.viewer.GeoPosition;

public class LoadEventsDAO_InputData {

    //TODO: Fill this class for other queries data as well

    @Getter
    String latitude1, latitude2, longitude1, longitude2;

    public LoadEventsDAO_InputData(GeoPosition initPoint) {
        latitude1 = String.valueOf(initPoint.getLatitude() - 5./111);
        latitude2 = String.valueOf(initPoint.getLatitude() + 5./111);
        longitude1 = String.valueOf(initPoint.getLongitude() - 5./111);
        longitude2 = String.valueOf(initPoint.getLongitude() + 5./111);
    }
}
