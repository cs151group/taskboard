package edu.sjsu.cs151.taskboard;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DeleteProjectController implements EventHandler<ActionEvent> {

	private TaskBoardModel model;
	private Stage primaryStage;
	
	public DeleteProjectController(Stage primaryStage, TaskBoardModel model) {
		this.model = model;
		this.primaryStage = primaryStage;
	}

	@Override
	public void handle(ActionEvent event) {
		Alert sure = new Alert(AlertType.CONFIRMATION);
		ProjectModel current = model.getCurrentProject();
		sure.setHeaderText("Delete project?");
		sure.setContentText("Are you sure you want to delete the project " + current.getName() + "?");
		Optional<ButtonType> result = sure.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) 
			deleteProject();
	}
	
	private void deleteProject() {
		ProjectModel current = model.getCurrentProject();
		model.deleteProject(current);
		// TODO: Add case for if taskboard is now empty.
		TaskBoardView view = new TaskBoardView(model, primaryStage);
		view.load();
	}

}
