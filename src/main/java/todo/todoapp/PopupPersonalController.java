package todo.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import todo.todoapp.General.Person;

public class PopupPersonalController {
    @FXML
    private Label username_Label, name_Label, surname_Label,role_Label,team_Label;

    private Person persondata;

    public void initialize(Person p){

        // DATA SETUP FROM PERSON OBJECT
        persondata = p;
        // LABEL SETUP
        username_Label.setText("username: " + persondata.getUsername());
        name_Label.setText("name: " + persondata.getName());
        surname_Label.setText("surname: " + persondata.getSurname());
        role_Label.setText("ROLE: " + persondata.getRole());
        team_Label.setText("TEAM: " + persondata.getTeam());

    }
}
