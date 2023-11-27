package INTERFACE_ADAPTER.logout_adapter;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import USE_CASE.logout.LogoutOutputBoundary;

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
