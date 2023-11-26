package INTERFACE_ADAPTER;

import INTERFACE_ADAPTER.map_adapter.LoggedInState;
import USE_CASE.login.LoginOutputData;

public class LoadMapState {

    private String username = "";

        public LoadMapState(LoadMapState copy) {
            username = copy.username;
        }

        // Because of the previous copy constructor, the default constructor must be explicit.
        public LoadMapState () {}

        public String getUsername() {
            return username;
        }
        public void setUsername(LoginOutputData user) {
            this.username = user.getUsername();
        }



    }

