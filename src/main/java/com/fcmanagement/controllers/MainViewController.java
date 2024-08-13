package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fcmanagement.model.Product;

import com.fcmanagement.config.StageManager;

import com.fcmanagement.model.User;
import com.fcmanagement.repositories.ProductRepository;
import com.fcmanagement.repositories.UserRepository;
import com.fcmanagement.service.UserService;
import com.fcmanagement.view.FxmlView;


import javafx.scene.Node;
import javafx.event.ActionEvent;

@Controller
public class MainViewController implements Initializable {
	
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
	
	@Autowired
	private UserService userService;
	
	@Lazy
    @Autowired
    private StageManager stageManager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
    private void home(ActionEvent event) {
		stageManager.switchScene(FxmlView.HOME);
    }
	
	@FXML
    private void handleViewAdminProfile(ActionEvent event) {
		stageManager.switchScene(FxmlView.ViewAdminProfile);
    }
  
	//Product Controller- Created by Younus
	@FXML
	private void handleAddProduct(ActionEvent event) {
		stageManager.switchScene(FxmlView.AddProduct);
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
//	@FXML
//	 private void handleSaveProduct(ActionEvent event) {
//		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		stage.close();
//		
//		String name = productNameField.getText();
//		String price=productPriceField.getText();
//		String quantity=productQuantityField.getText();
//		String unit=productUnitField.getText();
//		String size=productSizeField.getText();
//		
//		System.out.println("Product Saves >> "+ name + price + quantity + unit + size);
//		
//		Product product = new Product(1, name, price, quantity, unit, size);
//		productRepo.save(product);
//		
//		productNameField.clear();
//		productPriceField.clear();
//		productQuantityField.clear();
//		productUnitField.clear();
//		productSizeField.clear();
//		
//	 }
	//Handle Save Product New Controller
	@FXML
	private void handleSaveProduct(ActionEvent event) throws IOException {
		System.out.println("SAVE Button Clicked ...");
	    if (productNameField == null || productPriceField == null ||
	        productQuantityField == null || productUnitField == null ||
	        productSizeField == null) {
	        System.err.println("One or more input fields are not initialized");
	        return;
	    }

	    String name = this.productNameField.getText().trim();
	    String price = this.productPriceField.getText().trim();
	    String quantity = this.productQuantityField.getText().trim();
	    String unit = this.productUnitField.getText().trim();
	    String size = this.productSizeField.getText().trim();
	    
  
//	    String name = productNameField.getText();
//	    String price = productPriceField.getText();
//	    String quantity = productQuantityField.getText();
//	    String unit = productUnitField.getText();
//	    String size = productSizeField.getText();

	    System.out.println("Product Saved >> " + name + " " + price + " " + quantity + " " + unit + " " + size);

	    Product product = new Product(1, name, price, quantity, unit, size);
	    productRepo.save(product);

	    clearFields();

	    // Close the window if needed
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
	}

	private void clearFields() {
	    if (productNameField != null) productNameField.clear();
	    if (productPriceField != null) productPriceField.clear();
	    if (productQuantityField != null) productQuantityField.clear();
	    if (productUnitField != null) productUnitField.clear();
	    if (productSizeField != null) productSizeField.clear();
	}
	
	@FXML
	public void initialize() {
	    System.out.println("productNameField: " + (productNameField != null ? "initialized" : "null"));
	    System.out.println("productPriceField: " + (productPriceField != null ? "initialized" : "null"));
	    System.out.println("productQuantityField: " + (productQuantityField != null ? "initialized" : "null"));
	    System.out.println("productUnitField: " + (productUnitField != null ? "initialized" : "null"));
	    System.out.println("productSizeField: " + (productSizeField != null ? "initialized" : "null"));
	}
	
	//Sale Controller
		@FXML
		private void handleNewSale(ActionEvent event) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
			System.out.println("New Sale clicked by Younus");
			
		}

		@FXML
		private void handleSaleHistory() {
			System.out.println("Sale History clicked");
			// Add your logic here
		}
	
	@FXML
	private void handleLogout(ActionEvent event) {
		stageManager.switchScene(FxmlView.LOGIN);
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
