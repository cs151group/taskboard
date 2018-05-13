package edu.sjsu.cs151.taskboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class EditProjectController implements EventHandler<ActionEvent> {

	Stage primary;
	TaskBoardModel tbModel;
	ProjectModel pModel;
	TaskBoardView tbView;
	Stage editProjectStage = new Stage();

	public EditProjectController (Stage primary, ProjectModel pModel, TaskBoardModel tbModel, TaskBoardView tbView) {
		this.primary = primary;
		this.tbModel = tbModel;
		this.pModel = pModel;
		this.tbView = tbView;
	}

	@Override
	public void handle(ActionEvent event) {
		ProjectView projView = new ProjectView(editProjectStage, tbView, tbModel);
		projView.populateFields(pModel);
		projView.load();
		editProjectStage.setTitle("Edit Project");
		editProjectStage.setAlwaysOnTop(true);
	}
}
