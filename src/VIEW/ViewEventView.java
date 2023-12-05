package VIEW;

import INTERFACE_ADAPTER.add_event.AddEventState;
import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventState;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.register_for_event.RegisterController;
import INTERFACE_ADAPTER.view_event.ViewEventState;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import VIEW_CREATOR.FailViewFactory;
import VIEW_CREATOR.LoadMapViewModel;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ViewEventView extends VBox implements PropertyChangeListener {
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

    private final RegisterController registerEventController;



    public ViewEventView(ViewEventViewModel viewEventViewModel, DeleteEventController deleteEventController, DeleteEventViewModel deleteEventViewModel, RegisterController registerEventController) {
        this.viewEventViewModel = viewEventViewModel;
        this.deleteEventController = deleteEventController;
        this.deleteEventViewModel = deleteEventViewModel;

        this.registerEventController = registerEventController;
viewEventViewModel.addPropertyChangeListener(this);
        initUI();
    }
    /**
     * Initializes the user interface components for the ViewEvent" view.
     */
    private void initUI() {
        Label title = new Label("Event Details");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #3B59B6;");
        getChildren().addAll(
                 title,
                new Label("Type: "), typeLabel,
                new Label("Event Date: "), eventDateLabel,
                new Label("Event Time: "), eventTimeLabel,
                new Label("Description: "), descriptionLabel,
                new Label("Creator: "), creatorLabel,
                new Label("Attendants: "), attendantsLabel,
                actionButton
        );
        actionButton.setStyle("-fx-text-fill: #3B59B6; -fx-font-weight: bold; -fx-font-size: 16;-fx-padding: 10;");
        setSpacing(5);
        setPadding(new Insets(10));
        actionButton.setOnAction(event -> handleActionButtonClick());
    }
    /**
     * Updates the view with the latest information from the ViewEventViewModel.
     */
    public void updateView() {
        eventNameLabel.setText(viewEventViewModel.getState().getEventName());
        typeLabel.setText(viewEventViewModel.getState().getType());
        eventDateLabel.setText(String.valueOf(viewEventViewModel.getState().getDate()));
        eventTimeLabel.setText(String.valueOf(viewEventViewModel.getState().getTime()));
        descriptionLabel.setText(viewEventViewModel.getState().getDescription());
        creatorLabel.setText(viewEventViewModel.getState().getCreatedBy());
        attendantsLabel.setText(viewEventViewModel.getState().getRegisteredUsers());

        boolean isCreator = isUserCreator(viewEventViewModel.getState().getCreatedBy());
        actionButton.setText(isCreator ? "Delete" : "Register for Event");
    }
    /**
     * Checks if the logged-in user is the creator of the event.
     * @param eventCreator The username of the event creator.
     * @return true if the logged-in user is the creator, false otherwise.
     */
    private boolean isUserCreator(String eventCreator) {

        String loggedInUser = viewEventViewModel.getLoggedInUser();
        return loggedInUser != null && loggedInUser.equals(eventCreator);
    }
    /**
     * Handles the click event of the action button .
     * If the text is "Delete," a confirmation dialog is shown, and the event is deleted.
     * If the text is "Register for Event," a confirmation dialog is shown, and the user is registered for the event upon confirmation.
     */
    public void handleActionButtonClick() {

        String buttonText = actionButton.getText();
        if ("Delete".equals(buttonText)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this event?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                ViewEventState viewEventState = viewEventViewModel.getState();
                deleteEventController.execute(viewEventState.getEventId());

            }
        } else if ("Register for Event".equals(buttonText)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to register this event?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                ViewEventState viewEventState = viewEventViewModel.getState();
                registerEventController.execute(viewEventState.getEventId(), viewEventState.getLoggedinuser());


            }
        }
    }
    /**
     * Responds to property change events and updates the view accordingly.
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        ViewEventState viewEventState =( ViewEventState) evt.getNewValue();
        if (viewEventState.getError() != null) {
            FailViewFactory.showAlert("Error",viewEventState.getError(), Alert.AlertType.INFORMATION);

        }
    }


}
