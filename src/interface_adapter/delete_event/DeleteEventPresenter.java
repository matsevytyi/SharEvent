package interface_adapter.delete_event;

import javafx.scene.control.Alert;
import use_case.delete_event.DeleteEventOutputBoundary;
import use_case.delete_event.DeleteEventOutputData;
import view.ViewManagerModel;

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

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event deleted successfully!");
        alert.showAndWait();
    }

    @Override
    public void prepareFailCase(String error) {

    }
}
