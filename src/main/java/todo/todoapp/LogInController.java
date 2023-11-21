package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoDB;

import java.io.IOException;
import java.util.Objects;

public class LogInController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private DashboardController dashboardController;
    private Stage stage;
    private Scene scene;


    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUpScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        String username = "";
        String password = "";
        if (!usernameTextField.getText().isEmpty()) {
            username = usernameTextField.getText();
        }
        if (!passwordField.getText().isEmpty()) {
            password = passwordField.getText();
        }

        String found_username = MongoDB.check_user_password(username,password);

        if(found_username.isEmpty()){
            System.out.println("ERROR PUSTY STRING");
        }

        else {

            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("DashboardScene.fxml")));
            Parent root = loader.load();
            dashboardController = loader.getController();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            Person loginPerson = MongoDB.get_single(found_username);
            dashboardController.initialize(loginPerson);
        }

    }
}
