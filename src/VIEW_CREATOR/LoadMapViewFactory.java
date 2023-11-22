package VIEW_CREATOR;

import INTERFACE_ADAPTER.LoadMapController;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class LoadMapViewFactory {


    public StackPane createView(StackPane pane, LoadMapViewModel viewModel, LoadMapController controller) {
        LinkedList<Button> buttons = createButtons(viewModel);

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(viewModel.getMapKit());

        pane.getChildren().add(swingNode);
        for (Button button : buttons) pane.getChildren().add(button);

        adjustButtonLocation(pane);
        setButtonListeners(pane, controller);

        if(viewModel.getMap_Load_Error() == null) {
            //create failview
            createFailView(pane, "Oops, looks like you are using wrong map", "Check your connection, ensure that your app is up-to-date and reload the app.\n If problem still appears, contact our support");
            return pane;
        }

        //TODO: remove back to API_error
        //TODO: create subclasses-models for these cases on LoadMapViewModel
        if(viewModel.getAPI_error() != null) {
            //create fail view
            createFailView(pane, "Failed to get or process your IP address", "Check your connection, ensure that your app is up-to-date and reload the app.\nAlso you may try to find your approximate location on the map\nand then press «Update Events» button");
        }

        return pane;
    }

    private LinkedList<Button> createButtons(LoadMapViewModel viewModel) {
        //TODO: addjust buttons so that they meet the UI
        Button viewProfileButton = LoadMapButtonFactory.createViewProfileButton(LoadMapButtonFactory.class, viewModel.getViewProfileButtonImgPath(), viewModel.getViewProfileButtonStyle(), viewModel.getViewProfileButtonSize());
        Button filterEventsButton = LoadMapButtonFactory.createFilterEventsButton(LoadMapButtonFactory.class, viewModel.getFilterEventsButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button viewFriendsButton = LoadMapButtonFactory.createViewFriendsButton(LoadMapButtonFactory.class, viewModel.getViewFriendsButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button viewEventsButton = LoadMapButtonFactory.createViewEventsButton(LoadMapButtonFactory.class, viewModel.getViewEventsButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button addEventButton = LoadMapButtonFactory.createAddEventButton(LoadMapButtonFactory.class, viewModel.getAddEventButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button updateEventsButton = LoadMapButtonFactory.createUpdateEventsButton(viewModel.getUpdateEventsButtonName(), viewModel.getUpdateEventsButtonStyle());

        LinkedList<Button> buttons = new LinkedList<>();

        buttons.add(viewProfileButton);
        buttons.add(filterEventsButton);
        buttons.add(viewFriendsButton);
        buttons.add(viewEventsButton);
        buttons.add(addEventButton);
        buttons.add(updateEventsButton);

        return buttons;
    }

    private void adjustButtonLocation(StackPane pane){

        Button viewProfileButton = (Button) pane.getChildren().get(1);
        Button filterEventsButton = (Button) pane.getChildren().get(2);
        Button viewFriendsButton = (Button) pane.getChildren().get(3);
        Button viewEventsButton = (Button) pane.getChildren().get(4);
        Button addEventButton = (Button) pane.getChildren().get(5);
        Button updateEventsButton = (Button) pane.getChildren().get(6);

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

    public void createFailView(StackPane pane, String titleLabelText, String descriptionLabelText){

        Rectangle overlay = new Rectangle(1600, 1200);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.5);

        //TODO: adjust styling for labels and buttons

        Label titleLabel = new Label(titleLabelText);
        Label descriptionLabel = new Label(descriptionLabelText);
        Button exitButton = new Button("OK");

        titleLabel.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;");
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
        pane.getChildren().addAll(overlay, vbox);

        // Handle button click to remove the overlay and VBox
        exitButton.setOnAction(event -> pane.getChildren().removeAll(overlay, vbox));
    }
}
