/**
 * The SignUpViewModel class serves as the view model for the sign-up functionality, providing data and behavior for the associated view.
 */
package INTERFACE_ADAPTER.signup_adapter;

import INTERFACE_ADAPTER.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpViewModel extends ViewModel {

        public final String TITLE_LABEL = "Sign Up";
        public final String USERNAME_LABEL = "Choose username";

        public final String NAME_LABEL = "Write name";

        public final String EMAIL = "Write email";
        public final String PASSWORD_LABEL = "Choose password";
        public final String REPEAT_PASSWORD_LABEL = "Enter password again";

        public final String SIGNUP_BUTTON_LABEL = "Sign up";
        public final String HAVE_ACCOUNT_BUTTON_LABEL = "Already have account";

        private SignUpState state = new SignUpState();

        public SignUpViewModel() {
            super("sign up");
        }

    /**
     * Sets the state of the sign-up view.
     *
     * @param state The state object containing user input fields and associated error messages.
     */
        public void setState(SignUpState state) {
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

        public SignUpState getState() {
            return state;
        }

    }

