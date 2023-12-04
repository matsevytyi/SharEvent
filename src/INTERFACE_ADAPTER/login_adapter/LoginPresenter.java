package INTERFACE_ADAPTER.login_adapter;

import APP.main;
import ENTITY.User;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapState;
import INTERFACE_ADAPTER.ViewManagerModel;
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
import java.awt.*;


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

        Platform.runLater(() -> {
            LoadMapView loadMapView = main.loadMapView;
            Scene scene = new Scene(loadMapView.getStackPane(), 1600, 1200);

            Stage stage = new Stage();
            stage.setTitle("Map View: " + mapViewModel.getLoggedInUser());
            stage.setScene(scene);
            stage.setResizable(false);

            stage.setOnCloseRequest(event -> {

                System.out.println("Window is closing. Stopping the program.");

                Platform.exit();
                System.exit(0);
            });

            stage.show();
        });
    }



}
