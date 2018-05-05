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
		
	}
	
	/**
	 * This is used to test during development of this class. We can remove later if necessary.
	 */
	public void test() {
		TaskModel task = new TaskModel("Lorem Ipsum",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
						+ "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
						+ "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
						+ "nisi ut aliquip ex ea commodo consequat.",
				LocalDate.of(2018, 5, 20), new ArrayList<String>());
		TaskModel task2 = new TaskModel("Complete TaskBoardView",
				"The TaskBoardView should have multiple columns with tasks. It will also have the option to",
				LocalDate.of(2018, 5, 19), new ArrayList<String>());
		ColumnModel col = new ColumnModel("In Progress", new ArrayList<TaskModel>());
		col.addTask(task);
		col.addTask(task2);
		primaryStage.setScene(new Scene(new InnerColumnView(col)));
		primaryStage.show();
	}
	 
	 
	private class InnerColumnView extends VBox {
		private ColumnModel colModel; // Do we need to save this as instance variable?
		
		// TODO: Visual styling

		public InnerColumnView(ColumnModel columnModel) {
			this.colModel = columnModel;
			for(TaskModel t : colModel.getTasks()) {
				InnerTaskView nextTask = new InnerTaskView(t);
				this.getChildren().add(nextTask);
			}
			
		}
	}

	
	private class InnerTaskView extends VBox {
		private TaskModel taskModel;
		private static final double TASK_PADDING = 10;
		private static final double TASK_ITEM_SPACING = 5;

		public InnerTaskView(TaskModel task) {
			
			/*
			 * TODO: Make this look better. Font style, background color, etc. Consider
			 * using inline CSS with this.setStyle.
			 */
			
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
			
			// TODO: Set colors for each tag maybe?
			if (tags != null) {
				for (String t : tags) {
					tagView.getChildren().add(new Text("#" + t));
				}
				this.getChildren().add(tagView);
			}
		}
		
	}
}
