package com.example.mapsdemo;

import data_access.DatabaseDAO;
import data_access.UserSignUpDataAccessInterface;
import entity.UserFactory;
import entity.UserFactoryImplementation;
import interface_adapter.ViewManagerModel;
import interface_adapter.login_adapter.LoginViewModel;
import interface_adapter.signup_adapter.SighUpController;
import interface_adapter.signup_adapter.SignUpPresenter;
import interface_adapter.signup_adapter.SignUpViewModel;
import use_case.signup.SignUpInputBoundary;
import use_case.signup.SignUpInteractor;
import use_case.signup.SignUpOutputBoundary;
import view.SignUpView;

import javax.swing.*;
import java.io.IOException;

public class SignUpUseCaseFactory {


    public class SignupUseCaseFactory {

        /** Prevent instantiation. */
        private SignupUseCaseFactory() {}

        public static SignUpView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signupViewModel) {

            try {
                SighUpController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
                return new SignUpView(signupController, signupViewModel, loginViewModel, viewManagerModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }

        private static SighUpController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignUpViewModel signupViewModel, LoginViewModel loginViewModel) throws IOException {
            UserSignUpDataAccessInterface userDataAccessObject= new DatabaseDAO();

            // Notice how we pass this method's parameters to the Presenter.
            SignUpOutputBoundary signupOutputBoundary = new SignUpPresenter(viewManagerModel, signupViewModel, loginViewModel);

            UserFactory userFactory = new UserFactoryImplementation();

            SignUpInputBoundary userSignupInteractor = new SignUpInteractor(
                    userDataAccessObject, signupOutputBoundary, userFactory);

            return new SighUpController(userSignupInteractor);
        }
    }
}
