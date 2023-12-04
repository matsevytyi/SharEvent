package view;

import APP.use_case_factory.LoginUseCaseFactory;
import APP.use_case_factory.SignUpUseCaseFactory;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginController;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;
import VIEW.LoginView;
import VIEW.SignUpView;
import VIEW_CREATOR.LoadMapViewModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class LoginFactoryTest {

    @Test
    public void testSuccessfulCreation() {
        // Set up the necessary dependencies
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoadMapViewModel mapViewModel = new LoadMapViewModel();

        LoginView result = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, mapViewModel);

        Assert.assertNotNull(result);
    }

    @Test
    public void testExceptionThrown() {
        ViewManagerModel viewManagerModel = new ViewManagerModel() {
            @Override
            public void setActiveView(String viewName) {
                throw new RuntimeException("Simulated RuntimeException");
            }
        };
        LoginViewModel loginViewModel = new LoginViewModel();
        LoadMapViewModel mapViewModel = new LoadMapViewModel();

        LoginView loginView = null;
        try {
            loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, mapViewModel);
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }

        assertNotNull(loginView);
    }
}
