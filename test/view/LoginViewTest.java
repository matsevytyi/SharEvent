package view;

import INTERFACE_ADAPTER.login_adapter.LoginController;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import VIEW.LoginView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginViewTest {

    private LoginController loginController;
    private LoginViewModel loginViewModel;
    private LoginView loginView;

    @BeforeEach
    public void setUp() {
        loginController = mock(LoginController.class);
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginController, loginViewModel);
    }

    @Test
    public void testActionPerformed() throws SQLException {
        JButton logInButton = loginView.getLogIn();
        JButton cancelButton = loginView.getCancel();

        assertTrue(logInButton.getActionListeners().length > 0);
        assertTrue(cancelButton.getActionListeners().length > 0);

        doNothing().when(loginController).execute(anyString(), anyString());

        logInButton.doClick();

        verify(loginController).execute(anyString(), anyString());
    }

    @Test
    public void testPropertyChange() {
        LoginState newState = new LoginState();
        newState.setUsernameError("Invalid username");
        assertFalse(loginView.getUsernameErrorField().getText().contains("Invalid username"));
    }

}
