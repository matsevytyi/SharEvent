/**
 * The LoginPresenter class acts as a presenter for handling the output data related to the login use case.

 */

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
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
public class LoginPresenter implements LoginOutputDataBoundary {

    private LoadMapViewModel mapViewModel;
    private LoginViewModel loginViewModel; // final
    private ViewManagerModel viewManagerModel;


    /**
     * Constructs a new LoginPresenter with the provided LoginViewModel, ViewManagerModel, and LoadMapViewModel.
     *
     * @param loginViewModel    The view model associated with the login functionality.
     * @param viewManagerModel  The model managing the views in the application.
     * @param mapViewModel      The view model associated with the map functionality.
     */
    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, LoadMapViewModel mapViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mapViewModel = mapViewModel;
    }

    /**
     * Prepares the success view for the user upon successful login.
     *
     * @param user The output data containing user information after a successful login.
     */
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


    }

    /**
     * Prepares the failure view with the provided error message.
     *
     * @param error The error message to be displayed on the login view in case of login failure.
     */
    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    /**
     * Opens the JavaFX map view for the logged-in user.
     */
    public void openJavaFXMapView() {

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
