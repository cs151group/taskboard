package edu.sjsu.cs151.taskboard;
import javafx.concurrent.Task;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXB;

public class TaskBoardModel {
    private String name = "TaskBoard1";
    private ArrayList<ProjectModel> projects = new ArrayList<>();
    private String fileName;

    public TaskBoardModel() {

    }

    public TaskBoardModel(String fileName) {
    	TaskBoardModel tempModel = JAXB.unmarshal(new File(fileName), TaskBoardModel.class);
    	this.fileName = fileName;
    	this.name = tempModel.name;
    	this.projects = tempModel.projects;
    }

    public TaskBoardModel(String name, ArrayList<ProjectModel> projects, String fileName) {
        this.name = name;
        this.projects = projects;
        this.fileName = fileName;
    }
    
    public TaskBoardModel(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }


    // Begin Getters and Setters

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ProjectModel> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<ProjectModel> projects) {
        this.projects = projects;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // End Getters and Setters


    public void save() {
        JAXB.marshal(this, new File(fileName));
    }

    public static TaskBoardModel load(String fileName) {
    	return JAXB.unmarshal(new File(fileName), TaskBoardModel.class);
    }

    public void editProject() {
        // TODO: 5/3/18 do we need to do this here?
    }

    public void addProject(ProjectModel p) {
        projects.add(p);
    }


    // What is this? -J
    public ProjectModel getCurrentProject() {
        return projects.get(0);
    }
    
    public void deleteProject(ProjectModel p) {
    	projects.remove(p);
    }
    
    public boolean isEmpty() {
		return projects.isEmpty();
	}

}
