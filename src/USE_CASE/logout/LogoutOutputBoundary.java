package USE_CASE.logout;

import USE_CASE.login.LoginOutputData;

public interface LogoutOutputBoundary {

    void prepareSuccessView();

    void prepareFailView(String error);
}
