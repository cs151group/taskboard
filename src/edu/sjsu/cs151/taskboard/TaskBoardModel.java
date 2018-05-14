package edu.sjsu.cs151.taskboard;
import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXB;

public class TaskBoardModel {
    private String name = "TaskBoard1";
    private ArrayList<ProjectModel> projects = new ArrayList<>();
    private String fileName;
    // TODO: Should we represent filename as a File object  ?? ? 

    public TaskBoardModel() {

    }

    public TaskBoardModel(String fileName) {
    	this(new File(fileName));
    }
    
    public TaskBoardModel(File file) {
    	TaskBoardModel tempModel = JAXB.unmarshal(file, TaskBoardModel.class);
    	this.fileName = file.getName();
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

    public TaskBoardModel(String name, ArrayList<ProjectModel> projects) {
		this.name = name;
		this.projects = projects;
	}

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


    public void addProject(ProjectModel p) {
        projects.add(p);
    }


    // What is this? -J
    public ProjectModel getCurrentProject() {
        if (projects.size() > 0) {
            return projects.get(0);
        }
        else return null;
    }
    
    public void deleteProject(ProjectModel p) {
    	projects.remove(p);
    }

    public boolean isEmpty() {
		return projects.isEmpty();
	}
    
    /**
     * Sets the current project to p.
     * @param p the proect to switch to
     */
    public void changeCurrProject(ProjectModel p) {
    	projects.remove(p);
    	projects.add(0, p);
    }

}
