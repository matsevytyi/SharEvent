package API_calls;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jxmapviewer.viewer.GeoPosition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getCoordByIP_API_call {
    public static GeoPosition getCoord() {
        String apiKey = "21c8d093151b434dad0212bbb482d7e6";
        String apiUrl = "https://api.ipgeolocation.io/ipgeo?apiKey=" + apiKey;

        String longitude = "0";
        String latitude = "0";

        try {
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

                double delta = 5./111;

                Double maxLatitude = Double.parseDouble(latitude) + delta;
                Double minLatitude = Double.parseDouble(latitude) - delta;
                Double maxLongitude = Double.parseDouble(longitude) + delta;
                Double minLongitude = Double.parseDouble(longitude) - delta;

                // Output latitude and longitude
                System.out.println("Latitude: " + latitude);
                System.out.println("Max Latitude: " + maxLatitude);
                System.out.println("Min Latitude: " + minLatitude);
                System.out.println("\n\nLongitude: " + longitude);
                System.out.println("Max Longitude: " + maxLongitude);
                System.out.println("Min Longitude: " + minLongitude);

                // You can use latitude and longitude variables as needed
            } else {
                System.out.println("HTTP GET request failed: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GeoPosition(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

}

