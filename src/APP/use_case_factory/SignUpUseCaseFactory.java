package APP.use_case_factory;

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

/**
 * The SignUpUseCaseFactory class is responsible for creating and initializing
 * instances related to the signup functionality in the application. It provides methods
 * to create a {@code SignUpView} and a {@code SignUpController} by coordinating the
 * necessary components such as data access objects, presenters, and interactors.
 */
public class SignUpUseCaseFactory {


    public class SignupUseCaseFactory {

        /** Prevent instantiation. */
        private SignupUseCaseFactory() {}

        /**
         * Creates and returns a new instance of {@code SignUpView} by initializing the
         * required {@code SignUpController}. This method handles exceptions related to
         * the creation of the user signup use case and displays an error message if
         * an IOException occurs during the process.
         *
         * @param viewManagerModel The model for managing views in the application.
         * @param loginViewModel   The view model for the login functionality.
         * @param signupViewModel  The view model for the signup functionality.
         * @return A new instance of {@code SignUpView} or {@code null} if an IOException occurs.
         */
        public static SignUpView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signupViewModel) {

            try {
                SighUpController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
                return new SignUpView(signupController, signupViewModel, loginViewModel, viewManagerModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }

        /**
         * Creates and returns a new instance of {@code SighUpController} by initializing
         * the necessary components such as data access objects, presenters, and interactors.
         * This method follows the dependency inversion principle, accepting interfaces for
         * greater flexibility and testability.
         *
         * @param viewManagerModel The model for managing views in the application.
         * @param signupViewModel  The view model for the signup functionality.
         * @param loginViewModel   The view model for the login functionality.
         * @return A new instance of {@code SighUpController}.
         * @throws IOException If an error occurs during the creation of the user signup use case.
         */
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
