/**
 * The UserLoginDataAccessInterface provides methods for accessing user-related data for login operations.
 * Implementing classes are responsible for interacting with database.
 */

package DATA_ACCESS;

import ENTITY.User;

import java.sql.SQLException;

public interface UserLoginDataAccessInterface {
    boolean existsByName(String identifier) throws SQLException;

    User getUserByUsername(String username);

    boolean checkPassword(String username, String password);
    public void registerUserForEvent(String username, int event_id);
}
