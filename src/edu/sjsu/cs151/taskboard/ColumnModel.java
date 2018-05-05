package edu.sjsu.cs151.taskboard;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColumnModel {
	private String name;
	private ArrayList<TaskModel> tasks;

	public ColumnModel (String name, ArrayList<TaskModel> tasks) {
		this.name = name;
		this.tasks = tasks;
	}
	
	public void addTask(TaskModel task) { tasks.add(task); }
	
	public void addTask(String s) {
		TaskModel newTask = new TaskModel(s);
		tasks.add(newTask);
	}
	
	public void removeTask(TaskModel task) { tasks.remove(task); }
	
	public ArrayList<TaskModel> getTasks() {
		return tasks;
	}
}