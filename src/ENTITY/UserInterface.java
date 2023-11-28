package ENTITY;
import java.util.List;

public interface UserInterface {
//    String getName();
//    String getPassword();
//
//    List<UserInterface> getFriends();

    List<EventInterface> getRegisteredEvents();

    List<EventInterface> getHostedEvents();
//    int getUsername();
//    void add_friend(UserInterface friend);
//    void remove_friend(UserInterface friend);

    void add_registered_event(EventInterface event);

    void remove_registered_event(EventInterface event);
    void add_hosted_event(EventInterface event);

    void remove_hosted_event(EventInterface event);

}