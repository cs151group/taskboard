package edu.sjsu.cs151.taskboard;

/*
 * This is likely the most complicated view we have in the program. We probably
 * want to have reusable ColumnView and TaskView classes. These could be
 * implemented as inner classes or as separate external classes.
 * 
 */

// TODO: TaskBoardView should extend something

public class TaskBoardView {
	private TaskBoardModel model;

	public TaskBoardView(TaskBoardModel model) {
		this.model = model;
		// TODO: Initialize the view using the appropriate model objects.
	}

	private class InnerColumnView {

	}

	private class InnerTaskView {

	}
}
