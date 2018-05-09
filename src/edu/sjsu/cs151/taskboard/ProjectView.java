package edu.sjsu.cs151.taskboard;

import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

//import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ProjectView {
	private Stage primaryStage;
	private ArrayList<ColumnModel> columnList = new ArrayList<>();
	
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

        /*
		Button minusButton = new Button();
		minusButton.setText(" - ");
		GridPane.setConstraints(minusButton, 3, 1);
		grid.getChildren().add(minusButton);
        */

        Button plusButton = new Button();
        plusButton.setText("+");
        GridPane.setConstraints(plusButton, 4, 1);
        grid.getChildren().add(plusButton);


		//int ogCol_colIndex = 1;
		//int ogCol_rowIndex = 1;
		ArrayList<Integer> i = new ArrayList<>();
		i.add(1);

		ArrayList<TextField> colFields = new ArrayList<>();
		ArrayList<Button> minusButtons = new ArrayList<>();

		/*
		TextField currentColField = new TextField();
		colFields.add(currentColField);
		currentColField.setPromptText("Enter column name");
		GridPane.setConstraints(currentColField, 1, 1);
		grid.getChildren().add(currentColField);*/


		
		
        plusButton.setOnMousePressed((event) -> {

        	/*
        	final TextField currentColField = new TextField();
			colFields.add(currentColField);
			currentColField.setPromptText("Enter column name");
			GridPane.setConstraints(currentColField, 1, i.size());
			grid.getChildren().add(currentColField);

			Button minusButton = new Button();
			minusButton.setText(" - ");
			GridPane.setConstraints(minusButton, 3, 1);
			grid.getChildren().add(minusButton);



			// TODO: 5/9/18 Move this to event listener for "Create" button

			// When Create is clicked, then get the names from the column fields
			if ((currentColField.getText() != null && !currentColField.getText().isEmpty())) {
				//columnList.add(new ColumnModel(currentColField.getText(), null));
				System.out.println(currentColField.getText());
			}



			i.add(1);
			System.out.println("+ BUTTON PRESSED!");
			System.out.println(colFields.size());
			for (int j = 0; j < colFields.size(); j++) {
				System.out.println(colFields.get(j).getText());
			}
			*/

 
        });
        return grid;
	}

	private VBox vbox;

	private class NewRow {
		private HBox hbox;
		private TextField field;
		private Button minus;

		public NewRow() {
			hbox = new HBox();
			field = new TextField();
			minus = new Button();

			hbox.getChildren().add(field);
			hbox.getChildren().add(minus);

			minus.setOnMouseClicked(event -> {
				vbox.getChildren().remove(this);
			});
		}

		public ColumnModel getColumn() {
			// get field text
			// return new column
			return new ColumnModel(field.getText(), null);
		}

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
