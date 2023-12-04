/**
 * The LoginInputData class represents the input data for the user login process.
 */

package USE_CASE.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputData {
    final private String username;
    final private String password;

    /**
     * Constructs a new LoginInputData with the specified username and password.
     *
     * @param username The username for login.
     * @param password The password for login.
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
