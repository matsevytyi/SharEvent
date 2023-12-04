
/**
 * The LoginController class serves as a controller for handling user login operations.
 */
package INTERFACE_ADAPTER.login_adapter;

import USE_CASE.login.LoginInputBoundary;
import USE_CASE.login.LoginInputData;

import java.sql.SQLException;

public class LoginController {
    final LoginInputBoundary userLoginInteractor;

    /**
     * Constructs a new LoginController with the provided user login interactor.
     *
     * @param userLoginInteractor The user login interactor to be used for login operations.
     */
    public LoginController(LoginInputBoundary userLoginInteractor) {
        this.userLoginInteractor = userLoginInteractor;
    }

    /**
     * Executes the login operation based on the provided username and password.
     *
     * @param username The username entered by the user for login.
     * @param password The password entered by the user for login.
     * @throws SQLException If a SQL-related error occurs during the login operation.
     */
    public void execute(String username, String password) throws SQLException {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        userLoginInteractor.execute(loginInputData);
    }
}
