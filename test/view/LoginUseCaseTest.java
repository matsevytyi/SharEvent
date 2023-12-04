package view;

import APP.main;
import APP.use_case_factory.LoginUseCaseFactory;
import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.UserLoginDataAccessInterface;
import ENTITY.User;
import ENTITY.UserFactory;
import ENTITY.UserFactoryImplementation;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapState;
import INTERFACE_ADAPTER.login_adapter.LoginController;
import INTERFACE_ADAPTER.login_adapter.LoginPresenter;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SighUpController;
import USE_CASE.login.*;
import USE_CASE.signup.SignUpInputBoundary;
import USE_CASE.signup.SignUpInputData;
import VIEW.LoadMapView;
import VIEW.LoginView;
import VIEW_CREATOR.LoadMapViewModel;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.StackPane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class LoginUseCaseTest {

    @Test
    public void testUserExistsPasswordsMatch() throws SQLException {
        // Arrange
        DatabaseDAO databaseDAO = new DatabaseDAO();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoadMapViewModel mapViewModel = new LoadMapViewModel();

        LoginPresenter mockPresenter = new LoginPresenter(loginViewModel, viewManagerModel, mapViewModel);
        UserFactory userFactory = new UserFactoryImplementation();

        LoginInteractor interactor = new LoginInteractor(databaseDAO, mockPresenter, userFactory);

        // Add a user to the database
        String username = "testLogin02";
        String name = "testLogin02";
        String email = "testLogin02";
        String password = "testLogin02";
        databaseDAO.save(new User(username, name, email, password));

        LoginInputData input = new LoginInputData(username, password);

        interactor.execute(input);

        LoginState loginState = loginViewModel.getState();
        LoadMapState loadMapState = mapViewModel.getState();
        assertTrue(loadMapState.getUsername().equals(username));
        assertNull(loginState.getUsernameError());
        assertNull(loginState.getPasswordError());
    }

    @Test
    public void testUserDoesNotExist() throws SQLException {
        // Arrange
        DatabaseDAO databaseDAO = new DatabaseDAO();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoadMapViewModel mapViewModel = new LoadMapViewModel();

        LoginPresenter mockPresenter = new LoginPresenter(loginViewModel, viewManagerModel, mapViewModel);
        UserFactory userFactory = new UserFactoryImplementation();

        LoginInteractor interactor = new LoginInteractor(databaseDAO, mockPresenter, userFactory);

        String username = "nonExistentUser";
        String password = "password";

        LoginInputData input = new LoginInputData(username, password);

        interactor.execute(input);

        LoginState loginState = loginViewModel.getState();
        assertEquals("User not exist.", loginState.getUsernameError());
    }

    @Test
    public void testIncorrectPassword() throws SQLException {
        // Arrange
        DatabaseDAO databaseDAO = new DatabaseDAO();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoadMapViewModel mapViewModel = new LoadMapViewModel();

        LoginPresenter mockPresenter = new LoginPresenter(loginViewModel, viewManagerModel, mapViewModel);
        UserFactory userFactory = new UserFactoryImplementation();

        LoginInteractor interactor = new LoginInteractor(databaseDAO, mockPresenter, userFactory);

        // Add a user to the database for testing
        String username = "testLoginPassword";
        String name = "testLoginPassword";
        String email = "testLoginPassword";
        String password = "testLoginPassword";
        databaseDAO.save(new User(username, name, email, password));

        String incorrectPassword = "incorrectPassword";

        LoginInputData input = new LoginInputData(username, incorrectPassword);

        interactor.execute(input);

        LoginState loginState = loginViewModel.getState();
        assertEquals("Password don't match.", loginState.getUsernameError());
    }

    @Test
    public void testSuccessfulExecution() throws SQLException {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        LoginInputBoundary mockInteractor = mock(LoginInputBoundary.class);
        LoginController controller = new LoginController(mockInteractor);

        controller.execute(username, password);

        verify(mockInteractor, times(1)).execute(any(LoginInputData.class));

    }

    @Test
    public void testControllerExecute() throws SQLException {
        LoginInputBoundary mockInteractor = mock(LoginInputBoundary.class);
        LoginController controller = new LoginController(mockInteractor);

        controller.execute("testUser", "testPassword");

        verify(mockInteractor, times(1)).execute(any(LoginInputData.class));
    }

    @Test
    public void testPrepareFailView() {
        LoginViewModel loginViewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoadMapViewModel loadMapViewModel = new LoadMapViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel, viewManagerModel, loadMapViewModel);

        LoginViewModel mockLoginViewModel = mock(LoginViewModel.class);
        Mockito.when(mockLoginViewModel.getState()).thenReturn(new LoginState());

        loginPresenter.setLoginViewModel(mockLoginViewModel);

        String errorMessage = "Invalid credentials";
        loginPresenter.prepareFailView(errorMessage);

        LoginState loginState = mockLoginViewModel.getState();
        assertEquals(errorMessage, loginState.getUsernameError());
    }

    @Test
    public void testGetLogged() {
        LoginViewModel loginViewModel = new LoginViewModel();

        assertFalse(loginViewModel.getLogged());

        loginViewModel.setLogged();

        assertTrue(loginViewModel.getLogged());
    }








    /*@Mock
    private LoadMapView mockLoadMapView;

    @InjectMocks
    private LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        // Initialize JavaFX
        new JFXPanel();

        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Mock behavior for loadMapView.getStackPane()
        when(mockLoadMapView.getStackPane()).thenReturn(new StackPane()); // Replace StackPane with your actual node implementation
    }

    @Test
    public void testOpenJavaFXMapView() {
        // Act
        loginPresenter.openJavaFXMapView();

        // Assert
        verify(mockLoadMapView).getStackPane();
    }*/

}
