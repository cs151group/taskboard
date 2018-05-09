package edu.sjsu.cs151.taskboard;

import javafx.application.Application;
import javafx.stage.Stage;

public class TaskBoard extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		LoginView loginView = new LoginView(primaryStage);
		loginView.load();
		
//		//This is to test Project View
//		ProjectView projView = new ProjectView(primaryStage);
//		projView.load();
		
	}
	

	

}
