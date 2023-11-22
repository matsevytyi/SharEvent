package use_case.view_event;

import java.time.LocalDate;
import java.time.LocalTime;

public class ViewEventOutputData {
    public String getEventName() {
        return eventName;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getDescription() {
        return description;
    }



    public String getRegisteredUsers() {
        return registeredUsers;
    }

    private final String eventName;

    public ViewEventOutputData(String eventName, String description, LocalTime eventTime, LocalDate eventDate,  String creator, String registeredUsers) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        this.description = description;
        this.creator = creator;
        this.registeredUsers = registeredUsers;
    }

    private final LocalTime eventTime;
    private final LocalDate eventDate;

    private final String description;

    public String getCreator() {
        return creator;
    }

    private final String creator;

    private final String registeredUsers ;
}
