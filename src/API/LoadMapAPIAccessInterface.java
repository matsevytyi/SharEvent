package API;

import org.jxmapviewer.viewer.GeoPosition;

import java.io.IOException;

/**
 * Interface for accessing the IPGeolocation API.
 * Helps to adhere to Open-Closed SOLID principle
 * */

public interface LoadMapAPIAccessInterface {
    GeoPosition getCoord() throws IOException;

}
