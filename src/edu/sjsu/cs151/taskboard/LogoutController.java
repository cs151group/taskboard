package edu.sjsu.cs151.taskboard;

import java.io.FileNotFoundException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class LogoutController implements EventHandler<ActionEvent> {

	private Stage primary;
	private TaskBoardModel model;

	public LogoutController(Stage primary, TaskBoardModel model) {
		this.primary = primary;
		this.model = model;
	}

	@Override
	public void handle(ActionEvent event) {
		Alert sureAlert = new Alert(AlertType.CONFIRMATION);
		sureAlert.setHeaderText("Logout Confirmation");
		sureAlert.setContentText("Are you sure you wish to log out?");
		Optional<ButtonType> result = sureAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			try {
				model.save();
			} catch (FileNotFoundException e) {
				Alert fnfAlert = new Alert(AlertType.ERROR);
				fnfAlert.setHeaderText("File Error");
				fnfAlert.setContentText("The file did not successfully save.");
				e.printStackTrace();
				
				// TODO: Fix this exception handling. Have user pick new file
			}
			LoginView view = new LoginView(primary);
			view.load();
		}
		
	}

}
