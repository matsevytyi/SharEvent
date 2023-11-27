package APP;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.UserSignUpDataAccessInterface;
import ENTITY.UserFactory;

import ENTITY.UserFactoryImplementation;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SighUpController;
import INTERFACE_ADAPTER.signup_adapter.SignUpPresenter;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;
import USE_CASE.signup.SignUpInputBoundary;
import USE_CASE.signup.SignUpInteractor;
import USE_CASE.signup.SignUpOutputBoundary;
import VIEW.SignUpView;

import javax.swing.*;
import java.io.IOException;

public class SignUpUseCaseFactory {


    public class SignupUseCaseFactory {

        /** Prevent instantiation. */
        private SignupUseCaseFactory() {}

        public static SignUpView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signupViewModel) {

            try {
                SighUpController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
                return new SignUpView(signupController, signupViewModel, loginViewModel, viewManagerModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }

        private static SighUpController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignUpViewModel signupViewModel, LoginViewModel loginViewModel) throws IOException {
            UserSignUpDataAccessInterface userDataAccessObject= new DatabaseDAO();

            // Notice how we pass this method's parameters to the Presenter.
            SignUpOutputBoundary signupOutputBoundary = new SignUpPresenter(viewManagerModel, signupViewModel, loginViewModel);

            UserFactory userFactory = new UserFactoryImplementation();

            SignUpInputBoundary userSignupInteractor = new SignUpInteractor(
                    userDataAccessObject, signupOutputBoundary, userFactory);

            return new SighUpController(userSignupInteractor);
        }
    }
}
