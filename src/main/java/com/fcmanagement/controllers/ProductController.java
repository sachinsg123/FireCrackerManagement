package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProductController {

	 @FXML private TextField productNameField;
	    @FXML private TextField priceField;
	    @FXML private TextField quantityField;
	    @FXML private TextField unitField;
	    @FXML private TextField sizeField;

	    @FXML
	    private void handleSaveProduct() {
	        // Implement the logic to save the product
	        String name = productNameField.getText();
	        double price = Double.parseDouble(priceField.getText());
	        int quantity = Integer.parseInt(quantityField.getText());
	        String unit = unitField.getText();
	        String size = sizeField.getText();

	        // TODO: Save the product to your data source
	        System.out.println("Product saved: " + name);

	        // Clear the fields after saving
	        productNameField.clear();
	        priceField.clear();
	        quantityField.clear();
	        unitField.clear();
	        sizeField.clear();
	    }
}
