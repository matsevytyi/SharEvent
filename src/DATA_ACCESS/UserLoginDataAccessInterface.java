package DATA_ACCESS;

import ENTITY.User;

import java.sql.SQLException;

public interface UserLoginDataAccessInterface {
    boolean existsByName(String identifier) throws SQLException;

    User getUserByUsername(String username);

    boolean checkPassword(String username, String password);

    ///забрати потім
    public void registerUserForEvent(String username, int event_id);
}
