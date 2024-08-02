package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProductController {

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
	private void handleAddProduct() {
		String productName = productNameField.getText();
		String productPrice = productPriceField.getText();
		String productQuantity = productQuantityField.getText();
		String productUnit = productUnitField.getText();
		String productSize = productSizeField.getText();

		// Implement your logic to add the product here
		System.out.println("Product added: " + productName + " - " + productPrice);
		System.out.println("Product Quantity: " + productQuantity + " Product Unit: " + productUnit + " Product Size: " + productSize);
	}

	@FXML
    private void handleAddProductForm() {
        String productName = productNameField.getText();
        String productPrice = productPriceField.getText();
        String productQuantity = productQuantityField.getText();
		String productUnit = productUnitField.getText();
		String productSize = productSizeField.getText();
		
        // Implement your logic to add the product here
        System.out.println("Product added: " + productName + " - " + productPrice);
    }
	
	//AddProductViewController
	@FXML
    private void handleSaveProduct() {
        // Implement the save logic here
        String productName = productNameField.getText();
        String productPrice = productPriceField.getText();
        String productQuantity = productQuantityField.getText();
		String productUnit = productUnitField.getText();
		String productSize = productSizeField.getText();
		
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Quantity: "+ productQuantity);
        System.out.println("Product Unit: "+ productUnit);
        System.out.println("Product Size: "+ productSize);
        // Add code to save the product information
    }
}
