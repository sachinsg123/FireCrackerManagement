package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fcmanagement.model.User;
import com.fcmanagement.repositories.UserRepository;

import javafx.scene.Node;
import javafx.event.ActionEvent;

@Controller
public class MainViewController {

	@Autowired
	private UserRepository userRepository;
	
	@FXML
	private ListView<String> listView;
	
	@FXML
    private ImageView userImageView;

    @FXML
    private TextField companyNameLable;

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField mobileInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField passwordInput;

	@FXML
	private TextField productNameField;

	@FXML
	private TextField productPriceField;

	@FXML
	private TextField productQuantityField;

	@FXML
	private TextField productUnitField;

	@FXML
	private TextField productSizeField;

	@FXML
	private BorderPane mainBorderPane;

	@FXML
    private void handleViewAdminProfile(ActionEvent event) {
    	// Close the current stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();

        try {
        	Stage primaryStage = new Stage();
        	URL fxmlLocation = getClass().getResource("/fxml_files/ProfileView.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
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
        }
    }
  
	// Product Controller- Created by Younus
	@FXML
	private void handleAddProduct(ActionEvent event) {
		// Close the current stage
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_files/AddProductView.fxml"));
			Parent addProductPage = loader.load();

			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			Scene scene = new Scene(addProductPage, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
			primaryStage.setTitle("FireCracker Desktop Application");
			primaryStage.setScene(scene);
		    primaryStage.setWidth(primaryScreenBounds.getWidth());
		    primaryStage.setHeight(primaryScreenBounds.getHeight());
		    primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleViewProducts(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_files/ProductList.fxml"));
			Parent addProductPage = loader.load();

			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			Stage stage1 = new Stage();
			Scene scene = new Scene(addProductPage, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
			stage1.setScene(scene);
			stage1.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Method to save the Product
	@FXML
	 private void handleSaveProduct(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
		String name = productNameField.getText();
		String price=productPriceField.getText();
		String quantity=productQuantityField.getText();
		String text=productUnitField.getText();
		String size=productSizeField.getText();
		
		System.out.println("Product Saves >> "+ name + price + quantity + text + size);
		
		productNameField.clear();
		productPriceField.clear();
		productQuantityField.clear();
		productUnitField.clear();
		productSizeField.clear();
		
	 }
	
	@FXML
	private void handleLogout(ActionEvent event) {
		// Close the current stage
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

		try {
			Stage primaryStage = new Stage();
			URL fxmlLocation = getClass().getResource("/fxml_files/LoginView.fxml");
      
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
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
		}
	}
	
	@FXML
	private void handleDashboardButton() {
		loadNewScene("/fxml_files/MainView.fxml");
	}

	@FXML
	private void handleProfileButton() {
		loadNewScene("/fxml_files/ProfileView.fxml");
	}

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

	@FXML
	private void handlePurchaseBill() {
		System.out.println("Purchase Bill clicked");
		// Add your logic here
		String productName = productNameField.getText();
		String productPrice = productPriceField.getText();
		String productQuantity = productQuantityField.getText();
		String productUnit = productUnitField.getText();
		String productSize = productSizeField.getText();

		// Implement your logic to add the product here
		System.out.println("Product added: " + productName + " - " + productPrice);
		System.out.println("Product Quantity: " + productQuantity + " Product Unit: " + productUnit + " Product Size: "
				+ productSize);
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
