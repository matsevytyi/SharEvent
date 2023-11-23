package use_case.login;

import use_case.signup.SignUpInputData;

import java.sql.SQLException;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData) throws SQLException;
}
