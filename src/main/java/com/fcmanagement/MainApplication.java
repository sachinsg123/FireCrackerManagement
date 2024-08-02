package com.fcmanagement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApplication extends Application{
	
	private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception{
        springContext = new SpringApplicationBuilder(FireCrackerManagementApplication.class)
        		.properties("server.port = 8087")
        		.run();
    }
	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/MainView.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("JavaFX and Spring Boot FireCracker application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
		// Top Header
//        HBox topHeader = new HBox(10);
//        topHeader.setStyle("-fx-background-color: #2E3A4F;");
//        Label dashboardLabel = new Label("Dashboard");
//        dashboardLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
//        topHeader.getChildren().add(dashboardLabel);
//
//        // Left Sidebar
//        VBox leftSidebar = new VBox(10);
//        leftSidebar.setStyle("-fx-background-color: #3A4D6D;");
//        Button homeButton = new Button("Home");
//        Button profileButton = new Button("Profile");
//        Button settingsButton = new Button("Settings");
//        Button logoutButton = new Button("Logout");
//        leftSidebar.getChildren().addAll(homeButton, profileButton, settingsButton, logoutButton);
//
//        // Main Content Area
//        VBox mainContent = new VBox(10);
//        mainContent.setPadding(new javafx.geometry.Insets(10));
//        Label welcomeLabel = new Label("Welcome to the Dashboard!");
//        welcomeLabel.setStyle("-fx-font-size: 24px;");
//        ListView<String> listView = new ListView<>();
//        mainContent.getChildren().addAll(welcomeLabel, listView);
//
//        // Main Layout
//        BorderPane mainLayout = new BorderPane();
//        mainLayout.setTop(topHeader);
//        mainLayout.setLeft(leftSidebar);
//        mainLayout.setCenter(mainContent);
//
//        // Scene and Stage
//        Scene scene = new Scene(mainLayout, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Dashboard");
//        primaryStage.show();
//		
	}
	
	@Override
    public void stop() throws Exception {
        springContext.close();
    }

    public static void main(String[] args) {
    	
        launch(args);
    }

}
