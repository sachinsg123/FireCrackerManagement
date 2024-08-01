package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
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

    // Add any additional methods or event handlers here
}
