package edu.sjsu.cs151.taskboard;

import java.io.File;

import javax.xml.bind.DataBindingException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class LoadBoardController implements EventHandler<ActionEvent> {
	
	private Stage primaryStage;
	
	public LoadBoardController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void handle(ActionEvent event) {
		FileChooser loader = new FileChooser();
		loader.setTitle("Load Taskboard");
		loader.getExtensionFilters().add(new ExtensionFilter("XML Files", "*.xml"));
		loader.setInitialDirectory(new File(System.getProperty("user.dir")));
		Stage newStage = new Stage();
		File selectedFile = loader.showOpenDialog(newStage);
		if (selectedFile != null) {
			try {
				TaskBoardModel newModel = new TaskBoardModel(selectedFile);
				TaskBoardView newView = new TaskBoardView(newModel, primaryStage);
				newView.load();
				
			} catch (DataBindingException e) {
				Alert failedAlert = new Alert(AlertType.ERROR);
				failedAlert.setContentText("The file failed to load.");
				failedAlert.show();
			}
		}
	}

}
