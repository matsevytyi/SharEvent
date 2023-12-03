package INTERFACE_ADAPTER.view_profile;


import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.view_event.ViewEventState;
import USE_CASE.view_event.ViewEventOutputBoundary;
import USE_CASE.view_event.ViewEventOutputData;
import USE_CASE.view_profile.ViewProfileOutputBoundary;
import USE_CASE.view_profile.ViewProfileOutputData;


public class ViewProfilePresenter implements ViewProfileOutputBoundary {
    private final ViewProfileViewModel viewProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewProfilePresenter(ViewProfileViewModel viewProfileViewModel, ViewManagerModel viewManagerModel) {
        this.viewProfileViewModel = viewProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }
@Override
    public void successesView(ViewProfileOutputData user) {

        ViewProfileState profileState = viewProfileViewModel.getState();
        profileState.setDetails(user.getUsername(), user.getName(), user.getEmail(), user.getRegistered_events(), user.getHosted_events());
        viewProfileViewModel.setState(profileState);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ViewProfileState profileState = viewProfileViewModel.getState();
        profileState.setError(error);
        viewProfileViewModel.firePropertyChanged();

    }
}
