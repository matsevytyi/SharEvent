package USE_CASE.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputData {
    final private String username;
    final private String password;
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
