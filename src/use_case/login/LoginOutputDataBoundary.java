package USE_CASE.login;



public interface LoginOutputDataBoundary {

    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);
}
