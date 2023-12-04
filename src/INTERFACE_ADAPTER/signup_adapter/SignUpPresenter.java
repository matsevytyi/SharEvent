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

    public SignUpPresenter(ViewManagerModel viewManagerModel,
                           SignUpViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

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

    @Override
    public void prepareFailView(String error) {
        SignUpState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}
