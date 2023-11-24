package ENTITY;

import java.util.ArrayList;
import java.util.List;

class User implements UserInterface{

    private final int userId;
    private final String name;
    private final String password;

    private final String email;
    private final List<EventInterface> registered_events;
    private final List<EventInterface> hosted_events;
    User(String name, String password, String email, int userId){
        this.name = name;
        this.password = password;
        this.email = email;
        this.userId = userId;
        this.registered_events = new ArrayList<EventInterface>();
        this.hosted_events = new ArrayList<EventInterface>();
    }

    public String getName(){return this.name;}

    public String getPassword(){return this.password;}

    public String getEmail(){return this.email;}

    public int getUserId(){return this.userId;}

    public List<EventInterface> getRegisteredEvents() {
        return this.registered_events;
    }

    public List<EventInterface> getHostedEvents() {
        return this.hosted_events;
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


}
