package todo.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import todo.todoapp.General.Person;

import java.util.HashMap;

public class PopupPersonalController {
    @FXML
    private Label username_Label, name_Label, surname_Label;

    private String username, name, surname;

    public void user_info(Person p){

        // DATA SETUP FROM PERSON OBJECT
        username = p.getUsername();
        name = p.getName();
        surname = p.getSurname();
        // LABEL SETUP
        username_Label.setText("username: " + username);
        name_Label.setText("name: " + name);
        surname_Label.setText("surname: " + surname);

    }
}
