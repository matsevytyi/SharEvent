package USE_CASE.logout;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;

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
