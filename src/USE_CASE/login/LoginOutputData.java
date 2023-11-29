package USE_CASE.login;

import ENTITY.User;

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

    public User getUser() {
        return new User(username, name, password, email);
    }
}
