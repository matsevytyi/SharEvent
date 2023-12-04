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
    void viewProfileController_Execute_CallsViewProfileUseCaseInteractorWithCorrectInputData() {
        // Arrange
        ViewProfileInputBoundaryStub mockInteractor = new ViewProfileInputBoundaryStub();
        ViewProfileController controller = new ViewProfileController(mockInteractor);
        String username = "testUser";

        // Act
        controller.execute(username);

        // Assert
        assertTrue(mockInteractor.executeCalled);
        assertEquals(username, mockInteractor.inputData.getUsername());
    }

    @Test
    void viewProfileInteractor_Execute_UserFound_SuccessfulView() {
        // Arrange
        LoadUserDataAccessInterfaceStub dataAccess= new LoadUserDataAccessInterfaceStub();
        ViewProfileOutputBoundaryStub presenter = new ViewProfileOutputBoundaryStub();
        ViewProfileInteractor interactor = new ViewProfileInteractor(dataAccess, presenter);

        // Create a sample user for dataAccess to return
        User sampleUser = new User("ff", "ff", "ff", "ff");

        // Set up the stub to return the sample user when called
        dataAccess.setUserToReturn(sampleUser);

        ViewProfileInputData inputData = new ViewProfileInputData("ff");

        // Act
        interactor.execute(inputData);

        // Assert
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
    void viewProfileInteractor_Execute_UserNotFound_PrepareFailViewCalled() {
        // Arrange
        LoadUserDataAccessInterfaceStub dataAccess = new LoadUserDataAccessInterfaceStub();
        dataAccess.user = null;
        ViewProfileOutputBoundaryStub presenter = new ViewProfileOutputBoundaryStub();
        ViewProfileInteractor interactor = new ViewProfileInteractor(dataAccess, presenter);

        ViewProfileInputData inputData = new ViewProfileInputData("nonExistentUser");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.prepareFailViewCalled);
        assertFalse(presenter.successesViewCalled);
    }

    @Test
    void viewProfilePresenter_SuccessfulView_SetsStateAndFiresPropertyChanged() {
        // Arrange
        ViewProfileViewModel viewModel = new ViewProfileViewModel();
        ViewProfilePresenter presenter = new ViewProfilePresenter(viewModel, new ViewManagerModel());
        ViewProfileOutputData outputData = createOutputData();

        // Act
        presenter.successesView(outputData);

        // Assert
        assertEquals(outputData.getUsername(), viewModel.getState().getUsername());
        assertEquals(outputData.getName(), viewModel.getState().getName());
        assertEquals(outputData.getEmail(), viewModel.getState().getEmail());
        assertEquals(outputData.getRegistered_events(), viewModel.getState().getRegistered_events());
        assertEquals(outputData.getHosted_events(), viewModel.getState().getHosted_events());
    }

    @Test
    void viewProfilePresenter_PrepareFailView_SetsErrorAndFiresPropertyChanged() {
        // Arrange
        ViewProfileViewModelStub viewModel = new ViewProfileViewModelStub();
        ViewProfilePresenter presenter = new ViewProfilePresenter(viewModel, null);
        String error = "Test error message";

        // Act
        presenter.prepareFailView(error);

        // Assert
        assertEquals(error, viewModel.getState().getError());
        assertTrue(viewModel.propertyChangedCalled);
    }

    private ViewProfileOutputData createOutputData() {
        return new ViewProfileOutputData("testUser", "Test Name", "test@example.com", null, null);
    }

    public class ViewProfileInputBoundaryStub implements ViewProfileInputBoundary {
        public boolean executeCalled = false;
        public ViewProfileInputData inputData;

        @Override
        public void execute(ViewProfileInputData input) {
            executeCalled = true;
            inputData = input;
        }
    }

    // Stub class for the output boundary
    public class ViewProfileOutputBoundaryStub implements ViewProfileOutputBoundary {
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

    // Stub class for the view model
    public class ViewProfileViewModelStub extends ViewProfileViewModel {
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
    void viewProfileViewModel_FirePropertyChanged_FiresPropertyChange() {
        // Arrange
        ViewProfileViewModel viewModel = new ViewProfileViewModel();
        PropertyChangeListenerStub listener = new PropertyChangeListenerStub();

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(listener.propertyChangedCalled);
    }

    @Test
    void viewProfileViewModel_UpdateView_UpdatesViewCorrectly() {
        // Arrange
        ViewProfileViewModel viewModel = new ViewProfileViewModel();
        viewModel.setState(new ViewProfileState());
        viewModel.getState().setUsername("TestUser");

        // Act
        String username = viewModel.getState().getUsername();

        // Assert
        assertEquals("TestUser", username);
        // Add more assertions for other properties
    }

    static class PropertyChangeListenerStub implements PropertyChangeListener {
        boolean propertyChangedCalled = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyChangedCalled = true;
        }
    }
}
