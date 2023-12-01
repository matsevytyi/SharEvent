package INTERFACE_ADAPTER.register_for_event;

import INTERFACE_ADAPTER.ViewManagerModel;

import USE_CASE.register_for_event.RegisterOutputBoundary;
import javafx.scene.control.Alert;

public class RegisterPresenter implements RegisterOutputBoundary {
    private final RegisterViewModel registerViewModel;

    public RegisterPresenter(RegisterViewModel registerViewModel, ViewManagerModel viewManagerModel) {
        this.registerViewModel = registerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    private final ViewManagerModel viewManagerModel;



    @Override
    public void prepareSuccessCase() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You registered for this event successfully!");
        alert.showAndWait();
    }


    @Override
    public void prepareFailCase(String error) {

    }
}
