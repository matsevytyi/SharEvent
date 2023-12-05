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

    /**
     * Constructs a ViewProfileOutputData with the specified user profile details.
     * @param username        The username of the user.
     * @param name            The name of the user.
     * @param email           The email address of the user.
     * @param registeredEvents The list of events the user is registered for.
     * @param hostedEvents    The list of events hosted by the user.
     */
    public ViewProfileOutputData(String username, String name, String email, List<EventInterface> registeredEvents, List<EventInterface> hostedEvents) {
        this.username = username;
        this.name = name;
        this.email = email;
        registered_events = registeredEvents;
        hosted_events = hostedEvents;
    }
}
