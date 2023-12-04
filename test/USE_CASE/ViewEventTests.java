package USE_CASE;

import DATA_ACCESS.DatabaseDAO;

import ENTITY.Event;
import ENTITY.User;
import INTERFACE_ADAPTER.ViewManagerModel;


import INTERFACE_ADAPTER.view_event.ViewEventController;
import INTERFACE_ADAPTER.view_event.ViewEventPresenter;
import INTERFACE_ADAPTER.view_event.ViewEventState;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;

import USE_CASE.view_event.*;

import org.junit.jupiter.api.Test;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewEventTests {

    @Test
    void viewEventControllerExecute() {
        ViewEventInputBoundaryStub mockInteractor = new ViewEventInputBoundaryStub();
        ViewEventController controller = new ViewEventController(mockInteractor);
        double latitude = 3.0;
        double longitude = 10.0;
        JXMapViewerStub mapViewer = new JXMapViewerStub();

        controller.execute(latitude, longitude, mapViewer);

        assertTrue(mockInteractor.executeCalled);
        assertEquals(latitude, mockInteractor.inputData.getLatitude());
        assertEquals(longitude, mockInteractor.inputData.getLongitude());
        assertEquals(mapViewer, mockInteractor.inputData.getMapViewer());
    }

    @Test
    void viewEventControllerCorrect() {
        ViewEventInputBoundaryStub mockInteractor = new ViewEventInputBoundaryStub();
        ViewEventController controller = new ViewEventController(mockInteractor);
        double latitude = 3.0;
        double longitude = 10.0;
        JXMapViewerStub mapViewer = new JXMapViewerStub();

        controller.execute(latitude, longitude, mapViewer);

        assertTrue(mockInteractor.executeCalled);
        assertEquals(latitude, mockInteractor.inputData.getLatitude());
        assertEquals(longitude, mockInteractor.inputData.getLongitude());
        assertEquals(mapViewer, mockInteractor.inputData.getMapViewer());
    }


    @Test
    void viewEventPresenterSuccessesView() {
        ViewEventViewModel viewModel = new ViewEventViewModel();
        ViewEventPresenter presenter = new ViewEventPresenter(viewModel, new ViewManagerModel());
        ViewEventOutputData outputData = createOutputData();

        presenter.successesView(outputData);

        assertEquals(outputData.getEventId(), viewModel.getState().getEventId());
        assertEquals(outputData.getEventName(), viewModel.getState().getEventName());
        assertEquals(outputData.getType(), viewModel.getState().getType());

    }

    @Test
    void viewEventPresenterPrepareFailView() {
        ViewEventViewModelStub viewModel = new ViewEventViewModelStub();
        ViewEventPresenter presenter = new ViewEventPresenter(viewModel, null);
        String error = "Test error message";

        presenter.prepareFailView(error);

        assertEquals(error, viewModel.getState().getError());
        assertTrue(viewModel.propertyChangedCalled);
    }


    @Test
    void viewEventStateSetDetailsCorrectly() {
        ViewEventState state = new ViewEventState();
        state.setDetails(1, "EventName", "Type", "Description",
                LocalDate.now(), LocalTime.now(), "Creator", "User1, User2");
        assertEquals(1, state.getEventId());
        assertEquals("EventName", state.getEventName());
        assertEquals("Type", state.getType());

    }


    @Test
    void viewEventViewModel_FirePropertyChanged() {
        ViewEventViewModel viewModel = new ViewEventViewModel();
        PropertyChangeListenerStub listener = new PropertyChangeListenerStub();

        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChanged();

        assertTrue(listener.propertyChangedCalled);
    }

    @Test
    void viewEventViewModelUpdateViewCorrectly() {
        ViewEventViewModel viewModel = new ViewEventViewModel();
        viewModel.setState(new ViewEventState());
        viewModel.setLoggedInUser("TestUser");
        String a = viewModel.getState().getLoggedinuser();
        assertEquals("TestUser", a);

    }
    @Test
    void execute_EventNotNull_SuccessfulView() {
        // Arrange
        LoadEventsDataAccessInterfaceStub eventDataAccessInterface = new LoadEventsDataAccessInterfaceStub();
        ViewEventOutputBoundaryStub viewEventPresenter = new ViewEventOutputBoundaryStub();
        ViewEventInteractor interactor = new ViewEventInteractor(eventDataAccessInterface, viewEventPresenter);
        List<User> u = new ArrayList<>();
        u.add(new User("w","w","w","w"));
        Event sampleEvent = new Event("TestEvent", "Type", "Description", LocalDate.now(), LocalTime.now(),
                new User("Creator", "username", "password", "email"), u, 1.0, 2.0);


        eventDataAccessInterface.setEventToReturn(sampleEvent);

        ViewEventInputData position = new ViewEventInputData(1.0, 2.0, new JXMapViewerStub());

        interactor.execute(position);

        assertTrue(viewEventPresenter.successesViewCalled);
        assertFalse(viewEventPresenter.prepareFailViewCalled);

        ViewEventOutputData outputData = viewEventPresenter.outputData;
        assertNotNull(outputData);

        assertEquals(sampleEvent.getEventId(), outputData.getEventId());
        assertEquals(sampleEvent.getEventName(), outputData.getEventName());
        assertEquals(sampleEvent.getType(), outputData.getType());
        assertEquals(sampleEvent.getDescription(), outputData.getDescription());
        assertEquals(sampleEvent.getEventDate(), outputData.getEventDate());
        assertEquals(sampleEvent.getEventTime(), outputData.getEventTime());
        assertEquals(sampleEvent.getCreator().getName(), outputData.getCreator());
        assertEquals(sampleEvent.getEventAttendants().toString(), outputData.getRegisteredUsers());

    }

    @Test
    void viewEventInteractorNullEvent() {
        LoadEventsDataAccessInterfaceStub dataAccess = new LoadEventsDataAccessInterfaceStub();
        dataAccess.event = null;
        ViewEventOutputBoundaryStub presenter = new ViewEventOutputBoundaryStub();
        ViewEventInteractor interactor = new ViewEventInteractor(dataAccess, presenter);

        interactor.execute(new ViewEventInputData(1.0, 2.0, new JXMapViewerStub()));

        assertTrue(presenter.prepareFailViewCalled);
    }


    @Test
    void viewEventOutputData_Constructor_SetsValuesCorrectly() {
        ViewEventOutputData outputData = createOutputData();

        assertEquals(1, outputData.getEventId());
        assertEquals("EventName", outputData.getEventName());
        assertEquals("Type", outputData.getType());

    }


    private ViewEventOutputData createOutputData() {
        return new ViewEventOutputData(1, "EventName", "Type", "Description",
                LocalDate.now(), LocalTime.now(), "Creator", "User1, User2");
    }

    static class ViewEventInputBoundaryStub implements ViewEventInputBoundary {
        boolean executeCalled = false;
        ViewEventInputData inputData;

        @Override
        public void execute(ViewEventInputData viewEventInputData) {
            executeCalled = true;
            inputData = viewEventInputData;
        }
    }

    static class JXMapViewerStub extends JXMapViewer {

    }

    static class ViewEventViewModelStub extends ViewEventViewModel {
        boolean propertyChangedCalled = false;

        @Override
        public void firePropertyChanged() {
            propertyChangedCalled = true;
        }
    }

    static class LoadEventsDataAccessInterfaceStub extends DatabaseDAO {
        Event event;
        public void setEventToReturn(Event eventToReturn) {
            this.event = eventToReturn;
        }
        @Override
        public Event getEventByPosition(double latitude, double longitude, JXMapViewer mapViewer) {
            return event;
        }
    }

    static class ViewEventOutputBoundaryStub implements ViewEventOutputBoundary {
        boolean successesViewCalled = false;
        boolean prepareFailViewCalled = false;
        ViewEventOutputData outputData;

        @Override
        public void successesView(ViewEventOutputData viewEventOutputData) {
            successesViewCalled = true;
            outputData = viewEventOutputData;
        }

        @Override
        public void prepareFailView(String error) {
            prepareFailViewCalled = true;
        }


    }

    static class PropertyChangeListenerStub implements PropertyChangeListener {
        boolean propertyChangedCalled = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyChangedCalled = true;
        }
    }



    @Test
    void setDetailsValidInput() {

        ViewEventState state = new ViewEventState();
        int eventId = 1;
        String eventName = "Test Event";
        String type = "Test Type";
        String description = "Test Description";
        LocalDate date = LocalDate.of(2023, 12, 1);
        LocalTime time = LocalTime.of(12, 30);
        String createdBy = "TestUser";
        String registeredUsers = "User1, User2";

        state.setDetails(eventId, eventName, type, description, date, time, createdBy, registeredUsers);

        assertEquals(eventId, state.getEventId());
        assertEquals(eventName, state.getEventName());
        assertEquals(type, state.getType());
        assertEquals(description, state.getDescription());
        assertEquals(date, state.getDate());
        assertEquals(time, state.getTime());
        assertEquals(createdBy, state.getCreatedBy());
        assertEquals(registeredUsers, state.getRegisteredUsers());
    }

    @Test
    void setClickedPositionValidGeoPosition() {

       ViewEventViewModel viewModel = new ViewEventViewModel();
        GeoPosition clickedPosition = new GeoPosition(10.0, 20.0);

        viewModel.setClickedPosition(clickedPosition);

        assertEquals(10.0,viewModel.getState().getLatitude());
        assertEquals(20.0, viewModel.getState().getLongitude());
    }

    @Test
    void setMapViewerValidMapViewer() {

        ViewEventState state = new ViewEventState();
        JXMapViewer mapViewer = new JXMapViewer();

        state.setMapViewer(mapViewer);

        assertEquals(mapViewer, state.getMapViewer());
    }

    @Test
    void setLoggedInUser_ValidUser_SetsLoggedInUser() {

        ViewEventState state = new ViewEventState();
        String loggedInUser = "TestUser";

        state.setLoggedinuser(loggedInUser);

        assertEquals(loggedInUser, state.getLoggedinuser());
    }

}
