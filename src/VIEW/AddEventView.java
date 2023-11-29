
package VIEW;

import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventState;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@Getter @Setter
public class AddEventView extends StackPane {

    private final AddEventViewModel addEventViewModel;
    private final AddEventController addEventController;

    private final TextField eventNameInputField = new TextField();
    private final ComboBox<String> eventTypeComboBox = new ComboBox<>();
    private final DatePicker eventDatePicker = new DatePicker();
    private final TextField eventTimeField = new TextField();
    private final TextField descriptionInputField = new TextField();
    private final Button addEventButton = new Button("Add Event");

    public AddEventView(AddEventViewModel addEventViewModel, AddEventController addEventController) {
        this.addEventViewModel = addEventViewModel;
        this.addEventController = addEventController;

        initUI();
        addEventHandlers();
    }

    private void initUI() {
        Label title = new Label("Add Event");

        LabelTextPane eventNameInfo = new LabelTextPane("Event name", eventNameInputField);
        LabelTextPane eventDateInfo = new LabelTextPane("Date", eventDatePicker);
        LabelTextPane eventTimeInfo = new LabelTextPane("Time", eventTimeField);
        LabelTextPane eventDescriptionInfo = new LabelTextPane("Description", descriptionInputField);
        LabelTextPane eventTypeInfo = new LabelTextPane("Event type", eventTypeComboBox);
        eventTypeComboBox.getItems().addAll("music", "sport", "food", "lecture");
        getChildren().addAll(title, eventNameInfo, eventDateInfo, eventTimeInfo, eventDescriptionInfo,eventTypeInfo, addEventButton);

        setPadding(new Insets(10));
        VBox contentBox = new VBox(title, eventNameInfo, eventDateInfo, eventTimeInfo, eventDescriptionInfo, eventTypeInfo, addEventButton);
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));

        // Center the contentBox within the StackPane
        StackPane.setAlignment(contentBox, javafx.geometry.Pos.CENTER);

        getChildren().add(contentBox);
          addEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 AddEventState currentState = addEventViewModel.getState();
                addEventController.execute(
                        currentState.getEventName(),
                        currentState.getEventType(),
                        currentState.getEventDescription(),
                        currentState.getEventDate(),
                        currentState.getEventTime(),
                        currentState.getCreator(),
                        currentState.getEventLatitude(),
                        currentState.getEventLongitude()

                );
            }
        });
    }
    private void addEventHandlers() {
        eventNameInputField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                AddEventState currentState = addEventViewModel.getState();
                String text = eventNameInputField.getText() + event.getCharacter();
                currentState.setEventName(text);
                addEventViewModel.setState(currentState);
            }
        });

        eventDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddEventState currentState = addEventViewModel.getState();
                currentState.setEventDate(eventDatePicker.getValue());
                addEventViewModel.setState(currentState);
            }
        });

        eventTimeField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                AddEventState currentState = addEventViewModel.getState();
                String text = eventTimeField.getText() + event.getCharacter();
                currentState.setEventTime(LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm")));
                addEventViewModel.setState(currentState);
            }
        });

        descriptionInputField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                AddEventState currentState = addEventViewModel.getState();
                String text = descriptionInputField.getText() + event.getCharacter();
                currentState.setEventDescription(text);
                addEventViewModel.setState(currentState);
            }
        });

        eventTypeComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddEventState currentState = addEventViewModel.getState();
                currentState.setEventType(eventTypeComboBox.getValue());
                addEventViewModel.setState(currentState);
            }
        });
    }

}
