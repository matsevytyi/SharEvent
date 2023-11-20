
package  view;

import interface_adapter.LoadMapPresenter;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.awt.geom.Point2D;




public class LoadMapView {

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
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);

        // Create a button
        //TODO: addjust buttons so that they meet the UI
        viewProfileButton = ButtonFactory.createViewProfileButton(ButtonFactory.class, mapKit, "/UI_elements/DefaultProfilePicture.png", 200);
        filterEventsButton = ButtonFactory.createFilterEventsButton(ButtonFactory.class, mapKit, "/UI_elements/MapFiltersButton.png", 170);
        viewFriendsButton = ButtonFactory.createViewFriendsButton(ButtonFactory.class, mapKit, "/UI_elements/ViewFriendsButtonBg.png", 170);
        viewEventsButton = ButtonFactory.createViewEventsButton(ButtonFactory.class, mapKit, "/UI_elements/ViewMyEventsButtonBg.png", 170);
        addEventButton = ButtonFactory.createAddEventButton(ButtonFactory.class, mapKit, "/UI_elements/AddEventButtonBg.png", 170);
        updateEventsButton = ButtonFactory.createUpdateEventsButton(mapKit);



        //TODO: find out how to add buttons using array
        //ArrayList<Button> MainViewButtons = new ArrayList<>();
        //MainViewButtons.add(updateEventsButton);
        //MainViewButtons.add(createViewEventsButton);

        //TODO: adjusting should be done on the LoadMapViewModel
        pane = new StackPane();
        pane.getChildren().addAll(swingNode, viewProfileButton, filterEventsButton, viewFriendsButton, viewEventsButton, addEventButton, updateEventsButton);


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

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {

            mapKit = LoadMapPresenter.getMapKit();

            mapViewer = mapKit.getMainMap();

            //TODO: mapViewer.setOverlayPainter(waypointPainter);

            mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    Point clickPoint = e.getPoint();

                   //TODO: to convert the click coordinates to a GeoPosition by a method from a presenter
                }
            });

            mapViewer.addPropertyChangeListener("centerPosition", evt -> {
                GeoPosition centerPosition = (GeoPosition) evt.getNewValue();
                GeoPosition addressLocation = mapKit.getAddressLocation();
                updateEventsButton.setVisible(!centerPosition.equals(addressLocation));
            });
            swingNode.setContent(mapKit);
        });
    }
}


