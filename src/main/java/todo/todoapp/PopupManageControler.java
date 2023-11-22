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
        // LISTVIEW SETUP WITH LOGGED PERSON TEAM + UNASSIGNED (0) TEAM
        teamlist_ListView.getItems().setAll(MongoDB.findTeam(loginPerson.getTeam()));
        userlist_ListView.getItems().setAll(MongoDB.findTeam(0));
    }
    /*
    @FXML
    public void dragUser(MouseEvent e){
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clip = new ClipboardContent();

        clip.put(Person,);
    }
    public void dropUser(DragEvent e){

    }
     */
}
