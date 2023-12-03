/*
package view;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SighUpController;
import INTERFACE_ADAPTER.signup_adapter.SignUpState;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;
import VIEW.SignUpView;
import javafx.scene.input.KeyEvent;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.sql.SQLException;

public class SignUpViewTest {

    private SignUpView signUpView;
    private SignUpViewModel signUpViewModel;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private SighUpController sighUpController;

    @Before
    public void setUp() {
        sighUpController = mock(SighUpController.class);
        signUpViewModel = new SignUpViewModel();
        loginViewModel = new LoginViewModel();
        viewManagerModel = new ViewManagerModel();
        signUpView = new SignUpView(sighUpController, signUpViewModel, loginViewModel, viewManagerModel);
    }

    @Test
    public void testSignUpButtonActionPerformed() throws SQLException {
        // Simulate a user clicking the sign-up button
        signUpView.signUp.doClick();

        // Verify that the controller's execute method was called with the expected parameters
        verify(sighUpController).execute(anyString(), anyString(), anyString(), anyString(), anyString());

        // You might also want to check other aspects of the UI state or behavior after the button click
    }

    @Test
    public void testHaveAccountButtonActionPerformed() {
        // Simulate a user clicking the "Have Account" button
        signUpView.have_account.doClick();

        // Verify that the view manager and login view model were updated
        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());

        // You may need to check other aspects of the UI state or behavior after this button click
    }


    @Test
    public void testUsernameInputFieldKeyTyped() {
        // Simulate a key press in the username input field
        signUpView.usernameInputField.setText("testUser");
        //signUpView.usernameInputField.dispatchEvent(new KeyEvent(signUpView.usernameInputField, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK, 'a'));

        // Verify that the state in the SignUpViewModel is updated
        assertEquals("testUsera", signUpViewModel.getState().getUsername());
    }

    // Add similar tests for other input fields, key events, and possible edge cases

    @Test
    public void testPropertyChange() {
        // Simulate a property change event
        SignUpState newState = new SignUpState();
        newState.setUsernameError("Test Error");
        signUpView.propertyChange(new PropertyChangeEvent(signUpView, "propertyName", signUpViewModel.getState(), newState));

        // Verify that a JOptionPane is displayed with the expected error message
        // Note: This may require the use of a library like JUnit Swing to handle UI interactions in tests
    }
}

*/
