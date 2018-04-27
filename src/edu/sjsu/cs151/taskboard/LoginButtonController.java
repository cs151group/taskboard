package edu.sjsu.cs151.taskboard;

import javafx.*;
import javafx.event.*;
/**
 * Handles the event when the "Login" Button is pressed.
 *
 */
public class LoginButtonController implements EventHandler<ActionEvent> {

	public LoginButtonController() {
		// TODO If necessary, add more parameters to this constructor.
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		/*
		 * Check username and password.
		 * If username/password are incorrect:
		 * 		Stay on LoginView
		 * 		Create a message saying "Invalid username/password"
		 * 			Either as a pop-up or a message at the top of the login window
		 * If username/password are correct
		 * 		Create TaskBoardModel from XML
		 * 		Create TaskBoardView with TaskBoardModel
		 * 		Switch View to TaskBoardView
		 */
	}

}
