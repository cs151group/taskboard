package edu.sjsu.cs151.taskboard;

//import java.awt.Insets;
//import java.awt.TextField;
import java.awt.*;
//import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/*
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
*/

import javafx.scene.control.TextField;


public class LoginView extends Application {

	public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
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
        usernameField.setPrefColumnCount(10);
        usernameField.getText();
        GridPane.setConstraints(usernameField, 1, 0);
        grid.getChildren().add(usernameField);
        
        Text passwordText = new Text (10, 50, "Password");
        GridPane.setConstraints(passwordText, 0, 1);
        grid.getChildren().add(passwordText);
        
        final TextField passwordFiled = new TextField();
        passwordFiled.setPromptText("Enter password");
        GridPane.setConstraints(passwordFiled, 1, 1);
        grid.getChildren().add(passwordFiled);
        

        Button loginButton = new Button();
        loginButton.setText("Login");
        GridPane.setConstraints(loginButton, 2, 2);
        grid.getChildren().add(loginButton);
        loginButton.setOnAction(
        		new LoginButtonController(usernameField.getText(), passwordFiled.getText()));
        


        primaryStage.setScene(new Scene(grid, 300, 100));
        primaryStage.show();
    }
}
