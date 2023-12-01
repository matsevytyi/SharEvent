
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
public class AddEventView extends VBox {

    private final AddEventViewModel addEventViewModel;
    private final AddEventController addEventController;

    private final TextField eventNameInputField = new TextField();
    private final ComboBox<String> eventTypeComboBox = new ComboBox<>();
    private final DatePicker eventDatePicker = new DatePicker();
    private final TimePicker eventTimePicker = new TimePicker();
    private final TextField descriptionInputField = new TextField();
    private final Button addEventButton = new Button("Add Event");

    public AddEventView(AddEventViewModel addEventViewModel, AddEventController addEventController) {
        this.addEventViewModel = addEventViewModel;
        this.addEventController = addEventController;
        initUI();


    }


    private void initUI() {
        Label title = new Label("Add Event");

        LabelTextPane eventNameInfo = new LabelTextPane("Event name", eventNameInputField);
        LabelTextPane eventDateInfo = new LabelTextPane("Date", eventDatePicker);
        LabelTextPane eventTimeInfo = new LabelTextPane("Time", eventTimePicker);
        LabelTextPane eventDescriptionInfo = new LabelTextPane("Description", descriptionInputField);
        LabelTextPane eventTypeInfo = new LabelTextPane("Event type", eventTypeComboBox);

        eventTypeComboBox.getItems().addAll("Sports and Fitness", "Music", "Food and Drinks", "Gaming", "Education and Learning", "Outdoors and Adventure", "Other");
        getChildren().addAll(title, eventNameInfo, eventDateInfo, eventTimeInfo, eventDescriptionInfo, eventTypeInfo, addEventButton);

        setPadding(new Insets(10));
        VBox contentBox = new VBox(title, eventNameInfo, eventDateInfo, eventTimeInfo, eventDescriptionInfo, eventTypeInfo, addEventButton);
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));

        // Center the contentBox within the StackPane
        StackPane.setAlignment(contentBox, javafx.geometry.Pos.CENTER);

        getChildren().add(contentBox);
    }

}
