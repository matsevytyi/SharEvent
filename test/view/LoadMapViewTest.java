package VIEW;

import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapController;
import INTERFACE_ADAPTER.register_for_event.RegisterController;
import INTERFACE_ADAPTER.view_event.ViewEventController;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import USE_CASE.loadevents.LoadEventsInteractor;
import USE_CASE.loadmap.LoadMapInteractor;
import VIEW.LoadMapView;
import VIEW_CREATOR.LoadMapViewModel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class LoadMapViewTest {

    private LoadMapViewModel loadMapViewModel;

    @Mock
    private AddEventViewModel addEventViewModel;

    @Mock
    private AddEventController addEventController;

    @Mock
    private ViewEventViewModel viewEventViewModel;

    @Mock
    private ViewEventController viewEventController;

    @Mock
    private DeleteEventViewModel deleteEventViewModel;

    @Mock
    private DeleteEventController deleteEventController;

    @Mock
    private RegisterController registerController;

    @Mock
    private ViewProfileViewModel viewProfileViewModel;

    @Mock
    private ViewProfileController viewProfileController;
    @Mock
    private Alert alertMock;

    @Getter
    private LoadMapView loadMapView;

    @BeforeEach
    public void setUp() {
        // Initialize mocks and inject them into the LoadMapView instance
        MockitoAnnotations.openMocks(this);

        // Initialize JavaFX toolkit to handle JavaFX components
        new JFXPanel();

        loadMapViewModel = new LoadMapViewModel();

        // Create an instance of LoadMapView with the mocked dependencies
        loadMapView = new LoadMapView(
                loadMapViewModel,
                addEventViewModel,
                addEventController,
                viewEventViewModel,
                viewEventController,
                deleteEventViewModel,
                deleteEventController,
                registerController,
                viewProfileViewModel,
                viewProfileController
        );
    }

    @Test
    public void testLoadMapViewCreation() {
        assertNotNull(loadMapView);
        assertNotNull(loadMapView.getController());
        assertNotNull(loadMapView.getPresenter());
        assertNotNull(loadMapView.getController().getLoadMapOutputData());
        assertNotNull(loadMapView.getController().getInteractor());
        assertNotNull(loadMapView.getController().getView());
        loadMapView.getController().setInteractor(new LoadMapInteractor());
    }

   /* @Test
    public void test_button_listeners() {
        Button filterEventsButton = new Button();
        filterEventsButton.setOnAction(controller::filterEventsButtonAction);

        // Simulate a button click
        filterEventsButton.fire();

        // Verify that the corresponding method in the controller was called
        verify(filterController).filterEvents(*//* any necessary arguments *//*);

    }*/

    @Test
    public void test_LoadMapView_getters_seters() {
        assertNotNull(loadMapView.getViewName());
        assertNotNull(loadMapView.getPresenter());
        assertNotNull(loadMapView.getController());
        assertNotNull(loadMapView.getViewModel());
        assertNotNull(loadMapView.getViewEventViewModel());
        assertNotNull(loadMapView.getViewEventController());
        assertNotNull(loadMapView.getStackPane());
        assertNotNull(loadMapView.getAddEventViewModel());
        assertNotNull(loadMapView.getAddEventController());
        assertNotNull(loadMapView.getDeleteEventViewModel());
        assertNotNull(loadMapView.getDeleteEventController());
        assertNotNull(loadMapView.getMapViewer());
        assertNotNull(loadMapView.getMapViewerforViewing());
        assertNull(loadMapView.getAddEvent());
        assertNotNull(loadMapView.getRegisterController());
        assertNotNull(loadMapView.getViewProfileViewModel());
        assertNotNull(loadMapView.getViewProfileController());
        assertNotNull(loadMapView.getFilterEventsView());
    }


}
