package edu.sjsu.cs151.taskboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

/*
 * This class handles the event when a task is clicked on in TaskBoardView.
 * It should switch the view to the "Create task" view.
 * Feel free to change constructor parameters as necessary.
 */
public class EditTaskController implements EventHandler<InputEvent> {
	
	TaskModel model;
	
	public EditTaskController(Stage primary, TaskModel taskModel, TaskBoardView previousView) {
		this.model = taskModel;
	}

	@Override
	public void handle(InputEvent event) {
		// TODO Auto-generated method stub
		
	}

}
