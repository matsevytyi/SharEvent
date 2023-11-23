package use_case.logout;

import use_case.login.LoginOutputData;

public interface LogoutOutputBoundary {

    void prepareSuccessView();

    void prepareFailView(String error);
}
