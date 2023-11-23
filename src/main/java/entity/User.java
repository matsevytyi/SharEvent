package entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final List<User> friends;
    private final List<Event> attendingEvents;

}
