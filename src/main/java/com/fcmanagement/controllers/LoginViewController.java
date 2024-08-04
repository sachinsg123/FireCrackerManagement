package com.fcmanagement.controllers;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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

        if ("admin".equals(username) && "123".equals(password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_files/MainView.fxml"));
                Parent dashbord = loader.load();
                
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = new Scene(dashbord, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
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
    	Stage primaryStage = new Stage();
    	primaryStage.setTitle("Create Account");

        // Create form components
        Label usernameLabel = new Label("UserName*");
        usernameLabel.setStyle("-fx-font-weight: bold;");
        TextField username = new TextField();
        username.setPromptText("Enter your name");

        Label companyNameLabel = new Label("Company Name*");
        companyNameLabel.setStyle("-fx-font-weight: bold;");
        TextField companyName = new TextField();
        companyName.setPromptText("Enter your Company Name");

        Label emailLabel = new Label("Email*");
        emailLabel.setStyle("-fx-font-weight: bold;");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your e-mail");

        Label mobileLabel = new Label("Mobile*");
        mobileLabel.setStyle("-fx-font-weight: bold;");
        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter your mobile No");
        mobileField.setTextFormatter(new TextFormatter<>(change -> 
            Pattern.matches("[0-9]*", change.getText()) ? change : null));

        Label imageLabel = new Label("Image");
        imageLabel.setStyle("-fx-font-weight: bold;");
        Button uploadImageButton = new Button("Upload Image");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        // Image upload handling
        uploadImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File imageFile = fileChooser.showOpenDialog(primaryStage);
            if (imageFile != null) {
                Image image = new Image(imageFile.toURI().toString());
                imageView.setImage(image);
            }
        });

        Label passwordLabel = new Label("Password*");
        passwordLabel.setStyle("-fx-font-weight: bold;");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter a Password");

        Button submitButton = new Button("Add");
        submitButton.setStyle("-fx-text-fill: black; " +
                              "-fx-font-weight: bold; " +
                              "-fx-border-radius: 5; " +
                              "-fx-background-radius: 5; " +
                              "-fx-padding: 10 20; " +
                              "-fx-font-size: 12px; " +
                              "-fx-cursor: hand;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-text-fill: black; " +
                              "-fx-font-weight: bold; " +
                              "-fx-border-radius: 5; " +
                              "-fx-background-radius: 5; " +
                              "-fx-padding: 10 20; " +
                              "-fx-font-size: 12px; " +
                              "-fx-cursor: hand;");


        // Submit button action
        submitButton.setOnAction(e -> handleSubmit());

        // Cancel button action
        cancelButton.setOnAction(e -> primaryStage.close());

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(username, 1, 1);
        gridPane.add(companyNameLabel, 0, 3);
        gridPane.add(companyName, 1, 3);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailField, 1, 5);
        gridPane.add(mobileLabel, 0, 7);
        gridPane.add(mobileField, 1, 7);
        gridPane.add(imageLabel, 0, 9);
        gridPane.add(uploadImageButton, 1, 9); gridPane.add(imageView, 2, 9);
        gridPane.add(passwordLabel, 0, 11);
        gridPane.add(password, 1, 11);

        HBox buttonBox = new HBox(10, submitButton, cancelButton);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        gridPane.add(buttonBox, 1, 14);

        Scene scene = new Scene(new VBox(gridPane), 400, 500);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleSubmit() {
    	//Write code for Add New User 
    }
    
    @FXML
    private void handleForgotPassword(){
    	Stage primaryStage = new Stage();
    	primaryStage.setTitle("Forgot Password");

        // Email field
        Label emailLabel = new Label("Email");
        emailLabel.setStyle("-fx-font-weight: bold;");
        TextField emailField1 = new TextField();
        emailField1.setPromptText("Enter your Email");

        // New Password field
        Label newPasswordLabel = new Label("New Password");
        newPasswordLabel.setStyle("-fx-font-weight: bold;");
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("New Password");

        Button updatePasswordButton = new Button("Update Password");
        updatePasswordButton.setStyle("-fx-text-fill: black; " +
                "-fx-font-weight: bold; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-padding: 10 20; " +
                "-fx-font-size: 12px; " +
                "-fx-cursor: hand;");
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-text-fill: black; " +
                              "-fx-font-weight: bold; " +
                              "-fx-border-radius: 5; " +
                              "-fx-background-radius: 5; " +
                              "-fx-padding: 10 20; " +
                              "-fx-font-size: 12px; " +
                              "-fx-cursor: hand;");
        
        updatePasswordButton.setOnAction(e -> handleUpdatePassword());
        cancelButton.setOnAction(e -> primaryStage.close());
        
        // Layout for Forgot Password form
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField1, 1, 1);
        gridPane.add(newPasswordLabel, 0, 3);
        gridPane.add(newPasswordField, 1, 3);
        
        HBox buttonBox = new HBox(10, updatePasswordButton, cancelButton);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        gridPane.add(buttonBox, 1, 5);

        // Create a VBox for the entire form
        VBox vbox = new VBox(10, gridPane);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleUpdatePassword() {
    	//Write code for Update User Password
    }
}