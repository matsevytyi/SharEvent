/**
 * The SignUpInputBoundary interface defines the contract for handling user sign-up input.
 */

package USE_CASE.signup;

import USE_CASE.signup.SignUpInputData;

import java.sql.SQLException;

public interface SignUpInputBoundary {
    /**
     * Executes the sign-up process based on the provided sign-up input data.
     *
     * @param signupInputData The input data containing user information for sign-up.
     * @throws SQLException If a SQL exception occurs during the sign-up process.
     */
    void execute(SignUpInputData signupInputData) throws SQLException;
}
