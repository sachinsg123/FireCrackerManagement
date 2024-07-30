package com.fcmanagement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

public class DashBoardApplication extends Application{
	
	private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception{
        springContext = new SpringApplicationBuilder(FireCrackerManagementApplication.class)
        		.properties("server.port = 8087")
        		.run();
    }
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		
	}
	
	@Override
    public void stop() throws Exception {
        springContext.close();
    }

    public static void main(String[] args) {
    	
        launch(args);
    }

}



