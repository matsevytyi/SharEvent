package interface_adapter.add_event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AddEventState {


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventNameError() {
        return eventNameError;
    }

    public void setEventNameError(String eventNameError) {
        this.eventNameError = eventNameError;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDateError() {
        return eventDateError;
    }

    public void setEventDateError(String eventDateError) {
        this.eventDateError = eventDateError;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTimeError() {
        return eventTimeError;
    }

    public void setEventTimeError(String eventTimeError) {
        this.eventTimeError = eventTimeError;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDescriptionError() {
        return eventDescriptionError;
    }

    public void setEventDescriptionError(String eventDescriptionError) {
        this.eventDescriptionError = eventDescriptionError;
    }

    public int getEventLongitude() {
        return eventLongitude;
    }

    public void setEventLongitude(int eventLongitude) {
        this.eventLongitude = eventLongitude;
    }

    public String getEventLongitudeError() {
        return eventLongitudeError;
    }

    public void setEventLongitudeError(String eventLongitudeError) {
        this.eventLongitudeError = eventLongitudeError;
    }

    public int getEventLatitude() {
        return eventLatitude;
    }

    public void setEventLatitude(int eventLatitude) {
        this.eventLatitude = eventLatitude;
    }

    public String getEventLatitudeError() {
        return eventLatitudeError;
    }

    public void setEventLatitudeError(String eventLatitudeError) {
        this.eventLatitudeError = eventLatitudeError;
    }

    private String eventName = "";
    private String eventNameError = null;
    private LocalDate eventDate = null;
    private String eventDateError = null;
    private LocalTime eventTime = null;
    private String eventTimeError = null;
    private String eventDescription = "";
    private String eventDescriptionError = null;
    private int eventLongitude = 0;

    private String eventLongitudeError = null;

    private int eventLatitude = 0;
    private String eventLatitudeError = null;


    public AddEventState(){}





}
