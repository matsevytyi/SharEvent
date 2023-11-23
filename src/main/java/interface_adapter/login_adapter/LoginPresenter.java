package interface_adapter.login_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.map_adapter.LoggedInState;
import interface_adapter.map_adapter.LoggedInViewModel;
import interface_adapter.signup_adapter.SignUpState;
import use_case.login.LoginOutputData;
import use_case.login.LoginOutputDataBoundary;

public class LoginPresenter implements LoginOutputDataBoundary {

    private final LoggedInViewModel mapViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, LoggedInViewModel mapViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mapViewModel = mapViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData user) {

        // On success, switch to the user's map.

        LoggedInState mapState = mapViewModel.getState();
        mapState.setUsername(user);
        this.mapViewModel.setState(mapState);
        mapViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(mapViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
