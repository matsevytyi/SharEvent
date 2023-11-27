package USE_CASE.signup;


public interface SignUpOutputBoundary {

    void prepareSuccessView(SignUpOutputData user);

    void prepareFailView(String error);
}
