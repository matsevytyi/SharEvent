package INTERFACE_ADAPTER.loadmap_adapter;

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

