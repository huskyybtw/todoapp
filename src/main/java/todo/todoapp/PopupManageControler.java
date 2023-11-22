package todo.todoapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import todo.todoapp.Enums.Role;
import todo.todoapp.General.Assignment;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoAS;
import todo.todoapp.Mongo.MongoDB;

public class PopupManageControler {
    @FXML
    private ListView<Person> team_ListView,unassigned_ListView;
    private Person loginPerson;

    public void initialize(Person person){
        loginPerson = person;
        // LISTVIEW SETUP WITH LOGGED PERSON TEAM + UNASSIGNED (0) TEAM
        team_ListView.getItems().setAll(MongoDB.findTeam(loginPerson.getTeam()));
        unassigned_ListView.getItems().setAll(MongoDB.findTeam(0));
    }

    @FXML
    public void save(ActionEvent e){
        ObservableList<Person> assignedUsersList = team_ListView.getItems();

        // Check if there are assigned users in the list
        if (!assignedUsersList.isEmpty()) {
            for (Person p:assignedUsersList){
                // ITERATE LOOP WHILE ASSIGNING CHANGING TEAM TO LOGGED PERSONS TEAM
                MongoDB.updateOne("team",loginPerson.getTeam(),p);
                MongoDB.updateOne("role",Role.WORKER,p);
            }
        }
        else {
            System.out.println("ERROR: with saving users");
        }
    }

    // MOVE FROM USERS TO YOUR TEAM
    @FXML
    public void onUserClicked(MouseEvent e) {
        Person selectedUser = unassigned_ListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            unassigned_ListView.getItems().remove(selectedUser);
            team_ListView.getItems().add(selectedUser);
        }
    }

    // MOVE FROM UNASSIGNED TO USERS
    @FXML
    public void onAssignedUserClicked(MouseEvent e) {
        Person selectedUser = team_ListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            team_ListView.getItems().remove(selectedUser);
            unassigned_ListView.getItems().add(selectedUser);
        }
    }

}
