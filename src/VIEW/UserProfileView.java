package VIEW;


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



    public UserProfileView(String username, String name, String email, String registeredEvents, String hostedEvents, LogoutListener logoutListener) {
        this.usernameLabel.setText("Username: " + username);
        this.nameLabel.setText("Name: " + name);
        this.emailLabel.setText("Email: " + email);
        this.registeredEventsLabel.setText("Registered Events: " + registeredEvents);
        this.hostedEventsLabel.setText("Hosted Events: " + hostedEvents);



        initUI();
    }

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

