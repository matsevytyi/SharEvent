/**
 * The LoginViewModel class serves as the view model for the login functionality, providing data and behavior for the associated view.

 */

package INTERFACE_ADAPTER.login_adapter;

import INTERFACE_ADAPTER.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    public final String TITLE_LABEL = "Log In View";
    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter password";

    public final String LOGIN_BUTTON_LABEL = "Log in";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private boolean LoginSuccessed = false;

    /**
     * Sets the login success status to true.
     */
    public void setLogged() {
        LoginSuccessed = true;
    }

    /**
     * Retrieves the login success status.
     *
     * @return True if login was successful, false otherwise.
     */
    public boolean getLogged() {
        return LoginSuccessed;
    }

    private LoginState state = new LoginState();

    public LoginViewModel() {
        super("log in");
    }

    /**
     * Sets the state of the login view.
     *
     * @param state The state object containing username, password, and associated error messages.
     */
    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies registered listeners of a change in the view model's state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the view model.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retrieves the current state of the login view.
     *
     * @return The LoginState object representing the state of the login view.
     */
    public LoginState getState() {
        return state;
    }
}
