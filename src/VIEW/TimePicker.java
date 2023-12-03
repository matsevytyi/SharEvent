package VIEW;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.LocalTime;


public class TimePicker extends HBox {

    protected final ComboBox<Integer> hourComboBox;
    protected final ComboBox<Integer> minuteComboBox;
    protected final ComboBox<String> amPmComboBox;

    public TimePicker() {
        hourComboBox = new ComboBox<>();
        minuteComboBox = new ComboBox<>();
        amPmComboBox = new ComboBox<>();

        initUI();
    }

    private void initUI() {

        for (int i = 1; i <= 12; i++) {
            hourComboBox.getItems().add(i);
        }


        for (int i = 0; i <= 59; i++) {
            minuteComboBox.getItems().add(i);
        }


        amPmComboBox.getItems().addAll("AM", "PM");


        hourComboBox.setValue(12); // Default to 12
        minuteComboBox.setValue(0); // Default to 0
        amPmComboBox.setValue("AM"); // Default to AM

        getChildren().addAll( hourComboBox, new Label(":"), minuteComboBox, amPmComboBox);
        setAlignment(Pos.CENTER);
        setSpacing(5);
    }

    public LocalTime getSelectedTime() {
        int hour = hourComboBox.getValue();
        int minute = minuteComboBox.getValue();
        if ("PM".equals(amPmComboBox.getValue()) && hour < 12) {
            hour += 12;
        } else if ("AM".equals(amPmComboBox.getValue()) && hour == 12) {
            hour = 0;
        }

        return LocalTime.of(hour, minute);
    }




}
