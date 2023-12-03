package API;

import org.jxmapviewer.viewer.GeoPosition;

import java.io.IOException;

public interface LoadMapAPIAccessInterface {
    GeoPosition getCoord() throws IOException;

}
