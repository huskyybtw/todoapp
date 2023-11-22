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
import todo.todoapp.General.Assignment;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoAS;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DashboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private  Label display_username_Label;
    @FXML
    private ListView<Assignment> tasklist_ListView; // TASK LIST
    private ObservableList<Assignment> taskList = FXCollections.observableArrayList();

    private static Person loginPerson; // Stored person object
    // LOGOUT FUNCTION
    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException {
        loginPerson = null;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // DASHBOARD SETUP
    @FXML
    public void initialize(Person send_person){
        loginPerson = send_person;
        display_username_Label.setText("WELCOME : " + send_person.getUsername());

        updateTaskList(send_person);
    }
    // DISPLAY PROPER USER BASED ON LOGIN
    @FXML
    public void updateTaskList(Person send_person){
        //CREATE LIST OF TASKS WITH FOUND TASKS
        List<Assignment> tasks = MongoAS.findTasks(send_person.getUsername());
        taskList.setAll(tasks);
        // SET TASK LISTVIEW WITH TASK LIST
        tasklist_ListView.setItems(taskList);
    }

    // DISPLAY POPUP WINDOW WITH PERSONAL INFO
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

    // DISPLAY POPUP WINDOW WITH TASK CREATION
    @FXML
    public void openPopupTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupTask.fxml"));
        Parent root = loader.load();
        PopupTaskController popupController = loader.getController();

            popupController.initialize(loginPerson,this);

            // SOME UGLY CODE (SHOWS POPUP WINDOW)
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            popupStage.setScene(new Scene(root));

            //STAGE SETUP LESS UGLY
            popupStage.setTitle("Task creation");
            popupStage.showAndWait();
    }
    // DISPLAY POPUP WINDOW WITH TEAM MANAGE OPTIONS
    @FXML
    public void openPopupManage (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupManage.fxml"));
        Parent root = loader.load();
        PopupManageController popupController = loader.getController();

        popupController.initialize(loginPerson);

        // SOME UGLY CODE (SHOWS POPUP WINDOW)
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        popupStage.setScene(new Scene(root));

        //STAGE SETUP LESS UGLY
        popupStage.setTitle("Manage users");
        popupStage.showAndWait();
    }

}

