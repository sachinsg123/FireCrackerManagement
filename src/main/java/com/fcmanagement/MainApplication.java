package com.fcmanagement;

import java.io.IOException;
import java.net.URL;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Screen;

import javafx.stage.Stage;

public class MainApplication extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void init() throws Exception {
		springContext = new SpringApplicationBuilder(FireCrackerManagementApplication.class)
				.properties("server.port = 8087").run();
	}

	@Override
	public void start(Stage stage) throws Exception {

		try {
			Stage primaryStage = new Stage();
			URL fxmlLocation = getClass().getResource("/fxml_files/LoginView.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
			fxmlLoader.setControllerFactory(springContext::getBean);
			Parent root = fxmlLoader.load();
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		    
		    Scene scene = new Scene(root, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
			scene.getStylesheets().add(getClass().getResource("/static/style.css").toExternalForm());
			primaryStage.setTitle("FireCracker Desktop Application");
			primaryStage.setScene(scene);
		    primaryStage.setWidth(primaryScreenBounds.getWidth());
		    primaryStage.setHeight(primaryScreenBounds.getHeight());
		    primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to load FXML file.");
		}
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
