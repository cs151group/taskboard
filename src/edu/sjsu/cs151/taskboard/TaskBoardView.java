package edu.sjsu.cs151.taskboard;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
	
	private static final double COLUMN_WIDTH = 300;
	
	public TaskBoardView(TaskBoardModel model, Stage primaryStage) {
		this.model = model;
		this.primaryStage = primaryStage;
	}
	
	
	 public void load() { 
		// Test code //
		 
		 TaskModel task = new TaskModel("Name test", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
		 		+ "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
		 		+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
		 		+ "nisi ut aliquip ex ea commodo consequat.", LocalDate.of(2018, 5, 20), new ArrayList<String>());
        primaryStage.setScene(new Scene(new InnerTaskView(task)));
        primaryStage.show();
        
        // End test code
	 }
	 
	 
	// TODO: InnerColumnView should extend something so it can be drawn in JavaFX
	private class InnerColumnView {
		private ColumnModel colModel;

		public InnerColumnView(ColumnModel columnModel) {
			this.colModel = columnModel;
			
		}
	}

	
	private class InnerTaskView extends VBox {
		private TaskModel taskModel;
		private static final double TASK_PADDING = 10;
		private static final double TASK_ITEM_SPACING = 5;

		public InnerTaskView(TaskModel task) {
			
			
			// TODO: Make this look better
			//this.setPrefWrapLength(COLUMN_WIDTH - 100);
			this.setStyle("-fx-border-color: black");
			this.setPrefWidth(COLUMN_WIDTH);
			this.setMaxWidth(COLUMN_WIDTH);
			this.setPadding(new Insets(TASK_PADDING));
			this.setSpacing(TASK_ITEM_SPACING);
			this.taskModel = task;
			Text name = new Text(taskModel.getName());
			Text desc = new Text(taskModel.getDescription());
			desc.setWrappingWidth(COLUMN_WIDTH);
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
