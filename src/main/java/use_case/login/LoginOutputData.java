package use_case.login;

import entity.User;

public class LoginOutputData {

    private final String name;
    private final String username;
    private final String password;
    private final String email;

    private boolean useCaseFailed;
    public LoginOutputData(User loggedInUser, boolean useCaseFailed) {
        this.username = loggedInUser.getUsername();
        this.name = loggedInUser.getName();
        this.email = loggedInUser.getEmail();
        this.password = loggedInUser.getPassword();
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return this.username;
    }
}
