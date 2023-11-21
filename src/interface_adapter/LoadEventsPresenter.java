package interface_adapter;

import Entities.Temporary_entites.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;
import use_case.LoadEventsInteractor;
import view.LoadEventsView;
import view.LoadMapView;

import java.util.Set;

public class LoadEventsPresenter {

    private LoadEventsInteractor loadEventsInteractor;
    private LoadEventsView loadEventsView;

    @Getter
    private static JXMapKit mapKit;
    private GeoPosition initialGeo;
    private LoadMapView loadMapView;

    public LoadEventsPresenter(LoadMapView loadMapViewResult, LoadEventsView loadEventsViewResult) {
        loadMapView = loadMapViewResult;
        mapKit = loadMapView.getPresenter().getMapKit();
        initialGeo = loadMapView.getPresenter().getInitialGeo();
        loadEventsInteractor = new LoadEventsInteractor(initialGeo);
        loadEventsView = loadEventsViewResult;
    }
    public boolean PrepareSuccesView(Set<Event> localEvents) {

        WaypointPainter<Event> eventPainter = new WaypointPainter<>();
        eventPainter.setWaypoints(localEvents);

        mapKit.getMainMap().setOverlayPainter(eventPainter);

        return true;
    }

    public void PrepareFailView(String reason){
        if(reason == "No_events") {
            StackPane initialPane = loadEventsView.getInitialPane();
            Rectangle overlay = new Rectangle(1600, 1200);
            overlay.setFill(Color.BLACK);
            overlay.setOpacity(0.5);

            //TODO: adjust styling for labels and buttons

            Label titleLabel = new Label("No events nearby, try another location");
            Label descriptionLabel = new Label("Move to another location and click \"Update Events\" button");
            Button exitButton = new Button("OK");

            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;");
            descriptionLabel.setStyle("-fx-font-size: 10px; -fx-font-family: 'Arial'; -fx-font-style: oblique; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;");
            exitButton.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-background-color: rgba(0,0,0,0); -fx-background-radius: 30; -fx-border-color: BLACK; -fx-border-width: 2px");

            VBox vbox = new VBox(titleLabel, descriptionLabel, exitButton);
            vbox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-spacing: 10px;");
            vbox.setAlignment(Pos.CENTER);
            //space at the bottom
            VBox.setMargin(exitButton, new Insets(0, 0, 10, 0)); // bottom margin//empty Region for alignment

            vbox.setMaxSize(400, 300);

            // Set the initial visibility of the overlay and VBox
            overlay.setVisible(true);
            vbox.setVisible(true);

            // Add the rectangle and VBox to the StackPane
            initialPane.getChildren().addAll(overlay, vbox);

            // Handle button click to remove the overlay and VBox
            exitButton.setOnAction(event -> {
                initialPane.getChildren().removeAll(overlay, vbox);
            });
        }
        else if (reason == "Database_error"){
            StackPane initialPane = loadEventsView.getInitialPane();
            Rectangle overlay = new Rectangle(1600, 1200);
            overlay.setFill(Color.BLACK);
            overlay.setOpacity(0.5);

            //TODO: adjust styling for labels and buttons

            Label titleLabel = new Label("Error connecting to the database");
            Label descriptionLabel = new Label("Check your connection and if your app is up to date");
            Button exitButton = new Button("OK");
            Button retryButton = new Button("Try Again");

            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;");
            descriptionLabel.setStyle("-fx-font-size: 10px; -fx-font-family: 'Arial'; -fx-font-style: oblique; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;");
            exitButton.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-background-color: rgba(0,0,0,0); -fx-background-radius: 30; -fx-border-color: BLACK; -fx-border-width: 2px");
            retryButton.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-background-color: rgba(0,0,0,0); -fx-background-radius: 30; -fx-border-color: BLACK; -fx-border-width: 2px");

            VBox vbox = new VBox(titleLabel, descriptionLabel, exitButton, retryButton);
            vbox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-spacing: 10px;");
            vbox.setAlignment(Pos.CENTER);
            //space at the bottom
            VBox.setMargin(exitButton, new Insets(0, 0, 10, 0)); // bottom margin//empty Region for alignment

            vbox.setMaxSize(400, 300);

            // Set the initial visibility of the overlay and VBox
            overlay.setVisible(true);
            vbox.setVisible(true);

            // Add the rectangle and VBox to the StackPane
            initialPane.getChildren().addAll(overlay, vbox);

            // Handle button click to remove the overlay and VBox
            exitButton.setOnAction(event -> {
                initialPane.getChildren().removeAll(overlay, vbox);
            });

            retryButton.setOnAction(event -> {
                System.out.println("Retry");
                //TODO: tries to connect to the database one more time
            });
        }
    }
}
