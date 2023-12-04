
/**
 * The SignUpOutputData class represents the output data for the user sign-up process.
 */

package USE_CASE.signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpOutputData {

    private final String username;

    private boolean useCaseFailed;

    /**
     * Constructs a new SignUpOutputData with the provided username and use case status.
     *
     * @param username      The username associated with the signed-up user.
     * @param useCaseFailed A flag indicating whether the use case failed.
     */
    public SignUpOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }
}
