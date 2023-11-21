
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

import lombok.Getter;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import use_case.LoadEventsInputBoundary;
import use_case.LoadEventsInteractor;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


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

    @Getter
    private static StackPane pane;


    public LoadMapView() {

        presenter = new LoadMapPresenter();
        mapKit = presenter.getMapKit();
        mapViewer = mapKit.getMainMap();
        controller = new LoadMapController(mapViewer, this);

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(mapKit);

        addButtons();
        adjustButtonLocation();
        setButtonListeners();

        LinkedList<Button> buttons = new LinkedList<>();

        buttons.add(viewProfileButton);
        buttons.add(filterEventsButton);
        buttons.add(viewFriendsButton);
        buttons.add(viewEventsButton);
        buttons.add(addEventButton);
        buttons.add(updateEventsButton);

        //TODO: adjusting should be done on the LoadMapViewModel
        pane = new StackPane();
        pane.getChildren().add(swingNode);
        for (Button button : buttons) pane.getChildren().add(button);

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
        updateEventsButton.setVisible(true);
    }

    public LoadMapPresenter getPresenter() {
        return presenter;
    }

    public LoadMapController getController() {
        return controller;
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


