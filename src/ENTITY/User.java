package ENTITY;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class User implements UserInterface {

    private final String username;
    private final String name;
    private final String password;
    private final String email;
    private final List<UserInterface> friends;
    private final List<EventInterface> registered_events;
    private final List<EventInterface> hosted_events;


    public User(String name, String password, String userId, String email) {
        this.name = name;
        this.password = password;
        this.username = userId;
        this.email = email;
        this.friends = new ArrayList<UserInterface>();
        this.registered_events = new ArrayList<EventInterface>();
        this.hosted_events = new ArrayList<EventInterface>();
    }


    public List<EventInterface> getRegisteredEvents() {
        return this.registered_events;
    }

    public List<EventInterface> getHostedEvents() {
        return this.hosted_events;
    }


    public void add_friend(UserInterface friend) {
        this.friends.add(friend);
    }

    public void remove_friend(UserInterface friend) {
        this.friends.remove(friend);
    }

    public void add_registered_event(EventInterface event) {
        this.registered_events.add(event);
    }

    public void remove_registered_event(EventInterface event) {
        this.registered_events.remove(event);
    }

    public void add_hosted_event(EventInterface event) {
        this.hosted_events.add(event);
    }

    public void remove_hosted_event(EventInterface event) {
        this.hosted_events.remove(event);
    }

    @Override
    public String toString(){
        return this.name;
    }
}