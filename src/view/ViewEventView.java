package view;

import entity.User;
import interface_adapter.delete_event.DeleteEventController;
import interface_adapter.delete_event.DeleteEventState;
import interface_adapter.delete_event.DeleteEventViewModel;
import interface_adapter.view_event.ViewEventViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class ViewEventView extends VBox {
    private final Label eventNameLabel = new Label();
    private final Label typeLabel = new Label();
    private final Label eventDateLabel = new Label();
    private final Label eventTimeLabel = new Label();
    private final Label descriptionLabel = new Label();
    private final Label creatorLabel = new Label();
    private final Label attendantsLabel = new Label();
    private final Button actionButton = new Button();

    private final ViewEventViewModel viewEventViewModel;

    private final DeleteEventController deleteEventController;

    private final DeleteEventViewModel deleteEventViewModel;



    public ViewEventView(ViewEventViewModel viewEventViewModel, DeleteEventController deleteEventController, DeleteEventViewModel deleteEventViewModel) {
        this.viewEventViewModel = viewEventViewModel;
        this.deleteEventController = deleteEventController;
        this.deleteEventViewModel = deleteEventViewModel;
        initUI();
    }

    private void initUI() {
        getChildren().addAll(
                new Label("Event Name: "), eventNameLabel,
                new Label("Type: "), typeLabel,
                new Label("Event Date: "), eventDateLabel,
                new Label("Event Time: "), eventTimeLabel,
                new Label("Description: "), descriptionLabel,
                new Label("Creator: "), creatorLabel,
                new Label("Attendants: "), attendantsLabel,
                new Label("Action: "), actionButton
        );
        setSpacing(5);
        setPadding(new Insets(10));
        actionButton.setOnAction(event -> handleActionButtonClick());
    }

    public void updateView() {
        // Assuming ViewEventViewModel has appropriate getters
        eventNameLabel.setText(viewEventViewModel.getState().getEventName());
        typeLabel.setText(viewEventViewModel.getState().getType());
        eventDateLabel.setText(String.valueOf(viewEventViewModel.getState().getDate()));
        eventTimeLabel.setText(String.valueOf(viewEventViewModel.getState().getTime()));
        descriptionLabel.setText(viewEventViewModel.getState().getDescription());
        creatorLabel.setText(viewEventViewModel.getState().getCreatedBy());
        attendantsLabel.setText(viewEventViewModel.getState().getRegisteredUsers());

        boolean isCreator = isUserCreator(viewEventViewModel.getState().getCreatedBy());

        // Set the text of the actionButton based on the user's role
        actionButton.setText(isCreator ? "Delete" : "Register for Event");
    }

    private boolean isUserCreator(String eventCreator) {
        // Get the logged-in user from your authentication system
        User loggedInUser = INTERFACE_ADAPTER.map_adapter.getLoggedInUser(); // Adjust this according to your actual implementation

        // Compare the usernames
        return loggedInUser != null && loggedInUser.getUsername().equals(eventCreator);
    }

    private void handleActionButtonClick() {
        // Implement the logic for the actionButton click based on the text
        String buttonText = actionButton.getText();
        if ("Delete".equals(buttonText)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this event?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                DeleteEventState deleteEventState = new DeleteEventState();
                deleteEventController.execute(deleteEventState.getDeletedEventId());

            }
        } else if ("Register for Event".equals(buttonText)) {

            // Implement register for event logic
        }
    }
}
