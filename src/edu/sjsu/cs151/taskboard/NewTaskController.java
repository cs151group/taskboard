package edu.sjsu.cs151.taskboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class NewTaskController implements EventHandler<ActionEvent> {
	
	Stage primary;
	ColumnModel model;
	TaskBoardModel tbModel;
		
	public NewTaskController(Stage primary, TaskBoardModel tbModel,ColumnModel model) {
		this.primary = primary;
		this.model = model;
		this.tbModel = tbModel;
	}

	@Override
	public void handle(ActionEvent event) {
		TaskView taskView = new TaskView(primary, tbModel, model);
		taskView.load();
	}

}
