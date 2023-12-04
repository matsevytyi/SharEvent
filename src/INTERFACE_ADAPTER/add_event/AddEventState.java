package INTERFACE_ADAPTER.add_event;

import ENTITY.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
/**
 * The {@code AddEventState} class encapsulates the state information for the "add event" functionality.
 * It includes properties such as event name, type, description, date, time, creator, attendants,
 * longitude, latitude, and corresponding error messages.
 */
@Getter @Setter
public class AddEventState {


    private String eventName = null;
    private String eventNameError = null;
    private String eventType = null;
    private String eventTypeError = null;
    private String eventDescription = null;
    private String eventDescriptionError = null;
    private LocalDate eventDate = null;
    private String eventDateError = null;
    private LocalTime eventTime = null;
    private String eventTimeError = null;

    private User creator = null;
    private List<User> attendant = null;
    private double eventLongitude = 0;

    private String eventLongitudeError = null;

    private double eventLatitude = 0;
    private String eventLatitudeError = null;


    public AddEventState(){}





}
