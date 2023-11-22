package use_case.add_event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AddEventInputData {

   // private final int eventId;
    private final String eventName;
    private final long latitude;

    public LocalTime getEventTime() {
        return eventTime;
    }

    private final  LocalTime eventTime;

    public LocalDate getEventDate() {
        return eventDate;
    }

    private final LocalDate eventDate;

    public String getEventName() {
        return eventName;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }



    public String getDescription() {
        return description;
    }

    private final long longitude;

    private final String description;
   // private final User creator;

    public AddEventInputData(String eventName, long latitude, LocalTime eventTime, long longitude, LocalDate eventDate, String description) {
       // this.eventId = eventId;
        this.eventName = eventName;
        this.latitude = latitude;
        this.eventTime = eventTime;
        this.longitude = longitude;
        this.eventDate = eventDate;
        this.description = description;
      //  this.creator = creator;
    }

}
