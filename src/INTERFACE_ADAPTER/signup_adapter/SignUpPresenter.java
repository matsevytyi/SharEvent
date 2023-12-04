/**
 * The SignUpPresenter class is responsible for preparing views based on the outcome of the user sign-up process.
 */

package INTERFACE_ADAPTER.signup_adapter;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import USE_CASE.signup.SignUpOutputBoundary;
import USE_CASE.signup.SignUpOutputData;
import lombok.Getter;

@Getter
public class SignUpPresenter implements SignUpOutputBoundary {

    private final SignUpViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new SignUpPresenter with the specified ViewManagerModel, SignUpViewModel, and LoginViewModel.
     *
     * @param viewManagerModel The model managing views in the application.
     * @param signupViewModel  The view model associated with user sign-up.
     * @param loginViewModel   The view model associated with user login.
     */
    public SignUpPresenter(ViewManagerModel viewManagerModel,
                           SignUpViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the view for a successful user sign-up.
     *
     * @param response The output data containing information about the signed-up user.
     */
    @Override
    public void prepareSuccessView(SignUpOutputData response) {
        // On success, switch to the login view.

        SignUpState signupState = signupViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        //loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed user sign-up.
     *
     * @param error The error message associated with the sign-up failure.
     */
    @Override
    public void prepareFailView(String error) {
        SignUpState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}
