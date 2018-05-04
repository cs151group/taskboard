package edu.sjsu.cs151.taskboard;

import java.util.ArrayList;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

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
		
		
        primaryStage.setScene(new Scene(new InnerTaskView(new TaskModel("Test"))));
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
			// TODO: Make this look better
			this.taskModel = task;
			this.setOrientation(Orientation.VERTICAL);
			Text name = new Text(taskModel.getName());
			Text desc = new Text(taskModel.getDescription());
			Text date = new Text("Due: " + taskModel.getDueDate().toString());
			this.getChildren().add(name);
			this.getChildren().add(desc);
			this.getChildren().add(date);
			
			FlowPane tagView = new FlowPane(Orientation.HORIZONTAL);
			ArrayList<String> tags = taskModel.getTags();
			
			// TODO: Set colors for each tag
			if (tags != null) {
				for (String t : tags) {
					tagView.getChildren().add(new Text("#" + t));
				}
				this.getChildren().add(tagView);
			}
		}
		
	}
}
