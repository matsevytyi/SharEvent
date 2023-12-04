package VIEW;

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

            assertNotNull(view);

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

            view.handleActionButtonClick();

            assertNotNull(view);

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

            PropertyChangeEvent event = new PropertyChangeEvent(
                    this, "propertyName", "oldValue", "newValue");

            view.propertyChange(event);

            assertNotNull(view);

        });
    }




    public static class RealDeleteEventInputBoundary implements DeleteEventInputBoundary {

        @Override
        public void execute(DeleteEventInputData inputData) {

            System.out.println("Executing DeleteEventInputBoundary with event ID: " + inputData.getEventId());
        }
    }

    public static class RealRegisterInputBoundary implements RegisterInputBoundary {

        @Override
        public void execute(RegisterInputData inputData) {

            System.out.println("Executing RegisterInputBoundary with event ID: " + inputData.getEventId()
                    + " and username: " + inputData.getUserName());
        }
    }
}
