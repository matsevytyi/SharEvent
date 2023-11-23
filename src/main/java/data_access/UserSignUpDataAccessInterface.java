package data_access;

import entity.User;

import java.sql.SQLException;

// database methods
public interface UserSignUpDataAccessInterface {

    boolean existsByName(String identifier) throws SQLException;

    boolean save(User user);
}
