package USE_CASE.view_profile;

import ENTITY.EventInterface;
import lombok.Getter;

import java.util.List;

@Getter
public class ViewProfileOutputData {

    private final String username;
    private final String name;
    private final String email;
    private final List<EventInterface> registered_events;
    private final List<EventInterface> hosted_events;


    public ViewProfileOutputData(String username, String name, String email, List<EventInterface> registeredEvents, List<EventInterface> hostedEvents) {
        this.username = username;
        this.name = name;
        this.email = email;
        registered_events = registeredEvents;
        hosted_events = hostedEvents;
    }
}
