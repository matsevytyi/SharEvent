package INTERFACE_ADAPTER.view_profile;

import ENTITY.EventInterface;
import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.JXMapViewer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter
public class ViewProfileState {
    public ViewProfileState(){}
    /**
     * Method sets the details of the viewed user profile based on the provided parameters.
     * @param username The username of the viewed user.
     * @param name The name of the viewed user.
     * @param email The email address of the viewed user.
     * @param registered_events The list of events the user is registered for.
     * @param hosted_events    The list of events the user is hosting.
     */
    public void setDetails(String username, String name, String email, List<EventInterface> registered_events, List<EventInterface> hosted_events) {
        setEmail(email);
        setName(name);
        setUsername(username);
        setHosted_events(hosted_events);
        setRegistered_events(registered_events);
    }

    private  String username;
    private  String name;
    private  String email;
    private List<EventInterface> registered_events;
    private  List<EventInterface> hosted_events;
    private String error;

    }

