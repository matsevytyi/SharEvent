package view;

import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.register_for_event.RegisterController;
import INTERFACE_ADAPTER.register_for_event.RegisterPresenter;
import INTERFACE_ADAPTER.register_for_event.RegisterViewModel;
import USE_CASE.register_for_event.RegisterInputBoundary;
import USE_CASE.register_for_event.RegisterInputData;
import USE_CASE.register_for_event.RegisterInteractor;
import USE_CASE.register_for_event.RegisterOutputBoundary;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.is;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;



public class RegisterForEventTest {

    @Test
    public void testRegisterController() {
        RegisterInputBoundary mockInputBoundary = mock(RegisterInputBoundary.class);

        RegisterController registerController = new RegisterController(mockInputBoundary);

        int eventId = 1;
        String username = "testUser";
        registerController.execute(eventId, username);
        verify(mockInputBoundary).execute(argThat(inputData ->
                inputData.getEventId() == eventId && inputData.getUserName().equals(username)));
    }

    @Test
    public void testRegisterInteractor() {
        LoadEventsDataAccessInterface mockDataAccess = mock(LoadEventsDataAccessInterface.class);
        RegisterOutputBoundary mockOutputBoundary = mock(RegisterOutputBoundary.class);
        RegisterInteractor registerInteractor = new RegisterInteractor(mockDataAccess, mockOutputBoundary);

        RegisterInputData inputData = new RegisterInputData(1, "testUser");

        registerInteractor.execute(inputData);
        verify(mockOutputBoundary).prepareSuccessCase();
    }

    @Test
    public void testConstructor() {
        RegisterViewModel mockRegisterViewModel = mock(RegisterViewModel.class);
        ViewManagerModel mockViewManagerModel = mock(ViewManagerModel.class);

        RegisterPresenter registerPresenter = new RegisterPresenter(mockRegisterViewModel, mockViewManagerModel);
        assertNotNull(registerPresenter);
    }





}
