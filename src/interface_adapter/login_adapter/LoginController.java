package INTERFACE_ADAPTER.login_adapter;

import USE_CASE.login.LoginInputBoundary;
import USE_CASE.login.LoginInputData;

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
