package INTERFACE_ADAPTER.logout_adapter;

import APP.use_case_factory.LoginUseCaseFactory;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginController;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import USE_CASE.logout.LogoutOutputBoundary;
import VIEW.LoginView;
import VIEW_CREATOR.LoadMapViewModel;

import javax.swing.*;

public class LogOutPresenter implements LogoutOutputBoundary {

    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LogOutPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        /*LoginState loginState = loginViewModel.getState();
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();*/

        SwingUtilities.invokeLater(() -> {
            showSwingLoginWindow();
        });
    }

    @Override
    public void prepareFailView(String error) {

    }

    private void showSwingLoginWindow() {
        // Create and show the Swing login window

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
