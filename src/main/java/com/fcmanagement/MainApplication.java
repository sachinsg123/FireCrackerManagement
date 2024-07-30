package com.fcmanagement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		
	}
	
	@Override
    public void stop() throws Exception {
        springContext.close();
    }

    public static void main(String[] args) {
    	
        launch(args);
    }

}
