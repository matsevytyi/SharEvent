package use_case.login;

import use_case.signup.SignUpOutputData;

public interface LoginOutputDataBoundary {

    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);
}
