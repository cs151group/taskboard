package edu.sjsu.cs151.taskboard;

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
				model.save();
			LoginView view = new LoginView(primary);
			view.load();
		}
		
	}

}
