import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.register_for_event.RegisterController;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import USE_CASE.delete_event.DeleteEventInputBoundary;
import USE_CASE.delete_event.DeleteEventInputData;
import USE_CASE.register_for_event.RegisterInputBoundary;
import USE_CASE.register_for_event.RegisterInputData;
import VIEW.ViewEventView;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;

public class ViewEventViewTest {

    @BeforeAll
    public static void initJFX() {
        // Initialize JavaFX for headless testing
        new JFXPanel();
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(new ViewEventView(
                    new ViewEventViewModel(),
                    new DeleteEventController(new RealDeleteEventInputBoundary()),
                    new DeleteEventViewModel(),
                    new RegisterController(new RealRegisterInputBoundary())
            )));
            stage.show();
        });
    }
    @Test
    public void testUpdateView() {
        Platform.runLater(() -> {
            ViewEventView view = new ViewEventView(
                    new ViewEventViewModel(),
                    new DeleteEventController(new RealDeleteEventInputBoundary()),
                    new DeleteEventViewModel(),
                    new RegisterController(new RealRegisterInputBoundary())
            );
            view.updateView();
            // Add assertions based on the expected state after the updateView() method
            assertNotNull(view);
            // Add more assertions as needed
        });
    }

    @Test
    public void testHandleActionButtonClick() {
        Platform.runLater(() -> {
            ViewEventView view = new ViewEventView(
                    new ViewEventViewModel(),
                    new DeleteEventController(new RealDeleteEventInputBoundary()),
                    new DeleteEventViewModel(),
                    new RegisterController(new RealRegisterInputBoundary())
            );
            // Assuming proper initialization of the view and its components
            view.handleActionButtonClick();
            // Add assertions based on the expected behavior after clicking the actionButton
            assertNotNull(view);
            // Add more assertions as needed
        });
    }

    @Test
    public void testPropertyChange() {
        Platform.runLater(() -> {
            ViewEventView view = new ViewEventView(
                    new ViewEventViewModel(),
                    new DeleteEventController(new RealDeleteEventInputBoundary()),
                    new DeleteEventViewModel(),
                    new RegisterController(new RealRegisterInputBoundary())
            );
            // Assuming proper initialization of the view and its components
            PropertyChangeEvent event = new PropertyChangeEvent(
                    this, "propertyName", "oldValue", "newValue");

            view.propertyChange(event);
            // Add assertions based on the expected behavior after propertyChange
            assertNotNull(view);
            // Add more assertions as needed
        });
    }




    public static class RealDeleteEventInputBoundary implements DeleteEventInputBoundary {

        @Override
        public void execute(DeleteEventInputData inputData) {
            // Implement the actual logic for delete event use case
            // For testing purposes, you can print a message or perform some dummy operations
            System.out.println("Executing DeleteEventInputBoundary with event ID: " + inputData.getEventId());
        }
    }

    public static class RealRegisterInputBoundary implements RegisterInputBoundary {

        @Override
        public void execute(RegisterInputData inputData) {
            // Implement the actual logic for register for event use case
            // For testing purposes, you can print a message or perform some dummy operations
            System.out.println("Executing RegisterInputBoundary with event ID: " + inputData.getEventId()
                    + " and username: " + inputData.getUserName());
        }
    }
}
