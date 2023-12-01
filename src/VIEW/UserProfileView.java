package VIEW;


import INTERFACE_ADAPTER.logout_adapter.LogOutController;
import INTERFACE_ADAPTER.view_event.ViewEventState;
import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserProfileView extends VBox {
    private final Label usernameLabel = new Label();
    private final Label nameLabel = new Label();
    private final Label emailLabel = new Label();
    private final Label registeredEventsLabel = new Label();
    private final Label hostedEventsLabel = new Label();
    private final Button logoutButton = new Button("Log Out");

    private final LogOutController logOutController;

    private final ViewProfileViewModel viewProfileViewModel;

    public UserProfileView(ViewProfileViewModel viewProfileViewModel, ViewProfileController viewProfileController, LogOutController logOutController) {
        this.viewProfileViewModel = viewProfileViewModel;
        this.viewProfileController = viewProfileController;
        this.logOutController = logOutController;

        initUI();
    }

    private final ViewProfileController viewProfileController;





    private void initUI() {
        getChildren().addAll(
                new Label("User Profile"),
                new Label("Username"), usernameLabel,
                new Label("Name "), nameLabel,
                new Label("Email "),emailLabel,
                new Label("Registered Events "),registeredEventsLabel,
                new Label("Hosted Events"),hostedEventsLabel,
                new Label("Log out "),logoutButton
        );
        setSpacing(5);

        logoutButton.setOnAction(event -> handleLogoutButtonClick());

    }

    private void handleLogoutButtonClick() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //logOutController.execute();

            Platform.exit();
            System.exit(0);
        }
    }

    public void updateProfile() {
        // Встановлення нових значень для елементів інтерфейсу на основі даних користувача
        usernameLabel.setText("Username: " + viewProfileViewModel.getState().getUsername());
        nameLabel.setText("Name: " + viewProfileViewModel.getState().getName());
        emailLabel.setText("Email: " + viewProfileViewModel.getState().getEmail());
        registeredEventsLabel.setText("Registered Events: " + viewProfileViewModel.getState().getRegistered_events());
        hostedEventsLabel.setText("Hosted Events: " + viewProfileViewModel.getState().getHosted_events());
    }

}

