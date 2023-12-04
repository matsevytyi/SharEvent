/**
 * The SignUpController class is responsible for handling user sign-up requests and interacting with the corresponding interactor.
 */

package INTERFACE_ADAPTER.signup_adapter;

import USE_CASE.signup.SignUpInputBoundary;
import USE_CASE.signup.SignUpInputData;

import java.sql.SQLException;

public class SighUpController {

    final SignUpInputBoundary userSignUpInteractor;

    /**
     * Constructs a new SignUpController with the specified SignUpInputBoundary.
     *
     * @param userSignUpInteractor The boundary interface for user sign-up interactions.
     */
    public SighUpController(SignUpInputBoundary userSignUpInteractor) {
        this.userSignUpInteractor = userSignUpInteractor;
    }

    /**
     * Executes the user sign-up process.
     *
     * @param username       The desired username for the new user.
     * @param name           The name of the new user.
     * @param email          The email address of the new user.
     * @param password1      The password chosen by the new user.
     * @param password2      The repeated password for confirmation.
     * @throws SQLException If a SQL exception occurs during the sign-up process.
     */
    public void execute(String username, String name, String email, String password1, String password2) throws SQLException {
        SignUpInputData signupInputData = new SignUpInputData(
                username, name, email, password1, password2);

        userSignUpInteractor.execute(signupInputData);
    }
}
