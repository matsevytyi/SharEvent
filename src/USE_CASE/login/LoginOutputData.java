/**
 * The LoginOutputData class represents the output data for the user login process.
 */

package USE_CASE.login;

import ENTITY.User;

public class LoginOutputData {

    private final String name;
    private final String username;
    private final String password;
    private final String email;

    private boolean useCaseFailed;

    /**
     * Constructs a new LoginOutputData with the provided user and use case status.
     *
     * @param loggedInUser   The user who has successfully logged in.
     * @param useCaseFailed  A flag indicating whether the use case failed.
     */
    public LoginOutputData(User loggedInUser, boolean useCaseFailed) {
        this.username = loggedInUser.getUsername();
        this.name = loggedInUser.getName();
        this.email = loggedInUser.getEmail();
        this.password = loggedInUser.getPassword();
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username associated with the logged-in user.
     *
     * @return The username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the User instance representing the logged-in user.
     *
     * @return The User instance.
     */
    public User getUser() {
        return new User(username, name, password, email);
    }
}
