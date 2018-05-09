package edu.sjsu.cs151.taskboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
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
		
		// Testing rendering of multiple columns 
		ColumnModel c1 = createDummyColumn();
		ColumnModel c2 = createDummyColumn();
		ColumnModel c3 = createDummyColumn();
		ColumnModel c4 = createDummyColumn();
		ColumnModel c5 = createDummyColumn();
		
		ArrayList<ColumnModel> columns = new ArrayList<>();
		columns.addAll(Arrays.asList(c1, c2, c3, c4, c5));
		
		TaskBoardModel board = new TaskBoardModel("TaskBoard Name", new ArrayList<>(), "mytest.dat");
		
		ProjectModel project = new ProjectModel("My Cool Project", columns);
		
	
		TaskModel wowTask = new TaskModel("Really Cool Task","Wow it's a description!", 
				LocalDate.of(2019, 2, 20), new ArrayList<>());
		ColumnModel wowCol = new ColumnModel("Wow!!!", new ArrayList<TaskModel>());
		ProjectModel wowProj = new ProjectModel("This Project", new ArrayList<>());
		wowProj.addColumn(wowCol);
		wowProj.addTask(wowCol, wowTask);
		board.addProject(wowProj);
		board.addProject(project);
		
		TaskBoardView testView = new TaskBoardView(board, primaryStage);
		testView.load();
		
	}
	
	private static ColumnModel createDummyColumn() {
		TaskModel task = new TaskModel("Lorem Ipsum",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
						+ "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
						+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat.",
				LocalDate.of(2018, 5, 20), new ArrayList<String>());
		TaskModel task2 = new TaskModel("Complete TaskBoardView",
				"The TaskBoardView should have multiple columns with tasks. There will also be a title bar with options.",
				LocalDate.of(2018, 5, 19), new ArrayList<String>());
		TaskModel task3 = new TaskModel("Cattle ",
				"Cattle is a word for certain mammals that belong to the genus Bos. "
				+ "Cattle may be cows, bulls, oxen, heifers, steers, bullocks or calves. "
				+ "Cattle are the most common type of large domesticated hoofed animals. "
				+ "They are a prominent modern member of the subfamily Bovinae.",
				LocalDate.of(2018, 5, 12), new ArrayList<String>());
		
		ColumnModel col = new ColumnModel("In Progress", new ArrayList<TaskModel>());
		col.addTask(task);
		col.addTask(task2);
		col.addTask(task3);
		return col;
	}
}
