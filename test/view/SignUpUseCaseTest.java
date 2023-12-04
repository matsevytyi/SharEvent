
package view;

import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.UserSignUpDataAccessInterface;
import ENTITY.User;
import ENTITY.UserFactory;
import ENTITY.UserFactoryImplementation;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SighUpController;
import INTERFACE_ADAPTER.signup_adapter.SignUpPresenter;
import INTERFACE_ADAPTER.signup_adapter.SignUpState;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;
import USE_CASE.signup.*;
import VIEW.SignUpView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import APP.use_case_factory.SignUpUseCaseFactory;

import java.sql.SQLException;

public class SignUpUseCaseTest {

    @Test
    public void testCreateSuccess() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignUpViewModel signupViewModel = new SignUpViewModel();

        SignUpView signUpView = SignUpUseCaseFactory.SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);

        assertNotNull(signUpView);
    }

    @Test
    public void testExceptionThrown() {
        ViewManagerModel viewManagerModel = new ViewManagerModel() {
            @Override
            public void setActiveView(String viewName) {
                throw new RuntimeException("Simulated RuntimeException");
            }
        };
        LoginViewModel loginViewModel = new LoginViewModel();
        SignUpViewModel signupViewModel = new SignUpViewModel();

        SignUpView signUpView = null;
        try {
            signUpView = SignUpUseCaseFactory.SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
        } catch (Exception e) {
            // Catch the exception thrown during creation
            assertTrue(e instanceof RuntimeException);
        }

        assertNotNull(signUpView);
    }

    @Test
    public void testUserDoesNotExist() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        SignUpPresenter mockPresenter = new SignUpPresenter(new ViewManagerModel(), new SignUpViewModel(), new LoginViewModel());
        UserFactory userFactory = new UserFactoryImplementation();

        SignUpInteractor interactor = new SignUpInteractor(databaseDAO, mockPresenter, userFactory);

        SignUpInputData input = new SignUpInputData("kk", "kk", "kk", "kk", "kk");

        interactor.execute(input);
        assertTrue(databaseDAO.existsByName("kk"));

        SignUpState signUpState = mockPresenter.getSignupViewModel().getState();
        assertTrue(signUpState.getUsernameError().equals("User already exists."));

    }

    @Test
    public void testPasswordsMatch() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        SignUpPresenter mockPresenter = new SignUpPresenter(new ViewManagerModel(), new SignUpViewModel(), new LoginViewModel());
        UserFactory userFactory = new UserFactoryImplementation();

        SignUpInteractor interactor = new SignUpInteractor(databaseDAO, mockPresenter, userFactory);

        SignUpInputData input = new SignUpInputData("kl", "kl", "kl", "kl", "kp");

        interactor.execute(input);

        SignUpState signUpState = mockPresenter.getSignupViewModel().getState();
        assertTrue(signUpState.getUsernameError().equals("Passwords don't match."));

    }

    @Test
    public void testUserDoesNotExistPasswordsMatch() throws SQLException {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        SignUpPresenter mockPresenter = new SignUpPresenter(new ViewManagerModel(), new SignUpViewModel(), new LoginViewModel());
        UserFactory userFactory = new UserFactoryImplementation();

        SignUpInteractor interactor = new SignUpInteractor(databaseDAO, mockPresenter, userFactory);

        String username = "new";
        SignUpInputData input = new SignUpInputData(username, "new", "new", "new", "new");

        if (databaseDAO.existsByName(username)) {
            assertTrue(databaseDAO.existsByName(username));
        }

        interactor.execute(input);

        assertTrue(databaseDAO.existsByName(username));
    }

    @Test
    public void testExecuteDuplicateUser() throws SQLException {

        DatabaseDAO databaseDAO = new DatabaseDAO();
        SignUpPresenter mockPresenter = new SignUpPresenter(new ViewManagerModel(), new SignUpViewModel(), new LoginViewModel());
        UserFactory userFactory = new UserFactoryImplementation();

        String existingUsername = "new02";
        User existingUser = userFactory.create(existingUsername, "new02", "new02", "new02");
        databaseDAO.save(existingUser);

        SignUpInteractor interactor = new SignUpInteractor(databaseDAO, mockPresenter, userFactory);

        // create a user with the same username
        SignUpInputData input = new SignUpInputData(existingUsername, "new02", "new02", "new02", "new02");
        interactor.execute(input);

        SignUpState signUpState = mockPresenter.getSignupViewModel().getState();
        assertTrue(signUpState.getUsernameError().equals("User already exists."));
    }

    @Test
    public void testControllerExecute() throws SQLException {
        SignUpInputBoundary mockInteractor = mock(SignUpInputBoundary.class);
        SighUpController controller = new SighUpController(mockInteractor);

        controller.execute("newUser", "New User", "new@example.com", "password123", "password123");

        verify(mockInteractor, times(1)).execute(any(SignUpInputData.class));
    }

    @Test
    public void testPrepareSuccessView() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        SignUpViewModel signupViewModel = mock(SignUpViewModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);

        SignUpPresenter signUpPresenter = new SignUpPresenter(viewManagerModel, signupViewModel, loginViewModel);

        SignUpOutputData signUpOutputData = new SignUpOutputData("TestUser", false);

        SignUpState signupState = mock(SignUpState.class);
        when(signupViewModel.getState()).thenReturn(signupState);

        signUpPresenter.prepareSuccessView(signUpOutputData);


        verify(signupViewModel, times(1)).getState();
        verify(loginViewModel, times(1)).setState(any());
        verify(loginViewModel, times(1)).firePropertyChanged();

    }

    @Test
    public void testGetterSetter() {
        SignUpOutputData signUpOutputData = new SignUpOutputData("john_doe", true);

        assertEquals("john_doe", signUpOutputData.getUsername());

        assertEquals(true, signUpOutputData.isUseCaseFailed());
        signUpOutputData.setUseCaseFailed(false);
        assertEquals(false, signUpOutputData.isUseCaseFailed());
    }

    @Test
    public void testGetterSetter2() {
        SignUpInputData signUpInputData = new SignUpInputData("john_doe", "John Doe", "john@example.com", "password123", "password123");

        assertThat(signUpInputData.getUsername()).isEqualTo("john_doe");
        assertThat(signUpInputData.getName()).isEqualTo("John Doe");
        assertThat(signUpInputData.getEmail()).isEqualTo("john@example.com");
        assertThat(signUpInputData.getPassword()).isEqualTo("password123");
        assertThat(signUpInputData.getRepeatPassword()).isEqualTo("password123");

        signUpInputData.setUsername("new_username");
        signUpInputData.setName("New Name");
        signUpInputData.setEmail("new_email@example.com");
        signUpInputData.setPassword("new_password");
        signUpInputData.setRepeatPassword("new_password");

        assertThat(signUpInputData.getUsername()).isEqualTo("new_username");
        assertThat(signUpInputData.getName()).isEqualTo("New Name");
        assertThat(signUpInputData.getEmail()).isEqualTo("new_email@example.com");
        assertThat(signUpInputData.getPassword()).isEqualTo("new_password");
        assertThat(signUpInputData.getRepeatPassword()).isEqualTo("new_password");
    }


    private SignUpPresenter signUpPresenter;
    private ViewManagerModel viewManagerModel;
    private SignUpViewModel signUpViewModel;
    private LoginViewModel loginViewModel;

    @BeforeEach
    public void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        signUpViewModel = mock(SignUpViewModel.class);
        loginViewModel = mock(LoginViewModel.class);
        signUpPresenter = new SignUpPresenter(viewManagerModel, signUpViewModel, loginViewModel);
    }

    @Test
    public void testPrepareSuccessView02() {
        SignUpOutputData response = new SignUpOutputData("john_doe", false);
        when(loginViewModel.getState()).thenReturn(mock(LoginState.class));

        signUpPresenter.prepareSuccessView(response);
        verify(loginViewModel).setState(any());
        verify(loginViewModel).firePropertyChanged();
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testSuccessfulSignUp() throws SQLException {
        UserSignUpDataAccessInterface userDataAccessObject = mock(UserSignUpDataAccessInterface.class);
        SignUpOutputBoundary userPresenter = mock(SignUpOutputBoundary.class);
        UserFactory userFactory = mock(UserFactory.class);
        SignUpInteractor signUpInteractor = new SignUpInteractor(userDataAccessObject, userPresenter, userFactory);

        SignUpInputData signUpInputData = new SignUpInputData("TestUser", "TestUser", "TestUser", "TestUser", "TestUser");

        when(userDataAccessObject.existsByName(anyString())).thenReturn(false);

        User mockUser = mock(User.class);
        when(userFactory.create(anyString(), anyString(), anyString(), anyString())).thenReturn(mockUser);

        signUpInteractor.execute(signUpInputData);

        verify(userDataAccessObject, times(1)).existsByName("TestUser");
        verify(userFactory, times(1)).create("TestUser", "TestUser", "TestUser", "TestUser");
        verify(userDataAccessObject, times(1)).save(mockUser);


    }

}


