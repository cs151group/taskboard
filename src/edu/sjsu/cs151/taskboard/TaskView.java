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
	
	public TaskView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
    
    public void load() {
        primaryStage.setTitle("Create New Task");
        
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
        
        border.setCenter(addGridPane());
        border.setBottom(addAnchorPane(grid));
        
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
        
    }
    
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
        nameField.getText();
        GridPane.setConstraints(nameField, 1, 0);
        grid.getChildren().add(nameField);
        
        //Description
        Text descriptionText = new Text (10, 50, "Description");
        GridPane.setConstraints(descriptionText, 0, 1);
        grid.getChildren().add(descriptionText);
   
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefColumnCount(30);
        descriptionArea.setPrefRowCount(10);
        descriptionArea.getText();
        GridPane.setConstraints(descriptionArea, 1, 1);
        grid.getChildren().add(descriptionArea);
        
        //Status
        Text statusText = new Text (10, 50, "Status");
        GridPane.setConstraints(statusText, 0, 2);
        grid.getChildren().add(statusText);
        
        // Choice Box
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
        	    "Todo", "In Process", "Done"));
        GridPane.setConstraints(cb, 1, 2);
        grid.getChildren().add(cb);
             
        //Due Data
        Text dueDateText = new Text (10, 50, "Due Date");
        GridPane.setConstraints(dueDateText, 0, 3);
        grid.getChildren().add(dueDateText);
        
        final TextField dueDateField = new TextField();
        dueDateField.setMaxWidth(100);
        dueDateField.getText();
        GridPane.setConstraints(dueDateField, 1, 3);
        grid.getChildren().add(dueDateField);
          
        return grid;
        
	}

    public AnchorPane addAnchorPane(GridPane grid) 
	{
	    AnchorPane anchorpane = new AnchorPane();
	    Button buttonSave = new Button("Create");
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
        
}
