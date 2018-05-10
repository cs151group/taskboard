package edu.sjsu.cs151.taskboard;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.MouseEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextArea;
//import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class TaskView 
{

	private Stage primaryStage;
	private TaskBoardView taskBoardView;
	private ColumnModel colModel;
	private TaskModel taskModel = new TaskModel();
	private TaskBoardModel tbModel;
	
	public TaskView(Stage primaryStage, TaskBoardView taskBoardView) {
		this.primaryStage = primaryStage;
		this.taskBoardView = taskBoardView;
	}
	
	public TaskView(Stage primaryStage, TaskBoardModel tbModel, ColumnModel colModel ) {
		this.primaryStage = primaryStage;
		this.tbModel = tbModel;
		this.colModel = colModel;
	}
	
    
	//This is the load for New Task
    public void load() {
        primaryStage.setTitle("Create New Task");
        
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
        
        border.setCenter(addGridPane());
       // border.setBottom(addAnchorPaneNew(grid));
        
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
        
    }
    
    
    //GridPane for New Task
    public GridPane addGridPane()
	{

		GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        //Name 
        Text nameText = new Text (10, 50, "Name");
        GridPane.setConstraints(nameText, 0, 0); //First is VER, and second HOR
        grid.getChildren().add(nameText);
        
        final TextField nameField = new TextField();
        nameField.setPromptText("Enter name");
        nameField.setMaxWidth(200);
        GridPane.setConstraints(nameField, 1, 0);
        grid.getChildren().add(nameField);

        
        //Description
        Text descriptionText = new Text (10, 50, "Description");
        GridPane.setConstraints(descriptionText, 0, 1);
        grid.getChildren().add(descriptionText);
   
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefColumnCount(30);
        descriptionArea.setPrefRowCount(10);
        GridPane.setConstraints(descriptionArea, 1, 1);
        grid.getChildren().add(descriptionArea);

        
        //Status
        Text statusText = new Text (10, 50, "Status");
        GridPane.setConstraints(statusText, 0, 2);
        grid.getChildren().add(statusText);
        
        
        //TODO: 5/9/18
        //Instead of being Todo, In Process, ... it should be the input from add column
        
        // Choice Box
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.getItems().add("Todo");
        cb.getItems().add("In Process");
        cb.getItems().add("Done");
        
        GridPane.setConstraints(cb, 1, 2);
        grid.getChildren().add(cb);

        
        
//        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
//        	    "Todo", "In Process", "Done"));
//        GridPane.setConstraints(cb, 1, 2);
//        grid.getChildren().add(cb);
             
        //Due Data
        Text dueDateText = new Text (10, 50, "Due Date");
        GridPane.setConstraints(dueDateText, 0, 3);
        grid.getChildren().add(dueDateText);
        
        DatePicker checkInDatePicker = new DatePicker();
        GridPane.setConstraints(checkInDatePicker, 1, 3);
        grid.getChildren().add(checkInDatePicker);
        
        
        Button buttonSave = new Button("Create");
        GridPane.setConstraints(buttonSave, 2, 4);
        grid.getChildren().add(buttonSave);
        
	    buttonSave.setOnMouseClicked(event -> {
	    	
	        taskModel.setName(nameField.getText());
	        taskModel.setDescription(descriptionArea.getText());
	        taskModel.setStatus(cb.getValue());
	    	taskModel.setDueDate(checkInDatePicker.getValue());
	    	colModel.addTask(taskModel);
	    	TaskBoardView tbView = new TaskBoardView(tbModel, primaryStage);
            tbView.load();
	    });
        
	    Button buttonCancel = new Button("Cancel");
	    GridPane.setConstraints(buttonCancel, 3, 4);
	    grid.getChildren().add(buttonCancel);
        
        
   //     checkInDatePicker.getValue().toString();
        
        /*
        final TextField dueDateField = new TextField();
        dueDateField.setMaxWidth(100);
        dueDateField.getText();
        GridPane.setConstraints(dueDateField, 1, 3);
        grid.getChildren().add(dueDateField);
          */
        
        return grid;
        
	}
    
    //Buttons for New Task
    public AnchorPane addAnchorPaneNew(GridPane grid) 
	{
	    AnchorPane anchorpane = new AnchorPane();
	    Button buttonSave = new Button("Create");
	    
	    buttonSave.setOnMouseClicked(event -> {
	    	colModel.addTask(taskModel);
	    	TaskBoardView tbView = new TaskBoardView(tbModel, primaryStage);
            tbView.load();
	    });
	    
	    Button buttonCancel = new Button("Cancel");

	    HBox hb = new HBox();
	    hb.setPadding(new Insets(0, 10, 10, 10));
	    hb.setSpacing(10);
	    hb.getChildren().addAll(buttonSave, buttonCancel);

	    anchorpane.getChildren().addAll(grid,hb);   // Add grid from Example 1-5
	    AnchorPane.setBottomAnchor(hb, 8.0);
	    AnchorPane.setRightAnchor(hb, 5.0);
	    AnchorPane.setTopAnchor(grid, 10.0);

	    return anchorpane;
	}
    
    //This is the load to edit a task
    public void load(TaskModel taskModel, TaskBoardView taskBoardView)
    {
    	primaryStage.setTitle("Edit a Task");
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
        
        border.setCenter(addGridPane(taskModel ));
        border.setBottom(addAnchorPaneEdit(grid, taskBoardView));
        
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
    }
    
    //GridPane for Edit Task
    public GridPane addGridPane(TaskModel taskModel)
	{

		GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        //Name 
        Text nameText = new Text (10, 50, "Name");
        GridPane.setConstraints(nameText, 0, 0); //First is VER, and second HOR
        grid.getChildren().add(nameText);
        
        final TextField nameField = new TextField();
        nameField.setText(taskModel.getName());
        nameField.setMaxWidth(200);
        GridPane.setConstraints(nameField, 1, 0);
        grid.getChildren().add(nameField);
        taskModel.setName(nameField.getText());
        
        //Description
        Text descriptionText = new Text (10, 50, "Description");
        GridPane.setConstraints(descriptionText, 0, 1);
        grid.getChildren().add(descriptionText);
        
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefColumnCount(30);
        descriptionArea.setPrefRowCount(10);
        descriptionArea.setText(taskModel.getDescription());
        descriptionArea.setWrapText(true);
        GridPane.setConstraints(descriptionArea, 1, 1);
        grid.getChildren().add(descriptionArea);
        taskModel.setDescription(descriptionArea.getText());
        
        //Status
        Text statusText = new Text (10, 50, "Status");
        GridPane.setConstraints(statusText, 0, 2);
        grid.getChildren().add(statusText);
        
        
        //TODO: 5/9/18
        //Instead of being Todo, In Process, ... it should be the input from add column
        
        // Choice Box
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.getItems().add("Todo");
        cb.getItems().add("In Process");
        cb.getItems().add("Done");
        
        GridPane.setConstraints(cb, 1, 2);
        grid.getChildren().add(cb);
        
             
        //Due Data
        Text dueDateText = new Text (10, 50, "Due Date");
        GridPane.setConstraints(dueDateText, 0, 3);
        grid.getChildren().add(dueDateText);
        
        DatePicker checkInDatePicker = new DatePicker(taskModel.getDueDate());
        GridPane.setConstraints(checkInDatePicker, 1, 3);
        grid.getChildren().add(checkInDatePicker);
        taskModel.setDueDate(checkInDatePicker.getValue()); 
        
     /*   
        final TextField dueDateField = new TextField();
        dueDateField.setMaxWidth(100);
        dueDateField.setText(taskModel.getDueDate());
        GridPane.setConstraints(dueDateField, 1, 3);
        grid.getChildren().add(dueDateField);
*/
        return grid;
        
	}
    
    //Buttons for Edit a Task
    public AnchorPane addAnchorPaneEdit(GridPane grid, TaskBoardView taskBoardView) 
	{
	    AnchorPane anchorpane = new AnchorPane();
	    Button buttonEdit = new Button("Edit");
	    buttonEdit.setOnAction(event -> taskBoardView.load());
	    Button buttonCancel = new Button("Cancel");

	    HBox hb = new HBox();
	    hb.setPadding(new Insets(0, 10, 10, 10));
	    hb.setSpacing(10);
	    hb.getChildren().addAll(buttonEdit, buttonCancel);

	    anchorpane.getChildren().addAll(grid,hb);   // Add grid from Example 1-5
	    AnchorPane.setBottomAnchor(hb, 8.0);
	    AnchorPane.setRightAnchor(hb, 5.0);
	    AnchorPane.setTopAnchor(grid, 10.0);

	    return anchorpane;
	}


        
}
