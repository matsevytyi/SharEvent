package com.example.mapsdemo;

import data_access.DatabaseDAO;
import data_access.UserLoginDataAccessInterface;
import data_access.UserSignUpDataAccessInterface;
import entity.UserFactory;
import entity.UserFactoryImplementation;
import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginController;
import interface_adapter.login_adapter.LoginPresenter;
import interface_adapter.login_adapter.LoginViewModel;
import interface_adapter.map_adapter.LoggedInViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputDataBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel mapViewModel) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, mapViewModel);
            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel loggedViewModel) throws IOException {
        UserLoginDataAccessInterface userDataAccessObject = new DatabaseDAO();

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputDataBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, viewManagerModel, loggedViewModel);

        UserFactory userFactory = new UserFactoryImplementation();

        LoginInputBoundary userSignupInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, userFactory);

        return new LoginController(userSignupInteractor);
    }
}
