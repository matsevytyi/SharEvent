package USE_CASE.logout;

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
