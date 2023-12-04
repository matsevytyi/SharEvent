package USE_CASE.view_event;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
public class ViewEventOutputData {

private final int eventId;
    private final String eventName;
    private final String type;
    private final String description;
    private final LocalDate eventDate;
    private final LocalTime eventTime;
    private final String creator;
    private final String registeredUsers ;




    public ViewEventOutputData(int eventId, String eventName, String type, String description, LocalDate eventDate, LocalTime eventTime, String creator, String registeredUsers) {
        this.eventName = eventName;
        this.type = type;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.creator = creator;
        this.registeredUsers = registeredUsers;
        this.eventId = eventId;
    }



}
