
package com.example.mapsdemo;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Map Embed Example");

        // Create a JavaFX SwingNode to host the map component
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        primaryStage.setScene(new Scene(pane, 800, 600));
        primaryStage.show();
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {

            double latitude = 43.651070;
            double longitude = -79.347015;

            JXMapKit mapKit = new JXMapKit();
            mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);

            mapKit.setZoom(4);
            mapKit.setAddressLocation(new GeoPosition(latitude, longitude));

            // Create a list of waypoints (markers)
            Set<Waypoint> waypoints = new HashSet<>();
            DefaultWaypoint restaurantMarker = new DefaultWaypoint(44.651575, -78.345010);
            waypoints.add(restaurantMarker);
            waypoints.add(new DefaultWaypoint(43.651070, -79.347015));

            // Create a WaypointPainter to display the markers
            WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
            waypointPainter.setWaypoints(waypoints);

            // Set the WaypointPainter for the map
            JXMapViewer mapViewer = mapKit.getMainMap();
            mapViewer.setOverlayPainter(waypointPainter);

            // Create a JavaFX SwingNode to host the map component
            swingNode.setContent(mapKit);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}


