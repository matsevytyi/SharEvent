package interface_adapter.add_event;

import use_case.add_event.AddEventOutputBoundary;
import use_case.add_event.AddEventOutputData;
import view.MapViewModel;
import view.ViewManagerModel;


public class AddEventPresenter implements AddEventOutputBoundary {

    private final AddEventViewModel addEventViewModel;
    private final MapViewModel mapViewModel;
    private ViewManagerModel viewManagerModel;

    public AddEventPresenter(AddEventViewModel signupViewModel, MapViewModel mapViewModel, ViewManagerModel viewManagerModel) {
        this.addEventViewModel = signupViewModel;
        this.mapViewModel = mapViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddEventOutputData event) {

        MapState mapState = mapViewModel.getState();
        this.mapViewModel.setState(mapState);
        mapViewModel.firePropertyChanged();//open map again


        AddEventState addEventState = addEventViewModel.getState();
        addEventState.setEventName(addEventState.getEventName());
        addEventViewModel.firePropertyChanged(); // having message about successful adding of event

        viewManagerModel.setActiveView(mapViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddEventState addEventState= addEventViewModel.getState();
        addEventState.setEventNameError(error);
        addEventViewModel.firePropertyChanged();
    }
}
