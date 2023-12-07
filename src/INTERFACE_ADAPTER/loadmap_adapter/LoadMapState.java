package INTERFACE_ADAPTER.loadmap_adapter;

import USE_CASE.login.LoginOutputData;

/**
 * LoadMapState is used to store
 * current state of LoadMapView
 * User who is LoggedIn
 * */
public class LoadMapState {

    private String username = "";

    public LoadMapState(LoadMapState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.

    public LoadMapState () {}

    /**
     * Getter for username of a user that is using the app
     * */
    public String getUsername() {
        return username;
    }
    /**
     * Getter for username of a user that is using the app
     *
     * is used while LogIn
     * */
    public void setUsername(LoginOutputData user) {
        this.username = user.getUsername();
    }



    }

