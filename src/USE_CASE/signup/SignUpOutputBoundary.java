package USE_CASE.signup;

/**
 * The SignUpOutputBoundary interface defines the contract for preparing views based on the outcome of the user sign-up process.
 */
public interface SignUpOutputBoundary {

    /**
     * Prepares the view for a successful user sign-up.
     *
     * @param user The output data containing information about the signed-up user.
     */
    void prepareSuccessView(SignUpOutputData user);

    /**
     * Prepares the view for a failed user sign-up.
     *
     * @param error The error message associated with the sign-up failure.
     */
    void prepareFailView(String error);
}
