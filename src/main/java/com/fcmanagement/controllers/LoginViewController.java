package com.fcmanagement.controllers;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fcmanagement.model.User;
import com.fcmanagement.repositories.UserRepository;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
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
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

@Component
public class LoginViewController {

	@Autowired
	private UserRepository userRepository;
	
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;
    
    @FXML
    private Label userMessage;
    
    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();
        
        User user = userRepository.findByEmail(email);
        if(user == null) {
        	messageLabel.setText("User Not Found !!!");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
        
        if (user.getPassword().equals(password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_files/MainView.fxml"));
                Parent dashbord = loader.load();
                
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                Stage stage = (Stage) emailField.getScene().getWindow();
                Scene scene = new Scene(dashbord, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Error loading main view");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            messageLabel.setText("Invalid Password");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    @FXML
    private void handleCreateAccount() {
    	Stage primaryStage = new Stage();
    	primaryStage.setTitle("Create Account");

        // Create form components
    	Label alert = new Label("* Indicates Mandatory Fields");
    	alert.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
    	
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
        submitButton.setOnAction(e -> handleSubmit(username, companyName, emailField, mobileField, password, e));

        // Cancel button action
        cancelButton.setOnAction(e -> primaryStage.close());

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(alert, 0, 1);
        gridPane.add(usernameLabel, 0, 3);
        gridPane.add(username, 1, 3);
        gridPane.add(companyNameLabel, 0, 5);
        gridPane.add(companyName, 1, 5);
        gridPane.add(emailLabel, 0, 7);
        gridPane.add(emailField, 1, 7);
        gridPane.add(mobileLabel, 0, 9);
        gridPane.add(mobileField, 1, 9);
        gridPane.add(imageLabel, 0, 11);
        gridPane.add(uploadImageButton, 1, 11); gridPane.add(imageView, 2, 11);
        gridPane.add(passwordLabel, 0, 13);
        gridPane.add(password, 1, 13);

        HBox buttonBox = new HBox(10, submitButton, cancelButton);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        gridPane.add(buttonBox, 1, 20);

        Scene scene = new Scene(new VBox(gridPane), 500, 600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleSubmit(TextField userName, TextField companyName, TextField emailField, TextField mobileField, TextField Password, javafx.event.ActionEvent event) {
    	String username = userName.getText();
    	String companyname = companyName.getText();
        String email = emailField.getText();
        String mobile = mobileField.getText();
        String password = Password.getText();
        //String imagePath = imageFile.toURI().toString();
        
        // Create a new User object
        User user = new User();
        user.setUsername(username);
        user.setCompanyName(companyname);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(password);

        userRepository.save(user);
        //Provide feedback to the user (e.g., display a success message)
        
        // Close the current stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        
        userMessage.setText("User Added Successfully !!!");
        userMessage.setStyle("-fx-text-fill: green;");
        userMessage.setVisible(true);

        // Create a timeline to hide the message after 5 seconds (5000 milliseconds)
        Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(2000),
            even -> userMessage.setVisible(false)
        ));
        timeline.setCycleCount(1); // Execute the event only once
        timeline.play(); // Start the timeline
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