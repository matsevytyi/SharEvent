package VIEW_CREATOR;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OtherViewFactory {
    /**
     * Creates an overlay view with the specified content.
     * @param parentPane The parent pane to which the overlay and content will be added.
     * @param centerBox  The content box to be displayed in the center of the overlay.
     */
    public static void createOtherView(StackPane parentPane, VBox centerBox) {

            Rectangle overlay = new Rectangle(1600, 1200);
            overlay.setFill(Color.GRAY);
            overlay.setOpacity(0.5);
            overlay.setVisible(true);
            centerBox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-spacing: 10px;");
            centerBox.setAlignment(Pos.CENTER);
            centerBox.setMaxSize(600, 400);
             Button closeButton = new Button("Close");
          closeButton.setStyle("-fx-text-fill: #3B59B6; -fx-font-weight: bold; -fx-font-size: 16;-fx-padding: 10;");
          closeButton.setOnAction(event -> {
            parentPane.getChildren().removeAll(overlay, centerBox);

        });
        centerBox.getChildren().add(closeButton);
            parentPane.getChildren().addAll(overlay,centerBox);

    }

}
