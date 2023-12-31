package jma.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField passwordPasswordField;
    @FXML
    private Button loginButton;



    @FXML
    public void loginButtonAction() {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validateLogin();
        }
        else {
            loginMessageLabel.setText("Please enter username and password.");
        }

    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectdb = connectNow.getConnection();
        String verifLogin = "select count(1) FROM users WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";

        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Welcome");
                }
                else {
                    loginMessageLabel.setText("Invalid Login! Please try again.");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}