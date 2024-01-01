package jma.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class AppController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Label changePasswordMessageLabel;
    @FXML
    private Label errorChangePasswordMessageLabel;
    @FXML
    private TextField passwordPasswordField;
    @FXML
    private TextField currentPasswordField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private TextField confirmNewPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button userButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button saveSettingsButton;
    @FXML
    private Button resultsButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label fullNameLabel;


    @FXML
    public void loginButtonAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectdb = connectNow.getConnection();
            String verifLogin = "SELECT * FROM users WHERE username = ? AND password = ?";

            try (PreparedStatement preparedStatement = connectdb.prepareStatement(verifLogin)) {
                preparedStatement.setString(1, usernameTextField.getText());
                preparedStatement.setString(2, passwordPasswordField.getText());

                try (ResultSet queryResult = preparedStatement.executeQuery()) {
                    if (queryResult.next()) {
                        String userName = queryResult.getString("username");
                        UserInfo.getInstance().setUserName(userName);

                        String fullName = queryResult.getString("first_name") + " " + queryResult.getString("last_name");
                        UserInfo.getInstance().setFullName(fullName);
                        System.out.println(fullName);

                        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene scene = new Scene(root, 700, 465);
                            Stage stageUser = new Stage();
                            stageUser.setTitle(UserInfo.getInstance().getFullName());
                            stageUser.setScene(scene);
                            stageUser.show();
                            System.out.println("Open User-View");

                            // Set labels if they are not null
                            if (userNameLabel != null) {
                                userNameLabel.setText("Username: " + userName);
                            } else {
                                System.out.println("userNameLabel is null.");
                            }

                            if (fullNameLabel != null) {
                                fullNameLabel.setText("Full Name: " + fullName);
                            } else {
                                System.out.println("fullNameLabel is null.");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        loginMessageLabel.setText("Invalid Login! Please try again.");
                        System.out.println("Invalid Login!");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectNow.closeConnection(connectdb);
            }
        } else {
            loginMessageLabel.setText("Please enter username and password.");
            System.out.println("Null username and password.");
        }
    }


    @FXML
    public void logoutButtonAction(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();
            System.out.println("Open Login-View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void settingsButtonAction(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_settings-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 465);
            Stage stageUser = new Stage();
            stageUser.setTitle("Settings");
            stageUser.setScene(scene);
            stageUser.show();
            System.out.println("Open Settings-View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void userButtonAction(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 465);
            Stage stageUser = new Stage();
            stageUser.setTitle("User");
            stageUser.setScene(scene);
            stageUser.show();
            System.out.println("Open User-View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void resultsButtonAction(ActionEvent event) {
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_results-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 465);
            Stage stageUser = new Stage();
            stageUser.setTitle("Results");
            stageUser.setScene(scene);
            stageUser.show();
            System.out.println("Open Results-View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveSettingsButtonAction(ActionEvent event) {
        if (!currentPasswordField.getText().isBlank() && !newPasswordField.getText().isBlank() && !confirmNewPasswordField.getText().isBlank()) {
            if (Objects.equals(newPasswordField.getText(), confirmNewPasswordField.getText())) {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectdb = connectNow.getConnection();

                String updatePasswordQuery = "UPDATE users SET password = ? WHERE username = ?";

                try (PreparedStatement preparedStatement = connectdb.prepareStatement(updatePasswordQuery)) {
                    preparedStatement.setString(1, newPasswordField.getText());
                    preparedStatement.setString(2, UserInfo.getInstance().getUserName());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Password updated in the database.");
                        errorChangePasswordMessageLabel.setText("");
                        changePasswordMessageLabel.setText("Password changed successfully!");
                    } else {
                        System.out.println("Failed to update password in the database.");
                        changePasswordMessageLabel.setText("");
                        errorChangePasswordMessageLabel.setText("Error, please try again later.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connectNow.closeConnection(connectdb);
                }

            }else {
                changePasswordMessageLabel.setText("");
                errorChangePasswordMessageLabel.setText("Oops! Passwords don't match.");
            }
        }else {
            changePasswordMessageLabel.setText("");
            errorChangePasswordMessageLabel.setText("All fields are required.");
        }
    }
}