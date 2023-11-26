//package view;
//
//import interface_adapter.add_event.AddEventController;
//import interface_adapter.add_event.AddEventState;
//import interface_adapter.add_event.AddEventViewModel;
//import org.jxmapviewer.input.MapClickListener;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.text.DateFormat;
//import java.text.Format;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//
//public class AddEventView extends JPanel implements ActionListener, PropertyChangeListener {
//
//    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//    private Format shortTime = DateFormat.getTimeInstance(DateFormat.SHORT);
//  private final AddEventViewModel addEventViewModel;
//  private final AddEventController addEventController;
//
//    final JTextField eventNameInputField = new JTextField(15);
//
//     final  JFormattedTextField eventDate = new JFormattedTextField(df);
//    final JFormattedTextField eventTime = new JFormattedTextField(shortTime);
//
//    final JTextField descriptionInputField = new JTextField(15);
//
//    final JButton addEvent;
//    //private MapView mapView;
//
//    public AddEventView(AddEventViewModel addEventViewModel, AddEventController addEventController, JButton addEvent) {
//        this.addEventViewModel = addEventViewModel;
//        this.addEventController = addEventController;
//
//        this.addEventViewModel.addPropertyChangeListener(this);
//
//        this.addEvent = addEvent;
//
//
//        JLabel title = new JLabel("Add Event");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        LabelTextPanel eventNameInfo = new LabelTextPanel(
//                new JLabel("Username"), eventNameInputField);
//        LabelTextPanel eventDateInfo = new LabelTextPanel(
//                new JLabel("Date"), eventDate);
//        LabelTextPanel eventTimeInfo = new LabelTextPanel(
//                new JLabel("Time"), eventTime);
//        LabelTextPanel eventDescriptionInfo = new LabelTextPanel(
//                new JLabel("Description"), descriptionInputField);
//
//        JPanel buttons = new JPanel();
//        addEvent = new JButton(AddEventViewModel.ADD_EVENT_BUTTON_LABEL);
//        buttons.add(addEvent);
//
//
////        mapView = new MapView();
////        mapView.addMapClickListener(new MapClickListener() {
////            @Override
////            public void onMapClick(LatLng point) {
////                // Handle map click, pass latitude and longitude to the controller
////                addEventController.executeMapClick(point.getLatitude(), point.getLongitude());
////            }
////        });
//
////        this.add(mapView);
//
//        JButton finalAddEvent = addEvent;
//        addEvent.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(finalAddEvent)) {
//                            AddEventState currentState = addEventViewModel.getState();
//
//                            addEventController.execute(
//                                    currentState.getEventName(),
//                                    currentState.getEventLongitude(),
//                                    currentState.getEventLatitude(),
//                                    currentState.getEventDate(),
//                                    currentState.getEventTime(),
//                                    currentState.getEventDescription()
//
//                            );
//                        }
//                    }
//                }
//        );
//
//        addEvent.addActionListener(this);
//
//        eventNameInputField.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                AddEventState currentState = addEventViewModel.getState();
//                currentState.setEventName(eventNameInputField.getText() + e.getKeyChar());
//                addEventViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//
//        eventDate.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                AddEventState currentState = addEventViewModel.getState();
//
//                String dateString = eventDate.getText() + e.getKeyChar();
//                LocalDate parsedDate = LocalDate.parse(dateString);
//                currentState.setEventDate(parsedDate);
//                addEventViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        eventTime.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                AddEventState currentState = addEventViewModel.getState();
//                String timeString = eventTime.getText() + e.getKeyChar();
//                LocalTime parsedTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
//
//                currentState.setEventTime(parsedTime);
//                addEventViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//       descriptionInputField.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                AddEventState currentState = addEventViewModel.getState();
//                currentState.setEventDescription(descriptionInputField.getText() + e.getKeyChar());
//                addEventViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//    }
//
//    @Override
//
//    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        AddEventState state = (AddEventState) evt.getNewValue();
//        setFields(state);
//    }
//    private void setFields(AddEventState state) {
//        eventNameInputField.setText(state.getEventName());
//    }
//}
package view;

import interface_adapter.add_event.AddEventController;
import interface_adapter.add_event.AddEventState;
import interface_adapter.add_event.AddEventViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddEventView extends VBox {

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
        setSpacing(10);
        setPadding(new Insets(10));

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
