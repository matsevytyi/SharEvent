
package VIEW;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginController;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SighUpController;
import INTERFACE_ADAPTER.signup_adapter.SignUpState;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;


    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();


    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final LoginController loginController;
    final JButton logIn;
    final JButton cancel;

    public LoginView(LoginController loginController, LoginViewModel loginViewModel) {
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        title.setFont(titleFont);

        title.setForeground(new Color(59, 89, 182));

        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        logIn.setForeground(new Color(59, 89, 182));
        logIn.setFocusPainted(false);
        logIn.setFont(new Font("Arial", Font.BOLD, 16));

        cancel.setForeground(new Color(173, 5, 45));
        cancel.setFocusPainted(false);
        cancel.setFont(new Font("Arial", Font.BOLD, 16));

        // Set button size
        Dimension buttonSize = new Dimension(170, 70);
        logIn.setPreferredSize(buttonSize);
        cancel.setPreferredSize(buttonSize);

        // Set layout manager and add components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue()); // Align components to the center
        add(title);
        add(Box.createRigidArea(new Dimension(0, 13))); // Add some space
        add(usernameInfo);
        add(passwordInfo);
        add(buttons);
        add(Box.createVerticalGlue()); // Align components to the center

        logIn.addActionListener(this);
        cancel.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    System.exit(0);
                }
            }
        });


        logIn.addActionListener(

                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState state = loginViewModel.getState();
                            String username = state.getUsername();
                            String password = state.getPassword();

                            try {
                                loginController.execute(username, password);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LoginView.this);

                            frame.dispose();
                        }
                    }
                }
        );
        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setPassword(passwordErrorField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }




    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

}
