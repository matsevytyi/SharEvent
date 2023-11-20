package entity;
import java.util.ArrayList;
import java.util.List;

class User implements UserInterface{

    private final int userId;
    private final String name;
    private final String password;
    private final List<UserInterface> friends;
    private final List<EventInterface> registered_events;
    private final List<EventInterface> hosted_events;
    User(String name, String password, int userId){
        this.name = name;
        this.password = password;
        this.userId = userId;
        this.friends = new ArrayList<UserInterface>();
        this.registered_events = new ArrayList<EventInterface>();
        this.hosted_events = new ArrayList<EventInterface>();
    }

    public String getName(){return this.name;}

    public String getPassword(){return this.password;}

    public List<UserInterface> getFriends() {
        return this.friends;
    }

    public List<EventInterface> getRegisteredEvents() {
        return this.registered_events;
    }

    public List<EventInterface> getHostedEvents() {
        return this.hosted_events;
    }


    public int getUserId(){return this.userId;}

    public void add_friend(UserInterface friend){
        this.friends.add(friend);
    }

    public void remove_friend(UserInterface friend){
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


}
