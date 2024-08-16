package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

import com.fcmanagement.config.StageManager;
import com.fcmanagement.model.User;
import com.fcmanagement.repositories.UserRepository;
import com.fcmanagement.service.UserService;
import com.fcmanagement.view.FxmlView;

import javafx.scene.Node;
import javafx.event.ActionEvent;

@Controller
public class MainViewController implements Initializable {

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

	@FXML
	private void handleAddProduct() {
		stageManager.switchScene(FxmlView.AddProduct);
	}
	
	@FXML
	private void handleViewProducts(ActionEvent event) {
		stageManager.switchScene(FxmlView.ProductList);
	}
	
	@FXML
	private void handleLogout(ActionEvent event) {
		stageManager.switchScene(FxmlView.LOGIN);
	}
}
