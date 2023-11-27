package VIEW_CREATOR;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OtherViewFactory {

    public void createOtherView(StackPane parentPane, StackPane view) {
        Platform.runLater(() -> {
            // Create your custom view
            Rectangle overlay = new Rectangle(1600, 1200);
            overlay.setFill(Color.GRAY);
            overlay.setOpacity(0.5);

            // Set the initial visibility of the overlay
            overlay.setVisible(true);

            // Add the rectangle and ViewBase to the parentPane
            parentPane.getChildren().addAll(overlay, new VBox(view));
        });
    }

    // Example usage:
    // OtherViewFactory otherViewFactory = new OtherViewFactory();
    // otherViewFactory.createOtherView(parentPane, new AddEventView(addEventViewModel, addEventController));
    // ...
}
