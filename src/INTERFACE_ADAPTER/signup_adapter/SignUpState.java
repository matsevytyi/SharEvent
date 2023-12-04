/**
 * The SignUpState class represents the state of the sign-up view, including user input fields and associated error messages.
 */

package INTERFACE_ADAPTER.signup_adapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpState {
    private String username = "";
    private String usernameError = null;
    private String name = "";
    private String email = "";
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
}
