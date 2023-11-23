package use_case.signup;

import use_case.signup.SignUpOutputData;

public interface SignUpOutputBoundary {

    void prepareSuccessView(SignUpOutputData user);

    void prepareFailView(String error);
}
