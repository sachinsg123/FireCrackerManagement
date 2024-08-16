package com.fcmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import com.fcmanagement.config.StageManager;
import com.fcmanagement.service.UserService;
import com.fcmanagement.view.FxmlView;
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
