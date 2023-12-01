package VIEW;


import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserProfileView extends VBox {
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


        logoutButton.setOnAction(event -> {
          //////////////////
        });
    }

    public void updateProfile() {
        // Встановлення нових значень для елементів інтерфейсу на основі даних користувача
        usernameLabel.setText( viewProfileViewModel.getState().getUsername());
        nameLabel.setText(viewProfileViewModel.getState().getName());
        emailLabel.setText( viewProfileViewModel.getState().getEmail());
        registeredEventsLabel.setText(viewProfileViewModel.getState().getRegistered_events().toString());
        hostedEventsLabel.setText(viewProfileViewModel.getState().getHosted_events().toString());
    }

}

