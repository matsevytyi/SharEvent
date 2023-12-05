package USE_CASE;

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
    public AddEventInputData getInputData() {
        return inputData;
    }
}
class AddEventControllerTest {

    @Test
    void testExecute() {

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

        AddEventOutputData eventData = new AddEventOutputData("cook",false);


        addEventPresenter.prepareSuccessView(eventData);


        AddEventState state = addEventViewModel.getState();
        assertEquals(eventData.getEventName(), state.getEventName());

    }

    @Test
    void testPrepareFailView() {

        String error = "Error message";


        addEventPresenter.prepareFailView(error);


        AddEventState state = addEventViewModel.getState();
        assertEquals(error, state.getEventNameError());

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

        assertNull(addEventState.getEventName());
        assertNull(addEventState.getEventNameError());

    }

    @Test
    void testSettersAndGetters() {

        String eventName = "Test Event";
        String eventNameError = "Invalid event name";


        addEventState.setEventName(eventName);
        addEventState.setEventNameError(eventNameError);


        assertEquals(eventName, addEventState.getEventName());
        assertEquals(eventNameError, addEventState.getEventNameError());

    }

    @Test
    void testSetState() {


        AddEventState newState = new AddEventState();
        newState.setEventName("New Event");


        addEventViewModel.setState(newState);


        assertEquals(newState, addEventViewModel.getState());
        assertEquals("New Event", addEventViewModel.getState().getEventName());
    addEventViewModel.firePropertyChanged();

        AddEventState updatedState =  addEventViewModel.getState();
        assertEquals(newState, updatedState);

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

        assertNull(addEventViewModel.getState().getEventName());
        assertNull(addEventViewModel.getState().getEventNameError());

    }

    @Test
    void testSetClickedPosition() {

        double latitude = 10.0;
        double longitude = 20.0;


        addEventViewModel.setClickedPosition(new GeoPosition(latitude, longitude));


        assertEquals(latitude, addEventViewModel.getState().getEventLatitude());
        assertEquals(longitude, addEventViewModel.getState().getEventLongitude());
    }
}
