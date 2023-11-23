package interface_adapter.signup_adapter;

import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInputData;

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
