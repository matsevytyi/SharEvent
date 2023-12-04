/**
 * The LoginInputBoundary interface defines the contract for handling user login input.
 */

package USE_CASE.login;

import USE_CASE.signup.SignUpInputData;

import java.sql.SQLException;

public interface LoginInputBoundary {

    /**
     * Executes the login process based on the provided login input data.
     *
     * @param loginInputData The input data containing username and password for login.
     * @throws SQLException If a SQL exception occurs during the login process.
     */
    void execute(LoginInputData loginInputData) throws SQLException;
}
