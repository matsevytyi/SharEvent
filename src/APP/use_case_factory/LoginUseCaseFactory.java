/**
 * The {@code LoginUseCaseFactory} class is responsible for creating and initializing
 * instances related to the login functionality in the application. It provides methods
 * to create a {@code LoginView} and a {@code LoginController} by coordinating the
 * necessary components such as data access objects, presenters, and interactors.
 */

package APP.use_case_factory;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.UserLoginDataAccessInterface;
import ENTITY.UserFactory;
import ENTITY.UserFactoryImplementation;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginController;
import INTERFACE_ADAPTER.login_adapter.LoginPresenter;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import USE_CASE.login.LoginInputBoundary;
import USE_CASE.login.LoginInteractor;
import USE_CASE.login.LoginOutputDataBoundary;
import VIEW.LoginView;
import VIEW_CREATOR.LoadMapViewModel;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    /**
     * Creates and returns a new instance of {@code LoginView} by initializing the
     * required {@code LoginController}. This method handles exceptions related to
     * the creation of the user login use case and displays an error message if
     * an IOException occurs during the process.
     *
     * @param viewManagerModel The model for managing views in the application.
     * @param loginViewModel   The view model for the login functionality.
     * @param mapViewModel     The view model for loading map-related data.
     * @return A new instance of {@code LoginView} or {@code null} if an IOException occurs.
     */

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoadMapViewModel mapViewModel) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, mapViewModel);
            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    /**
     * Creates and returns a new instance of {@code LoginController} by initializing
     * the necessary components such as data access objects, presenters, and interactors.
     * This method follows the dependency inversion principle, accepting interfaces for
     * greater flexibility and testability.
     *
     * @param viewManagerModel The model for managing views in the application.
     * @param loginViewModel   The view model for the login functionality.
     * @param loggedViewModel  The view model for loading map-related data.
     * @return A new instance of {@code LoginController}.
     * @throws IOException If an error occurs during the creation of the user login use case.
     */

    public static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoadMapViewModel loggedViewModel) throws IOException {
        UserLoginDataAccessInterface userDataAccessObject = new DatabaseDAO();

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputDataBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, viewManagerModel, loggedViewModel);

        UserFactory userFactory = new UserFactoryImplementation();

        LoginInputBoundary userSignupInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, userFactory);

        return new LoginController(userSignupInteractor);
    }
}
