
package  view;

import Entities.ButtonFactory;
import interface_adapter.LoadMapPresenter;
import interface_adapter.LoadMapController;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;


public class LoadMapView {

    LoadMapPresenter presenter;
    LoadMapController controller;

    private static JXMapKit mapKit;

    private Button updateEventsButton;

    private Button filterEventsButton;

    private Button viewFriendsButton;

    private Button viewEventsButton;

    private Button addEventButton;
    private Button viewProfileButton;

    JXMapViewer mapViewer;

    private static StackPane pane;


    public LoadMapView() {
        // Create a JavaFX SwingNode to host the map component

        presenter = new LoadMapPresenter();
        mapKit = presenter.getMapKit();
        mapViewer = mapKit.getMainMap();
        controller = new LoadMapController(mapViewer);

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(mapKit);

        addButtons();
        adjustButtonLocation();
        setButtonListeners();

        //TODO: adjusting should be done on the LoadMapViewModel
        pane = new StackPane();
        pane.getChildren().addAll(swingNode, viewProfileButton, filterEventsButton, viewFriendsButton, viewEventsButton, addEventButton, updateEventsButton);

    }

    private void addButtons() {
        //TODO: addjust buttons so that they meet the UI
        viewProfileButton = ButtonFactory.createViewProfileButton(ButtonFactory.class, mapKit, "/UI_elements/DefaultProfilePicture.png", 200);
        filterEventsButton = ButtonFactory.createFilterEventsButton(ButtonFactory.class, mapKit, "/UI_elements/MapFiltersButton.png", 170);
        viewFriendsButton = ButtonFactory.createViewFriendsButton(ButtonFactory.class, mapKit, "/UI_elements/ViewFriendsButtonBg.png", 170);
        viewEventsButton = ButtonFactory.createViewEventsButton(ButtonFactory.class, mapKit, "/UI_elements/ViewMyEventsButtonBg.png", 170);
        addEventButton = ButtonFactory.createAddEventButton(ButtonFactory.class, mapKit, "/UI_elements/AddEventButtonBg.png", 170);
        updateEventsButton = ButtonFactory.createUpdateEventsButton(mapKit);
    }

    private void adjustButtonLocation(){
        StackPane.setAlignment(viewProfileButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(viewProfileButton, new Insets(0, 20, 820, 0));

        StackPane.setAlignment(filterEventsButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(filterEventsButton, new Insets(0, 20, 620, 0));

        StackPane.setAlignment(viewFriendsButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(viewFriendsButton, new Insets(0, 20, 420, 0));

        StackPane.setAlignment(viewEventsButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(viewEventsButton, new Insets(0, 20, 220, 0));

        StackPane.setAlignment(addEventButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(addEventButton, new Insets(0, 20, 20, 0));

        StackPane.setAlignment(updateEventsButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(updateEventsButton, new Insets(0, 0, 12, 0));
    }

    public static StackPane getStackPane() {
        return pane;
    }

    private void setButtonListeners(){
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
            controller.updateEvents();
        });
    }



}


