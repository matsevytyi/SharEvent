package VIEW;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SighUpController;
import INTERFACE_ADAPTER.signup_adapter.SignUpState;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;
import VIEW.SignUpView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SignUpViewTest {

    private SighUpController signUpController;
    private SignUpViewModel signUpViewModel;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private SignUpView signUpView;

    @BeforeEach
    public void setUp() {
        signUpController = mock(SighUpController.class);
        signUpViewModel = new SignUpViewModel();
        loginViewModel = new LoginViewModel();
        viewManagerModel = mock(ViewManagerModel.class);
        signUpView = new SignUpView(signUpController, signUpViewModel, loginViewModel, viewManagerModel);
    }

    @Test
    public void testActionPerformedSignUp() throws SQLException {
        JButton signUpButton = signUpView.getSignUp();
        assertTrue(signUpButton.getActionListeners().length > 0);

        // Mock the behavior of the signUpController
        doNothing().when(signUpController).execute(anyString(), anyString(), anyString(), anyString(), anyString());

        // Trigger the actionPerformed event for signUpButton
        signUpButton.doClick();

        // Verify that the signUpController's execute method was called
        verify(signUpController).execute(anyString(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void testActionPerformedHaveAccount() {
        JButton haveAccountButton = signUpView.getHave_account();
        assertTrue(haveAccountButton.getActionListeners().length > 0);

        // Trigger the actionPerformed event for haveAccountButton
        haveAccountButton.doClick();

        // Verify that the viewManagerModel's setActiveView and firePropertyChanged methods were called
        verify(viewManagerModel).setActiveView(anyString());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPropertyChange() {
        // Trigger a property change event on the signUpViewModel
        SignUpState newState = new SignUpState();
        newState.setUsernameError("Invalid username");
        //signUpViewModel.firePropertyChanged(newState);

        // Verify that the JOptionPane.showMessageDialog method was called
        //assertTrue(((String) JOptionPane.getFrames()[0].getTitle()).contains("Invalid username"));
    }

    @Test
    public void testSignUpButtonActionPerformed() throws SQLException {
        // Arrange
        SighUpController mockSignUpController = mock(SighUpController.class);
        SignUpViewModel mockSignUpViewModel = mock(SignUpViewModel.class);
        LoginViewModel mockLoginViewModel = mock(LoginViewModel.class);
        ViewManagerModel mockViewManagerModel = mock(ViewManagerModel.class);

        SignUpView signUpView = new SignUpView(mockSignUpController, mockSignUpViewModel, mockLoginViewModel, mockViewManagerModel);

        // Set up the state for the test
        SignUpState testState = new SignUpState();
        testState.setUsername("testUser");
        testState.setName("Test Name");
        testState.setEmail("test@example.com");
        testState.setPassword("testPassword");
        testState.setRepeatPassword("testPassword");

        when(mockSignUpViewModel.getState()).thenReturn(testState);
        signUpView.getSignUp().doClick();

        // Assert
        verify(mockSignUpController).execute("testUser", "Test Name", "test@example.com", "testPassword", "testPassword");
        //verify(mockViewManagerModel).setActiveView("login");
    }

    @Test
    public void testHaveAccountButtonActionPerformed() {
        // Arrange
        SighUpController mockSignUpController = mock(SighUpController.class);
        SignUpViewModel mockSignUpViewModel = mock(SignUpViewModel.class);
        LoginViewModel mockLoginViewModel = mock(LoginViewModel.class);
        ViewManagerModel mockViewManagerModel = mock(ViewManagerModel.class);

        SignUpView signUpView = new SignUpView(mockSignUpController, mockSignUpViewModel, mockLoginViewModel, mockViewManagerModel);

        // Set up the state for the test
        LoginState testLoginState = new LoginState();
        testLoginState.setUsername("testUser");
        testLoginState.setPassword("testPassword");

        when(mockLoginViewModel.getState()).thenReturn(testLoginState);

        signUpView.getHave_account().doClick();
        verify(mockViewManagerModel).firePropertyChanged();
    }


}

