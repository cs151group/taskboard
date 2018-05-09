package edu.sjsu.cs151.taskboard;

import java.util.ArrayList;
import java.util.Collections;

/* Every method of ProjectModel requires a ColumnModel parameter!
 * Is this a cause for concern? 
 */

public class ProjectModel {
	private String name = "Project1";
	private ArrayList<ColumnModel> columns;

	public ProjectModel(String name, ArrayList<ColumnModel> columns) {
		this.name = name;
		this.columns = columns;
	}

	public void moveLeft(ColumnModel col) {
		int currentIndex = columns.indexOf(col);
		int leftIndex = currentIndex - 1;
		Collections.swap(columns, currentIndex, leftIndex);
	}

	public void moveRight(ColumnModel col) {
		int currentIndex = columns.indexOf(col);
		int rightIndex = currentIndex + 1;
		Collections.swap(columns, currentIndex, rightIndex);
	}

	// TODO: 5/3/18 How are we calling this function? How will we know which col we are moving task from
	// (delete task from that list)
	public void changeTaskStatus(ColumnModel col, TaskModel task) {
		col.addTask(task);

	}

	public void addTask(ColumnModel col, TaskModel task) {
		col.addTask(task);
	}
	
	public ArrayList<ColumnModel> getColumns() {
		return columns;
	}

	public String getName() {
		return name;
	}
	
	public void addColumn(ColumnModel c) {
		columns.add(c);
	}
}
