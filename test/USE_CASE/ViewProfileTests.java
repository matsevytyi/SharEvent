package USE_CASE;

import DATA_ACCESS.DatabaseDAO;
import ENTITY.User;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.view_profile.ViewProfileState;
import USE_CASE.view_profile.ViewProfileInputBoundary;
import USE_CASE.view_profile.ViewProfileInputData;
import USE_CASE.view_profile.ViewProfileInteractor;
import USE_CASE.view_profile.ViewProfileOutputBoundary;
import USE_CASE.view_profile.ViewProfileOutputData;
import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfilePresenter;

import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import org.junit.jupiter.api.Test;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

public class ViewProfileTests {

    @Test
    void viewProfileControllerExecute() {

        ViewProfileInputBoundaryFake mockInteractor = new ViewProfileInputBoundaryFake();
        ViewProfileController controller = new ViewProfileController(mockInteractor);
        String username = "testUser";


        controller.execute(username);

        assertTrue(mockInteractor.executeCalled);
        assertEquals(username, mockInteractor.inputData.getUsername());
    }

    @Test
    void viewProfileInteractionistSuccesses() {


        LoadUserDataAccessInterfaceStub dataAccess= new LoadUserDataAccessInterfaceStub();
        ViewProfileOutputBoundaryFake presenter = new ViewProfileOutputBoundaryFake();
        ViewProfileInteractor interactor = new ViewProfileInteractor(dataAccess, presenter);


        User sampleUser = new User("ff", "ff", "ff", "ff");


        dataAccess.setUserToReturn(sampleUser);

        ViewProfileInputData inputData = new ViewProfileInputData("ff");

        interactor.execute(inputData);

        assertTrue(presenter.successesViewCalled);
        assertFalse(presenter.prepareFailViewCalled);

        ViewProfileOutputData outputData = presenter.outputData;
        assertNotNull(outputData);

        assertEquals(sampleUser.getUsername(), outputData.getUsername());
        assertEquals(sampleUser.getName(), outputData.getName());
        assertEquals(sampleUser.getEmail(), outputData.getEmail());
        assertEquals(sampleUser.getRegisteredEvents(), outputData.getRegistered_events());
        assertEquals(sampleUser.getHostedEvents(), outputData.getHosted_events());
    }

    @Test
    void viewProfileInteractorUserNotFound() {

        LoadUserDataAccessInterfaceStub dataAccess = new LoadUserDataAccessInterfaceStub();
        dataAccess.user = null;
        ViewProfileOutputBoundaryFake presenter = new ViewProfileOutputBoundaryFake();
        ViewProfileInteractor interactor = new ViewProfileInteractor(dataAccess, presenter);

        ViewProfileInputData inputData = new ViewProfileInputData("nonExistUser");


        interactor.execute(inputData);

        assertTrue(presenter.prepareFailViewCalled);
        assertFalse(presenter.successesViewCalled);
    }

    @Test
    void viewProfilePresenterSuccessfulView() {

        ViewProfileViewModel viewModel = new ViewProfileViewModel();
        ViewProfilePresenter presenter = new ViewProfilePresenter(viewModel, new ViewManagerModel());
        ViewProfileOutputData outputData = createOutputData();

        presenter.successesView(outputData);

        assertEquals(outputData.getUsername(), viewModel.getState().getUsername());
        assertEquals(outputData.getName(), viewModel.getState().getName());
        assertEquals(outputData.getEmail(), viewModel.getState().getEmail());
        assertEquals(outputData.getRegistered_events(), viewModel.getState().getRegistered_events());
        assertEquals(outputData.getHosted_events(), viewModel.getState().getHosted_events());
    }

    @Test
    void viewProfilePresenterPrepareFailView() {

        ViewProfileViewModelFake viewModel = new ViewProfileViewModelFake();
        ViewProfilePresenter presenter = new ViewProfilePresenter(viewModel, null);
        String error = "Test error message";


        presenter.prepareFailView(error);

        assertEquals(error, viewModel.getState().getError());
        assertTrue(viewModel.propertyChangedCalled);
    }

    private ViewProfileOutputData createOutputData() {
        return new ViewProfileOutputData("testUser", "Test Name", "test@example.com", null, null);
    }

    public class ViewProfileInputBoundaryFake implements ViewProfileInputBoundary {
        public boolean executeCalled = false;
        public ViewProfileInputData inputData;

        @Override
        public void execute(ViewProfileInputData input) {
            executeCalled = true;
            inputData = input;
        }
    }


    public class ViewProfileOutputBoundaryFake implements ViewProfileOutputBoundary {
        public boolean successesViewCalled = false;
        public boolean prepareFailViewCalled = false;
        public ViewProfileOutputData outputData;

        @Override
        public void successesView(ViewProfileOutputData output) {
            successesViewCalled = true;
            outputData = output;
        }

        @Override
        public void prepareFailView(String error) {
            prepareFailViewCalled = true;
        }
    }


    public class ViewProfileViewModelFake extends ViewProfileViewModel {
        public boolean propertyChangedCalled = false;

        @Override
        public void firePropertyChanged() {
            propertyChangedCalled = true;
        }
    }

    static class LoadUserDataAccessInterfaceStub extends DatabaseDAO {
       User user;
        public void setUserToReturn(User user) {
            this.user = user;
        }

    }

    @Test
    void viewProfileViewModelFireProperty() {

        ViewProfileViewModel viewModel = new ViewProfileViewModel();
        PropertyChangeListenerFake listener = new PropertyChangeListenerFake();


        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChanged();

        assertTrue(listener.propertyChangedCalled);
    }

    @Test
    void viewProfileViewModelUpdateView() {

        ViewProfileViewModel viewProfileViewModel = new ViewProfileViewModel();
        viewProfileViewModel.setState(new ViewProfileState());
        viewProfileViewModel.getState().setUsername("TestUser11");


        String username = viewProfileViewModel.getState().getUsername();

        assertEquals("TestUser11", username);

    }

    static class PropertyChangeListenerFake implements PropertyChangeListener {
        boolean propertyChangedCalled = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyChangedCalled = true;
        }
    }
}
