package INTERFACE_ADAPTER.add_event;

import INTERFACE_ADAPTER.ViewManagerModel;
import USE_CASE.add_event.AddEventOutputBoundary;
import USE_CASE.add_event.AddEventOutputData;



public class AddEventPresenter implements AddEventOutputBoundary {

    private final AddEventViewModel addEventViewModel;
//    private final MapViewModel mapViewModel;
    private ViewManagerModel viewManagerModel;

    public AddEventPresenter(AddEventViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.addEventViewModel = signupViewModel;
//        this.mapViewModel = mapViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddEventOutputData event) {



        AddEventState addEventState = addEventViewModel.getState();
        addEventState.setEventName(event.getEventName());
        addEventViewModel.firePropertyChanged(); // having message about successful adding of event

        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddEventState addEventState= addEventViewModel.getState();
        addEventState.setEventNameError(error);
        addEventViewModel.firePropertyChanged();

    }
}
