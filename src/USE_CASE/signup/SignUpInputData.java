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

    public SignUpInputData(String username, String name, String email, String password, String repeatPassword) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

}
