package INTERFACE_ADAPTER.login_adapter;

import ENTITY.User;
import INTERFACE_ADAPTER.LoadMapState;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.map_adapter.LoggedInState;
import INTERFACE_ADAPTER.map_adapter.LoggedInViewModel;
import INTERFACE_ADAPTER.signup_adapter.SignUpState;
import USE_CASE.login.LoginOutputData;
import USE_CASE.login.LoginOutputDataBoundary;
import VIEW.LoadMapView;
import VIEW.LoginView;
import VIEW_CREATOR.LoadMapViewModel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;


public class LoginPresenter implements LoginOutputDataBoundary {

    private final LoadMapViewModel mapViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, LoadMapViewModel mapViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mapViewModel = mapViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData user) {

        // On success, switch to the user's map.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                openJavaFXMapView();
            }
        });

        User curruser = user.getUser();

        LoadMapState mapState = mapViewModel.getState();
        mapState.setUsername(user);
        mapViewModel.setLoggedInUser(user);
        mapViewModel.setLoggedInUserObject(curruser);
        mapViewModel.firePropertyChanged();

        loginViewModel.setLogged();

        viewManagerModel.setActiveView("mapViewName");
        viewManagerModel.firePropertyChanged();


//        User curruser = user.getUser();
//
//        LoadMapState mapState = mapViewModel.getState();
//        mapState.setUsername(user);
//        this.mapViewModel.setLoggedInUser(user);
//        this.mapViewModel.setLoggedInUserObject(curruser);
//        mapViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(mapViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    private void openJavaFXMapView() {

        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            LoadMapView loadMapView = new LoadMapView(mapViewModel);  // Replace with your actual JavaFX view class
            Scene scene = new Scene(loadMapView.getStackPane(), 1600, 1200);  // Set width and height

            Stage stage = new Stage();
            stage.setTitle("Map View: " + mapViewModel.getLoggedInUser());
            stage.setScene(scene);

            stage.show();
        });
    }



}
