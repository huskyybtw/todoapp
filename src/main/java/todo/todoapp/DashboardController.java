package todo.todoapp;

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
import todo.todoapp.Mongo.MongoDB;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private  Label display_username_Label;
    @FXML
    private ListView<Assignment> tasklist_ListView;

    private static String username; // username stores username that logged in
    @FXML
    public void switchToLogIn1(ActionEvent event) throws IOException {
        username = "";

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void user_dashboard(String send_username){ // DISPLAY PROPER USER BASED ON LOGIN
        username = send_username; // username stores username that logged in
        display_username_Label.setText("WELCOME : " + username);
        tasklist_ListView.getItems().setAll(MongoAS.findTasks(username));
    }

    // DISPLAY POPUP WINDOW WITH PERSNOAL INFO
    @FXML
    private void openPopupPersonal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupPersonal.fxml"));
        Parent root = loader.load();
        PopupPersonalController popupController = loader.getController();

            //GET SINGLE DATA OF PERSON LOGGED IN
            // PASS IT INTO POPUP
            Person person = MongoDB.get_single(username);
            popupController.user_info(person);

            // SOME UGLY CODE (SHOWS POPUP WINDOW)
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            popupStage.setScene(new Scene(root));

            //STAGE SETUP LESS UGLY
            popupStage.setTitle(username + " personal info");
            popupStage.showAndWait();
        }


    @FXML
    public void openPopupTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupTask.fxml"));
        Parent root = loader.load();
        PopupTaskController popupController = loader.getController();

            popupController.set_creator(username);

            // SOME UGLY CODE (SHOWS POPUP WINDOW)
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            popupStage.setScene(new Scene(root));

            //STAGE SETUP LESS UGLY
            popupStage.setTitle("Task creation");
            popupStage.showAndWait();
    }

}

