package USE_CASE.login;

import DATA_ACCESS.UserLoginDataAccessInterface;
import ENTITY.User;
import ENTITY.UserFactory;

import java.sql.SQLException;

public class LoginInteractor implements LoginInputBoundary{

    final UserLoginDataAccessInterface userDataAccessObject;
    final LoginOutputDataBoundary userPresenter;
    final UserFactory userFactory;

    public LoginInteractor(UserLoginDataAccessInterface userDataAccessObject, LoginOutputDataBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(LoginInputData loginInputData) throws SQLException {

        if (!userDataAccessObject.existsByName(loginInputData.getUsername())) {
            userPresenter.prepareFailView("User not exist.");
        } else if (loginInputData.getPassword().equals(userDataAccessObject.checkPassword(loginInputData.getPassword()))) {
            userPresenter.prepareFailView("Password don't match.");
        } else {

            User loggedInUser = userDataAccessObject.getUserByUsername(loginInputData.getUsername());

            LoginOutputData loginOutputData = new LoginOutputData(loggedInUser,  false);
            userPresenter.prepareSuccessView(loginOutputData);
        }

    }
}
