package edu.sjsu.cs151.taskboard;

import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class ProjectView {
    private Stage primaryStage;
    private VBox vbox = new VBox();
    private TaskBoardView tbView;
    final TextField nameField = new TextField();
    TaskBoardModel tbModel;
    ArrayList<ColumnModel> colFields = new ArrayList<>();
    boolean isEditing = false;
    boolean firstCreate = true;
    ProjectModel editingProject;
    ArrayList<String> removedColumns = removedColumns = new ArrayList<>();

    public ProjectView(Stage primaryStage, TaskBoardModel tbModel) {
        this.primaryStage = primaryStage;
        this.tbModel = tbModel;
    }

    public ProjectView(Stage primaryStage, TaskBoardView tbView, TaskBoardModel tbModel) {
        this.primaryStage = primaryStage;
        this.tbView = tbView;
        this.tbModel = tbModel;
    }

    public void load() {
        primaryStage.setTitle("Create New Project");

        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();

        border.setCenter(addGridPane());
        border.setBottom(addAnchorPane(grid));

        primaryStage.setScene(new Scene(border));
        primaryStage.show();
    }

    public GridPane addGridPane() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Text nameText = new Text(10, 50, "Name");
        GridPane.setConstraints(nameText, 0, 0);
        grid.getChildren().add(nameText);


        nameField.setPromptText("Enter name");
        nameField.setPrefColumnCount(12);
        nameField.getText();
        GridPane.setConstraints(nameField, 1, 0);
        grid.getChildren().add(nameField);

        Text colText = new Text(10, 50, "Columns");
        GridPane.setConstraints(colText, 0, 1);
        grid.getChildren().add(colText);

        Button plusButton = new Button();
        plusButton.setText("+");
        GridPane.setConstraints(plusButton, 4, 1);
        grid.getChildren().add(plusButton);

        GridPane.setConstraints(vbox, 1, 1);
        grid.getChildren().add(vbox);


        ArrayList<Integer> i = new ArrayList<>();
        i.add(1);

        plusButton.setOnMousePressed((event) -> {

            NewRow currentRow = new NewRow();
            GridPane.setConstraints(currentRow, 1, i.size());
            vbox.getChildren().add(currentRow);
            i.add(1);

            primaryStage.sizeToScene();

        });

        return grid;
    }

    private class NewRow extends HBox {
        private TextField field;
        private Button minus;

        public NewRow() {
            field = new TextField();
            minus = new Button();

            this.getChildren().add(field);
            this.getChildren().add(minus);


            this.setPadding(new Insets(4, 0, 4, 0));
            this.setSpacing(5);
            field.setPrefColumnCount(10);

            field.setPromptText("Enter column name");

            minus.setText(" - ");
            GridPane.setConstraints(minus, 3, 1);

            minus.setOnMouseClicked(event -> {

                if (isEditing) {
                    String removedColName = this.field.getText();
                    //vbox.getChildren().remove(this);

                    System.out.println("removed col is: " + removedColName);
                    removedColumns.add(removedColName);
                    //primaryStage.sizeToScene();
                }

                vbox.getChildren().remove(this);
                primaryStage.sizeToScene();

            });

        }
    }

    public void populateFields(ProjectModel p) {
        this.editingProject = p;
        if (p != null) {
            nameField.setText(p.getName());
            for (ColumnModel c : p.getColumns()) {
                NewRow currentRow = new NewRow();
                currentRow.field.setText(c.getName());
                vbox.getChildren().add(currentRow);
            }
            isEditing = true;
            primaryStage.sizeToScene();
        }
    }

    public void createOrSaveProject() {
        if (isEditing) {
            //First, check if columns have been removed during editing
            if (removedColumns != null) {
                for (String k : removedColumns) {
                    System.out.println("removing column " + k);
                    editingProject.removeColumn(k);
                }
            }
            if (removedColumns != null) removedColumns.clear();
            //Then continue to change the data if column names were changed
            for (int i = 0; i < vbox.getChildren().size(); i++) {
                NewRow currentRow = (NewRow) vbox.getChildren().get(i);
                if ((currentRow.field.getText() != null && !currentRow.field.getText().isEmpty())) {
                    editingProject.setColumnName(i, currentRow.field.getText());
                }
            }
            //finish up
            editingProject.setName(nameField.getText());
            primaryStage.close();
            tbView.load();
            System.out.println("isEditing is true");
        } else {
            for (int i = 0; i < vbox.getChildren().size(); i++) {
                NewRow currentRow = (NewRow) vbox.getChildren().get(i);
                if ((currentRow.field.getText() != null && !currentRow.field.getText().isEmpty())) {
                    ColumnModel currentColumn = new ColumnModel(currentRow.field.getText());
                    colFields.add(currentColumn);
                }
            }
            ProjectModel currentProject = new ProjectModel(nameField.getText(), colFields);
            tbModel.addProject(currentProject);
        }

        System.out.println(tbModel.getProjects().size());
        if (tbModel.getProjects().size() <= 1 && !isEditing) {
            TaskBoardView newTBView = new TaskBoardView(tbModel, primaryStage);
            newTBView.load();
        } else {
            tbView.load();
            primaryStage.close();
        }
        isEditing = false;
        System.out.println("isEditing is not true");
    }

    public AnchorPane addAnchorPane(GridPane grid) {
        AnchorPane anchorpane = new AnchorPane();
        Button buttonSave = new Button("Create");
        Button buttonCancel = new Button("Cancel");

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(buttonSave, buttonCancel);

        anchorpane.getChildren().addAll(grid, hb);   // Add grid from Example 1-5
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(grid, 10.0);

        ArrayList<ColumnModel> colFields = new ArrayList<>();

        buttonSave.setOnMouseClicked(event -> createOrSaveProject());

        buttonCancel.setOnMouseClicked(event -> {
            primaryStage.close();
        });

        return anchorpane;
    }
}
