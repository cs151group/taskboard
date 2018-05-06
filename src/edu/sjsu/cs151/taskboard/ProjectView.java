package edu.sjsu.cs151.taskboard;

import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.MouseEvent;

//import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ProjectView {
	private Stage primaryStage;
	
	public ProjectView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void load() 
	{
		primaryStage.setTitle("Create New Project");
		
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
        
       
        Text nameText = new Text (10, 50, "Name");
        GridPane.setConstraints(nameText, 0, 0);
        grid.getChildren().add(nameText);
        
        final TextField nameField = new TextField();
        nameField.setPromptText("Enter name");
        nameField.setPrefColumnCount(10);
        nameField.getText();
        GridPane.setConstraints(nameField, 1, 0);
        grid.getChildren().add(nameField);
        
        Text colText = new Text (10, 50, "Columns");
        GridPane.setConstraints(colText, 0, 1);
        grid.getChildren().add(colText);
        
        Button plusButton = new Button();
        plusButton.setText("+");
        GridPane.setConstraints(plusButton, 1, 1);
        grid.getChildren().add(plusButton);
        
		int hCounter = 0;
		int vCounter = 2;
		
		
        plusButton.setOnMousePressed((event) -> {
        	
            final TextField colField = new TextField();
            colField.setPromptText("Enter column name");
            GridPane.setConstraints(colField, hCounter, vCounter + event.getClickCount());
            grid.getChildren().add(colField);
            
            Button minusButton = new Button();
            minusButton.setText("-");
            GridPane.setConstraints(minusButton, hCounter + 1, vCounter + event.getClickCount());
            grid.getChildren().add(minusButton);
 
        });
       
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
