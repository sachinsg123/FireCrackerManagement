package com.fcmanagement;

import java.io.IOException;
import java.net.URL;

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
		try {
			URL fxmlLocation = getClass().getResource("/fxml_files/MainView.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
	        fxmlLoader.setControllerFactory(springContext::getBean);
            Parent root = fxmlLoader.load();
            primaryStage.setTitle("JavaFX and Spring Boot FireCracker application");
            primaryStage.setScene(new Scene(root));
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
