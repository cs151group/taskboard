package edu.sjsu.cs151.taskboard;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
	private static final double TOP_BAR_ITEM_SPACING = 10;
	
	public TaskBoardView(TaskBoardModel model, Stage primaryStage) {
		this.model = model;
		this.primaryStage = primaryStage;
	}
	
	
	public void load() {
		primaryStage.setTitle("Taskboard: " + model.getName());
		VBox mainPane = new VBox();
		
		// === TOP BAR === //
		HBox topBar = new HBox();
		topBar.setSpacing(TOP_BAR_ITEM_SPACING);
		
		// TODO: Project select dropdown
		
		Button editProjButton = new Button("Edit");
		Button saveProjButton = new Button("Save");
		Button deleteProjButton = new Button("Delete");
		Button createProjButton = new Button("Create new");
		Separator topSep = new Separator(Orientation.VERTICAL);
		Button loadBoardButton = new Button("Load...");
		Button logOutButton = new Button("Logout");
		
		// TODO: Create event listeners for top bar
		
		topBar.getChildren().add(editProjButton);
		topBar.getChildren().add(saveProjButton);
		topBar.getChildren().add(deleteProjButton);
		topBar.getChildren().add(createProjButton);
		topBar.getChildren().add(topSep);
		topBar.getChildren().add(loadBoardButton);
		topBar.getChildren().add(logOutButton);
		
		// TODO: Add visual style to top bar
		
		mainPane.getChildren().add(topBar);
		
		// === COLUMNS === //
		
		HBox columnList = new HBox();
		ScrollPane scrollPane = new ScrollPane(columnList);
		
		// Allows the scroll pane to be moved by mouse dragging
		scrollPane.setPannable(true);
		// We could potentially use mainPane.setOnScroll to make the scroll wheel go horizontal

		ProjectModel currentProject = model.getCurrentProject();
		for(ColumnModel c : currentProject.getColumns()) {
			InnerColumnView colView = new InnerColumnView(c);
			columnList.getChildren().add(colView);
		}
		columnList.setSpacing(BOARD_COL_SPACING);

		/* Arbitrary size to test scrollpane.
		 * The actual width / height will be determined by the title bar dimensions.
		 */
		mainPane.setMaxSize(1200, 1080);

		mainPane.getChildren().add(scrollPane);

		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		
	}
	
	/**
	 * This is used to test during development of this class. We can remove later if necessary.
	 */
	public void testColumn() {
		
		// Testing rendering of a single column
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
	 
	// TODO: Make column view a scrollpane? Reference trello's column scrolling for an example.
	private class InnerColumnView extends VBox {
		private ColumnModel colModel; // Do we need to save this as instance variable?
		
		// TODO: Finalize visual appearance

		public InnerColumnView(ColumnModel columnModel) {
			
			this.colModel = columnModel;
			
			this.setPrefWidth(COLUMN_WIDTH);
			
			// Handling column title
			HBox titleBox = new HBox();
			Text colTitle = new Text(colModel.getName());
			titleBox.getChildren().add(colTitle);
			this.getChildren().add(titleBox);
			
			// Adding all task views to the column view
			for(TaskModel t : colModel.getTasks()) {
				InnerTaskView nextTask = new InnerTaskView(t);
				this.getChildren().add(nextTask);
			}

			// Arbitrary style info. Can be changed as desired //
			colTitle.setFill(Color.GHOSTWHITE);
			colTitle.setFont(new Font(24));
			titleBox.setStyle("-fx-background-color: steelblue");
			titleBox.setPadding(new Insets(10));
			this.setSpacing(COLUMN_ITEM_SPACING);
			this.setPadding(new Insets(COLUMN_PADDING));
			
			
		}
	}

	
	private class InnerTaskView extends VBox {
		private TaskModel taskModel;

		public InnerTaskView(TaskModel task) {
			
			/*
			 * TODO: Make this look better. Font style, background color, etc. 
			 * https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html
			 */
			
			this.taskModel = task;
			
			Text name = new Text(taskModel.getName());
			Text desc = new Text(taskModel.getDescription());
			
			// Making sure description text wraps properly
			desc.setWrappingWidth(COLUMN_WIDTH - TASK_PADDING);
			
			// TODO: Change date text to more readable format
			Text date = new Text("Due: " + taskModel.getDueDate().toString());
			
			// Adding elements to task
			this.getChildren().add(name);
			this.getChildren().add(desc);
			this.getChildren().add(date);
			
			// tagView is the list of all tags.
			FlowPane tagView = new FlowPane(Orientation.HORIZONTAL);
			ArrayList<String> tags = taskModel.getTags();
			
			// TODO: Set colors for each tag maybe?
			// Looping through all tags
			if (tags != null) {
				for (String t : tags) {
					tagView.getChildren().add(new Text("#" + t));
				}
				// Adding tagView to task
				this.getChildren().add(tagView);
			}
			
			// Arbitrary style info. Can be changed as desired. //
			this.setStyle("-fx-background-color: whitesmoke");
			this.setPadding(new Insets(TASK_PADDING));
			this.setSpacing(TASK_ITEM_SPACING);
			name.setFont(new Font("Verdana", 18));
			
			// Adding mouse click controller to go to edit task
			this.setOnMouseClicked(new EditTaskController(primaryStage, taskModel, TaskBoardView.this));
		}
		
	}
}
