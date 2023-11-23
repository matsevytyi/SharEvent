package use_case.signup;

import use_case.signup.SignUpInputData;

import java.sql.SQLException;

public interface SignUpInputBoundary {
    void execute(SignUpInputData signupInputData) throws SQLException;
}
