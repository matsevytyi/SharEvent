package INTERFACE_ADAPTER.delete_event;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventState;
import USE_CASE.delete_event.DeleteEventOutputBoundary;
import USE_CASE.delete_event.DeleteEventOutputData;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * The {@code DeleteEventPresenter} class is responsible for presenting the output of the "delete event" use case.
 * It implements the  DeleteEventOutputBoundary interface to receive and process the output data,
 * updating the associated view models accordingly.
 */
public class DeleteEventPresenter implements DeleteEventOutputBoundary {

private final DeleteEventViewModel deleteEventViewModel;
    /**
     * Constructs a DeleteEventPresenter with the specified view model and view manager model.
     * @param deleteEventViewModel The view model for the "delete event" functionality.
     * @param viewManagerModel     The view manager model for managing views in the application.
     */
    public DeleteEventPresenter(DeleteEventViewModel deleteEventViewModel, ViewManagerModel viewManagerModel) {
        this.deleteEventViewModel = deleteEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    private final ViewManagerModel viewManagerModel;
    /**
     * Prepares the success view based on the provided  DeleteEventOutputData.
     * Updates the view model and triggers property change events. Also, shows an information alert.
     * @param event The output data containing information about the deleted event.
     */
    @Override
    public void prepareSuccessCase(DeleteEventOutputData event) {
        DeleteEventState deleteEventState = deleteEventViewModel.getState();
        deleteEventState.setDeletedEventName(event.getDeletedEvent());
        deleteEventViewModel.firePropertyChanged();
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event " + event.getDeletedEvent() + " deleted successfully!");
            alert.showAndWait();
        });

    }

    /**
     * Prepares the fail view based on the provided error message.
     * Updates the view model with the error information and triggers property change events.
     * @param error The error message describing the failure.
     */
    @Override
    public void prepareFailCase(String error) {
        DeleteEventState deleteEventState= deleteEventViewModel.getState();
        deleteEventState.setDeletedEventError(error);
        deleteEventViewModel.firePropertyChanged();
    }
}
