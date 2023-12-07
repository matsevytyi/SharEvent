package VIEW_CREATOR;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * is responsible for creating fail views with purpose for
 * providing user with information
 * what went wrong and what to do in order to fix it
 * */
public class FailViewFactory {

    /**
     * Creates a fail view on the specified StackPane with the given title label text and description label text
     * All fail views lhas the same styling and are diplayed over the existing pane
     * but have different content and titles depending on its reason
     *
     * @param  pane                  the StackPane on which to create the fail view
     * @param  titleLabelText        the text to display on the title label of the fail view
     * @param  descriptionLabelText  the text to display on the description label of the fail view
     */
    public void createFailView(StackPane pane, String titleLabelText, String descriptionLabelText){

        Rectangle overlay = new Rectangle(1600, 1200);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.5);

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

    public static void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
