package com.fcmanagement.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

@Controller
public class ProductController implements Initializable {
	@FXML 
	private TextField productName;
	
	@FXML 
	private TextField price;
    
	@FXML 
	private TextField quantity;
    
	@FXML 
	private TextField unit;
    
	@FXML 
	private TextField size;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
    @FXML
    private void handleSaveProduct() {
        String name = productName.getText();
        String productPrice = price.getText();
        String productQuantity = quantity.getText();
        String productUnit = unit.getText();
        String productSize = size.getText();

        System.out.println("Product saved: " + name);

        productName.clear();
        price.clear();
        quantity.clear();
        unit.clear();
        size.clear();
    }
}
