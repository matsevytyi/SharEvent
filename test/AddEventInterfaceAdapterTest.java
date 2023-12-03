import ENTITY.User;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventPresenter;
import INTERFACE_ADAPTER.add_event.AddEventState;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import USE_CASE.add_event.AddEventInputBoundary;
import USE_CASE.add_event.AddEventInputData;
import USE_CASE.add_event.AddEventOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
class TestAddEventInputBoundary implements AddEventInputBoundary {
    private AddEventInputData inputData;

    @Override
    public void execute(AddEventInputData inputData) {
        this.inputData = inputData;
    }

    // Getter for the recorded input data.
    public AddEventInputData getInputData() {
        return inputData;
    }
}
class AddEventControllerTest {

    @Test
    void testExecute() {
        // Arrange
        TestAddEventInputBoundary testInputBoundary = new TestAddEventInputBoundary();
        AddEventController addEventController = new AddEventController(testInputBoundary);

        String eventName = "Test Event";
        String type = "Test Type";
        String description = "Test Description";
        LocalDate eventDate = LocalDate.of(2023, 12, 31);
        LocalTime eventTime = LocalTime.of(12, 0);
        User creator = new User("testuser", "testpassword", "innn", "njdnjnjd");
        double latitude = 10.0;
        double longitude = 20.0;

        // Act
        addEventController.execute(eventName, type, description, eventDate, eventTime, creator, latitude, longitude);

        AddEventInputData recordedInputData = testInputBoundary.getInputData();
        assertNotNull(recordedInputData);
        assertEquals(eventName, recordedInputData.getEventName());
    }
}
class AddEventPresenterTest {

    private AddEventViewModel addEventViewModel;
    private ViewManagerModel viewManagerModel;
    private AddEventPresenter addEventPresenter;

    @BeforeEach
    void setUp() {
        addEventViewModel = new AddEventViewModel();
        viewManagerModel = new ViewManagerModel();
        addEventPresenter = new AddEventPresenter(addEventViewModel, viewManagerModel);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        AddEventOutputData eventData = new AddEventOutputData("cook",false);

        // Act
        addEventPresenter.prepareSuccessView(eventData);

        // Assert
        AddEventState state = addEventViewModel.getState();
        assertEquals(eventData.getEventName(), state.getEventName());
        // Add more assertions based on the expected behavior
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String error = "Error message";

        // Act
        addEventPresenter.prepareFailView(error);

        // Assert
        AddEventState state = addEventViewModel.getState();
        assertEquals(error, state.getEventNameError());
        // Add more assertions based on the expected behavior
    }
}

class AddEventStateTest {

    private AddEventState addEventState;
    private AddEventViewModel addEventViewModel;

    @BeforeEach
    void setUp() {
        addEventState = new AddEventState();
        addEventViewModel = new AddEventViewModel();
    }

    @Test
    void testInitialState() {
        // Assert
        assertNull(addEventState.getEventName());
        assertNull(addEventState.getEventNameError());
        // Add more assertions based on the expected initial state
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        String eventName = "Test Event";
        String eventNameError = "Invalid event name";

        // Act
        addEventState.setEventName(eventName);
        addEventState.setEventNameError(eventNameError);

        // Assert
        assertEquals(eventName, addEventState.getEventName());
        assertEquals(eventNameError, addEventState.getEventNameError());
        // Add more assertions based on the expected behavior
    }

    @Test
    void testSetState() {
        // Arrange
        AddEventState initialState = addEventViewModel.getState();
        AddEventState newState = new AddEventState();
        newState.setEventName("New Event");

        // Act
        addEventViewModel.setState(newState);

        // Assert
        assertEquals(newState, addEventViewModel.getState());
        assertEquals("New Event", addEventViewModel.getState().getEventName());
    addEventViewModel.firePropertyChanged();
        // Verify that the state change triggers a property change event
        AddEventState updatedState =  addEventViewModel.getState();
        assertEquals(newState, updatedState);
        // Add more assertions based on the expected behavior
    }
}

class AddEventViewModelTest {

    private AddEventViewModel addEventViewModel;

    @BeforeEach
    void setUp() {
        addEventViewModel = new AddEventViewModel();
    }

    @Test
    void testInitialState() {
        // Assert
        assertNull(addEventViewModel.getState().getEventName());
        assertNull(addEventViewModel.getState().getEventNameError());
        // Add more assertions based on the expected initial state
    }

    @Test
    void testSetClickedPosition() {
        // Arrange
        double latitude = 10.0;
        double longitude = 20.0;

        // Act
        addEventViewModel.setClickedPosition(new GeoPosition(latitude, longitude));

        // Assert
        assertEquals(latitude, addEventViewModel.getState().getEventLatitude());
        assertEquals(longitude, addEventViewModel.getState().getEventLongitude());
        // Add more assertions based on the expected behavior
    }
}
