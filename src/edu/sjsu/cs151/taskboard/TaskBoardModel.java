package edu.sjsu.cs151.taskboard;
import javafx.concurrent.Task;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class TaskBoardModel {
    private String name = "TaskBoard1";
    private ArrayList<ProjectModel> projects;
    private String fileName;

    public TaskBoardModel() {

    }

    public TaskBoardModel(String fileName) {
        // TODO: 5/8/18 Load a TaskBoard from a fileName
    }

    public TaskBoardModel(String name, ArrayList<ProjectModel> projects, String fileName) {
        this.name = name;
        this.projects = projects;
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


    public void save() throws FileNotFoundException {
        // TODO: 5/3/18 XML Shit goes here
        XMLEncoder e = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(fileName)
                ));
        e.writeObject(this);
        e.close();
    }

    public TaskBoardModel load(String fileName) throws FileNotFoundException {
        XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(fileName)));
        // Will casting work here? - J
        TaskBoardModel result = (TaskBoardModel) d.readObject();
        d.close();
        return result;
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

}
