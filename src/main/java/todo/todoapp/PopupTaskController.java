package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import todo.todoapp.General.Assignment;
import todo.todoapp.Mongo.MongoAS;

import java.util.Date;

public class PopupTaskController {
    @FXML
    private TextField TITLE_TextField;
    @FXML
    private Label info_Label;

    private String creator;

    @FXML
    public void create(ActionEvent e){
        String temp_TITLE =  TITLE_TextField.getText();
        Date temp_deadline = new Date();

        Assignment new_task = new Assignment(temp_TITLE,creator,"test",temp_deadline);
        if(!MongoAS.check_single("TITLE",temp_TITLE)) {
            MongoAS.insert_one(new_task);

            info_Label.setText("dziala");
        }
        else{
            info_Label.setText("task z tym tytulem istnieje");
        }

    }

    public void set_creator(String user){
        creator = user;
    }
}
