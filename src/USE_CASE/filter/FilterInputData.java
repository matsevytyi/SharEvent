package USE_CASE.filter;

import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;

public class FilterInputData {

    final private String typeInput;

    public FilterInputData(String typeInput) {
        this.typeInput = typeInput;
    }

    String getTypeInput() {
        return this.typeInput;
    }

}
