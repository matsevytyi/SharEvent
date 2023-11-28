package USE_CASE.filter;

import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;

public class FilterInputData {

    final private String typeInput;

    final private GeoPosition geoPosition;

    public FilterInputData(String typeInput, GeoPosition geoPosition) {
        this.typeInput = typeInput;
        this.geoPosition = geoPosition;
    }

    String getTypeInput() {
        return this.typeInput;
    }

    GeoPosition getGeoPosition(){return this.geoPosition;}
}
