/**
 * The LoginInteractor class represents the business logic for user login.
 */

package USE_CASE.login;

import DATA_ACCESS.UserLoginDataAccessInterface;
import ENTITY.User;
import ENTITY.UserFactory;

import java.sql.SQLException;

public class LoginInteractor implements LoginInputBoundary{

    final UserLoginDataAccessInterface userDataAccessObject;
    final LoginOutputDataBoundary userPresenter;
    final UserFactory userFactory;


    /**
     * Constructs a new LoginInteractor with the specified dependencies.
     *
     * @param userDataAccessObject The data access object for user-related operations.
     * @param userPresenter        The presenter for handling output data.
     * @param userFactory          The factory for creating user instances.
     */
    public LoginInteractor(UserLoginDataAccessInterface userDataAccessObject, LoginOutputDataBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    /**
     * Executes the user login process based on the provided input data.
     *
     * @param loginInputData The input data containing username and password for login.
     * @throws SQLException If a SQL exception occurs during the login process.
     */
    @Override
    public void execute(LoginInputData loginInputData) throws SQLException {

        if (!userDataAccessObject.existsByName(loginInputData.getUsername())) {
            userPresenter.prepareFailView("User not exist.");
        } else if (!userDataAccessObject.checkPassword(loginInputData.getUsername(), loginInputData.getPassword())) {
            userPresenter.prepareFailView("Password don't match.");
        } else {

            User loggedInUser = userDataAccessObject.getUserByUsername(loginInputData.getUsername());

            userDataAccessObject.registerUserForEvent(loggedInUser.getUsername(), 1);

            LoginOutputData loginOutputData = new LoginOutputData(loggedInUser,  false);
            userPresenter.prepareSuccessView(loginOutputData);
        }

    }
}
