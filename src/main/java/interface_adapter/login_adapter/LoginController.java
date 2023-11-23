package interface_adapter.login_adapter;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInputData;

import java.sql.SQLException;

public class LoginController {
    final LoginInputBoundary userLoginInteractor;

    public LoginController(LoginInputBoundary userLoginInteractor) {
        this.userLoginInteractor = userLoginInteractor;
    }

    public void execute(String username, String password) throws SQLException {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        userLoginInteractor.execute(loginInputData);
    }
}
