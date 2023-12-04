package VIEW;

import API.LoadMap_API;

import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventState;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.filter.FilterController;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapController;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapPresenter;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapState;
import INTERFACE_ADAPTER.register_for_event.RegisterController;
import INTERFACE_ADAPTER.view_event.ViewEventController;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfileState;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import VIEW_CREATOR.FilterEventsViewFactory;
import VIEW_CREATOR.LoadMapViewFactory;
import VIEW_CREATOR.LoadMapViewModel;

import VIEW_CREATOR.OtherViewFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

@Getter
public class LoadMapView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private LoadMapPresenter presenter;

    private LoadMapController controller;

    private LoadMapViewModel viewModel;
    private ViewEventViewModel viewEventViewModel;
    private ViewEventController viewEventController;

    // private final LogOutController logOutController;

    private static StackPane pane;

    private final AddEventViewModel addEventViewModel;

    private final AddEventController addEventController;



    private final DeleteEventViewModel deleteEventViewModel;

    private final DeleteEventController deleteEventController;


    private final JXMapViewer mapViewer ;
    private final JXMapViewer mapViewerforViewing ;

    private GeoPosition addEvent = null;

    private final RegisterController registerController;

    private final ViewProfileViewModel viewProfileViewModel;

    private final ViewProfileController viewProfileController;

    private final FilterEventsView filterEventsView;



    public LoadMapView(LoadMapViewModel loggedInViewModel,
                       AddEventViewModel addEventViewModel,
                       AddEventController addEventController,
                       ViewEventViewModel viewEventViewModel,
                       ViewEventController viewEventController,
                       DeleteEventViewModel deleteEventViewModel,
                       DeleteEventController deleteEventController,
                       RegisterController registerController,
                       ViewProfileViewModel viewProfileViewModel,
                       ViewProfileController viewProfileController) {


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
        this.registerController = registerController;
        this.viewProfileController = viewProfileController;
        this.viewProfileViewModel = viewProfileViewModel;


        mapViewer = this.getViewModel().getMapKit().getMainMap();
        mapViewerforViewing = this.getViewModel().getMapKit().getMainMap();


        //The LOAD_EVENTS Use Case is firstly called just after launching the map and user authorisation
        controller.updateEvents(this);

        FilterEventsViewFactory filterEventsViewFactory = new FilterEventsViewFactory();
        this.filterEventsView = filterEventsViewFactory.create(this.getViewModel());

    }

    public StackPane getStackPane() {
        return pane;
    }

    public void setButtonListeners(StackPane pane, LoadMapController controller) {

        Button viewProfileButton = (Button) pane.getChildren().get(1);
        Button filterEventsButton = (Button) pane.getChildren().get(2);
        Button searchEventsButton = (Button) pane.getChildren().get(3);
        Button viewEventsButton = (Button) pane.getChildren().get(4);
        Button addEventButton = (Button) pane.getChildren().get(5);
        Button updateEventsButton = (Button) pane.getChildren().get(6);

        viewProfileButton.setOnAction(e -> {
           UserProfileView userProfileView  = new UserProfileView(viewProfileViewModel,viewProfileController);
           ViewProfileState viewProfileState = viewProfileViewModel.getState();
            viewProfileState.setUsername(viewModel.getLoggedInUser());

            viewProfileController.execute(viewProfileState.getUsername());
            userProfileView.updateProfile();
            OtherViewFactory.createOtherView(pane, userProfileView);
        });

        filterEventsButton.setOnAction(e -> {
            controller.filterEvents(filterEventsView);
        });

        searchEventsButton.setOnAction(e -> {
            controller.searchEvents(this.viewModel);
        });

        viewEventsButton.setOnAction(e -> {
            handleMapClick(false);
        });


        addEventButton.setOnAction(e -> {
            handleMapClick(true);
        });

        updateEventsButton.setOnAction(e -> {
            controller.updateEvents(this);
        });


    }


    public void handleMapClick(boolean isAdding) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choose Map Point");
        alert.setHeaderText(null);
        alert.setContentText(isAdding ? "Please choose a point on the map." : "Please choose an event you want to look at.");

        alert.showAndWait();

        CompletableFuture<GeoPosition> mapClickFuture = new CompletableFuture<>();

        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Point clickPoint = e.getPoint();
                mapClickFuture.complete(LoadMap_API.getClickedPosition(clickPoint, mapViewer));
                mapViewer.removeMouseListener(this);

                if (isAdding) {
                    handleClosedEventForAdding(mapClickFuture);
                } else {
                    handleClosedEventForViewing(mapClickFuture);
                }
            }
        });
    }


    void handleClosedEventForAdding(CompletableFuture<GeoPosition> mapClickFuture) {
        Platform.runLater(() -> {

            try {
                GeoPosition clickedPosition = mapClickFuture.get();
                addEventViewModel.setClickedPosition(clickedPosition);
                AddEventView addEventView = new AddEventView(addEventViewModel, addEventController);
                OtherViewFactory.createOtherView(pane,addEventView);
                addEventView.getEventNameInputField().setOnKeyTyped(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        AddEventState currentState = addEventViewModel.getState();
                        String text = addEventView.getEventNameInputField().getText() + event.getCharacter();
                        currentState.setEventName(text);
                        addEventViewModel.setState(currentState);
                    }
                });

                addEventView.getEventDatePicker().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        AddEventState currentState = addEventViewModel.getState();
                        currentState.setEventDate(addEventView.getEventDatePicker().getValue());
                        addEventViewModel.setState(currentState);
                    }
                });

                addEventView.getEventTimePicker().hourComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    AddEventState currentState = addEventViewModel.getState();
                    currentState.setEventTime(addEventView.getEventTimePicker().getSelectedTime());
                    addEventViewModel.setState(currentState);
                });

                addEventView.getEventTimePicker().minuteComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    AddEventState currentState = addEventViewModel.getState();
                    currentState.setEventTime(addEventView.getEventTimePicker().getSelectedTime());
                    addEventViewModel.setState(currentState);
                });

                addEventView.getEventTimePicker().amPmComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    AddEventState currentState = addEventViewModel.getState();
                    currentState.setEventTime(addEventView.getEventTimePicker().getSelectedTime());
                    addEventViewModel.setState(currentState);
                });
                addEventView.getDescriptionInputField().setOnKeyTyped(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        AddEventState currentState = addEventViewModel.getState();
                        String text = addEventView.getDescriptionInputField().getText() + event.getCharacter();
                        currentState.setEventDescription(text);
                        addEventViewModel.setState(currentState);
                    }
                });

                addEventView.getEventTypeComboBox().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        AddEventState currentState = addEventViewModel.getState();
                        currentState.setEventType(addEventView.getEventTypeComboBox().getValue());
                        addEventViewModel.setState(currentState);
                    }
                });
                addEventView.getAddEventButton().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        AddEventState currentState = addEventViewModel.getState();
                        currentState.setCreator(viewModel.getLoggedInUserObject());
                        addEventController.execute(
                                currentState.getEventName(),
                                currentState.getEventType(),
                                currentState.getEventDescription(),
                                currentState.getEventDate(),
                                currentState.getEventTime(),
                                currentState.getCreator(),
                                currentState.getEventLatitude(),
                                currentState.getEventLongitude()

                        );

                        controller.updateEvents(LoadMapView.this);
                    }


                });


            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        });
    }

    void handleClosedEventForViewing(CompletableFuture<GeoPosition> mapClickFutureForViewing) {
        Platform.runLater(() -> {

            try {

                GeoPosition clickedPosition = mapClickFutureForViewing.get();
                viewEventViewModel.setClickedPosition(clickedPosition);
                viewEventViewModel.setMapViewer(mapViewer);
                viewEventViewModel.setLoggedInUser(viewModel.getLoggedInUser());

                viewEventController.execute(viewEventViewModel.getState().getLatitude(), viewEventViewModel.getState().getLongitude(), viewEventViewModel.getState().getMapViewer());


                ViewEventView viewEventView = new ViewEventView(viewEventViewModel, deleteEventController, deleteEventViewModel, registerController);
                viewEventView.updateView();
                // Set the scene for the AddEventView stage
                OtherViewFactory.createOtherView(pane, viewEventView);
                controller.updateEvents(LoadMapView.this);

            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
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