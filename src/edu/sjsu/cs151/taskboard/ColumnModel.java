package edu.sjsu.cs151.taskboard;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Comparator;

public class ColumnModel {
	private String name;
	private ArrayList<TaskModel> tasks = new ArrayList<>();

	public ColumnModel (String name, ArrayList<TaskModel> tasks) {
		this.name = name;
		this.tasks = tasks;
	}
	
	public ColumnModel() {
		// TODO Auto-generated constructor stub
	}

	public ColumnModel (String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setTasks(ArrayList<TaskModel> tasks) {
		this.tasks = tasks;
	}

	public void addTask(TaskModel task) {
		tasks.add(task);
		tasks.sort(new Comparator<TaskModel>() {

			@Override
			public int compare(TaskModel o1, TaskModel o2) {
				return o1.getDueDate().compareTo(o2.getDueDate());
			}
		});
	}
	
	public void addTask(String s) {
		TaskModel newTask = new TaskModel(s);
		tasks.add(newTask);
	}
	
	public void removeTask(TaskModel task) { tasks.remove(task); }
	
	public ArrayList<TaskModel> getTasks() {
		return tasks;
	}
	
	public String getName() {
		return name;
	}
}