package view;

import interface_adapter.add_event.AddEventController;
import interface_adapter.add_event.AddEventState;
import interface_adapter.add_event.AddEventViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

public class AddEventView extends JPanel implements ActionListener, PropertyChangeListener {

    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    private Format shortTime = DateFormat.getTimeInstance(DateFormat.SHORT);
  private final AddEventViewModel addEventViewModel;
  private final AddEventController addEventController;

    final JTextField eventNameInputField = new JTextField(15);

     final  JFormattedTextField eventDate = new JFormattedTextField(df);
    final JFormattedTextField eventTime = new JFormattedTextField(shortTime);

    final JTextField descriptionInputField = new JTextField(15);

    final JButton addEvent;

    public AddEventView(AddEventViewModel addEventViewModel, AddEventController addEventController, JButton addEvent) {
        this.addEventViewModel = addEventViewModel;
        this.addEventController = addEventController;
        this.addEventViewModel.addPropertyChangeListener(this);

        this.addEvent = addEvent;


        JLabel title = new JLabel("Add Event");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel eventNameInfo = new LabelTextPanel(
                new JLabel("Username"), eventNameInputField);
        LabelTextPanel eventDateInfo = new LabelTextPanel(
                new JLabel("Date"), eventDate);
        LabelTextPanel eventTimeInfo = new LabelTextPanel(
                new JLabel("Time"), eventTime);
        LabelTextPanel eventDescriptionInfo = new LabelTextPanel(
                new JLabel("Description"), descriptionInputField);

        JPanel buttons = new JPanel();
        addEvent = new JButton(AddEventViewModel.ADD_EVENT_BUTTON_LABEL);
        buttons.add(addEvent);

        addEvent.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addEvent)) {
                            AddEventState currentState = addEventViewModel.getState();

                            addEventController.execute(
                                    currentState.getEventName(),
                                    currentState.getEventDate(),
                                    currentState.getEventTime(),
                                    currentState.getEventDescription()

                            );
                        }
                    }
                }
        );

        addEvent.addActionListener(this);

        eventNameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                AddEventState currentState = addEventViewModel.getState();
                currentState.setEventName(eventNameInputField.getText() + e.getKeyChar());
                addEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        eventDate.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                AddEventState currentState = addEventViewModel.getState();
                currentState.setEventDate(eventDate.getText() + e.getKeyChar());
                addEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        eventTime.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                AddEventState currentState = addEventViewModel.getState();
                currentState.setEventTime(eventTime.getText() + e.getKeyChar());
                addEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

       descriptionInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                AddEventState currentState = addEventViewModel.getState();
                currentState.setEventDescription(descriptionInputField.getText() + e.getKeyChar());
                addEventViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddEventState state = (AddEventState) evt.getNewValue();
        setFields(state);
    }
    private void setFields(AddEventState state) {
        eventNameInputField.setText(state.getEventName());
    }
}
