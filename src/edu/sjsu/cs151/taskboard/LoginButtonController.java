package edu.sjsu.cs151.taskboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
/**
 * Handles the event when the "Login" Button is pressed.
 *
 */
public class LoginButtonController implements EventHandler<ActionEvent> {
	private Stage primary;
	private TextField userField;
	private TextField passField;
	private static final String DEFAULT_FILE_PATH = "TaskBoard1.xml";
	
	public LoginButtonController(Stage primaryStage, TextField userField, TextField passField) {
		this.primary = primaryStage;
		this.userField = userField;
		this.passField = passField;
	}



	@Override
	public void handle(ActionEvent event) {
		/*
		 * Check username and password.
		 * If username/password are correct
		 * 		Create TaskBoardModel from XML
		 * 		Create TaskBoardView with TaskBoardModel
		 * 		Switch View to TaskBoardView
		 * If username/password are incorrect:
		 * 		Stay on LoginView
		 * 		Create a message saying "Invalid username/password"
		 * 			Either as a pop-up or a message at the top of the login window
		 */
		String username = userField.getText();
		String password = passField.getText();
		if (username == null | password == null | username.trim().equals("") | password.trim().equals("")) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Invalid login");
			errorAlert.setContentText("Please enter a username and password.");
			errorAlert.show();
		} else if (username.trim().equals("admin") && password.trim().equals("admin")) {
			login();
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Invalid login");
			errorAlert.setContentText("Incorrect username / password combination.");
			errorAlert.show();
		}
	}

	private void login() {
		
		File defaultFile = new File(DEFAULT_FILE_PATH);
		File workingDir = new File(System.getProperty("user.dir"));
		File[] matches = workingDir.listFiles((path) -> path.getName().matches(".*.xml"));
		// If file exists...
		if (defaultFile.exists()) {
			TaskBoardModel model = new TaskBoardModel(DEFAULT_FILE_PATH);
			TaskBoardView view = new TaskBoardView(model, primary);
			view.load();
		}
		else if (matches != null && matches.length != 0) {
			TaskBoardModel model = new TaskBoardModel(matches[0].getName());
			TaskBoardView view = new TaskBoardView(model, primary);
			view.load();
		}
		else {
			TaskBoardModel model = new TaskBoardModel("TaskBoard1", new ArrayList<>());
			ProjectView newProj = new ProjectView(primary, model);
			newProj.load();
		}

	}

}
