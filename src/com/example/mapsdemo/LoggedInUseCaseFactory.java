package com.example.mapsdemo;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginViewModel;
import interface_adapter.logout_adapter.LogOutController;
import interface_adapter.logout_adapter.LogOutPresenter;
import interface_adapter.map_adapter.LoggedInViewModel;
import use_case.logout.LogOutInteractor;
import view.LoggedInView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {

    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel) { //ClearInputBoundary clearInputBoundary) {


        LogOutPresenter logoutPresenter = new LogOutPresenter (loginViewModel, viewManagerModel);

        LogOutInteractor logoutInteractor = new LogOutInteractor(logoutPresenter);


        LogOutController logoutController = new LogOutController (logoutInteractor);
        // LoggedController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);

        return new LoggedInView(loggedInViewModel, logoutController);

    }
}
