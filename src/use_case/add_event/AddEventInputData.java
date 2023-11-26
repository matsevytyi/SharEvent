package use_case.add_event;

import entity.User;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
public class AddEventInputData {



    private final String eventName;
    private final  String type;
    private final LocalDate eventDate ;
    private final LocalTime eventTime;
    private final String description;
    private final User creator;
    private final List<User> eventAttendants;
    private final double latitude;
    private final double longitude;

    public AddEventInputData(String eventName, String type, String description, LocalDate eventDate, LocalTime eventTime, User creator, List<User> eventAttendants, double latitude, double longitude) {

        this.eventName = eventName;
        this.type = type;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
        this.eventAttendants = eventAttendants;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creator = creator;
    }




}
