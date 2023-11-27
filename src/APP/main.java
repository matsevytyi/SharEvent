package APP;

import APP.use_case_factory.LoggedInUseCaseFactory;
import APP.use_case_factory.LoginUseCaseFactory;
import APP.use_case_factory.SignUpUseCaseFactory;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;
import VIEW.*;
import VIEW_CREATOR.LoadMapViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Map Embed Example");
        JFrame application = new JFrame("SharEvent");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        LoginViewModel loginViewModel = new LoginViewModel();
        SignUpViewModel signupViewModel = new SignUpViewModel();

        LoadMapViewModel mapViewModel = new LoadMapViewModel();

        SignUpView signupView = SignUpUseCaseFactory.SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, mapViewModel);
        views.add(loginView, loginView.viewName);

        //LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, mapViewModel);

        LoadMapView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, mapViewModel);
        //views.add(loggedInView, loggedInView.viewName);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
       /* primaryStage.setResizable(false);
        primaryStage.setTitle("SharEvent");

        LoadMapView loadMapView = new LoadMapView();

        StackPane pane = loadMapView.getStackPane();
        primaryStage.setScene(new Scene(pane, 1600, 1200));
        primaryStage.show();*/
    }
}
