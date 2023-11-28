package API;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadMap_API implements LoadMapAPIAccessInterface {

    final private String apiUrl;
    final private String apiKey;

    public LoadMap_API() {
        //I didn't place the API key in constructor call since its used only once and is a sensitive information
        this.apiKey = "21c8d093151b434dad0212bbb482d7e6";
        this.apiUrl = "https://api.ipgeolocation.io/ipgeo?apiKey=" + apiKey;
    }


    public GeoPosition getCoord() throws IOException {
        String longitude = "0";
        String latitude = "0";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the response as JSON
            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();

            // Get latitude and longitude values
            latitude = jsonObject.get("latitude").getAsString();
            longitude = jsonObject.get("longitude").getAsString();


            //Checkout the range of latitude and longitude to load the events, can be removed in further
            double delta = 5./111;

            double maxLatitude = Double.parseDouble(latitude) + delta;
            double minLatitude = Double.parseDouble(latitude) - delta;
            double maxLongitude = Double.parseDouble(longitude) + delta;
            double minLongitude = Double.parseDouble(longitude) - delta;

            // Output latitude and longitude
            System.out.println("Latitude: " + latitude);
            System.out.println("Max Latitude: " + maxLatitude);
            System.out.println("Min Latitude: " + minLatitude);
            System.out.println("\n\nLongitude: " + longitude);
            System.out.println("Max Longitude: " + maxLongitude);
            System.out.println("Min Longitude: " + minLongitude);

        } else {
            System.out.println("HTTP GET request failed: " + responseCode);
        }
        connection.disconnect();

        return new GeoPosition(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    public static GeoPosition getClickedPosition(Point clickPoint, JXMapViewer mapViewer) {
        double latitude = mapViewer.convertPointToGeoPosition(clickPoint).getLatitude();
        double longitude = mapViewer.convertPointToGeoPosition(clickPoint).getLongitude();
        return new GeoPosition(latitude, longitude);
    }

}

