package edu.sjsu.cs151.taskboard;

import javafx.*;
import javafx.event.*;
/**
 * Handles the event when the "Login" Button is pressed.
 *
 */
public class LoginButtonController implements EventHandler<ActionEvent> {
	private String username;
	private String password;

	public LoginButtonController(String username, String password) {
		// TODO If necessary, add more parameters to this constructor.
		// For example, do we need any other information about LoginView?
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
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
		if(username.equals("admin") && password.equals("admin")) {
			TaskBoardModel model = new TaskBoardModel();
			/*
			 * TODO: Add new branch if user XML data already exists.
			 * If it exists, call the TaskboardModel(filename) constructor instead.
			 */
			
			TaskBoardView view = new TaskBoardView(model);
			// TODO: set the view visible somehow
		}
		else {
			
		}
	}

}
