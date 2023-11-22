package todo.todoapp;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoDB;

public class PopupManageControler {
    @FXML
    private ListView<Person> teamlist_ListView,userlist_ListView;
    private Person loginPerson;

    public void initialize(Person person){
        loginPerson = person;
        System.out.println(loginPerson.getTeam());
        teamlist_ListView.getItems().setAll(MongoDB.findTeam(loginPerson.getTeam()));
        userlist_ListView.getItems().setAll(MongoDB.findTeam(0));
    }
}
