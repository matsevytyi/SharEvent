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

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoadMapViewModel mapViewModel) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, mapViewModel);
            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

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
