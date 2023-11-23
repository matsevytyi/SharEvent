package data_access;

import entity.User;

import java.sql.SQLException;

public interface UserLoginDataAccessInterface {
    boolean existsByName(String identifier) throws SQLException;

    User getUserByUsername(String username);

    boolean checkPassword(String password);
}
