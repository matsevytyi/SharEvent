
package VIEW;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginState;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.signup_adapter.SighUpController;
import INTERFACE_ADAPTER.signup_adapter.SignUpState;
import INTERFACE_ADAPTER.signup_adapter.SignUpViewModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

@Getter
        public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {
            public final String viewName = "sign up";

            private final SignUpViewModel signupViewModel;
            private final JTextField usernameInputField = new JTextField(15);
            private final JTextField nameInputField = new JTextField(15);
            private final JTextField emailInputField = new JTextField(15);
            private final JPasswordField passwordInputField = new JPasswordField(15);
            private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
            private final SighUpController sighUpController;

            private final LoginViewModel loginViewModel;
            private final ViewManagerModel viewManagerModel;

            private final JButton signUp;
            private final JButton have_account;

            public SignUpView(SighUpController sighUpController, SignUpViewModel signupViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {

                this.sighUpController = sighUpController;
                this.signupViewModel = signupViewModel;
                this.loginViewModel = loginViewModel;
                this.viewManagerModel = viewManagerModel;
                signupViewModel.addPropertyChangeListener(this);

                setPreferredSize(new Dimension(600, 400));

                JLabel title = new JLabel(signupViewModel.TITLE_LABEL);
                title.setAlignmentX(Component.CENTER_ALIGNMENT);

                Font titleFont = new Font("Arial", Font.BOLD, 24);
                title.setFont(titleFont);

                title.setForeground(new Color(59, 89, 182));

                title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

                signupViewModel.addPropertyChangeListener(this);


                LabelTextPanel usernameInfo = new LabelTextPanel(
                        new JLabel(signupViewModel.USERNAME_LABEL), usernameInputField);
                LabelTextPanel nameinfo = new LabelTextPanel(
                        new JLabel(signupViewModel.NAME_LABEL), nameInputField);
                LabelTextPanel emailInfo = new LabelTextPanel(
                        new JLabel(signupViewModel.EMAIL), emailInputField);
                LabelTextPanel passwordInfo = new LabelTextPanel(
                        new JLabel(signupViewModel.PASSWORD_LABEL), passwordInputField);
                LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                        new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

                JPanel buttons = new JPanel();
                signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
                buttons.add(signUp);
                have_account = new JButton(signupViewModel.HAVE_ACCOUNT_BUTTON_LABEL);
                buttons.add(have_account);

                // Set custom styles for Swing components
                signUp.setForeground(new Color(59, 89, 182));
                //signUp.setBackground(new Color(59, 89, 182));
                signUp.setFocusPainted(false);
                signUp.setFont(new Font("Arial", Font.BOLD, 16));

                have_account.setForeground(new Color(8, 79, 6));
                have_account.setFocusPainted(false);
                have_account.setFont(new Font("Arial", Font.BOLD, 14));

                // Set button size
                Dimension buttonSize = new Dimension(170, 70);
                signUp.setPreferredSize(buttonSize);
                have_account.setPreferredSize(buttonSize);

                // Set layout manager and add components
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                add(Box.createVerticalGlue()); // Align components to the center
                add(title);
                add(Box.createRigidArea(new Dimension(0, 13))); // Add some space
                add(usernameInfo);
                add(nameinfo);
                add(emailInfo);
                add(passwordInfo);
                add(repeatPasswordInfo);
                add(buttons);
                add(Box.createVerticalGlue()); // Align components to the center

                signUp.addActionListener(
                        // This creates an anonymous subclass of ActionListener and instantiates it.
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (evt.getSource().equals(signUp)) {
                                    SignUpState state = signupViewModel.getState();
                                    String username = state.getUsername();
                                    String name = state.getName();
                                    String email = state.getEmail();
                                    String password = state.getPassword();
                                    String repeatPassword = state.getRepeatPassword();

                                    try {
                                        sighUpController.execute(username, name, email, password, repeatPassword);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                );
                have_account.addActionListener(
                        // This creates an anonymous subclass of ActionListener and instantiates it.
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (evt.getSource().equals(have_account)) {

                                    LoginState loginState = loginViewModel.getState();

                                    loginViewModel.setState(loginState);
                                    loginViewModel.firePropertyChanged();

                                    viewManagerModel.setActiveView(loginViewModel.getViewName());
                                    viewManagerModel.firePropertyChanged();

                                }
                            }
                        }
                );

                usernameInputField.addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                SignUpState currentState = signupViewModel.getState();
                                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                                signupViewModel.setState(currentState);
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                            }
                        });

                nameInputField.addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                SignUpState currentState = signupViewModel.getState();
                                currentState.setName(nameInputField.getText() + e.getKeyChar());
                                signupViewModel.setState(currentState);
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                            }
                        });

                emailInputField.addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                SignUpState currentState = signupViewModel.getState();
                                currentState.setEmail(emailInputField.getText() + e.getKeyChar());
                                signupViewModel.setState(currentState);
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                            }
                        });

                passwordInputField.addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                SignUpState currentState = signupViewModel.getState();
                                currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                                signupViewModel.setState(currentState);
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                            }
                        });

                repeatPasswordInputField.addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                SignUpState currentState = signupViewModel.getState();
                                currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                                signupViewModel.setState(currentState);
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                            }
                        });

                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                this.add(title);
                this.add(usernameInfo);
                this.add(nameinfo);
                this.add(emailInfo);
                this.add(passwordInfo);
                this.add(repeatPasswordInfo);
                this.add(buttons);
            }



            public void actionPerformed(ActionEvent evt) {
                System.out.println("Cancel not implemented yet.");
            }

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                SignUpState state = (SignUpState) evt.getNewValue();
                if (state.getUsernameError() != null) {
                    JOptionPane.showMessageDialog(this, state.getUsernameError());
                }
            }
        }


