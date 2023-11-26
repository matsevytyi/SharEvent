package USE_CASE.signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpOutputData {

    private final String username;

    private boolean useCaseFailed;

    public SignUpOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }
}
