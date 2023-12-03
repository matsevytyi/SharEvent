package INTERFACE_ADAPTER.login_adapter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginState {

    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;


    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}
}
