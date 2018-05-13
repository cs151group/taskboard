package edu.sjsu.cs151.taskboard;

//import java.awt.Insets;
//import java.awt.TextField;
//import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/*
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
*/

import javafx.scene.control.TextField;

// TODO: Prompt user to select a file on login.
public class LoginView {
	
	private Stage primaryStage;
	
	public LoginView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
    
    public void load() {
        primaryStage.setTitle("Task Board Login");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
       
        Text userText = new Text (10, 50, "Username");
        GridPane.setConstraints(userText, 0, 0);
        grid.getChildren().add(userText);
        
        final TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setPrefColumnCount(12);
       // usernameField.getText();
        GridPane.setConstraints(usernameField, 1, 0);
        grid.getChildren().add(usernameField);
        
        Text passwordText = new Text (10, 50, "Password");
        GridPane.setConstraints(passwordText, 0, 1);
        grid.getChildren().add(passwordText);
        
        final PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setPrefColumnCount(12);
        GridPane.setConstraints(passwordField, 1, 1);
        grid.getChildren().add(passwordField);
        

        Button loginButton = new Button();
        loginButton.setText("Login");
        GridPane.setConstraints(loginButton, 2, 2);
        grid.getChildren().add(loginButton);
        loginButton.setOnAction(
        		new LoginButtonController(primaryStage, usernameField, passwordField));
        loginButton.setDefaultButton(true);
        
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();
    }
}
