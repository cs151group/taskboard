package edu.sjsu.cs151.taskboard;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class SaveBoardController implements EventHandler<ActionEvent> {

	TaskBoardModel model;
	
	public SaveBoardController(TaskBoardModel model) {
		this.model = model;
	}
	
	@Override
	public void handle(ActionEvent event) {
		// if filename is null, prompt for filename, save.
		if(model.getFileName() == null) {
			FileChooser picker = new FileChooser();
			picker.setTitle("Save Taskboard");
			picker.getExtensionFilters().add(new ExtensionFilter("XML Files", "*.xml"));
			picker.setInitialDirectory(new File(System.getProperty("user.dir")));
			Stage secondaryStage = new Stage();
			File f = picker.showSaveDialog(secondaryStage);
			if(f != null) {
				model.setFileName(f.getName());
				model.save();
			}
		} 
		else model.save();
		// else save without popup
	}

}
