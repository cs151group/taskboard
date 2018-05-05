package edu.sjsu.cs151.taskboard;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	
	private static final double BOARD_COL_SPACING = 10;
	private static final double COLUMN_WIDTH = 300;
	private static final double COLUMN_PADDING = 10;
	private static final double COLUMN_ITEM_SPACING = 10;
	private static final double TASK_PADDING = 10;
	private static final double TASK_ITEM_SPACING = 5;
	
	public TaskBoardView(TaskBoardModel model, Stage primaryStage) {
		this.model = model;
		this.primaryStage = primaryStage;
	}
	
	
	public void load() {
		// TODO: fix load()
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
				"The TaskBoardView should have multiple columns with tasks. There will also be a title bar with options.",
				LocalDate.of(2018, 5, 19), new ArrayList<String>());
		TaskModel task3 = new TaskModel("Cattle ",
				"Cattle is a word for certain mammals that belong to the genus Bos. "
				+ "Cattle may be cows, bulls, oxen, heifers, steers, bullocks or calves. "
				+ "Cattle are the most common type of large domesticated hoofed animals. "
				+ "They are a prominent modern member of the subfamily Bovinae.",
				LocalDate.of(2018, 5, 19), new ArrayList<String>());
		// 
		ColumnModel col = new ColumnModel("In Progress", new ArrayList<TaskModel>());
		col.addTask(task);
		col.addTask(task2);
		col.addTask(task3);
		primaryStage.setScene(new Scene(new InnerColumnView(col)));
		primaryStage.show();
	}
	 
	 
	private class InnerColumnView extends VBox {
		private ColumnModel colModel; // Do we need to save this as instance variable?
		
		// TODO: Visual styling

		public InnerColumnView(ColumnModel columnModel) {
			this.colModel = columnModel;
			
			// Handling column title
			HBox titleBox = new HBox();
			Text colTitle = new Text(colModel.getName());
			colTitle.setFill(Color.GHOSTWHITE);
			colTitle.setFont(new Font(24));
			titleBox.getChildren().add(colTitle);
			titleBox.setStyle("-fx-background-color: steelblue");
			titleBox.setPrefWidth(COLUMN_WIDTH);
			titleBox.setPadding(new Insets(10));
			this.getChildren().add(titleBox);
			
			for(TaskModel t : colModel.getTasks()) {
				InnerTaskView nextTask = new InnerTaskView(t);
				this.getChildren().add(nextTask);
			}
			this.setSpacing(COLUMN_ITEM_SPACING);
			this.setPadding(new Insets(COLUMN_PADDING));
			this.setPrefWidth(COLUMN_WIDTH);
		}
	}

	
	private class InnerTaskView extends VBox {
		private TaskModel taskModel;

		public InnerTaskView(TaskModel task) {
			
			/*
			 * TODO: Make this look better. Font style, background color, etc. Consider
			 * using inline CSS with this.setStyle.
			 * https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html
			 */
			
			//this.setPrefWrapLength(COLUMN_WIDTH - 100);
			this.setStyle("-fx-background-color: whitesmoke");
			this.setPadding(new Insets(TASK_PADDING));
			this.setSpacing(TASK_ITEM_SPACING);
			this.taskModel = task;
			Text name = new Text(taskModel.getName());
			name.setFont(new Font("Verdana", 18));
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
