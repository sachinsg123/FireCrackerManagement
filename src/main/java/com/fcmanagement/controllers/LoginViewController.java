package com.fcmanagement.controllers;

import java.io.IOException;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

@Component
public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Here you would typically check against a database or some other data source
        // For this example, we'll use a hardcoded username and password
        if ("admin".equals(username) && "123".equals(password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_files/MainView.fxml"));
                Parent mainView = loader.load();
                
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = new Scene(mainView, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Error loading main view");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            messageLabel.setText("Invalid username or password");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    @FXML
    private void handleCreateAccount() {
        // Add your logic for creating an account here
    	 Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Create Account");
         alert.setHeaderText("Create a New Account");
         alert.setContentText("Here you would typically show a new screen or modal to create an account.");
         alert.showAndWait();
    }
}