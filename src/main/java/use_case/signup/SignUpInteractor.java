package use_case.signup;

import data_access.UserSignUpDataAccessInterface;
import entity.User;
import entity.UserFactory;

import java.sql.SQLException;


public class SignUpInteractor implements SignUpInputBoundary {

    final UserSignUpDataAccessInterface userDataAccessObject;
    final SignUpOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignUpInteractor(UserSignUpDataAccessInterface userDataAccessObject, SignUpOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

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
