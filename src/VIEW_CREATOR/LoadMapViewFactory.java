package VIEW_CREATOR;

import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.LinkedList;

public class LoadMapViewFactory {


    public StackPane createView(StackPane pane, LoadMapViewModel viewModel) {
        LinkedList<Button> buttons = createButtons(viewModel);

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(viewModel.getMapKit());

        pane.getChildren().add(swingNode);
        for (Button button : buttons) pane.getChildren().add(button);

        adjustButtonLocation(pane);
        FailViewFactory failViewFactory = new FailViewFactory();

        if(viewModel.getMap_Load_Error() != null) {
            //create failview
            failViewFactory.createFailView(pane, "Oops, looks like you are using wrong map", "Check your connection, ensure that your app is up-to-date and reload the app.\n If problem still appears, contact our support");
            return pane;
        }

        //TODO: create subclasses-models for these cases on LoadMapViewModel
        if(viewModel.getAPI_error() != null) {
            //create fail view
            failViewFactory.createFailView(pane, "Failed to get or process your IP address", "Check your connection, ensure that your app is up-to-date and reload the app.\nAlso you may try to find your approximate location on the map\nand then press «Update Events» button");
        }

        return pane;
    }

    private LinkedList<Button> createButtons(LoadMapViewModel viewModel) {
        //TODO: addjust buttons so that they meet the UI
        Button viewProfileButton = LoadMapButtonFactory.createViewProfileButton(LoadMapButtonFactory.class, viewModel.getViewProfileButtonImgPath(), viewModel.getViewProfileButtonStyle(), viewModel.getViewProfileButtonSize());
        Button filterEventsButton = LoadMapButtonFactory.createFilterEventsButton(LoadMapButtonFactory.class, viewModel.getFilterEventsButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button searchEventsButton = LoadMapButtonFactory.createSearchEventsButton(LoadMapButtonFactory.class, viewModel.getSearchEventsButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button viewEventsButton = LoadMapButtonFactory.createViewEventsButton(LoadMapButtonFactory.class, viewModel.getViewEventsButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button addEventButton = LoadMapButtonFactory.createAddEventButton(LoadMapButtonFactory.class, viewModel.getAddEventButtonImgPath(), viewModel.getRightMenuButtonsStyle(), viewModel.getRightMenuButtonSize());
        Button updateEventsButton = LoadMapButtonFactory.createUpdateEventsButton(viewModel.getUpdateEventsButtonName(), viewModel.getUpdateEventsButtonStyle());

        LinkedList<Button> buttons = new LinkedList<>();

        buttons.add(viewProfileButton);
        buttons.add(filterEventsButton);
        buttons.add(searchEventsButton);
        buttons.add(viewEventsButton);
        buttons.add(addEventButton);
        buttons.add(updateEventsButton);

        return buttons;
    }

    private void adjustButtonLocation(StackPane pane){

        Button viewProfileButton = (Button) pane.getChildren().get(1);
        Button filterEventsButton = (Button) pane.getChildren().get(2);
        Button searchEventsButton = (Button) pane.getChildren().get(3);
        Button viewEventsButton = (Button) pane.getChildren().get(4);
        Button addEventButton = (Button) pane.getChildren().get(5);
        Button updateEventsButton = (Button) pane.getChildren().get(6);

        StackPane.setAlignment(viewProfileButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(viewProfileButton, new Insets(0, 20, 420, 0));

        StackPane.setAlignment(filterEventsButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(filterEventsButton, new Insets(0, 20, 620, 0));

        // TODO Adjust
        StackPane.setAlignment(searchEventsButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(searchEventsButton, new Insets(0, 20, 820, 0));

        StackPane.setAlignment(viewEventsButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(viewEventsButton, new Insets(0, 20, 220, 0));

        StackPane.setAlignment(addEventButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(addEventButton, new Insets(0, 20, 20, 0));

        StackPane.setAlignment(updateEventsButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(updateEventsButton, new Insets(0, 0, 12, 0));
        updateEventsButton.setVisible(true);
    }
}
