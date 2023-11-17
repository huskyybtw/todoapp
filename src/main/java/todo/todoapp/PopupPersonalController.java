package todo.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Optional;

public class PopupPersonalController {
    @FXML
    private Label username_Label, name_Label, surname_Label;

    private String username, name, surname;

    @FXML
    private void initialize(){

    }

    public void user_info(Optional<HashMap<String, Object>> data){
        data.get();

    }
}
