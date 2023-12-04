/**
 * The UserSignUpDataAccessInterface provides methods for accessing user-related data for SignUp operations.
 * Implementing classes are responsible for interacting with database.
 */

package DATA_ACCESS;

import ENTITY.User;

import java.sql.SQLException;

// database methods
public interface UserSignUpDataAccessInterface {

    boolean existsByName(String identifier) throws SQLException;

    boolean save(User user);
}
