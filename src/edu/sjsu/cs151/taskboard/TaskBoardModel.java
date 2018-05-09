package edu.sjsu.cs151.taskboard;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class TaskBoardModel {
	private String name = "TaskBoard1";
	private ArrayList<ProjectModel> projects;
	private String fileName;


	public TaskBoardModel(String fileName) {
		// TODO: 5/8/18 Load a TaskBoard from a fileName

	}


	public TaskBoardModel(String name, ArrayList<ProjectModel> projects, String fileName) {
		this.name = name;
		this.projects = projects;
		this.fileName = fileName;
	}

	public void save() throws FileNotFoundException {
		// TODO: 5/3/18 XML Shit goes here
		XMLEncoder e = new XMLEncoder(
				new BufferedOutputStream(
						new FileOutputStream("Test.xml")
				));
		e.writeObject(this);
		e.close();
	}


	// TODO: 5/3/18 do we need to do this here?
	public void editProject() {
	}

	public void addProject(ProjectModel p) {
		// TODO Auto-generated method stub
		projects.add(p);

	}
	
	public String getName() {
		return name;
	}
	
	public ProjectModel getCurrentProject() {
		return projects.get(0);
	}

}
