import APP.MapUseCasesFactory;
import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.register_for_event.RegisterViewModel;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import VIEW.LoadMapView;
import VIEW_CREATOR.LoadMapViewModel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapUseCaseFactoryTests {

    @BeforeAll
    public static void initJavaFX() {
        System.setProperty("javafx.headless", "true");
        new JFXPanel(); // initializes JavaFX environment
    }
    @Test
    void createLoadMapView_ValidViewModels_SuccessfulCreation() {
        // Arrange
        LoadMapViewModel loadMapViewModel = new LoadMapViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AddEventViewModel addEventViewModel = new AddEventViewModel();
        ViewEventViewModel viewEventViewModel = new ViewEventViewModel();
        LoadEventsDataAccessInterface loadEventsDataAccessInterface = new DatabaseDAO();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();
        RegisterViewModel registerViewModel = new RegisterViewModel();
        ViewProfileViewModel viewProfileViewModel = new ViewProfileViewModel();
        Platform.runLater(() -> {
            // Act
            LoadMapView loadMapView = MapUseCasesFactory.create(
                    loadMapViewModel, loginViewModel, addEventViewModel, viewEventViewModel,
                    loadEventsDataAccessInterface, viewManagerModel, deleteEventViewModel,
                    registerViewModel, viewProfileViewModel
            );

            // Assert
            assertNotNull(loadMapView);
        });

    }
}
