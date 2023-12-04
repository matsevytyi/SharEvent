/**
 * The User class represents a user in the system.
 */

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
//    private final List<UserInterface> friends;
    private final List<EventInterface> registered_events;
    private final List<EventInterface> hosted_events;


    /**
     * Constructs a new User with the specified details.
     *
     * @param name     The name of the user.
     * @param password The password associated with the user.
     * @param userId   The unique username of the user.
     * @param email    The email address of the user.
     */
    public User(String name, String password, String userId, String email) {
        this.name = name;
        this.password = password;
        this.username = userId;
        this.email = email;
//        this.friends = new ArrayList<UserInterface>();
        this.registered_events = new ArrayList<EventInterface>();
        this.hosted_events = new ArrayList<EventInterface>();
    }


    /**
     * Gets the list of events the user is registered for.
     *
     * @return The list of registered events.
     */
    public List<EventInterface> getRegisteredEvents() {
        return this.registered_events;
    }

    /**
     * Gets the list of events hosted by the user.
     *
     * @return The list of hosted events.
     */
    public List<EventInterface> getHostedEvents() {
        return this.hosted_events;
    }


//    public void add_friend(UserInterface friend) {
//        this.friends.add(friend);
//    }
//
//    public void remove_friend(UserInterface friend) {
//        this.friends.remove(friend);
//    }

    /**
     * Adds a registered event to the user's list.
     *
     * @param event The event to be added.
     */
    public void add_registered_event(EventInterface event) {
        this.registered_events.add(event);
    }

    /**
     * Removes a registered event from the user's list.
     *
     * @param event The event to be removed.
     */
    @Override
    public void remove_registered_event(EventInterface event) {
        this.registered_events.remove(event);
    }

    /**
     * Adds a hosted event to the user's list.
     *
     * @param event The event to be added.
     */
    public void add_hosted_event(EventInterface event) {
        this.hosted_events.add(event);
    }

    /**
     * Removes a hosted event from the user's list.
     *
     * @param event The event to be removed.
     */
    public void remove_hosted_event(EventInterface event) {
        this.hosted_events.remove(event);
    }

    @Override
    public String toString(){
        return this.name;
    }
}