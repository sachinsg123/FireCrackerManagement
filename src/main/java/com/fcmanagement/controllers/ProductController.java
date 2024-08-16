package com.fcmanagement.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.fcmanagement.config.StageManager;
import com.fcmanagement.model.Product;
import com.fcmanagement.repositories.ProductRepository;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

@Controller
public class ProductController implements Initializable {

	@FXML
	private TextField productName;

	@FXML
	private TextField price;

	@FXML
	private TextField category;
	
	@FXML
	private TextField quantity;

	@FXML
	private TextField unit;

	@FXML
	private TextField size;
	
	@FXML
	private TextField brand;
	
	@FXML
    private Label userMessage;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

    @FXML
    private void handleSaveProduct() {
        String name = productName.getText();
        String productPrice = price.getText();
        String productQuantity = quantity.getText();
        String productCategory = category.getText();
        String productBrand = brand.getText();
        String productUnit = unit.getText();
        String productSize = size.getText();
        
        Product product = new Product();
        
        
        
        
        product.setProductName(name);
        product.setPrice(productPrice);
        product.setQuantity(productQuantity);
//        product.setCategory(category);
//        product.setBrand(brand);
        product.setUnit(productUnit);
        product.setSize(productSize);
        
//        productRepository.save(product);
        
        
        productName.clear();
        price.clear();
        quantity.clear();
        category.clear();
        brand.clear();
        size.clear();
        unit.clear();
        
        showMessage("Product Added Successfully !!!", "-fx-text-fill: green;");
    }
    
    private void showMessage(String message, String style) {
        userMessage.setText(message);
        userMessage.setStyle(style);
        userMessage.setVisible(true);

        Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(2000),
            event -> userMessage.setVisible(false)
        ));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
