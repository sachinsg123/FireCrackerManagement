package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;

import org.springframework.stereotype.Controller;

@Controller
public class MainViewController {

    @FXML
    private ListView<String> listView;

    @FXML
    public void initialize() {
        // Initialize your UI components or set up event listeners
        listView.getItems().addAll("Item 1", "Item 2", "Item 3");
    }
    @FXML
    private void handleDashboardButton() {
        loadNewScene("/fxml_files/MainView.fxml");
    }
    
    @FXML
    private void handleProfileButton() {
        loadNewScene("/fxml_files/ProfileView.fxml");
    }
    
    private void loadNewScene(String fxmlFile) {
        try {
        	 FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
             Parent root = loader.load();
             Stage stage = (Stage) root.getScene().getWindow();
             stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
