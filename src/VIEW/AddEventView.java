
package VIEW;

import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventState;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static VIEW_CREATOR.FailViewFactory.showAlert;

@Getter @Setter
public class AddEventView extends VBox implements PropertyChangeListener {

    private final AddEventViewModel addEventViewModel;
    private final AddEventController addEventController;

    private final TextField eventNameInputField = new TextField();
    private final ComboBox<String> eventTypeComboBox = new ComboBox<>();
    private final DatePicker eventDatePicker = new DatePicker();
    private final TimePicker eventTimePicker = new TimePicker();
    private final TextField descriptionInputField = new TextField();
    private final Button addEventButton = new Button(AddEventViewModel.ADD_EVENT_BUTTON_LABEL);

    /**
     * Constructs an AddEventView with the specified view model and controller.
     * @param addEventViewModel  The view model responsible for managing the state of the add event view.
     * @param addEventController The controller responsible for handling user actions in the add event view.
     */
    public AddEventView(AddEventViewModel addEventViewModel, AddEventController addEventController) {
        this.addEventViewModel = addEventViewModel;
        this.addEventController = addEventController;
        addEventViewModel.addPropertyChangeListener(this);
        initUI();


    }

    /**
     * Initializes the user interface components for the "Add Event" view.
     */
    private void initUI() {
        Label title = new Label("Add Event");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #3B59B6;");

        LabelTextPane eventNameInfo = new LabelTextPane("Event name", eventNameInputField);
        LabelTextPane eventDateInfo = new LabelTextPane("Date", eventDatePicker);
        LabelTextPane eventTimeInfo = new LabelTextPane("Time", eventTimePicker);
        LabelTextPane eventDescriptionInfo = new LabelTextPane("Description", descriptionInputField);
        LabelTextPane eventTypeInfo = new LabelTextPane("Event type", eventTypeComboBox);

        eventTypeComboBox.getItems().addAll("Sports and Fitness", "Music", "Food and Drinks", "Gaming", "Education and Learning", "Outdoors and Adventure", "Other");

        addEventButton.setStyle("-fx-text-fill: #3B59B6; -fx-font-weight: bold; -fx-font-size: 16;-fx-padding: 10;");

        getChildren().addAll(title, eventNameInfo, eventDateInfo, eventTimeInfo, eventDescriptionInfo, eventTypeInfo, addEventButton);

        setPadding(new Insets(10));
        VBox contentBox = new VBox(eventNameInfo, eventDateInfo, eventTimeInfo, eventDescriptionInfo, eventTypeInfo, addEventButton);
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));
        StackPane.setAlignment(contentBox, javafx.geometry.Pos.CENTER);

        getChildren().add(contentBox);
    }
    /**
     * Responds to property change events and updates the view accordingly.
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        AddEventState addEventState = (AddEventState) evt.getNewValue();
        if(addEventState.getEventName() != null){
            showAlert("Done","You successfully added event " + addEventState.getEventName(), Alert.AlertType.INFORMATION);
        } else if (addEventState.getEventNameError() != null) {
            showAlert("Error",addEventState.getEventNameError(), Alert.AlertType.INFORMATION);
        }
    }


}
