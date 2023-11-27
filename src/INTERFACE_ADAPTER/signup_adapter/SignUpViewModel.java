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

        public void setState(SignUpState state) {
            this.state = state;
        }

        private final PropertyChangeSupport support = new PropertyChangeSupport(this);

        // This is what the Signup Presenter will call to let the ViewModel know
        // to alert the View
        public void firePropertyChanged() {
            support.firePropertyChange("state", null, this.state);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        public SignUpState getState() {
            return state;
        }

    }

