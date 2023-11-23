package interface_adapter.map_adapter;

import entity.User;
import use_case.login.LoginOutputData;

public class LoggedInState {
    private String username = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(LoginOutputData user) {
        this.username = user.getUsername();
    }



}
