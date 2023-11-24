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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class PopupTaskController {
    @FXML
    private ListView<String> assignedUsers_ListView, availableUsers_ListView;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextArea description_TextArea;
    @FXML
    private TextField TITLE_TextField;
    @FXML
    private Label info_Label;
    private DashboardManager dashboardManager;
    private Person self;
    private LocalDateTime deadline;

    public void initialize(Person p, DashboardManager d) {
        // POPUP SETUP
        self = p;
        dashboardManager = d;/* <---- IMPORTANT WITH PASSING AND SETTING UP DASHBOARD CONTROLLER NO LONGER
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardManageScene.fxml"));
                                    Parent root = loader.load();
                                    DashboardController dashboardController = loader.getController();
                                    dashboardController.updateTaskList(self);

                                    IS NEEDED IF U NEED TO ACCESS AND OVERWRITE SOMETHING IN OTHER CONTROLLERS
                                    ALSO WITH THAT WE ARE WORKING WITH ORIGINAL COMPONENTS (PREVIOUSLY ONLY COPIES)
                                    */

        // SETUP FOR AVAILABLE USERS
        ObservableList<String> availableUsers = FXCollections.observableArrayList(MongoDB.findTeam_usernames(p.getTeam()));
        availableUsers_ListView.setItems(availableUsers);

        // EMPTY LISTVIEW
        assignedUsers_ListView.setItems(FXCollections.observableArrayList());
    }

    @FXML
    public void create(ActionEvent e) {
        String temp_TITLE = TITLE_TextField.getText();
        String temp_description = description_TextArea.getText();
        // Retrieve assigned users from the assignedUsers_ListView
        // Nie wiem czemu observable ale dziala
        ObservableList<String> assignedUsersList = assignedUsers_ListView.getItems();


            // CREATES NEW ASSIGNMENT
            Assignment new_task = new Assignment(temp_TITLE, self.getUsername(), assignedUsersList, deadline,temp_description);
            MongoAS.insertOne(new_task);

            dashboardManager.updateTaskList(self);

            info_Label.setText("Task created successfully");
    }

    public void setDeadline(){
        LocalDate temp_deadline = datepicker.getValue();
        deadline = temp_deadline.atStartOfDay();
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