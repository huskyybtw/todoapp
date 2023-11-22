package todo.todoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import todo.todoapp.General.Assignment;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoAS;
import todo.todoapp.Mongo.MongoDB;

import java.util.Date;

public class PopupTaskController {
    @FXML
    private ListView<String> assignedUsers_ListView, availableUsers_ListView;
    @FXML
    private TextField TITLE_TextField;
    @FXML
    private Label info_Label;

    private String creator;

    public void initialize(Person p) {
        creator = p.getUsername();
        // SETUP FOR AVAILABLE USERS
        ObservableList<String> availableUsers = FXCollections.observableArrayList(MongoDB.findTeam_usernames(p.getTeam()));
        availableUsers_ListView.setItems(availableUsers);

        // EMPTY LISTVIEW
        assignedUsers_ListView.setItems(FXCollections.observableArrayList());
    }

    @FXML
    public void create(ActionEvent e) {
        String temp_TITLE = TITLE_TextField.getText();
        Date temp_deadline = new Date();

        // Retrieve assigned users from the assignedUsers_ListView
        // Nie wiem czemu observable ale dziala
        ObservableList<String> assignedUsersList = assignedUsers_ListView.getItems();

        // Check if there are assigned users in the list
        if (!assignedUsersList.isEmpty()) {
            // CREATES NEW ASSIGNMENT
            Assignment new_task = new Assignment(temp_TITLE, creator, assignedUsersList, temp_deadline);
            MongoAS.insertOne(new_task);

            info_Label.setText("Task created successfully");
        } else {
            info_Label.setText("Please assign the task to at least one user");
        }
    }

    // MOVE FROM USERS TO ASSIGNED
    @FXML
    public void onUserClicked(MouseEvent e) {
        String selectedUser = availableUsers_ListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            availableUsers_ListView.getItems().remove(selectedUser);
            assignedUsers_ListView.getItems().add(selectedUser);
        }
    }

    // MOVE FROM ASSIGNED TO USERS
    @FXML
    public void onAssignedUserClicked(MouseEvent e) {
        String selectedUser = assignedUsers_ListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            assignedUsers_ListView.getItems().remove(selectedUser);
            availableUsers_ListView.getItems().add(selectedUser);
        }
    }
}