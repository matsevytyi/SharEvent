
package com.example.mapsdemo;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginViewModel;
import interface_adapter.map_adapter.LoggedInViewModel;
import interface_adapter.signup_adapter.SignUpViewModel;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.stage.Stage;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.*;
import view.LoggedInView;
import view.LoginView;
import view.SignUpView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class SharEvent extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Map Embed Example");
        JFrame application = new JFrame("SharEvent");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        LoginViewModel loginViewModel = new LoginViewModel();
        SignUpViewModel signupViewModel = new SignUpViewModel();
        LoggedInViewModel mapViewModel = new LoggedInViewModel();

        SignUpView signupView = SignUpUseCaseFactory.SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, mapViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, mapViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

       /* SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        primaryStage.setScene(new Scene(pane, 800, 600));
        primaryStage.show();*/
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


