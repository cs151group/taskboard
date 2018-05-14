package edu.sjsu.cs151.taskboard;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/*
 * This is likely the most complicated view we have in the program. We probably
 * want to have reusable ColumnView and TaskView classes. These could be
 * implemented as inner classes or as separate external classes.
 */


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
	private static final double PLUS_BUTTON_HEIGHT = 35;
	
	public TaskBoardView(TaskBoardModel model, Stage primaryStage) {
		this.model = model;
		this.primaryStage = primaryStage;
	}
	
	public void load() {
		primaryStage.setTitle("Taskboard: " + model.getName());
		primaryStage.setMinHeight(800);
		primaryStage.setMinWidth(1200);
		primaryStage.centerOnScreen();
		BorderPane mainPane = new BorderPane();
		
		// === TOP BAR === //
		HBox topBar = new HBox();
		topBar.setSpacing(TOP_BAR_ITEM_SPACING);
		topBar.setPadding(new Insets(10));
		
		// Project controls
		Button editProjButton = new Button("Edit Project");
		Button deleteProjButton = new Button("Delete Project");
		Button createProjButton = new Button("Create new");
		ComboBox<ProjectModel> selectProj = new ComboBox<>();
		
		// Select project dropdown
		selectProj.setItems(FXCollections.observableList(model.getProjects()));
		selectProj.setConverter(new StringConverter<ProjectModel>() {
			
			@Override
			public String toString(ProjectModel object) {
				return object.getName();
			}

			@Override
			public ProjectModel fromString(String string) {
				// This shouldn't be used by the dropdown...
				throw new UnsupportedOperationException();
			}
		});
		selectProj.getSelectionModel().selectFirst();
		
		Separator topSep = new Separator(Orientation.VERTICAL);
		
		// File controls
		Button saveBoardButton = new Button("Save...");
		Button loadBoardButton = new Button("Load...");
		Button logOutButton = new Button("Logout");
		
		// Parameters will likely change.

		editProjButton.setOnAction(new EditProjectController(primaryStage, model.getCurrentProject(), model, this));
		deleteProjButton.setOnAction(new DeleteProjectController(primaryStage, model));
		createProjButton.setOnAction(new CreateProjectController(primaryStage, model.getCurrentProject(), model, this));
		
		selectProj.valueProperty().addListener(new ChangeListener<ProjectModel>() {

			@Override
			public void changed(ObservableValue<? extends ProjectModel> observable,
					ProjectModel oldValue, ProjectModel newValue) {
				model.changeCurrProject(newValue);
				load();
			}
		});
		
		saveBoardButton.setOnAction(new SaveBoardController(model));
		loadBoardButton.setOnAction(new LoadBoardController(primaryStage));
		logOutButton.setOnAction(new LogoutController(primaryStage, model));
		
		VBox projControls = new VBox(5);
		VBox fileControls = new VBox(5);
		
		HBox projItems = new HBox(5);
		HBox fileItems = new HBox(5);
		
		projItems.getChildren().add(editProjButton);
		projItems.getChildren().add(deleteProjButton);
		projItems.getChildren().add(createProjButton);
		projItems.getChildren().add(selectProj);
		
		fileItems.getChildren().add(saveBoardButton);
		fileItems.getChildren().add(loadBoardButton);
		fileItems.getChildren().add(logOutButton);
		
		Text projMenuText = new Text("Projects");
		projMenuText.setFill(Paint.valueOf("grey"));
		Text fileMenuText = new Text("File");
		fileMenuText.setFill(Paint.valueOf("grey"));
		
		projControls.setAlignment(Pos.TOP_CENTER);
		projControls.getChildren().add(projItems);
		projControls.getChildren().add(projMenuText);
		
		fileControls.setAlignment(Pos.TOP_CENTER);
		fileControls.getChildren().add(fileItems);
		fileControls.getChildren().add(fileMenuText);
		
		topBar.getChildren().add(projControls);
		topBar.getChildren().add(topSep);
		topBar.getChildren().add(fileControls);
		
		// TODO: Add visual style to top bar
		
		mainPane.setTop(topBar);
		//mainPane.minWidthProperty().bind(topBar.minWidthProperty());
		
		// === COLUMNS === //
		
		HBox columnList = new HBox();
		columnList.setBackground(
				new Background(new BackgroundImage(new Image("file:background.png"), 
						null, null, null, null)));
		ScrollPane scrollPane = new ScrollPane(columnList);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		
		// Allows the scroll pane to be moved by mouse dragging
		scrollPane.setPannable(true);
		// We could potentially use mainPane.setOnScroll to make the scroll wheel go horizontal

		ProjectModel currentProject = model.getCurrentProject();
		// TODO: 5/10/18 I don't know what I'm doing here. Sorry!
		// Added if else to wrap the first for loop
		if (!currentProject.getColumns().isEmpty()) {
			for(ColumnModel c : currentProject.getColumns()) {
				InnerColumnView colView = new InnerColumnView(c);
				columnList.getChildren().add(colView);
			}
		}
		else {
			ColumnModel currentColModel = new ColumnModel("First Column");
			InnerColumnView colView = new InnerColumnView(currentColModel);
			columnList.getChildren().add(colView);
		}
		
		// TODO: Quick-edit column name?
		// TODO: Quick-add column?
		
		columnList.setSpacing(BOARD_COL_SPACING);

		/* Arbitrary size to test scrollpane.
		 * The actual width / height will be determined by the title bar dimensions.
		 */

		mainPane.setCenter(scrollPane);

		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
	}
	
	// TODO: This is unused. Consider removing.
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
			
			// Handling column title bar
			HBox titleBox = new HBox();
			Button leftButton = new Button("", new ImageView(new Image("file:chevron-left.png")));
			Text colTitle = new Text(colModel.getName());
			Button rightButton = new Button("", new ImageView(new Image("file:chevron-right.png")));
			colTitle.setTextAlignment(TextAlignment.CENTER);
			colTitle.setWrappingWidth(215);
			
			leftButton.setOnAction(e -> {
				model.getCurrentProject().moveLeft(colModel);
				load();
			});
			rightButton.setOnAction(e -> {
				model.getCurrentProject().moveRight(colModel);
				load();
			});
			
			titleBox.getChildren().add(leftButton);
			titleBox.getChildren().add(colTitle);
			titleBox.getChildren().add(rightButton);
			
			this.getChildren().add(titleBox);
			
			// Adding '+' button
			
			HBox buttonBox = new HBox();
			buttonBox.setAlignment(Pos.CENTER);
			Button plusButton = new Button();
			plusButton.setText("+");
			plusButton.setOnAction(new NewTaskController(primaryStage, model, colModel));
			plusButton.setPrefWidth(COLUMN_WIDTH + 2 * TASK_PADDING);
			plusButton.setPrefHeight(PLUS_BUTTON_HEIGHT);
			plusButton.setStyle("-fx-font-size: 24; -fx-font-weight: 900; -fx-text-fill: #505050");
			plusButton.setAlignment(Pos.CENTER);
			buttonBox.getChildren().add(plusButton);
			buttonBox.setPadding(new Insets(5, 0, 5, 0));
			this.getChildren().add(buttonBox);
			
			// Adding all task views to the column view
			if (colModel.getTasks() != null) {
				for(TaskModel t : colModel.getTasks()) {
					InnerTaskView nextTask = new InnerTaskView(t);
					this.getChildren().add(nextTask);
				}
			}
			
			// Quick add
			BorderPane quickAdd = new BorderPane();
			TextField innerField = new TextField();
			innerField.setPromptText("Type a task to quick-add");
			//Button cancelButton = new Button("Cancel");
			Button okButton = new Button("Add Task");
			HBox buttons = new HBox(okButton);
			buttons.setAlignment(Pos.CENTER_RIGHT);
			quickAdd.setCenter(innerField);
			quickAdd.setBottom(buttons);
			innerField.setOnAction(e -> {
				String text = innerField.getText();
				TaskModel newTask = new TaskModel(text);
				model.getCurrentProject().addTask(colModel, newTask);
				int index = InnerColumnView.this.getChildren().size()-1;
				InnerColumnView.this.getChildren().add(index, (new InnerTaskView(newTask)));
				innerField.clear();
			});
			okButton.setOnAction(e -> {
				String text = innerField.getText();
				TaskModel newTask = new TaskModel(text);
				model.getCurrentProject().addTask(colModel, newTask);
				int index = InnerColumnView.this.getChildren().size()-1;
				InnerColumnView.this.getChildren().add(index, (new InnerTaskView(newTask)));
				innerField.clear();
			});

			this.getChildren().add(quickAdd);

			// Arbitrary style info. Can be changed as desired //
			colTitle.setFill(Color.GHOSTWHITE);
			colTitle.setFont(new Font(24));
			titleBox.setStyle("-fx-background-color: steelblue");
			titleBox.setPadding(new Insets(10));
			this.setSpacing(COLUMN_ITEM_SPACING);
			this.setPadding(new Insets(COLUMN_PADDING));

			quickAdd.setStyle("-fx-background-color: ghostwhite; -fx-border-color: lightgrey");
			quickAdd.setPadding(new Insets(TASK_PADDING));
			buttons.setSpacing(10);
			buttons.setPadding(new Insets(TASK_PADDING, 0, 0, 0));
			//innerField.setSp
			//name.setFont(new Font("Verdana", 18));
		}
	}

	
	private class InnerTaskView extends VBox {
		private TaskModel taskModel;

		public InnerTaskView(TaskModel task) {
			
			/*
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
			Color bgColor = task.getColor();
			
			// Arbitrary style info. Can be changed as desired. //
			this.setStyle("/*-fx-background-color: bgColor;*/ -fx-border-color: lightgrey");
			this.setBackground(new Background(new BackgroundFill(task.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
			this.setPadding(new Insets(TASK_PADDING));
			this.setSpacing(TASK_ITEM_SPACING);
			name.setFont(new Font("Verdana", 18));

			// Adding mouse click controller to go to edit task
			this.setOnMouseClicked(new EditTaskController(primaryStage, taskModel, model));
		}

	}

	
}
