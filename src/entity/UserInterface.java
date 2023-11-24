package ENTITY;
import java.util.ArrayList;
import java.util.List;

public interface UserInterface {
    String getName();
    String getPassword();

    String getEmail();

    int getUserId();

    List<EventInterface> getRegisteredEvents();
    List<EventInterface> getHostedEvents();

    void add_registered_event(EventInterface event);

    void remove_registered_event(EventInterface event);
    void add_hosted_event(EventInterface event);

    void remove_hosted_event(EventInterface event);

}
