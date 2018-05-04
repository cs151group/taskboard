package edu.sjsu.cs151.taskboard;

import java.util.ArrayList;

public class TaskBoardModel {
	private String name = "TaskBoard1";
	private ArrayList<ProjectModel> projects;
	private String fileName;
	

	public TaskBoardModel(String filePath) {
		// TODO Auto-generated constructor stub
	}

	public TaskBoardModel(String name, ArrayList<ProjectModel> projects, String fileName) {
		this.name = name;
		this.projects = projects;
		this.fileName = fileName;
	}

	public void saveTaskBoard() {
		// TODO: 5/3/18 XML Shit goes here 
	}

	public void saveProject() {
		// TODO Auto-generated method stub

	}

	// TODO: 5/3/18 do we need to do this here?
	public void editProject() {
	}

	public void addProject(ProjectModel p) {
		// TODO Auto-generated method stub
		projects.add(p);

	}

}
