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
	TaskModel taskModel;
	TaskBoardModel tbModel;
	
	
	public EditTaskController(Stage primary, TaskModel taskModel, TaskBoardModel tbModel) {
		this.primary = primary;
		this.taskModel = taskModel;
		this.tbModel = tbModel;
	}

	@Override
	public void handle(InputEvent event) {
		TaskView taskView = new TaskView(primary, tbModel);
		taskView.load(taskModel, tbModel);
	}
	


}
