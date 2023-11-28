package INTERFACE_ADAPTER.logout_adapter;

import USE_CASE.logout.LogOutInputBoundary;

public class LogOutController {
    final LogOutInputBoundary logoutUseCaseInteractor;
    public LogOutController(LogOutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }


    public void execute() {

        logoutUseCaseInteractor.execute();
    }
}
