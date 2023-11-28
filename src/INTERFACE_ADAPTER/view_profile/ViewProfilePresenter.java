package INTERFACE_ADAPTER.view_profile;


import INTERFACE_ADAPTER.ViewManagerModel;
import USE_CASE.view_event.ViewEventOutputBoundary;
import USE_CASE.view_event.ViewEventOutputData;



public class ViewProfilePresenter implements ViewEventOutputBoundary {
    private final ViewProfileViewModel viewProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewProfilePresenter(ViewProfileViewModel viewProfileViewModel, ViewManagerModel viewManagerModel) {
        this.viewProfileViewModel = viewProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void successesView(ViewEventOutputData user) {

        ViewProfileState profileState = viewProfileViewModel.getState();
        profileState.setDetails();
        viewProfileViewModel.setState(profileState);
        viewManagerModel.firePropertyChanged();
    }
}
