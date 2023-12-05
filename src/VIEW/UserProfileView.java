package VIEW;


import INTERFACE_ADAPTER.view_event.ViewEventState;
import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfileState;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;

import javafx.application.Platform;

import VIEW_CREATOR.FailViewFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@Getter
public class UserProfileView extends VBox implements PropertyChangeListener {
    private final Label usernameLabel = new Label();
    private final Label nameLabel = new Label();
    private final Label emailLabel = new Label();
    private final Label registeredEventsLabel = new Label();
    private final Label hostedEventsLabel = new Label();
    private final Button logoutButton = new Button("Log Out");

    private final ViewProfileViewModel viewProfileViewModel;

    public UserProfileView(ViewProfileViewModel viewProfileViewModel, ViewProfileController viewProfileController) {
        this.viewProfileViewModel = viewProfileViewModel;
        this.viewProfileController = viewProfileController;
        viewProfileViewModel.addPropertyChangeListener(this);

        initUI();
    }

    private final ViewProfileController viewProfileController;
    /**
     * Initializes the user interface components for the "View profile" view.
     */
    private void initUI() {
        Label title = new Label("User Profile");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #3B59B6;");

        getChildren().addAll(
                title,
                new Label("Username:"), usernameLabel,
                new Label("Name: "), nameLabel,
                new Label("Email: "),emailLabel,
                new Label("Registered Events: "),registeredEventsLabel,
                new Label("Hosted Events:"),hostedEventsLabel,
                new Label("Log out "),logoutButton
        );
        setSpacing(5);
        logoutButton.setStyle("-fx-text-fill: #3B59B6; -fx-font-weight: bold; -fx-font-size: 16;-fx-padding: 10;");

        logoutButton.setOnAction(event -> handleLogoutButtonClick());

    }

    private void handleLogoutButtonClick() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            Platform.exit();
            System.exit(0);
        }

    }

    /**
     * Update all fields of window with received from state information
     */
    public void updateProfile() {
        usernameLabel.setText( viewProfileViewModel.getState().getUsername());
        nameLabel.setText(viewProfileViewModel.getState().getName());
        emailLabel.setText( viewProfileViewModel.getState().getEmail());
        registeredEventsLabel.setText(viewProfileViewModel.getState().getRegistered_events().toString());
        hostedEventsLabel.setText(viewProfileViewModel.getState().getHosted_events().toString());
    }
    /**
     * Responds to property change events and updates the view accordingly.
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewProfileState viewProfileState = (ViewProfileState) evt.getNewValue();
        if (viewProfileState.getError() != null) {
            FailViewFactory.showAlert("Error",viewProfileState.getError(), Alert.AlertType.INFORMATION);

        }
    }
}

