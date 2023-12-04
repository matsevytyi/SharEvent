/**
 * The RegisterPresenter class is responsible for preparing views based on the outcome of the user registration process.
 */

package INTERFACE_ADAPTER.register_for_event;

import INTERFACE_ADAPTER.ViewManagerModel;

import USE_CASE.register_for_event.RegisterOutputBoundary;
import javafx.scene.control.Alert;
import lombok.Setter;

@Setter
public class RegisterPresenter implements RegisterOutputBoundary {
    private final RegisterViewModel registerViewModel;

    /**
     * Constructs a new RegisterPresenter with the specified RegisterViewModel and ViewManagerModel.
     *
     * @param registerViewModel The view model associated with user registration.
     * @param viewManagerModel  The model managing views in the application.
     */
    public RegisterPresenter(RegisterViewModel registerViewModel, ViewManagerModel viewManagerModel) {
        this.registerViewModel = registerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    private final ViewManagerModel viewManagerModel;

    /**
     * Prepares the view for a successful user registration.
     */
    @Override
    public void prepareSuccessCase() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You registered for this event successfully!");
        alert.showAndWait();
    }


}
