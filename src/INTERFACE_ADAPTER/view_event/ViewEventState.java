package INTERFACE_ADAPTER.view_event;

import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.JXMapViewer;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * The {@code ViewEventState} class encapsulates the state information for the "view event" functionality.
 * It includes properties such as event ID, name, type, description, date, time, creator, registered users,
 * latitude, longitude, map viewer, logged-in user, and error messages.
 */
@Getter @Setter
public class ViewEventState {

    /**
     * Sets the details of the viewed event based on the provided parameters.
     *
     * @param eventId         The ID of the event.
     * @param eventName       The name of the event.
     * @param type            The type or category of the event.
     * @param description     A description of the event.
     * @param date            The date of the event.
     * @param time            The time of the event.
     * @param createdBy       The user who created the event.
     * @param registeredUsers A string representation of registered users for the event.
     */
    public void setDetails(int eventId, String eventName, String type, String description,LocalDate date, LocalTime time, String createdBy, String registeredUsers ){
        setDate(date);
        setTime(time);
        setDescription(description);
        setCreatedBy(createdBy);
        setEventName(eventName);
        setRegisteredUsers(registeredUsers);
        setType(type);
        setEventId(eventId);
        setLoggedinuser(loggedinuser);
    }


private int eventId;
    private  String eventName ;
    private String description ;
    private String type;
    private  LocalDate date ;
        private  LocalTime time ;
        private  String createdBy;
        private  String registeredUsers ;
    private double latitude;
    private double longitude;
    private JXMapViewer mapViewer;

    private String loggedinuser;
    private String error;

    }

