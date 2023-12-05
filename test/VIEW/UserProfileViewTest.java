package VIEW;

import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfileState;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import USE_CASE.delete_event.DeleteEventInputBoundary;
import USE_CASE.delete_event.DeleteEventInputData;
import USE_CASE.view_profile.ViewProfileInputBoundary;
import USE_CASE.view_profile.ViewProfileInputData;
import VIEW.UserProfileView;
import VIEW_CREATOR.FailViewFactory;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeEvent;
import static org.junit.jupiter.api.Assertions.*;

public class UserProfileViewTest {

    @BeforeAll
    public static void initJFX() {
        new JFXPanel();
    }

    @Test
    public void testInitialization() {
        UserProfileView userProfileView = new UserProfileView(
                new ViewProfileViewModel(),
                new ViewProfileController(new RealViewProfileInputBoundary()));


        assertNotNull(userProfileView);

    }


    public static class RealViewProfileInputBoundary implements ViewProfileInputBoundary {

        @Override
        public void execute(ViewProfileInputData inputData) {

            System.out.println("Executing DeleteEventInputBoundary with event ID: " + inputData.getUsername());
        }
    }}
