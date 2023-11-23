package use_case.signup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SignUpInputData {

    final private String username;
    final private String name;
    final private String email;
    final private String password;
    final private String repeatPassword;

    public SignUpInputData(String username, String name, String email, String password, String repeatPassword) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

}
