package com.fcmanagement.controllers;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

@Controller
public class MainViewController {
	
	
	 @FXML
	 private ListView<String> listView;

	    @FXML
	    public void initialize() {
	        // Initialize your ListView or other components here
	        listView.getItems().addAll("Item 1", "Item 2", "Item 3");
	    }

}
