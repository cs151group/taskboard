package edu.sjsu.cs151.taskboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CreateProjectController implements EventHandler<ActionEvent> {

	Stage primary;
	TaskBoardModel tbModel;
	ProjectModel pModel;
	TaskBoardView tbView;
	Stage createProjectStage = new Stage();

	public CreateProjectController (Stage primary, ProjectModel pModel, TaskBoardModel tbModel, TaskBoardView tbView) {
		this.primary = primary;
		this.tbModel = tbModel;
		this.pModel = pModel;
		this.tbView = tbView;
	}


	@Override
	public void handle(ActionEvent event) {
		ProjectView projView = new ProjectView(createProjectStage, tbView, tbModel);
		//projView.populateFields(pModel);
		projView.load();
		createProjectStage.setTitle("New Project");
		createProjectStage.setAlwaysOnTop(true);
	}

}
