package use_case.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginState;
import interface_adapter.login_adapter.LoginViewModel;

import java.util.Set;

public class LogOutInteractor implements LogOutInputBoundary {

    final LogoutOutputBoundary logoutPresenter;

    public LogOutInteractor(LogoutOutputBoundary logoutPresenter) {
        this.logoutPresenter = logoutPresenter;

    }
    @Override
    public void execute() {
        logoutPresenter.prepareSuccessView();

    }


}
