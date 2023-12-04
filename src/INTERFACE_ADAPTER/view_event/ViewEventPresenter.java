package INTERFACE_ADAPTER.view_event;


import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventState;
import USE_CASE.view_event.ViewEventOutputBoundary;
import USE_CASE.view_event.ViewEventOutputData;


/**
 * The {@code ViewEventPresenter} class is responsible for presenting the output of the "view event" use case.
 * It implements the {@link ViewEventOutputBoundary} interface to receive and process the output data,
 * updating the associated view models accordingly.
 */
public class ViewEventPresenter implements ViewEventOutputBoundary {
    private final ViewEventViewModel viewEventViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a {@code ViewEventPresenter} with the specified view model and view manager model.
     *
     * @param viewEventViewModel The view model for the "view event" functionality.
     * @param viewManagerModel   The view manager model for managing views in the application.
     */
    public ViewEventPresenter(ViewEventViewModel viewEventViewModel, ViewManagerModel viewManagerModel) {
        this.viewEventViewModel = viewEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Prepares the success view based on the provided {@link ViewEventOutputData}.
     * Updates the view model with the event details and triggers property change events.
     *
     * @param event The output data containing information about the viewed event.
     */
    public void successesView(ViewEventOutputData event) {

        ViewEventState viewEventState = viewEventViewModel.getState();
        viewEventState.setDetails(event.getEventId(),event.getEventName(),event.getType(), event.getDescription(), event.getEventDate(), event.getEventTime(), event.getCreator(), event.getRegisteredUsers());
        viewEventViewModel.setState(viewEventState);
        viewManagerModel.firePropertyChanged();
    }
    /**
     * Prepares the fail view based on the provided error message.
     * Updates the view model with the error information and triggers property change events.
     *
     * @param error The error message describing the failure.
     */
    @Override
    public void prepareFailView(String error) {
        ViewEventState viewEventState = viewEventViewModel.getState();
        viewEventState.setError(error);
        viewEventViewModel.firePropertyChanged();

    }


}
