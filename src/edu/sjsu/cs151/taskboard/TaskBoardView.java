package edu.sjsu.cs151.taskboard;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/*
 * This is likely the most complicated view we have in the program. We probably
 * want to have reusable ColumnView and TaskView classes. These could be
 * implemented as inner classes or as separate external classes.
 */

// TODO: Add EventHandler classes for each clickable object in the UI.

public class TaskBoardView {
	
	private Stage primaryStage;
	private TaskBoardModel model;
	
	public TaskBoardView(TaskBoardModel model, Stage primaryStage) {
		this.model = model;
		this.primaryStage = primaryStage;
	}
	
	
	 public void load() { 
		
		 
        primaryStage.setScene(new Scene(ds));
        primaryStage.show();
	 }
	 
	 
	// TODO: InnerColumnView should extend something so it can be drawn in JavaFX
	private class InnerColumnView {
		private ColumnModel colModel;

		public InnerColumnView(ColumnModel columnModel) {
			this.colModel = columnModel;
		}
	}

	// TODO: InnerTaskView should extend something so it can be drawn in JavaFX
	private class InnerTaskView extends FlowPane {
		private TaskModel taskModel;

		public InnerTaskView(TaskModel task) {
			this.taskModel = taskModel;
			
		}
		
		// TODO: Use a FlowPane for tags
	}
}
