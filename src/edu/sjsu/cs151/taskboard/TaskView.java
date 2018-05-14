package edu.sjsu.cs151.taskboard;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class TaskView 
{

	private Stage primaryStage;
	private ColumnModel colModel;
	private TaskModel taskModel = new TaskModel();
	private TaskBoardModel tbModel;
	
	public String defaultText = null;
	
	public TaskView(Stage primaryStage, TaskBoardModel tbModel) {
		this.primaryStage = primaryStage;
		this.tbModel = tbModel;
	}
	
	public TaskView(Stage primaryStage, TaskBoardModel tbModel, ColumnModel colModel) {
		this.primaryStage = primaryStage;
		this.tbModel = tbModel;
		this.colModel = colModel;
	}
	
    
	//This is the load for New Task
    public void load() {
        primaryStage.setTitle("Create New Task");
        
        BorderPane border = new BorderPane();
        new GridPane();
        
        border.setCenter(addGridPane());
        
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
        
        
        // Choice Box
        ChoiceBox<String> cb = new ChoiceBox<>();
        ProjectModel projModel = tbModel.getCurrentProject();
        ArrayList<ColumnModel> colList = projModel.getColumns();
        
        for(ColumnModel col : colList)
        {
        	String name = col.getName();
        	cb.getItems().add(name);
        }
        
        GridPane.setConstraints(cb, 1, 2);
        grid.getChildren().add(cb);

        // Color Picker
        Text colorPicker = new Text (10, 50, "Color");
        GridPane.setConstraints(colorPicker, 0, 3);
        grid.getChildren().add(colorPicker);

        ColorPicker cPicker = new ColorPicker();
        GridPane.setConstraints(cPicker, 1,  3);
        grid.getChildren().add(cPicker);
             
        //Due Data
        Text dueDateText = new Text (10, 50, "Due Date");
        GridPane.setConstraints(dueDateText, 0, 4);
        grid.getChildren().add(dueDateText);
        
        DatePicker checkInDatePicker = new DatePicker();
        GridPane.setConstraints(checkInDatePicker, 1, 4);
        grid.getChildren().add(checkInDatePicker);
        
        
        Button buttonCreate = new Button("Create");
        GridPane.setConstraints(buttonCreate, 2, 5);
        grid.getChildren().add(buttonCreate);
        
        //EventHandeler for CREATE BUTTON
	    buttonCreate.setOnMouseClicked(event -> {
	    	
	        taskModel.setName(getTextField(nameField));
	        taskModel.setDescription(getTextField(descriptionArea));
	    	taskModel.setDueDate(getDate(checkInDatePicker));
	    	taskModel.setColor(getColor(cPicker));
	    	tbModel.getCurrentProject().addTask(getColumn(cb.getValue()), taskModel);
	    	TaskBoardView tbView = new TaskBoardView(tbModel, primaryStage);
            tbView.load();
	    });
	   
	    
	    Button buttonCancel = new Button("Cancel");
	    GridPane.setConstraints(buttonCancel, 3, 5);
	    grid.getChildren().add(buttonCancel);
	    
	  //EventHandeler for CANCEL BUTTON
	    buttonCancel.setOnMouseClicked(event -> {
	    	TaskBoardView tbView = new TaskBoardView(tbModel, primaryStage);
            tbView.load();
	    
	    });
        
        return grid;
        
	}
   
    
    //This is the load to edit a task
    public void load(TaskModel taskModel, TaskBoardModel tbModel)
    {
    	primaryStage.setTitle("Edit a Task");
        BorderPane border = new BorderPane();
        border.setCenter(addGridPane(taskModel));
        
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
        
        //Status
        Text statusText = new Text (10, 50, "Status");
        GridPane.setConstraints(statusText, 0, 2);
        grid.getChildren().add(statusText);
        
        
        // Choice Box
        ChoiceBox<String> cb = new ChoiceBox<>();
        ProjectModel projModel = tbModel.getCurrentProject();
        ArrayList<ColumnModel> colList = projModel.getColumns();
        
        for(ColumnModel col : colList)
        {
        	String name = col.getName();
        	cb.getItems().add(name);
        }
        
    	ColumnModel prevCol = tbModel.getCurrentProject().findColumn(taskModel);
        cb.setValue(prevCol.getName());
        
        GridPane.setConstraints(cb, 1, 2);
        grid.getChildren().add(cb);

        // Color Picker
        Text colorPicker = new Text (10, 50, "Color");
        GridPane.setConstraints(colorPicker, 0, 3);
        grid.getChildren().add(colorPicker);

        ColorPicker cPicker = new ColorPicker(taskModel.getColor());
        GridPane.setConstraints(cPicker, 1,  3);
        grid.getChildren().add(cPicker);

             
        //Due Data
        Text dueDateText = new Text (10, 50, "Due Date");
        GridPane.setConstraints(dueDateText, 0, 4);
        grid.getChildren().add(dueDateText);
        
        DatePicker checkInDatePicker = new DatePicker(taskModel.getDueDate());
        GridPane.setConstraints(checkInDatePicker, 1, 4);
        grid.getChildren().add(checkInDatePicker);
        
        Button buttonEdit = new Button("Edit");
        GridPane.setConstraints(buttonEdit, 2, 5);
        grid.getChildren().add(buttonEdit);
        
        
      //EventHandeler for LOAD BUTTON
	    buttonEdit.setOnMouseClicked(event -> {
	    	
	        taskModel.setName(getTextField(nameField));
	        taskModel.setDescription(getTextField(descriptionArea));
	    	taskModel.setDueDate(getDate(checkInDatePicker));
	    	taskModel.setColor(getColor(cPicker));

            //getInput of the choice kbox create a column to hold the value
            ColumnModel colInput = getColumn(cb.getValue());
            
            //Check if column Input is the same as the Column of the task passed as a parameter
            if(!colInput.equals(prevCol))
            {
                tbModel.getCurrentProject().eraseTask(prevCol, taskModel);
                tbModel.getCurrentProject().addTask(colInput, taskModel);

            }
	    	
	    	TaskBoardView tbView = new TaskBoardView(tbModel, primaryStage);
            tbView.load();
	    });
	   
	    
	    Button buttonCancel = new Button("Cancel");
	    GridPane.setConstraints(buttonCancel, 3, 5);
	    grid.getChildren().add(buttonCancel);
	    
	  //EventHandeler for CANCEL BUTTON
	    buttonCancel.setOnMouseClicked(event -> {
	    	TaskBoardView tbView = new TaskBoardView(tbModel, primaryStage);
	    	primaryStage.close();
            tbView.load();
	    
	    });
        
        return grid;
        
	}
    

    //This method checks if textfield is empty and return defaultText if it is
    public String getTextField(TextField text) 
    {
    	if (text.getText() == null || text.getText().trim().isEmpty()) {
    	     return defaultText;
    	}
    	return text.getText();
    }
    
    //This method checks if textArea is empty and return defaultText if it is
    public String getTextField(TextArea text) 
    {
    	if (text.getText() == null || text.getText().trim().isEmpty()) {
    	     return defaultText;
    	}
    	return text.getText();
    }
    
    //this method return the column selected in the ChoiceBox
    // if it is empty return current colModel
    public ColumnModel getColumn(String cb)
    {
    	if (cb == null)
    		return colModel;
    	
    	return tbModel.getCurrentProject().getSpecificCol(cb);
    }
    
    //Method to get Value of DatePicker
    //return current date if the user didn't select nothing
    public LocalDate getDate(DatePicker picker)
    {
    	if(picker.getValue() == null)
    		return LocalDate.now();
    	
    	return picker.getValue();
    }

    public Color getColor(ColorPicker colPick) {
	    return colPick.getValue();
    }
    
}
