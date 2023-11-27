package INTERFACE_ADAPTER.signup_adapter;

import USE_CASE.signup.SignUpInputBoundary;
import USE_CASE.signup.SignUpInputData;

import java.sql.SQLException;

public class SighUpController {

    final SignUpInputBoundary userSignUpInteractor;

    public SighUpController(SignUpInputBoundary userSignUpInteractor) {
        this.userSignUpInteractor = userSignUpInteractor;
    }

    public void execute(String username, String name, String email, String password1, String password2) throws SQLException {
        SignUpInputData signupInputData = new SignUpInputData(
                username, name, email, password1, password2);

        userSignUpInteractor.execute(signupInputData);
    }
}
