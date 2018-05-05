package edu.sjsu.cs151.taskboard;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

public class MyTester extends Application {
	
	/*
	 * This is a temporary tester I'm using to work on TaskBoardView. 
	 */

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		TaskBoardModel board = new TaskBoardModel("Board1");
		
		TaskBoardView testView = new TaskBoardView(board, primaryStage);
		testView.test();
	}
}
