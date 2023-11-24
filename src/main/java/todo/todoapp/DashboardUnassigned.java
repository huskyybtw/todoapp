package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todo.todoapp.General.Person;

import java.io.IOException;
import java.util.Objects;

public class DashboardUnassigned {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label display_username_Label;

    private static Person loginPerson;

    @FXML
    public void initialize(Person send_person){
        loginPerson = send_person;
        display_username_Label.setText("WELCOME : " + send_person.getUsername());

    }

    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException {
        loginPerson = null;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
}
