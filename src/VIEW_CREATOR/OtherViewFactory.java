package VIEW_CREATOR;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OtherViewFactory {

    public static void createOtherView(StackPane parentPane, VBox centerBox) {

            Rectangle overlay = new Rectangle(1600, 1200);
            overlay.setFill(Color.GRAY);
            overlay.setOpacity(0.5);
            overlay.setVisible(true);
            centerBox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-spacing: 10px;");
            centerBox.setAlignment(Pos.CENTER);
            centerBox.setMaxSize(600, 400);
          Button closeButton = new Button("Close");
          closeButton.setOnAction(event -> {
            // Close the view or perform any necessary actions
            parentPane.getChildren().removeAll(overlay, centerBox);
        });
        centerBox.getChildren().add(closeButton);
            parentPane.getChildren().addAll(overlay,centerBox);

    }

}
