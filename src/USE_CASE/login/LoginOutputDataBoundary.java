/**
 * The LoginOutputDataBoundary interface defines the contract for preparing views based on the outcome of the user login process.
 */

package USE_CASE.login;



public interface LoginOutputDataBoundary {

    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);
}
