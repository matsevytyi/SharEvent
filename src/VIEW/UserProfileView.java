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
                usernameLabel,
                nameLabel,
                emailLabel,
                registeredEventsLabel,
                hostedEventsLabel,
                logoutButton
        );
        setSpacing(5);

        logoutButton.setOnAction(event -> {
          //////////////////
        });
    }


}

