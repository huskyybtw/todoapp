package todo.todoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todo.todoapp.Enums.Role;
import todo.todoapp.General.Assignment;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoAS;
import todo.todoapp.Mongo.MongoDB;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DashboardWorker {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private  Label display_username_Label;

    @FXML
    private ListView<Assignment> tasklist_ListView;

    @FXML
    private ListView<String> listView_managers, listView_workers;

    private ObservableList<Assignment> taskList = FXCollections.observableArrayList();

    private static Person loginPerson;

    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException {
        loginPerson = null;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //@FXML
    public void initialize(Person send_person){
        loginPerson = send_person;
        display_username_Label.setText("WELCOME : " + send_person.getUsername());

        updateTaskList(send_person);
        listTeam();
    }

    //@FXML
    public void updateTaskList(Person send_person){
        List<Assignment> tasks = MongoAS.findTasks(send_person.getUsername());
        taskList.setAll(tasks);
        tasklist_ListView.setItems(taskList);
    }

    @FXML
    private void openPopupPersonal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupPersonal.fxml"));
        Parent root = loader.load();
        PopupPersonalController popupController = loader.getController();

        //GET SINGLE DATA OF PERSON LOGGED IN
        // PASS IT INTO POPUP
        popupController.initialize(loginPerson);

        // SOME UGLY CODE (SHOWS POPUP WINDOW)
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        popupStage.setScene(new Scene(root));

        //STAGE SETUP LESS UGLY
        popupStage.setTitle(loginPerson.getUsername() + " personal info");
        popupStage.showAndWait();
    }

    @FXML
    private void listTeam(){

        List<Person> list_person = MongoDB.findTeam(loginPerson.getTeam());
        ObservableList<String> workers_list = FXCollections.observableArrayList();
        ObservableList<String> manager_list = FXCollections.observableArrayList();

        for(int i = 0; i < list_person.size(); i++){

            System.out.println(list_person.get(i).getRole());

            if(list_person.get(i).getRole() == Role.MANAGER) {
                manager_list.add(list_person.get(i).getUsername()); //LIST WITH EVERY PERSON IN TEAM
                System.out.println("manager");
            }
            if(list_person.get(i).getRole() == Role.WORKER) {
                workers_list.add(list_person.get(i).getUsername());

            }
        }
        listView_managers.setItems(manager_list);
        listView_workers.setItems(workers_list);


    }


}
