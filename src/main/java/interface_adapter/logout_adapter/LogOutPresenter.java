package interface_adapter.logout_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginState;
import interface_adapter.login_adapter.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;

public class LogOutPresenter implements LogoutOutputBoundary {

    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LogOutPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        LoginState loginState = loginViewModel.getState();
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
