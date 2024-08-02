package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Controller;

@Controller
public class MainViewController {

    @FXML
    private ListView<String> listView;
    
    @FXML
    private BorderPane mainBorderPane;

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
    
//    @FXML
//    private void handlePurchaseBill() {
//        // Add your logic for handling Purchase Bill here
//        System.out.println("Purchase Bill clicked");
//        // For example, you could update the listView or open a new window
//        listView.getItems().add("New Purchase Bill");
//    }
//
//    @FXML
//    private void handlePurchaseList() {
//        // Add your logic for handling Purchase List here
//        System.out.println("Purchase List clicked");
//        // For example, you could update the listView or open a new window
//        listView.getItems().add("View Purchase List");
//    }
    
    @FXML
    private void handleOverview() {
        System.out.println("Overview clicked");
        // Add your logic here
    }

    @FXML
    private void handleAnalytics() {
        System.out.println("Analytics clicked");
        // Add your logic here
    }

//    @FXML
//    private void handleViewProducts() {
//        System.out.println("View Products clicked");
//        // Add your logic here
//    }
    
    @FXML
    private void handleViewProducts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fcmanagement/views/AddProductView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Product");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddProduct() {
        System.out.println("Add Product clicked");
        // Add your logic here
    }

    @FXML
    private void handlePurchaseBill() {
        System.out.println("Purchase Bill clicked");
        // Add your logic here
    }

    @FXML
    private void handlePurchaseList() {
        System.out.println("Purchase List clicked");
        // Add your logic here
    }

    @FXML
    private void handleNewSale() {
        System.out.println("New Sale clicked");
        // Add your logic here
    }

    @FXML
    private void handleSaleHistory() {
        System.out.println("Sale History clicked");
        // Add your logic here
    }

    @FXML
    private void handleSalesReport() {
        System.out.println("Sales Report clicked");
        // Add your logic here
    }

    @FXML
    private void handleInventoryReport() {
        System.out.println("Inventory Report clicked");
        // Add your logic here
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
