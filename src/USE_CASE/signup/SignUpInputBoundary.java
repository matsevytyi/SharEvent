package USE_CASE.signup;

import USE_CASE.signup.SignUpInputData;

import java.sql.SQLException;

public interface SignUpInputBoundary {
    void execute(SignUpInputData signupInputData) throws SQLException;
}
