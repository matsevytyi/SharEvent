/**
 * The SignUpInteractor class represents the business logic for user sign-up.
 */

package USE_CASE.signup;

import ENTITY.User;
import DATA_ACCESS.UserSignUpDataAccessInterface;

import ENTITY.UserFactory;

import java.sql.SQLException;


public class SignUpInteractor implements SignUpInputBoundary {

    final UserSignUpDataAccessInterface userDataAccessObject;
    final SignUpOutputBoundary userPresenter;
    final UserFactory userFactory;

    /**
     * Constructs a new SignUpInteractor with the specified dependencies.
     *
     * @param userDataAccessObject The data access object for user-related operations.
     * @param userPresenter        The presenter for handling output data.
     * @param userFactory          The factory for creating user instances.
     */
    public SignUpInteractor(UserSignUpDataAccessInterface userDataAccessObject, SignUpOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    /**
     * Executes the user sign-up process based on the provided input data.
     *
     * @param signupInputData The input data containing user information for sign-up.
     * @throws SQLException If a SQL exception occurs during the sign-up process.
     */
    @Override
    public void execute(SignUpInputData signupInputData) throws SQLException {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {


            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getName(), signupInputData.getEmail(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            SignUpOutputData signupOutputData = new SignUpOutputData(user.getName(),  false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
