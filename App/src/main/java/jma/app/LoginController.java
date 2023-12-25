package jma.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField passwordPasswordField;
    @FXML
    private Button loginButton;

    @FXML
    public void usernameTextFieldAction() {

    }

    @FXML
    public void passwordPasswordFieldAction() {

    }

    @FXML
    public void loginButtonAction() {
        loginMessageLabel.setText("Welcome");
    }
}