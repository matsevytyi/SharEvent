package USE_CASE;

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

    }

    private static class TestDeleteEventPresenter implements DeleteEventOutputBoundary {
        private DeleteEventOutputData outputData;

        @Override
        public void prepareSuccessCase(DeleteEventOutputData event) {
            this.outputData = event;
        }

        @Override
        public void prepareFailCase(String error) {

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

        int eventId = 123;


        deleteEventInteractor.execute(new DeleteEventInputData(eventId));


        assertNotNull(deleteEventPresenter.outputData);
        assertEquals("TestEvent", deleteEventPresenter.outputData.getDeletedEvent());
        assertFalse(deleteEventPresenter.outputData.isUseCaseFailed());
    }


    @Test
    void execute_HandlesFailureCase() {

        int eventId = 456;


        deleteEventInteractor.execute(new DeleteEventInputData(eventId));


        assertNotNull(deleteEventPresenter.outputData);
        assertFalse(deleteEventPresenter.outputData.isUseCaseFailed());
    }
}

public class DeleteEventControllerTest {

    @Test
    public void testExecute() {

        DeleteEventInputBoundary deleteEventUseCaseInteractor = new TestDeleteEventUseCaseInteractor();
        DeleteEventController deleteEventController = new DeleteEventController(deleteEventUseCaseInteractor);


        deleteEventController.execute(1);

        assertTrue(((TestDeleteEventUseCaseInteractor) deleteEventUseCaseInteractor).wasExecuteCalled());

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


     @Test
     public void testPrepareFailCase() {

         DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();
         ViewManagerModel viewManagerModel = new ViewManagerModel(); // Assuming ViewManagerModel is a mock or test implementation
         DeleteEventPresenter deleteEventPresenter = new DeleteEventPresenter(deleteEventViewModel, viewManagerModel);


         deleteEventPresenter.prepareFailCase("Some error message"); // Pass the expected error message


         assertEquals("Some error message", deleteEventViewModel.getState().getDeletedEventError());

     }
}

class DeleteEventStateTest {

    @Test
    public void testInitialState() {

        DeleteEventState deleteEventState = new DeleteEventState();


        assertEquals("", deleteEventState.getDeletedEventName());
        assertEquals("", deleteEventState.getDeletedEventError());
        assertEquals(0, deleteEventState.getDeletedEventId());
        assertEquals("", deleteEventState.getDeletedEventIdError());
    }


}

class DeleteEventViewModelTest {

    @Test
    public void testInitialState() {

        DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();

        assertEquals("", deleteEventViewModel.getState().getDeletedEventName());

    }

    @Test
    public void testSetState() {

        DeleteEventViewModel deleteEventViewModel = new DeleteEventViewModel();
        DeleteEventState newState = new DeleteEventState();
        newState.setDeletedEventName("NewEventName");


        deleteEventViewModel.setState(newState);


        assertEquals("NewEventName", deleteEventViewModel.getState().getDeletedEventName());

    }

}
