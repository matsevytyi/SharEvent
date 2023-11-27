package VIEW;

import API.LoadMap_API;


import INTERFACE_ADAPTER.LoadMapState;

import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.load_map.LoadMapController;
import INTERFACE_ADAPTER.load_map.LoadMapPresenter;
import INTERFACE_ADAPTER.map_adapter.LoggedInViewModel;
import INTERFACE_ADAPTER.view_event.ViewEventController;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import VIEW_CREATOR.LoadMapViewFactory;
import VIEW_CREATOR.LoadMapViewModel;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class LoadMapView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
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

    // private final LogOutController logOutController;

    @Getter
    private static StackPane pane;

    private final AddEventViewModel addEventViewModel;

    private final AddEventController addEventController;



    private final DeleteEventViewModel deleteEventViewModel;

    private final DeleteEventController deleteEventController;


    private final JXMapViewer mapViewer ;


    private GeoPosition addEvent = null;

    private CompletableFuture<GeoPosition> mapClickFuture;

    public LoadMapView(LoadMapViewModel loggedInViewModel, AddEventViewModel addEventViewModel, AddEventController addEventController, ViewEventViewModel viewEventViewModel, ViewEventController viewEventController, DeleteEventViewModel deleteEventViewModel, DeleteEventController deleteEventController) {


        viewModel = loggedInViewModel; // here was new LoadMapViewModel();
        this.deleteEventViewModel = deleteEventViewModel;
        this.deleteEventController = deleteEventController;

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
        //  Button viewFriendsButton = (Button) pane.getChildren().get(3);
        Button viewEventsButton = (Button) pane.getChildren().get(4);
        Button addEventButton = (Button) pane.getChildren().get(5);
        Button updateEventsButton = (Button) pane.getChildren().get(6);
        //Button logOut = (Button) pane.getChildren().get(7);

//        logOut.setOnAction(e -> {
//            logOutController.execute();
//        });

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
            handleMapClickForViewing();
        });


        addEventButton.setOnAction(e -> {
            handleMapClickForAdding();
        });

        updateEventsButton.setOnAction(e -> {
            controller.updateEvents(this);
        });


    }





    private void handleMapClickForAdding() {
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
                handleClosedEventForAdding(mapClickFuture);
            }
        });
    }


    private void handleClosedEventForAdding(CompletableFuture<GeoPosition> mapClickFuture) {
        Platform.runLater(() -> {

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

    @SneakyThrows
    private void handleMapClickForViewing() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choose Map Point");
        alert.setHeaderText(null);
        alert.setContentText("Please choose an event you want to look at.");

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



            }
        });
        handleClosedEventForViewing(mapClickFuture);
    }

    private void handleClosedEventForViewing(CompletableFuture<GeoPosition> mapClickFuture) {
        Platform.runLater(() -> {

            try {

                GeoPosition clickedPosition = mapClickFuture.get();
                viewEventViewModel.setClickedPosition(clickedPosition);
                viewEventViewModel.setMapViewer(mapViewer);

                viewEventController.execute(viewEventViewModel.getState().getLatitude(), viewEventViewModel.getState().getLongitude(), viewEventViewModel.getState().getMapViewer());


                ViewEventView viewEventView = new ViewEventView(viewEventViewModel, deleteEventController, deleteEventViewModel);
                viewEventView.updateView();
                // Set the scene for the AddEventView stage
                Scene scene = new Scene(viewEventView, 500, 500);

                // Create a new stage for AddEventView
                Stage viewEventStage = new Stage();
                viewEventStage.initModality(Modality.APPLICATION_MODAL);
                viewEventStage.setScene(scene);
                viewEventStage.setX(1600);
                viewEventStage.setY(1200);

                // Show the AddEventView stage
                viewEventStage.show();

//                OtherViewFactory otherViewFactory = new OtherViewFactory();
//              otherViewFactory.createOtherView(pane, new AddEventView(addEventViewModel, addEventController));
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace(); // Обробка відповідно до вашого випадку
            }

        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoadMapState state = (LoadMapState) evt.getNewValue();

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }
}