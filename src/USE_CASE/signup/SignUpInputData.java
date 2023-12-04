/**
 * The SignUpInputData class represents the input data for the user sign-up process.
 */

package USE_CASE.signup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SignUpInputData {

    private String username;
    private String name;
    private String email;
    private String password;
    private String repeatPassword;

    /**
     * Constructs a new SignUpInputData with the specified user information.
     *
     * @param username       The desired username for sign-up.
     * @param name           The user's name for sign-up.
     * @param email          The user's email for sign-up.
     * @param password       The desired password for sign-up.
     * @param repeatPassword The repeated password for sign-up.
     */
    public SignUpInputData(String username, String name, String email, String password, String repeatPassword) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

}
