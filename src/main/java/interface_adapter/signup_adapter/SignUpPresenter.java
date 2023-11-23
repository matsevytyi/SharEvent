package interface_adapter.signup_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginState;
import interface_adapter.login_adapter.LoginViewModel;
import use_case.signup.SignUpOutputBoundary;
import use_case.signup.SignUpOutputData;

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
