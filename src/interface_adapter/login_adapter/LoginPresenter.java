package INTERFACE_ADAPTER.login_adapter;

import APP.main;
import ENTITY.User;
import INTERFACE_ADAPTER.LoadMapState;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.view_event.ViewEventController;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import USE_CASE.login.LoginOutputData;
import USE_CASE.login.LoginOutputDataBoundary;
import VIEW.LoadMapView;
import VIEW_CREATOR.LoadMapViewModel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;

import javax.swing.*;


public class LoginPresenter implements LoginOutputDataBoundary {

    private final LoadMapViewModel mapViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;


    // private final LogOutController logOutController;


    private static StackPane pane;




    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, LoadMapViewModel mapViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mapViewModel = mapViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData user) {

        // On success, switch to the user's map.
        //dispose();// Close the login view

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

        // Assuming that your map view has a unique name, replace "mapViewName" with the actual name.
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
//       LoadMapView loadMapView = (mapViewModel,addEventViewModel, addEventController, viewEventViewModel, viewEventController, deleteEventViewModel, deleteEventController );  // Replace with your actual JavaFX view class
            LoadMapView loadMapView = main.loadMapView;
            Scene scene = new Scene(loadMapView.getStackPane(), 1600, 1200);  // Set width and height

            Stage stage = new Stage();
            stage.setTitle("Map View: " + mapViewModel.getLoggedInUser());
            stage.setScene(scene);

            stage.show();
        });
    }



}
