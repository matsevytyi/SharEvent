package INTERFACE_ADAPTER.view_profile;


import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.view_event.ViewEventState;
import USE_CASE.view_event.ViewEventOutputBoundary;
import USE_CASE.view_event.ViewEventOutputData;
import USE_CASE.view_profile.ViewProfileOutputBoundary;
import USE_CASE.view_profile.ViewProfileOutputData;

/**
 * The class is responsible for presenting the output of the "view profile" use case.
 * It implements the ViewProfileOutputBoundary interface to receive and process the output data,
 * updating the associated view models accordingly.
 */
public class ViewProfilePresenter implements ViewProfileOutputBoundary {
    private final ViewProfileViewModel viewProfileViewModel;
    private final ViewManagerModel viewManagerModel;
    /**
     * Constructor of class with the specified view model and view manager model.
     * @param viewProfileViewModel The view model for the "view profile" functionality.
     * @param viewManagerModel The view manager model for managing views in the application.
     */
    public ViewProfilePresenter(ViewProfileViewModel viewProfileViewModel, ViewManagerModel viewManagerModel) {
        this.viewProfileViewModel = viewProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * This method prepares the success view based on the provided ViewProfileOutputData.
     * Updates the view model with the user details and triggers property change events.
     * @param user The output data containing information about the viewed user profile.
     */
@Override
    public void successesView(ViewProfileOutputData user) {

        ViewProfileState profileState = viewProfileViewModel.getState();
        profileState.setDetails(user.getUsername(), user.getName(), user.getEmail(), user.getRegistered_events(), user.getHosted_events());
        viewProfileViewModel.setState(profileState);
        viewManagerModel.firePropertyChanged();
    }
    /**
     * This method prepares the fail view based on the provided error message.
     * Updates the view model with the error information and triggers property change events.
     * @param error The message describing the error.
     */
    @Override
    public void prepareFailView(String error) {
        ViewProfileState profileState = viewProfileViewModel.getState();
        profileState.setError(error);
        viewProfileViewModel.firePropertyChanged();

    }
}
