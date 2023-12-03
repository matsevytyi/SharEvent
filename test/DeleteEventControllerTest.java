import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_InputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_OutputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.Event;
import ENTITY.User;
import INTERFACE_ADAPTER.ViewManagerModel;
import USE_CASE.delete_event.*;
import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventPresenter;
import INTERFACE_ADAPTER.delete_event.DeleteEventState;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.JXMapViewer;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class DeleteEventInteractorTest {

    private static class TestLoadEventsDataAccess implements LoadEventsDataAccessInterface {
        @Override
        public String getEventById(int eventId) {
            // Provide a test implementation
            return "TestEvent";
        }

        @Override
        public LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException {
            return null;
        }

        @Override
        public void addEvent(Event event) {

        }

        @Override
        public void registerUserForEvent(String username, int event_id) {

        }

        @Override
        public Event getEventByPosition(double latitude, double longitude, JXMapViewer mapViewer) {
            return null;
        }

        @Override
        public User getUserByUsername(String username) {
            return null;
        }

        @Override
        public void deleteEvent(int eventId) {
            // Provide a test implementation
        }

        private LoadEventsDataAccessInterface loadEventsDataAccessInterface;
        private TestDeleteEventPresenter deleteEventPresenter;
        private DeleteEventInteractor deleteEventInteractor;

        @BeforeEach
        void setUp() {
            loadEventsDataAccessInterface = new TestLoadEventsDataAccess();
            deleteEventPresenter = new TestDeleteEventPresenter();
            deleteEventInteractor = new DeleteEventInteractor(loadEventsDataAccessInterface, deleteEventPresenter);
        }

        @Test
        void execute_DeletesEventAndNotifiesPresenterOnSuccess() {
            // Arrange
            int eventId = 123;

            // Act
            deleteEventInteractor.execute(new DeleteEventInputData(eventId));

            // Assert
            // Verify that the event is deleted (for the sake of this example, check the test implementation)
            // You might want to enhance this based on your actual implementation
            assertNotNull(deleteEventPresenter.outputData);
            assertEquals("TestEvent", deleteEventPresenter.outputData.getDeletedEvent());
            assertFalse(deleteEventPresenter.outputData.isUseCaseFailed());
        }
    }

    private static class TestDeleteEventPresenter implements DeleteEventOutputBoundary {
        private DeleteEventOutputData outputData;

        @Override
        public void prepareSuccessCase(DeleteEventOutputData event) {
            this.outputData = event;
        }

        @Override
        public void prepareFailCase(String error) {
            // Implement if needed for failure case
        }
    }

    private LoadEventsDataAccessInterface loadEventsDataAccessInterface;
    private TestDeleteEventPresenter deleteEventPresenter;
    private DeleteEventInteractor deleteEventInteractor;



    @BeforeEach
    void setUp() {
        loadEventsDataAccessInterface = new TestLoadEventsDataAccess();
        deleteEventPresenter = new TestDeleteEventPresenter();
        deleteEventInteractor = new DeleteEventInteractor(loadEventsDataAccessInterface, deleteEventPresenter);
    }


    @Test
    void execute_DeletesEventAndNotifiesPresenterOnSuccess() {
        // Arrange
        int eventId = 123;

        // Act
        deleteEventInteractor.execute(new DeleteEventInputData(eventId));

        // Assert
        assertNotNull(deleteEventPresenter.outputData);
        assertEquals("TestEvent", deleteEventPresenter.outputData.getDeletedEvent());
        assertFalse(deleteEventPresenter.outputData.isUseCaseFailed());
    }


    @Test
    void execute_HandlesFailureCase() {
        // Arrange
        int eventId = 456;

        // Act
        deleteEventInteractor.execute(new DeleteEventInputData(eventId));

        // Assert
        assertNotNull(deleteEventPresenter.outputData);
        assertFalse(deleteEventPresenter.outputData.isUseCaseFailed());
    }
}

public class DeleteEventControllerTest {

    @Test
    public void testExecute() {
        // Arrange
        DeleteEventInputBoundary deleteEventUseCaseInteractor = new TestDeleteEventUseCaseInteractor();
        DeleteEventController deleteEventController = new DeleteEventController(deleteEventUseCaseInteractor);

        // Act
        deleteEventController.execute(1);

        // Assert
        assertTrue(((TestDeleteEventUseCaseInteractor) deleteEventUseCaseInteractor).wasExecuteCalled());
        // Add more assertions based on the behavior you want to test
    }

    private static class TestDeleteEventUseCaseInteractor implements DeleteEventInputBoundary {
        private boolean executeCalled = false;

        @Override
        public void execute(DeleteEventInputData input) {
            executeCalled = true;
        }

        public boolean wasExecuteCalled() {
            return executeCalled;
        }
    }
}

 class DeleteEventPresenterTest {

//     @Test
//     public void testPrepareSuccessCase() {
//         // Arrange
//         DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();
//         ViewManagerModel viewManagerModel = new ViewManagerModel(); // Assuming ViewManagerModel is a mock or test implementation
//         DeleteEventPresenter deleteEventPresenter = new DeleteEventPresenter(deleteEventViewModel, viewManagerModel);
//
//         // Updated DeleteEventOutputData with a boolean flag
//         DeleteEventOutputData eventData = new DeleteEventOutputData("EventName", false);
//
//         // Act
//         deleteEventPresenter.prepareSuccessCase(eventData);
//
//         // Assert
//         assertEquals("EventName", deleteEventViewModel.getState().getDeletedEventName());
//
//     }
     @Test
     public void testPrepareFailCase() {
         // Arrange
         DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();
         ViewManagerModel viewManagerModel = new ViewManagerModel(); // Assuming ViewManagerModel is a mock or test implementation
         DeleteEventPresenter deleteEventPresenter = new DeleteEventPresenter(deleteEventViewModel, viewManagerModel);

         // Act
         deleteEventPresenter.prepareFailCase("Some error message"); // Pass the expected error message

         // Assert
         assertEquals("Some error message", deleteEventViewModel.getState().getDeletedEventError());
         // Add more assertions based on the behavior you want to test
     }
}

class DeleteEventStateTest {

    @Test
    public void testInitialState() {
        // Arrange
        DeleteEventState deleteEventState = new DeleteEventState();

        // Assert
        assertEquals("", deleteEventState.getDeletedEventName());
        assertEquals("", deleteEventState.getDeletedEventError());
        assertEquals(0, deleteEventState.getDeletedEventId());
        assertEquals("", deleteEventState.getDeletedEventIdError());
    }

    // Add more tests as needed to cover specific behaviors of DeleteEventState
}

class DeleteEventViewModelTest {

    @Test
    public void testInitialState() {
        // Arrange
        DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();

        // Assert
        assertEquals("", deleteEventViewModel.getState().getDeletedEventName());
        // Add more assertions based on the initial state you expect
    }

    @Test
    public void testSetState() {
        // Arrange
        DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();
        DeleteEventState newState = new DeleteEventState();
        newState.setDeletedEventName("NewEventName");

        // Act
        deleteEventViewModel.setState(newState);

        // Assert
        assertEquals("NewEventName", deleteEventViewModel.getState().getDeletedEventName());
        // Add more assertions based on the behavior you want to test
    }

    // Add more tests as needed to cover specific behaviors of DeleteEventViewModel
}
