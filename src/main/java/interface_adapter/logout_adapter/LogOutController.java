package interface_adapter.logout_adapter;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.logout.LogOutInputBoundary;

public class LogOutController {
    final LogOutInputBoundary logoutUseCaseInteractor;
    public LogOutController(LogOutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }


    public void execute() {

        logoutUseCaseInteractor.execute();
    }
}
