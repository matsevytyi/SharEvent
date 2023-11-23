package interface_adapter.login_adapter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginState {

    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}
}
