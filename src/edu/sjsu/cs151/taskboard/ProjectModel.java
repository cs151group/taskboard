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
	
	public ProjectModel() {
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public void setColumnName(int index, String name) {
		if (index >= columns.size()) {
			columns.add(new ColumnModel(name));
		}
		else {
			columns.get(index).setName(name);
		}

	}

	public void removeColumn(int index) {
		if (columns.get(index) != null) {
			columns.remove(index);
		}
	}

	public void removeColumn(String n) {
		ColumnModel deleteCol = new ColumnModel();
		for (ColumnModel c : columns) {
			if (c.getName().equals(n)) {
				deleteCol = c;
			}
		}
		columns.remove(deleteCol);
	}
	
	public void setColumns(ArrayList<ColumnModel> columns) {
		this.columns = columns;
	}

	public void moveLeft(ColumnModel col) {
		int currentIndex = columns.indexOf(col);
		if (currentIndex > 0) {
			int leftIndex = currentIndex - 1;
			Collections.swap(columns, currentIndex, leftIndex);
		}
	}

	public void moveRight(ColumnModel col) {
		int currentIndex = columns.indexOf(col);
		if (currentIndex < columns.size() - 1) {
			int rightIndex = currentIndex + 1;
			Collections.swap(columns, currentIndex, rightIndex);
		}

	}


	public void changeTaskStatus(ColumnModel col, TaskModel task) {
		col.addTask(task);

	}

	public void addTask(ColumnModel col, TaskModel task) {
		col.addTask(task);
	}
	
	public void eraseTask(ColumnModel col, TaskModel task) {
        col.removeTask(task);
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
	
	public ColumnModel getSpecificCol(String name)
	{
		for (ColumnModel c : columns)
		{
			if(c.getName().equals(name))
				return c;
		}
		
		return null;
	}
	
	public ColumnModel findColumn(TaskModel task) {
		for(ColumnModel c : columns) {
			ArrayList<TaskModel> tasks = c.getTasks();
			for(TaskModel t : tasks) {
				if(t.equals(task)) return c;
			}
		}
		return null;
	}
}
