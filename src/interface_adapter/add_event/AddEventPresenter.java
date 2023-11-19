package interface_adapter.add_event;

import use_case.add_event.AddEventOutputBoundary;
import use_case.add_event.AddEventOutputData;
import view.MapViewModel;
import view.ViewManagerModel;


public class AddEventPresenter implements AddEventOutputBoundary {

    private final AddEventViewModel signupViewModel;
    private final MapViewModel mapViewModel;
    private ViewManagerModel viewManagerModel;

    public AddEventPresenter(AddEventViewModel signupViewModel, MapViewModel mapViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.mapViewModel = mapViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddEventOutputData event) {

        MapState loginState = mapViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.mapViewModel.setState(loginState);
        mapViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(mapViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddEventState signupState = signupViewModel.getState();
        signupState.setEventError(error);
        signupViewModel.firePropertyChanged();
    }
}
