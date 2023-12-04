package VIEW;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OtherViewFactoryTest {

    private StackPane parentPane;
    private VBox centerBox;

    @BeforeAll
    public static void setUpToolkit() {
        new JFXPanel();
    }

    @Test
    public void testCreateOtherView() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            parentPane = new StackPane();
            centerBox = new VBox();

            Rectangle overlay = new Rectangle(1600, 1200);
            overlay.setFill(Color.GRAY);
            overlay.setOpacity(0.5);
            overlay.setVisible(true);
            centerBox.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-spacing: 10px;");
            centerBox.getChildren().add(new Button("Close"));

            parentPane.getChildren().addAll(overlay, centerBox);

            Scene scene = new Scene(parentPane, 800, 600);
            stage.setScene(scene);
            stage.show();

            assertTrue(parentPane.getChildren().contains(overlay));
            assertTrue(parentPane.getChildren().contains(centerBox));

            Button closeButton = (Button) centerBox.getChildren().get(0);
            closeButton.fire();

            assertFalse(parentPane.getChildren().contains(overlay));
            assertFalse(parentPane.getChildren().contains(centerBox));

            stage.close();
        });
    }
}
