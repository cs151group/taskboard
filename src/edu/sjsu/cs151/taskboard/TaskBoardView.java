package edu.sjsu.cs151.taskboard;

/*
 * This is likely the most complicated view we have in the program. We probably
 * want to have reusable ColumnView and TaskView classes. These could be
 * implemented as inner classes or as separate external classes.
 */

// TODO: TaskBoardView should extend something so it can be drawn in JavaFX
// TODO: Add EventHandler classes for each clickable object in the UI.

public class TaskBoardView {
	private TaskBoardModel model;

	public TaskBoardView(TaskBoardModel model) {
		this.model = model;
		// TODO: Initialize the view using the appropriate model objects.
	}

	// TODO: InnerColumnView should extend something so it can be drawn in JavaFX
	private class InnerColumnView {
		private ColumnModel colModel;

		public InnerColumnView(ColumnModel columnModel) {
			this.colModel = columnModel;
		}
	}

	// TODO: InnerTaskView should extend something so it can be drawn in JavaFX
	private class InnerTaskView {
		private TaskModel taskModel;

		public InnerTaskView(TaskModel task) {
			this.taskModel = taskModel;
		}
	}
}
