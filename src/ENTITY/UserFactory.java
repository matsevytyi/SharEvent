package ENTITY;

import java.time.LocalDateTime;

// just to create new user object
public interface UserFactory {
    /** Requires: password is valid. */
    User create(String username, String name, String email, String password);
}
