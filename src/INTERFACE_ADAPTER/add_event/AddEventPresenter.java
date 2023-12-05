package INTERFACE_ADAPTER.add_event;

import INTERFACE_ADAPTER.ViewManagerModel;
import USE_CASE.add_event.AddEventOutputBoundary;
import USE_CASE.add_event.AddEventOutputData;


/**
 * The class is responsible for presenting the output of the "add event" use case.
 * It implements the  AddEventOutputBoundary interface to receive and process the output data,
 * updating the associated view models accordingly.
 */
public class AddEventPresenter implements AddEventOutputBoundary {

    private final AddEventViewModel addEventViewModel;
//    private final MapViewModel mapViewModel;
    private ViewManagerModel viewManagerModel;
    /**
     * Constructs with the specified view model and view manager model.
     * @param addEventViewModel The view model for the "add event" functionality.
     * @param viewManagerModel  The view manager model for managing views in the application.
     */
    public AddEventPresenter(AddEventViewModel addEventViewModel, ViewManagerModel viewManagerModel) {
        this.addEventViewModel = addEventViewModel;
//        this.mapViewModel = mapViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Prepares the success view based on the provided AddEventOutputData.
     * Updates the view model and triggers property change events.
     * @param event The output data containing information about the added event.
     */
    @Override
    public void prepareSuccessView(AddEventOutputData event) {

        AddEventState addEventState = addEventViewModel.getState();
        addEventState.setEventName(event.getEventName());
        addEventViewModel.firePropertyChanged(); // having message about successful adding of event
        viewManagerModel.firePropertyChanged();
    }
    /**
     * Prepares the fail view based on the provided error message.
     * Updates the view model with the error information and triggers property change events.
     * @param error The error message describing the failure.
     */
    @Override
    public void prepareFailView(String error) {
        AddEventState addEventState= addEventViewModel.getState();
        addEventState.setEventNameError(error);
        addEventViewModel.firePropertyChanged();

    }
}
