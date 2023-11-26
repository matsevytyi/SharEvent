package USE_CASE.login;

import USE_CASE.signup.SignUpInputData;

import java.sql.SQLException;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData) throws SQLException;
}
