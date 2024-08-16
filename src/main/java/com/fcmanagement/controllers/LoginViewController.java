package com.fcmanagement.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fcmanagement.config.StageManager;
import com.fcmanagement.model.User;
import com.fcmanagement.repositories.UserRepository;
import com.fcmanagement.service.UserService;
import com.fcmanagement.view.FxmlView;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.util.Duration;
import javafx.event.ActionEvent;
@Controller
public class LoginViewController implements Initializable {
	
	@FXML
    private Button btnLogin;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@FXML
    private TextField userId;
    
	@FXML
    private ImageView userImageView;

    @FXML
    private Label companyNameLable;
    
    @FXML
    private TextField companyNameInput;

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField mobileInput;
    
    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label messageLabel;
    
    @FXML
    private Label userMessage;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	User user = userService.authenticate();
	    if (userNameInput == null) {
	        System.out.println("userNameInput is null");
	    } else {
	    	companyNameLable.setText(user.getCompanyName());
	        userNameInput.setText(user.getUsername());
	        emailInput.setText(user.getEmail());
	        mobileInput.setText(user.getMobile());
	    }
	}
	
	@FXML
    private void home(ActionEvent event) {
		stageManager.switchScene(FxmlView.HOME);
    }
	
    @FXML
    private void handleLogin() throws IOException{
    	if(userService.authenticate(getUsername(), getPassword())){
    		stageManager.switchScene(FxmlView.HOME);
    		
    	}else{
    		messageLabel.setText("Login Failed.");
    	}
    }
	
	public String getPassword() {
		return passwordInput.getText();
	}

	public String getUsername() {
		return emailInput.getText();
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
        userNameInput = new TextField();
        userNameInput.setPromptText("Enter your name");

        Label companyNameLabel = new Label("Company Name*");
        companyNameLabel.setStyle("-fx-font-weight: bold;");
        companyNameInput = new TextField();
        companyNameInput.setPromptText("Enter your Company Name");

        Label emailLabel = new Label("Email*");
        emailLabel.setStyle("-fx-font-weight: bold;");
        emailInput = new TextField();
        emailInput.setPromptText("Enter your e-mail");

        Label mobileLabel = new Label("Mobile*");
        mobileLabel.setStyle("-fx-font-weight: bold;");
        mobileInput = new TextField();
        mobileInput.setPromptText("Enter your mobile No");
        mobileInput.setTextFormatter(new TextFormatter<>(change -> 
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
        passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter a Password");

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
        submitButton.setOnAction(e -> handleSubmit(userNameInput, companyNameInput, emailInput, mobileInput, passwordInput, e));

        // Cancel button action
        cancelButton.setOnAction(e -> primaryStage.close());

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(alert, 0, 1);
        gridPane.add(usernameLabel, 0, 3);
        gridPane.add(userNameInput, 1, 3);
        gridPane.add(companyNameLabel, 0, 5);
        gridPane.add(companyNameInput, 1, 5);
        gridPane.add(emailLabel, 0, 7);
        gridPane.add(emailInput, 1, 7);
        gridPane.add(mobileLabel, 0, 9);
        gridPane.add(mobileInput, 1, 9);
        gridPane.add(imageLabel, 0, 11);
        gridPane.add(uploadImageButton, 1, 11); gridPane.add(imageView, 2, 11);
        gridPane.add(passwordLabel, 0, 13);
        gridPane.add(passwordInput, 1, 13);

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
        
        User userFound = userRepository.findByEmail(email);
        if(userFound != null) {
        	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
            
            showMessage("Email Address Already Used !!!", "-fx-text-fill: red;");
            return;
        }
        
        User user = new User();
        user.setUsername(username);
        user.setCompanyName(companyname);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(password);
        user.setStatus("Active");
        
        userService.save(user);
        
        stageManager.switchScene(FxmlView.LOGIN);
        
        showMessage("User Added Successfully !!!", "-fx-text-fill: green;");
    }
    
    @FXML
    private void handleForgotPassword() {
    	Stage primaryStage = new Stage();
    	primaryStage.setTitle("Forgot Password");

        // Email field
    	Label alert = new Label("* Indicates Mandatory Fields");
    	alert.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
    	
        Label emailLabel = new Label("Email*");
        emailLabel.setStyle("-fx-font-weight: bold;");
        TextField emailField1 = new TextField();
        emailField1.setPromptText("Enter your Email");

        // New Password field
        Label newPasswordLabel = new Label("New Password*");
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
        
        updatePasswordButton.setOnAction(e -> handleUpdatePassword(emailField1, newPasswordField, e));
        cancelButton.setOnAction(e -> primaryStage.close());
        
        // Layout for Forgot Password form
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(alert, 0, 1);
        gridPane.add(emailLabel, 0, 3);
        gridPane.add(emailField1, 1, 3);
        gridPane.add(newPasswordLabel, 0, 5);
        gridPane.add(newPasswordField, 1, 5);
        
        HBox buttonBox = new HBox(10, updatePasswordButton, cancelButton);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        gridPane.add(buttonBox, 1, 7);

        // Create a VBox for the entire form
        VBox vbox = new VBox(10, gridPane);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleUpdatePassword(TextField emailField, TextField Password,  javafx.event.ActionEvent event) {
    	String email = emailField.getText();
        String password = Password.getText();
        
        User userFound = userRepository.findByEmail(email);
        if(userFound == null) {
        	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
            
            showMessage("User Not Found !!!", "-fx-text-fill: red;");
            return;
        }
        
        userFound.setPassword(password);
        userService.save(userFound);
        
        stageManager.switchScene(FxmlView.LOGIN);
        
        showMessage("Password Updated Successfully !!!", "-fx-text-fill: green;");
    }
    
    @FXML
    private void updateUserProfile () {
    	String username = userNameInput.getText();
    	String companyname = companyNameLable.getText();
        String email = emailInput.getText();
        String mobile = mobileInput.getText();
        
        User user = userService.findByEmail(email);
        
        user.setUsername(username);
        user.setCompanyName(companyname);
        user.setEmail(email);
        user.setMobile(mobile);
        
        userService.save(user);
        showMessage("User Updated Successfully !!!", "-fx-text-fill: green;");
    }
    
    private void showMessage(String message, String style) {
        userMessage.setText(message);
        userMessage.setStyle(style);
        userMessage.setVisible(true);

        Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(2000),
            event -> userMessage.setVisible(false)
        ));
        timeline.setCycleCount(1);
        timeline.play();
    }
}