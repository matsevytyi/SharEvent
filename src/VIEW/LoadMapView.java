package view;

import API.LoadMap_API;
import VIEW_CREATOR.LoadMapViewFactory;
import VIEW_CREATOR.LoadMapViewModel;
import VIEW_CREATOR.OtherViewFactory;
import interface_adapter.add_event.AddEventController;
import interface_adapter.add_event.AddEventViewModel;
import interface_adapter.load_map.LoadMapController;
import interface_adapter.load_map.LoadMapPresenter;
import interface_adapter.view_event.ViewEventController;
import interface_adapter.view_event.ViewEventViewModel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LoadMapView {

    @Getter
    private LoadMapPresenter presenter;

    @Getter
    private LoadMapController controller;

    @Getter
    private LoadMapViewModel viewModel;
    @Getter
    private ViewEventViewModel viewEventViewModel;
    @Getter
    private ViewEventController viewEventController;

    @Getter
    private static StackPane pane;

    private final AddEventViewModel addEventViewModel;

    private final AddEventController addEventController;

    private final JXMapViewer mapViewer ;


    private GeoPosition addEvent = null;

    private CompletableFuture<GeoPosition> mapClickFuture;

    public LoadMapView(AddEventViewModel addEventViewModel, AddEventController addEventController, ViewEventViewModel viewEventViewModel, ViewEventController viewEventController) {

        viewModel = new LoadMapViewModel();

        presenter = new LoadMapPresenter();

        pane = new StackPane();

        controller =  new LoadMapController();

        controller.execute(viewModel);

        pane = new LoadMapViewFactory().createView(pane, viewModel);
        setButtonListeners(pane, controller);


        this.addEventViewModel = addEventViewModel;
        this.addEventController = addEventController;
        this.viewEventViewModel = viewEventViewModel;
        this.viewEventController = viewEventController;



        mapViewer = this.getViewModel().getMapKit().getMainMap();
        mapClickFuture = new CompletableFuture<>();

        //The LOAD_EVENTS Use Case is firstly called just after launching the map and user authorisation
        controller.updateEvents(this);

    }

    public StackPane getStackPane() {
        return pane;
    }

    private void setButtonListeners(StackPane pane, LoadMapController controller) {

        Button viewProfileButton = (Button) pane.getChildren().get(1);
        Button filterEventsButton = (Button) pane.getChildren().get(2);
        Button viewFriendsButton = (Button) pane.getChildren().get(3);
        Button viewEventsButton = (Button) pane.getChildren().get(4);
        Button addEventButton = (Button) pane.getChildren().get(5);
        Button updateEventsButton = (Button) pane.getChildren().get(6);

        viewProfileButton.setOnAction(e -> {
            controller.viewProfile();
        });

        filterEventsButton.setOnAction(e -> {
            controller.filterEvents();
        });

//        viewFriendsButton.setOnAction(e -> {
//            controller.viewFriends();
//        });

        viewEventsButton.setOnAction(e -> {

        });


        addEventButton.setOnAction(e -> {
            handleMapClick();
        });

        updateEventsButton.setOnAction(e -> {
            controller.updateEvents(this);
        });






    }

    private void handleMapClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choose Map Point");
        alert.setHeaderText(null);
        alert.setContentText("Please choose a point on the map.");

        // Показати спливаюче вікно та зачекати його закриття
        alert.showAndWait();

        // Оновлено: Встановлення нового об'єкту CompletableFuture для нового натискання на карті
        CompletableFuture<GeoPosition> mapClickFuture = new CompletableFuture<>();

        // Оновлено: Встановлення обробника подій для нового натискання на карті
        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Point clickPoint = e.getPoint();

                // Оновлено: Встановлення значення у CompletableFuture при новому натисканні на карті
                mapClickFuture.complete(LoadMap_API.getClickedPosition(clickPoint, mapViewer));
                mapViewer.removeMouseListener(this);

                // Оновлено: Виклик методу для обробки координат після закриття вікна
                handleClosedEvent(mapClickFuture);
            }
        });
    }

    // Оновлено: Метод для обробки координат після закриття вікна
    private void handleClosedEvent(CompletableFuture<GeoPosition> mapClickFuture) {
        Platform.runLater(() -> {
            // Використовуйте значення mapClickFuture як потрібно
            try {
                GeoPosition clickedPosition = mapClickFuture.get();

                addEventViewModel.setClickedPosition(clickedPosition);


                AddEventView addEventView = new AddEventView(addEventViewModel, addEventController);

                // Set the scene for the AddEventView stage
                Scene scene = new Scene(addEventView, 500, 500);

                // Create a new stage for AddEventView
                Stage addEventStage = new Stage();
                addEventStage.initModality(Modality.APPLICATION_MODAL);
                addEventStage.setScene(scene);
                addEventStage.setX(1600);
                addEventStage.setY(1200);

                // Show the AddEventView stage
                addEventStage.show();

//                OtherViewFactory otherViewFactory = new OtherViewFactory();
//              otherViewFactory.createOtherView(pane, new AddEventView(addEventViewModel, addEventController));

            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace(); // Обробка відповідно до вашого випадку
            }
        });
    }
}


