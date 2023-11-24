package todo.todoapp;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import todo.todoapp.General.Assignment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupTaskInfoController {

    @FXML
    Label TITLE_Label,CREATED_BY_Label,CREATION_DATE_Label,deadline_Label,status_Label;
    @FXML
    ListView<String> assigned_users_ListView;

    Assignment task;
    DashboardController dashboardController;

    public void initialize(Assignment t,DashboardController d){
        task = t;
        dashboardController = d;

        // FORMAT DATES INTO STRINGS
        Date creationDate = task.getCREATION_DATE();
        Date deadlineDate = task.getDeadline();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Adjust the pattern based on your requirements

        String temp_cdate = dateFormat.format(creationDate);
        String temp_ddate = dateFormat.format(deadlineDate);
        // FORMAT TASK STATUS INTO STRING
        String temp_status = task.getStatus().name();
        // LABEL SETUP

        TITLE_Label.setText("TITLE: " + (task.getTITLE()));
        CREATED_BY_Label.setText("CREATED BY " + task.getCREATED_BY());
        CREATION_DATE_Label.setText("CREATION DATE " + temp_cdate);
        deadline_Label.setText("deadline : " + temp_ddate);
        status_Label.setText("status " + temp_status);

        // ASSIGNED USERS LISTVIEW
            // Convert ArrayList to ObservableList
        ObservableList<String> observableList = FXCollections.observableList(task.getAssigned_users());
        // Set the ObservableList as the items for the ListView
        assigned_users_ListView.getItems().setAll(observableList);
    }

}