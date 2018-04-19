package edu.sjsu.cs151.taskboard;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskModel {
	private String name = "Task1";
	private String description;
	private LocalDate dueDate = LocalDate.now();
	private ArrayList<String> tags;

	public String getName() {
		return name;
	}

	public void setDescription(String d) {

	}
	
	public void getDescription() {
		
	}
	
	public void setDueDate(LocalDate date) {
		
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void addTag(String tag) {
		
	}
	
	public void removeTag() {
		
	}
}
