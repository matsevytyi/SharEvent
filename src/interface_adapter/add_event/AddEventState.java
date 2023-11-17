package interface_adapter.add_event;

public class AddEventState {

    public AddEventState(String eventName, String eventNameError, String eventDate, String eventDateError, String eventTime, String eventTimeError, String eventDescription, String eventDescriptionError, String eventLongitude, String eventLongitudeError, String eventLatitude, String eventLatitudeError) {
        this.eventName = eventName;
        this.eventNameError = eventNameError;
        this.eventDate = eventDate;
        this.eventDateError = eventDateError;
        this.eventTime = eventTime;
        this.eventTimeError = eventTimeError;
        this.eventDescription = eventDescription;
        this.eventDescriptionError = eventDescriptionError;
        this.eventLongitude = eventLongitude;
        this.eventLongitudeError = eventLongitudeError;
        this.eventLatitude = eventLatitude;
        this.eventLatitudeError = eventLatitudeError;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventNameError() {
        return eventNameError;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventDateError() {
        return eventDateError;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventTimeError() {
        return eventTimeError;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventDescriptionError() {
        return eventDescriptionError;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventNameError(String eventNameError) {
        this.eventNameError = eventNameError;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDateError(String eventDateError) {
        this.eventDateError = eventDateError;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventTimeError(String eventTimeError) {
        this.eventTimeError = eventTimeError;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventDescriptionError(String eventDescriptionError) {
        this.eventDescriptionError = eventDescriptionError;
    }

    private String eventName = "";
    private String eventNameError = null;
    private String eventDate = "";
    private String eventDateError = null;

    private String eventTime = "";
    private String eventTimeError = null;
    private String eventDescription = "";
    private String eventDescriptionError = null;

    public String getEventLongitude() {
        return eventLongitude;
    }

    public void setEventLongitude(String eventLongitude) {
        this.eventLongitude = eventLongitude;
    }

    public String getEventLongitudeError() {
        return eventLongitudeError;
    }

    public void setEventLongitudeError(String eventLongitudeError) {
        this.eventLongitudeError = eventLongitudeError;
    }

    public String getEventLatitude() {
        return eventLatitude;
    }

    public void setEventLatitude(String eventLatitude) {
        this.eventLatitude = eventLatitude;
    }

    public String getEventLatitudeError() {
        return eventLatitudeError;
    }

    public void setEventLatitudeError(String eventLatitudeError) {
        this.eventLatitudeError = eventLatitudeError;
    }

    private String eventLongitude = "";

    private String eventLongitudeError = null;

    private String eventLatitude = "";
    private String eventLatitudeError = null;


    public AddEventState(){}





}
