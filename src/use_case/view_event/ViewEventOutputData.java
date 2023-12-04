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



    /**
     * Constructs a ViewEventOutputData with the specified event details.
     * @param eventId         The identifier of the event.
     * @param eventName       The name of the event.
     * @param type            The type of the event.
     * @param description     The description of the event.
     * @param eventDate       The date of the event.
     * @param eventTime       The time of the event.
     * @param creator         The creator of the event.
     * @param registeredUsers The list of registered users for the event.
     */
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
