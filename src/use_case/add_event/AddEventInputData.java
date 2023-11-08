package use_case.add_event;

import java.util.Date;

public class AddEventInputData {

   // private final int eventId;
    private final String eventName;
    private final long latitude;

    public String getEventName() {
        return eventName;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongtitude() {
        return longtitude;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getDescription() {
        return description;
    }

    private final long longtitude;
    private final Date eventDate;
    private final String description;
   // private final User creator;

    public AddEventInputData(String eventName, long latitude, long longtitude, Date eventDate, String description) {
       // this.eventId = eventId;
        this.eventName = eventName;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.eventDate = eventDate;
        this.description = description;
      //  this.creator = creator;
    }

}
