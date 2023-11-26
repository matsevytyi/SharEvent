package VIEW;

import INTERFACE_ADAPTER.LoadMapController;
import INTERFACE_ADAPTER.LoadMapPresenter;
import INTERFACE_ADAPTER.LoadMapState;
import INTERFACE_ADAPTER.logout_adapter.LogOutController;
import INTERFACE_ADAPTER.map_adapter.LoggedInState;
import INTERFACE_ADAPTER.map_adapter.LoggedInViewModel;
import VIEW_CREATOR.LoadMapViewFactory;
import VIEW_CREATOR.LoadMapViewModel;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoadMapView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    @Getter
    private LoadMapPresenter presenter;

    @Getter
    private LoadMapController controller;

    @Getter
    private LoadMapViewModel viewModel;

   // private final LogOutController logOutController;

    @Getter
    private static StackPane pane;


    public LoadMapView(LoadMapViewModel loggedInViewModel) {
        //this.logOutController = logOutController;

        viewModel = loggedInViewModel; // here was new LoadMapViewModel();

        presenter = new LoadMapPresenter();

        pane = new StackPane();

        controller =  new LoadMapController();

        controller.execute(viewModel);

        pane = new LoadMapViewFactory().createView(pane, viewModel);

        setButtonListeners(pane, controller);
        
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

        viewFriendsButton.setOnAction(e -> {
            controller.viewFriends();
        });

        viewEventsButton.setOnAction(e -> {
            controller.viewEvents();
        });

        addEventButton.setOnAction(e -> {
            controller.addEvent();
        });

        updateEventsButton.setOnAction(e -> {
            controller.updateEvents(this);
        });
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoadMapState state = (LoadMapState) evt.getNewValue();

    }
}


