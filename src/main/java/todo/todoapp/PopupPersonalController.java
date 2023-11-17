package todo.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.HashMap;

public class PopupPersonalController {
    @FXML
    private Label username_Label, name_Label, surname_Label;

    private String username, name, surname;

    public void user_info(HashMap<String, Object> data){

        // CASTS FOUND DATA INTO STRING FROM HASHMAP
        username = (String) data.get("username");
        name = (String) data.get("name");
        surname = (String) data.get("surname");
        // LABEL SETUP
        username_Label.setText("username: " + username);
        name_Label.setText("name: " + name);
        surname_Label.setText("surname: " + surname);

    }
}
