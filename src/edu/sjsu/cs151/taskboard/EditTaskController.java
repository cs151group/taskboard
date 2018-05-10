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
	
	Stage primary;
	TaskModel model;
	TaskBoardView previousView;
	
	
	public EditTaskController(Stage primary, TaskModel taskModel, TaskBoardView previousView) {
		this.primary = primary;
		this.model = taskModel;
		this.previousView = previousView;
	}

	@Override
	public void handle(InputEvent event) {
		TaskView taskView = new TaskView(primary, previousView);
		taskView.load(model,previousView );
	}
	
	public void handle1(InputEvent event) {
		previousView.load();
	}

}
