package INTERFACE_ADAPTER.delete_event;

import INTERFACE_ADAPTER.ViewManagerModel;
import USE_CASE.delete_event.DeleteEventOutputBoundary;
import USE_CASE.delete_event.DeleteEventOutputData;
import javafx.scene.control.Alert;


public class DeleteEventPresenter implements DeleteEventOutputBoundary {

private final DeleteEventViewModel deleteEventViewModel;
    public DeleteEventPresenter(DeleteEventViewModel deleteEventViewModel, ViewManagerModel viewManagerModel) {
        this.deleteEventViewModel = deleteEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    private final ViewManagerModel viewManagerModel;

    @Override
    public void prepareSuccessCase(DeleteEventOutputData event) {
        DeleteEventState deleteEventState = deleteEventViewModel.getState();
        deleteEventState.setDeletedEventName(event.getDeletedEvent());
        deleteEventViewModel.firePropertyChanged();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event " +  event.getDeletedEvent() + "deleted successfully!");
        alert.showAndWait();
    }


    @Override
    public void prepareFailCase(String error) {

    }
}
